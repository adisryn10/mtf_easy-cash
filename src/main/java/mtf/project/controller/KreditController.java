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

import mtf.project.model.UserModel;
import mtf.project.service.FileService;
import mtf.project.service.UserService;

@Controller
public class KreditController{

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model){
        // List<UserModel> listUser = userService.getAllUser();
        // model.addAttribute("listUser", listUser);
        return "home";
    }

    @RequestMapping(path = "/user/manage/{idUser}", method = RequestMethod.GET)
    public String userManage(@PathVariable String idUser, Model model){
        UserModel user = userService.getUserById(idUser);
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping(path = "/user/detail/{idUser}", method = RequestMethod.GET)
    public String userDetail(@PathVariable String idUser, Model model){
        UserModel user = userService.getUserById(idUser);
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
}