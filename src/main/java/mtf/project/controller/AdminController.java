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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import mtf.project.model.KreditModel;
import mtf.project.model.UserRoleModel;
import mtf.project.service.AprService;
import mtf.project.service.AsuransiService;
import mtf.project.service.FileService;

import mtf.project.service.JaminanService;
import mtf.project.service.KendaraanService;
import mtf.project.service.KreditService;
import mtf.project.service.UserIdentityService;
import mtf.project.service.UserPersonalService;
import mtf.project.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController{

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
    KreditService kreditService;

    @Autowired
    AprService aprService;

    @RequestMapping(path = "")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority: auth.getAuthorities()){
            model.addAttribute("role", authority.getAuthority());
        }
        List<UserRoleModel> listUser = userService.getUserByRoleNama("CUSTOMER");
        model.addAttribute("listUser", listUser);
        return "admin";
    }

    @RequestMapping(path = "user/manage/{idUser}", method = RequestMethod.GET)
    public String userManage(@PathVariable String idUser, Model model){
        UserRoleModel user = userService.getUserById(idUser);
        int pendapatanBersih = Integer.valueOf(user.getUserPersonal().getPendapatan()) * 3 / 4;
        Double angsuran = user.getAngsuran().getNominal();
        boolean repayable = pendapatanBersih > angsuran;
        
        boolean rejectOrApprove = false;

        if(user.getKredit().getStatus().equals("Approved") || user.getKredit().getStatus().equals("Rejected")){
            rejectOrApprove = true;
        }

        model.addAttribute("user", user);
        model.addAttribute("repayable", repayable);
        model.addAttribute("angsuran", angsuran);
        model.addAttribute("pendapatanBersih", pendapatanBersih);
        model.addAttribute("rejectOrApprove", rejectOrApprove);

        return "user";
    }

    @RequestMapping(path = "conduct/{idUser}", method = RequestMethod.GET)
    public String conductSurvey(@PathVariable String idUser, Model model){
        UserRoleModel user = userService.getUserById(idUser);
        int pendapatanBersih = Integer.valueOf(user.getUserPersonal().getPendapatan()) * 3 / 4;
        Double angsuran = user.getAngsuran().getNominal();

        boolean rejectOrApprove = false;
        if(user.getKredit().getStatus() == "Approved" || user.getKredit().getStatus() == "Rejected"){
            rejectOrApprove = true;
        }

        KreditModel kreditUser = user.getKredit();
        kreditUser.setStatus("Survey");
        kreditService.changeKreditStatus(kreditUser);
        
        model.addAttribute("user", user);
        model.addAttribute("repayable", true);
        model.addAttribute("angsuran", angsuran);
        model.addAttribute("pendapatanBersih", pendapatanBersih);
        model.addAttribute("rejectOrApprove", rejectOrApprove);

        return "user";
    }

    
    @RequestMapping(path = "approve/{idUser}", method = RequestMethod.GET)
    public String approveKredit(@PathVariable String idUser, Model model){
        UserRoleModel user = userService.getUserById(idUser);

        KreditModel kreditUser = user.getKredit();
        kreditUser.setStatus("Approved");
        kreditService.changeKreditStatus(kreditUser);
        
        model.addAttribute("user", user);

        return "user-detail";
    }

    @RequestMapping(path = "reject/{idUser}", method = RequestMethod.GET)
    public String rejectKredit(@PathVariable String idUser, Model model){
        UserRoleModel user = userService.getUserById(idUser);

        KreditModel kreditUser = user.getKredit();
        kreditUser.setStatus("Rejected");
        kreditService.changeKreditStatus(kreditUser);
        
        model.addAttribute("user", user);

        return "user-detail";
    }


    @RequestMapping(path = "user/detail/{idUser}", method = RequestMethod.GET)
    public String userDetail(@PathVariable String idUser, Model model){
        UserRoleModel user = userService.getUserById(idUser);
        model.addAttribute("user", user);
        return "user-detail";
    }
}