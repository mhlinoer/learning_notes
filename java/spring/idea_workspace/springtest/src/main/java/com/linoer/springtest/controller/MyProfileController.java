package com.linoer.springtest.controller;

import com.linoer.springtest.model.ProfileForm;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyProfileController {
    private static final Log logger = LogFactory.getLog(MyProfileController.class);

    @RequestMapping("/profile")
    public String displayProfile(ProfileForm profileForm){
        return "profilePage";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(ProfileForm profileForm){
        logger.info("profile save success");
        return "redirect:/profile";
    }
}
