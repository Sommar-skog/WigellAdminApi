package com.example.wigelladminapi.services;

import com.example.wigelladminapi.entities.Member;
import com.example.wigelladminapi.exceptions.ResourceNotFoundException;
import com.example.wigelladminapi.repositories.MemberRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService  implements MemberServiceInterface{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public List<Member> getAllMembers() {
        List<Member> result = memberRepository.findAll();
        if (!result.isEmpty()) {
            return result;
        }
        throw new NoResultException(); //Ändra till rätt inparametrar
    }

    @Override
    public Member getMemberById(Long id) {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException(); //Lägg till rätt parametrar
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
    public void deleteMember(Long id) {
        //Ska jag använda string för konfermation? delited/not deleted
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            memberRepository.delete(result.get());
        }

        throw new ResourceNotFoundException(); //Lägg till parametrar
    }
}
