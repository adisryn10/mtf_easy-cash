package mtf.project.controller;

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
import mtf.project.service.FileService;
import mtf.project.model.JaminanModel;
import mtf.project.model.KreditModel;
import mtf.project.model.UserIdentityModel;
import mtf.project.model.UserPersonalModel;

import mtf.project.service.JaminanService;
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
    UserPersonalService userPersonalService;

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

    @RequestMapping(path = "/ajukan", method = RequestMethod.GET)
    public String ajukan(Model model){
        UserIdentityModel userIdentity = new UserIdentityModel();
        model.addAttribute("userIdentity", userIdentity);
        return "ajukan";
    }

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
        KreditModel kreditModel = new KreditModel();
        JaminanModel jaminanModel = new JaminanModel();
        jaminanModel.setKredit(kreditModel);
        model.addAttribute("jaminanModel", jaminanModel);
        return "formAngunan";
    }

    @RequestMapping(path = "/submitJaminan", method = RequestMethod.POST)
    public String submitJaminan(@ModelAttribute JaminanModel barangJaminan, Model model){
        jaminanService.addJaminan(barangJaminan);
        UserIdentityModel userIdentity = new UserIdentityModel();
        model.addAttribute("userIdentity", userIdentity);
        return "ajukan";
    }

    @RequestMapping(path = "/submitDokumen", method = RequestMethod.POST)
    public String submitDokumen(@ModelAttribute UserIdentityModel user, Model model){
        UserIdentityModel userIdentity = userIdentityService.addUserIdentity(user);
        UserPersonalModel userPersonal = new UserPersonalModel();
        model.addAttribute("userPersonal", userPersonal);
        return "formketiga";
    }

    @RequestMapping(path = "/submitDataPribadi", method = RequestMethod.POST)
    public String submitDataPribadi(@ModelAttribute UserPersonalModel userPersonal){
        UserPersonalModel userPers = userPersonalService.addDataPribadi(userPersonal);
        return "index";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute UserRoleModel user, Model model){
        userService.addUser(user);
        model.addAttribute("user", user);
        return "index";
    }
}