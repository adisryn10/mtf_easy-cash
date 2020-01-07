package mtf.project.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import mtf.project.model.UserRoleModel;
import mtf.project.service.AprService;
import mtf.project.service.AsuransiService;
import mtf.project.service.FileService;
import mtf.project.model.AngsuranModel;
import mtf.project.model.AprModel;
import mtf.project.model.AsuransiModel;
import mtf.project.model.JaminanModel;
import mtf.project.model.KendaraanModel;
import mtf.project.model.KreditModel;
import mtf.project.model.UserIdentityModel;
import mtf.project.model.UserPersonalModel;

import mtf.project.service.JaminanService;
import mtf.project.service.KendaraanService;
import mtf.project.service.UserIdentityService;
import mtf.project.service.UserPersonalService;
import mtf.project.service.UserService;


@Controller
public class KreditController{

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @Autowired
    JaminanService jaminanService;

    @Autowired
    UserIdentityService userIdentityService;

    @Autowired
    AsuransiService asuransiService;

    @Autowired
    KendaraanService kendaraanService;

    @Autowired
    UserPersonalService userPersonalService;

    @Autowired
    AprService aprService;

    @RequestMapping(path = "/admin")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority: auth.getAuthorities()){
            model.addAttribute("role", authority.getAuthority());
        }
        List<UserRoleModel> listUser = userService.getUserByRoleNama("Customer");
        model.addAttribute("listUser", listUser);
        return "admin";
    }

    @RequestMapping(path = "/user/manage/{idUser}", method = RequestMethod.GET)
    public String userManage(@PathVariable String idUser, Model model){
        UserRoleModel user = userService.getUserById(idUser);
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping(path = "/user/detail/{idUser}", method = RequestMethod.GET)
    public String userDetail(@PathVariable String idUser, Model model){
        UserRoleModel user = userService.getUserById(idUser);
        model.addAttribute("user", user);
        return "user-detail";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        fileService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public String account(){
        return "account";
    }

    // @RequestMapping(path = "/ajukan", method = RequestMethod.GET)
    // public String ajukan(Model model){
    //     UserIdentityModel userIdentity = new UserIdentityModel();
    //     model.addAttribute("userIdentity", userIdentity);
    //     return "ajukan";
    // }

    @RequestMapping(path = "/form", method = RequestMethod.GET)
    public String form(){
        return "form";
    }

    @RequestMapping(path = "/formketiga", method = RequestMethod.GET)
    public String formketiga(Model model){
        return "formketiga";
    }

    @RequestMapping(path = "/formAngunan", method = RequestMethod.GET)
    public String formAngunan(Model model){
        List<KendaraanModel> listKendaraan = kendaraanService.getAllKendaraan();
        List<AsuransiModel> listAsuransi  = asuransiService.getAllAsuransi();
        KreditModel kreditModel = new KreditModel();

        JaminanModel jaminanModel = new JaminanModel();
        jaminanModel.setKredit(kreditModel);

        model.addAttribute("listKendaraan", listKendaraan);
        model.addAttribute("listAsuransi", listAsuransi);
        model.addAttribute("jaminanModel", jaminanModel);
        return "formAngunan";
    }

    @RequestMapping(path = "/submitJaminan", method = RequestMethod.POST)
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

        int hargaMax = barangJaminan.getKendaraan().getHargaPasar() * 8 / 10 ;
        model.addAttribute("hargaMax", hargaMax);

        KreditModel kreditModel = new KreditModel();
        model.addAttribute("kreditModel", kreditModel);

        return "form-nominal";
    }

    @RequestMapping(path = "/submitKredit", method = RequestMethod.POST)
    public String submitKredit(@ModelAttribute KreditModel kreditModel, Model model){
        System.out.println("Kredit nominal : " + kreditModel.getNominal());

        List<AprModel> listApr = aprService.getAllApr();
        List<AprModel> listAprAddm = new ArrayList<AprModel>();
        List<AprModel> listAprAddb = new ArrayList<AprModel>();

        List<String> listAngsuranAddm = new ArrayList<String>();
        List<String> listAngsuranAddb = new ArrayList<String>();
        int counter = 0;

        DecimalFormat f = new DecimalFormat("##.00");

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
        model.addAttribute("listAngsuranAddm", listAngsuranAddm);
        model.addAttribute("listAprAddm", listAprAddm);

        model.addAttribute("listAngsuranAddb", listAngsuranAddb);
        model.addAttribute("listAprAddb", listAprAddb);
        
        return "daftar-angsuran";
    }

    public double perhitungan(int pv, double rate, int tenor){
        double pembilang = (pv * (rate/100) / tenor) * Math.pow((1+(rate/100)), tenor);
        double a =  (pv * (rate/100) / tenor);
        double b = Math.pow((1+(rate/100)), tenor);
        System.out.println("pembilang pv " + pv);
        System.out.println("pembilang b " + b);
        double penyebut = Math.pow((1+(rate/100)), tenor) - 1;
        System.out.println("penyebut " + penyebut);
        double result = pembilang/penyebut;
        return result;
    }


    @RequestMapping(path = "/submitDokumen", method = RequestMethod.POST)
    public String submitDokumen(Authentication auth, @ModelAttribute UserIdentityModel user, Model model){
        UserIdentityModel userIdentity = userIdentityService.addUserIdentity(user);
        UserRoleModel currentUser = userService.getUserByUsername(auth.getName());
        currentUser.setUserIdentity(user);

        userService.changeIdentity(currentUser);

        UserPersonalModel userPersonal = new UserPersonalModel();
        model.addAttribute("userPersonal", userPersonal);
        return "formketiga";
    }

    @RequestMapping(path = "/submitDataPribadi", method = RequestMethod.POST)
    public String submitDataPribadi(Authentication auth, @ModelAttribute UserPersonalModel userPersonal){
        UserRoleModel user = userService.getUserByUsername(auth.getName());
        UserPersonalModel userPers = userPersonalService.addDataPribadi(userPersonal);
        user.setUserPersonal(userPers);

        userService.changeUser(user);

        return "index";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute UserRoleModel user, Model model){
        userService.addUser(user);
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "/ajukan/{angsuran}", method = RequestMethod.GET)
    public String ajukanAngsuran(Authentication auth, @PathVariable String angsuran,  Model model){
        UserRoleModel user = userService.getUserByUsername(auth.getName());
        KreditModel kreditUser = new KreditModel();
        kreditUser.setNominal(Math.round(Float.valueOf(angsuran)));
        model.addAttribute("user", user);
        UserIdentityModel userIdentity = new UserIdentityModel();
        model.addAttribute("userIdentity", userIdentity);
        return "ajukan";
    }
}