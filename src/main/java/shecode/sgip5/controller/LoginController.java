package shecode.sgip5.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shecode.sgip5.model.User;
import shecode.sgip5.service.UserService;
import shecode.sgip5.util.GooglePojo;
import shecode.sgip5.util.GoogleUtils;

import java.io.IOException;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(){
        return "redirect:/universities";
    }

    @GetMapping("/login-google")
    public String userLoginGoogle(@RequestParam String code, Model model, HttpSession session) throws IOException {
        if (code == null || code.isEmpty()) {
            return "redirect:/universities";
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            if (!userService.checkExistMail(googlePojo.getEmail())) {
                User user = userService.registerAccountFromGoogle(googlePojo);
                session.setAttribute("user", user);
                log.info("User: {}", user);
                return "redirect:/universities";
            } else {
                User user = userService.findUserByEmail(googlePojo.getEmail());
//                if (!user.getStatus()) {
//                    model.addAttribute("errmsg", "Your account has been blocked");
//                    return "login";
//                }
                session.setAttribute("user", user);
                return "redirect:/universities";
            }

        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/universities";
    }
}
