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
public class HomeController{

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model){
        return "index";
    }