package com.linoer.springtest.controller;

import com.linoer.springtest.model.ProfileForm;
import com.linoer.springtest.utils.CNLocalDateFormatter;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class MyProfileController {
    private static final Log logger = LogFactory.getLog(MyProfileController.class);

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale){
        return CNLocalDateFormatter.getPattern(locale);
    }

    @RequestMapping("/profile")
    public String displayProfile(ProfileForm profileForm){
        return "profilePage";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "profilePage";
        }
        logger.info("profile save success");
        return "redirect:/profile";
    }
}
