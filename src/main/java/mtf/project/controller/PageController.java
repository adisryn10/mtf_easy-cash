package mtf.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import mtf.project.model.AsuransiModel;
import mtf.project.model.JaminanModel;
import mtf.project.model.KendaraanModel;
import mtf.project.model.KreditModel;
import mtf.project.service.AprService;
import mtf.project.service.AsuransiService;
import mtf.project.service.FileService;

import mtf.project.service.JaminanService;
import mtf.project.service.KendaraanService;
import mtf.project.service.UserIdentityService;
import mtf.project.service.UserPersonalService;
import mtf.project.service.UserService;


@Controller
public class PageController{

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

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority: auth.getAuthorities()){
            model.addAttribute("role", authority.getAuthority());
        }
        List<KendaraanModel> listKendaraan = kendaraanService.getAllKendaraan();
        List<AsuransiModel> asuransiDaftar  = asuransiService.getAllAsuransi();
        List<AsuransiModel> listAsuransi = new ArrayList<AsuransiModel>();

        listAsuransi.add(asuransiDaftar.get(1));
        listAsuransi.add(asuransiDaftar.get(5));

        JaminanModel jaminanModel = new JaminanModel();

        model.addAttribute("listKendaraan", listKendaraan);
        model.addAttribute("listAsuransi", listAsuransi);
        model.addAttribute("jaminanModel", jaminanModel);
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    
}
