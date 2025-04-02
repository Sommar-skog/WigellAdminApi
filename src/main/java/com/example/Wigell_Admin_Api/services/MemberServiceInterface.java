package com.example.Wigell_Admin_Api.services;

import com.example.Wigell_Admin_Api.entities.Member;

import java.util.List;

public interface MemberServiceInterface {

    List<Member> getAllMembers();
    Member getMemberById(Long id);
    Member updateMember(Member member);
    Member addMember(Member member);
    void deleteMember(Member member);


}
