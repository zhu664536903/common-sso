package com.gczhu.commonsso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/sso/page")
public class PageContoller {
    @RequestMapping("login")
    public String login(Model model,
                        @RequestParam String redirectUri,
                        @RequestParam String appId) throws IOException {


        model.addAttribute("redirectUri",redirectUri);
        model.addAttribute("appId",appId);

        return "login";
    }
}
