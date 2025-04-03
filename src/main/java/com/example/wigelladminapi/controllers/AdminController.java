package com.example.wigelladminapi.controllers;

import com.example.wigelladminapi.services.MemberServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MemberServiceInterface memberService;

    @Autowired
    public AdminController(MemberServiceInterface memberService) {
        this.memberService = memberService;
    }
}
