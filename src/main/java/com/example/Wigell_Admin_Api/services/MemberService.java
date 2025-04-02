package com.example.Wigell_Admin_Api.services;

import com.example.Wigell_Admin_Api.entities.Member;
import com.example.Wigell_Admin_Api.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService  implements MemberServiceInterface{

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public List<Member> getAllMembers() {
        return List.of();
    }

    @Override
    public Member getMemberById(Long id) {
        return null;
    }

    @Override
    public Member updateMember(Member member) {
        return null;
    }

    @Override
    public Member addMember(Member member) {
        return null;
    }

    @Override
    public void deleteMember(Member member) {

    }
}
