package burukeyou.search.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class AdminController {

    @Autowired
    private HttpSession session;

    @RequestMapping("/login")
    public void login(){
        session.setAttribute("bbq","fhakljsnfla");
    }

    @RequestMapping("/get")
    public Object getUser(){
        return session.getAttribute("bbq");
    }
}
