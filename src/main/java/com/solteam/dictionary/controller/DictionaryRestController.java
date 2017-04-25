package com.solteam.dictionary.controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Burak Baldirlioglu
 * @since 4/17/2017 9:07 AM
 */
@RestController
public class DictionaryRestController {

    /*@RequestMapping("/signUp")
    public String signUp(String name, String pass, String pass2, Model model, HttpServletResponse response) {
        if (retwis.isUserValid(name)) {
            model.addAttribute("errorduplicateuser", Boolean.TRUE);
            return "signin";
        }

        if (!StringUtils.hasText(pass) || !StringUtils.hasText(pass2) || !pass.equals(pass2)) {
            model.addAttribute("errormatch", Boolean.TRUE);
            return "signin";
        }

        String auth = retwis.addUser(name, pass);
        addAuthCookie(auth, name, response);

        return "redirect:/!" + name;
    }

    @RequestMapping("/signIn")
    public String signIn(@RequestParam(required = false) String name, @RequestParam(required = false) String pass, Model model, HttpServletResponse response) {
        // add tracing cookie
        if (retwis.auth(name, pass)) {
            addAuthCookie(retwis.addAuth(name), name, response);
            return "redirect:/!" + name;
        }
        else if (StringUtils.hasText(name) || StringUtils.hasText(pass)) {
            model.addAttribute("errorpass", Boolean.TRUE);
        }
        // go back to sign in screen
        return "signin";
    }*/
}
