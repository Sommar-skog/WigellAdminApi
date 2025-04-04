package com.example.wigelladminapi.services;

import com.example.wigelladminapi.entities.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberServiceInterface {

    List<Member> getAllMembers();
    Member getMemberById(Long id);
    Member updateMember(Member member);
    Member addMember(Member member);
    ResponseEntity<String> deleteMember(Long id);


}
