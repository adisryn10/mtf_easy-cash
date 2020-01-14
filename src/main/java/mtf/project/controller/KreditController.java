package mtf.project.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.RequestCache;

import mtf.project.service.*;
import mtf.project.model.*;


@Controller
public class KreditController{

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @Autowired
    JaminanService jaminanService;

    @Autowired
    AngsuranService angsuranService;

    @Autowired
    UserIdentityService userIdentityService;

    @Autowired
    AsuransiService asuransiService;

    @Autowired
    KendaraanService kendaraanService;

    @Autowired
    PembayaranService pembayaranService;

    @Autowired
    KreditService kreditService;

    @Autowired
    UserPersonalService userPersonalService;

    @Autowired
    UserIntegrityService userIntegrityService;

    @Autowired
    AprService aprService;

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public String account(Authentication auth, Model model){
        UserRoleModel user = userService.getUserByUsername(auth.getName());
        model.addAttribute("user", user);
        return "account";
    }

    // @RequestMapping(path = "/ajukan", method = RequestMethod.GET)
    // public String ajukan(Model model){
    //     UserIdentityModel userIdentity = new UserIdentityModel();
    //     model.addAttribute("userIdentity", userIdentity);
    //     return "ajukan";
    // }

    // @RequestMapping(path = "/form", method = RequestMethod.GET)
    // public String form(){
    //     return "form";
    // }

    // @RequestMapping(path = "/formketiga", method = RequestMethod.GET)
    // public String formketiga(Model model){
    //     return "formketiga";
    // }

    @RequestMapping(path = "/form-jaminan", method = RequestMethod.GET)
    public String formAngunan(Model model, Authentication auth){

        UserRoleModel user = userService.getUserByUsername(auth.getName());

        boolean haveKredit = false;

        if(user.getJaminan() != null || user.getKredit() != null ){
            haveKredit = true;
        }
        
        model.addAttribute("haveKredit", haveKredit);

        List<KendaraanModel> listKendaraan = kendaraanService.getAllKendaraan();
        List<AsuransiModel> asuransiDaftar  = asuransiService.getAllAsuransi();
        List<AsuransiModel> listAsuransi = new ArrayList<AsuransiModel>();

        listAsuransi.add(asuransiDaftar.get(1));
        listAsuransi.add(asuransiDaftar.get(5));

        JaminanModel jaminanModel = new JaminanModel();

        model.addAttribute("listKendaraan", listKendaraan);
        model.addAttribute("listAsuransi", listAsuransi);
        model.addAttribute("jaminanModel", jaminanModel);
        return "formAngunan";
    }

    @RequestMapping(path = "/form-nominal", method = RequestMethod.POST)
    public String submitJaminan(@ModelAttribute JaminanModel barangJaminan, Authentication auth, Model model){

        UserRoleModel user = userService.getUserByUsername(auth.getName());
        barangJaminan.setUser(user);
        
        AsuransiModel asuransi = asuransiService.getAsuransiById(barangJaminan.getAsuransi().getId());
        barangJaminan.setAsuransi(asuransi);

        KendaraanModel kendaraan = kendaraanService.getKendaraanById(barangJaminan.getKendaraan().getId());
        barangJaminan.setKendaraan(kendaraan);

        jaminanService.addJaminan(barangJaminan);

        UserIdentityModel userIdentity = new UserIdentityModel();
        model.addAttribute("userIdentity", userIdentity);

        long hargaMax = barangJaminan.getKendaraan().getHargaPasar() * 8 / 10 ;
        int selisihTahun = 2020 - barangJaminan.getTahun();

        for(int i = 0; i < selisihTahun; i ++){
            hargaMax = Math.round(hargaMax * 0.01);
        }

        model.addAttribute("hargaMax", hargaMax);

        KreditModel kreditModel = new KreditModel();
        model.addAttribute("kreditModel", kreditModel);

        return "form-nominal";
    }

    @RequestMapping(path = "/daftar-angsuran", method = RequestMethod.POST)
    public String submitKredit(@ModelAttribute KreditModel kreditModel, Model model){
        System.out.println("Kredit nominal : " + kreditModel.getNominal());

        List<AprModel> listApr = aprService.getAllApr();
        List<AprModel> listAprAddm = new ArrayList<AprModel>();
        List<AprModel> listAprAddb = new ArrayList<AprModel>();

        List<String> listAngsuranAddm = new ArrayList<String>();
        List<String> listAngsuranAddb = new ArrayList<String>();
        int counter = 0;

        DecimalFormat f = new DecimalFormat("##");

        for (AprModel apr : listApr){
            String angsuran = f.format(perhitungan(kreditModel.getNominal(), apr.getRate(), apr.getTenor()));
            if(counter < 3){
                listAngsuranAddm.add(angsuran);
                listAprAddm.add(apr);
            }
            else{
                listAngsuranAddb.add(angsuran);
                listAprAddb.add(apr);
            }
            counter++;
           
        }
        KreditModel newKredit = new KreditModel();
        newKredit.setNominal(kreditModel.getNominal());
        newKredit.setPembayaran(new PembayaranModel());

        model.addAttribute("listAngsuranAddm", listAngsuranAddm);
        model.addAttribute("listAprAddm", listAprAddm);

        model.addAttribute("listAngsuranAddb", listAngsuranAddb);
        model.addAttribute("listAprAddb", listAprAddb);

        model.addAttribute("kreditModel", newKredit);
        model.addAttribute("nominalKredit", kreditModel.getNominal());
        
        return "daftar-angsuran";
    }

    public double perhitungan(int pv, double rate, int tenor){
        double pembilang = pv * (rate/100);
        double pangkat = Math.pow((1+(rate/100)), tenor);
        double penyebut = 1 -  (1/pangkat);
        double result = pembilang/penyebut;
        return result;
    }


    @RequestMapping(path = "/form-data-diri", method = RequestMethod.POST)
    public String submitDokumen(Authentication auth, @ModelAttribute UserIdentityModel user, Model model){
        UserIdentityModel userIdentity = userIdentityService.addUserIdentity(user);
        UserRoleModel currentUser = userService.getUserByUsername(auth.getName());
        currentUser.setUserIdentity(user);

        userService.changeIdentity(currentUser);

        UserPersonalModel userPersonal = new UserPersonalModel();
        model.addAttribute("userPersonal", userPersonal);
        return "form-data-diri";
    }

    @RequestMapping(path = "/finish", method = RequestMethod.POST)
    public String submitDataPribadi(Authentication auth, @ModelAttribute UserPersonalModel userPersonal, Model model){
        UserRoleModel user = userService.getUserByUsername(auth.getName());
        UserPersonalModel userPers = userPersonalService.addDataPribadi(userPersonal);
        user.setUserPersonal(userPers);

        Double pendapatanBersih = Double.valueOf(user.getUserPersonal().getPendapatan());
        Double angsuranPerBulan = user.getAngsuran().getNominal();
        KreditModel kreditModel = user.getKredit();

        if(pendapatanBersih >= angsuranPerBulan){
            kreditModel.setStatus("Survey");
        }
        else{
            kreditModel.setStatus("Under Review");
        }
        kreditService.changeKreditStatus(kreditModel);

        userService.changeUser(user);
        model.addAttribute("user", user);

        return "account";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute UserRoleModel user, Model model){
        UserIntegrityModel userIntegrity = userIntegrityService.getIntegrityById(Long.valueOf(2));
        user.setUserIntegrity(userIntegrity);
        userService.addUser(user);
        return "login";
    }

    
    @RequestMapping(value = "/ajukan/{angsuran}", method = RequestMethod.POST)
    public String ajukanAngsuran(Authentication auth, @PathVariable Double angsuran, KreditModel kreditModel,   Model model){

        UserRoleModel user = userService.getUserByUsername(auth.getName());

        AngsuranModel angsuranModel = new AngsuranModel();
        angsuranModel.setNominal(Double.valueOf(angsuran));
        angsuranModel.setUser(user);

        angsuranService.addAngsuran(angsuranModel);

        PembayaranModel pembayaran = pembayaranService.getPembayaranById(Long.valueOf(2));
        kreditModel.setPembayaran(pembayaran);
        kreditModel.setUser(user);
        kreditModel.setAngsuran(angsuranModel);
        kreditModel.setStatus("Submitted");

        List<AprModel> aprList = aprService.getAllApr();

        kreditModel.setApr(aprList.get(2));

        kreditService.addKreditModel(kreditModel);

        model.addAttribute("user", user);
        UserIdentityModel userIdentity = new UserIdentityModel();
        model.addAttribute("userIdentity", userIdentity);
        return "ajukan";
    }
}