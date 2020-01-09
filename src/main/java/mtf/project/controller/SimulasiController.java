package mtf.project.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;

import mtf.project.model.UserRoleModel;
import mtf.project.service.AprService;
import mtf.project.service.AsuransiService;
import mtf.project.service.FileService;
import mtf.project.model.AprModel;
import mtf.project.model.AsuransiModel;
import mtf.project.model.JaminanModel;
import mtf.project.model.KendaraanModel;
import mtf.project.model.KreditModel;
import mtf.project.model.UserIdentityModel;

import mtf.project.service.JaminanService;
import mtf.project.service.KendaraanService;
import mtf.project.service.UserIdentityService;
import mtf.project.service.UserIntegrityService;
import mtf.project.service.UserPersonalService;
import mtf.project.service.UserService;

@Controller
@RequestMapping("/simulasi")
public class SimulasiController{

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
    AprService aprService;

    @RequestMapping(path = "/form-nominal", method = RequestMethod.POST)
    public String submitJaminan(@ModelAttribute JaminanModel barangJaminan, Model model){

        int hargaMax = barangJaminan.getKendaraan().getHargaPasar() * 8 / 10 ;
        model.addAttribute("hargaMax", hargaMax);

        KreditModel kreditModel = new KreditModel();
        model.addAttribute("kreditModel", kreditModel);

        return "simulasi-form-nominal";
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
        
        return "simulasi-daftar-angsuran";
    }

    public double perhitungan(int pv, double rate, int tenor){
        double pembilang = pv * (rate) / tenor;
        double pangkat = Math.pow((1+(rate)), tenor);
        double penyebut = 1 -  (1/pangkat);
        double result = pembilang/penyebut/10;
        return result;
    }
}