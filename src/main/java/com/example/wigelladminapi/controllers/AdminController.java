package com.example.wigelladminapi.controllers;

import com.example.wigelladminapi.entities.Member;
import com.example.wigelladminapi.services.MemberServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MemberServiceInterface memberService;

    @Autowired
    public AdminController(MemberServiceInterface memberService) {
        this.memberService = memberService;
    }

    //Get
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    //Post
    @PostMapping("/addmember")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.addMember(member), HttpStatus.CREATED);
    }

    //Put
    @PutMapping("/updatemember")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.updateMember(member));
    }

    //Delete
    @DeleteMapping("/deletemember/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }

    //Metoder för HTML
    @GetMapping("/deletemember")
    public String deleteMember(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "listmembers";
    }

    @PostMapping("/deletememberbyid")
    public String deleteMemberById(@RequestParam Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/deletemember";
    }
}
