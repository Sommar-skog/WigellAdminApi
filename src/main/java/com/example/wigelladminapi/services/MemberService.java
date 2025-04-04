package com.example.wigelladminapi.services;

import com.example.wigelladminapi.entities.Member;
import com.example.wigelladminapi.exceptions.InvalidInputException;
import com.example.wigelladminapi.exceptions.NotUniqException;
import com.example.wigelladminapi.exceptions.ResourceNotFoundException;
import com.example.wigelladminapi.repositories.MemberRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return result;
/*        if (!result.isEmpty()) {
            return result;
        }
        throw new NoResultException("Member");*/
    }

    @Override
    public Member getMemberById(Long id) {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException("Member", "id", id);
    }

    @Override
    public Member updateMember(Member member) {
        Optional<Member> memberToUpdate = memberRepository.findById(member.getId());

        if (memberToUpdate.isPresent()) {
            Member updatedMember = memberToUpdate.get();
            if (member.getFirstName() != null){
                if (!member.getFirstName().isEmpty()){
                    updatedMember.setFirstName(member.getFirstName());
                }
            }

            if (member.getLastName() != null){
                if (!member.getLastName().isEmpty()){
                    updatedMember.setLastName(member.getLastName());
                }
            }
            if (member.getEmail() != null) {
                if (!member.getEmail().isEmpty()){
                    if (!isEmailTaken(member.getEmail())) {
                        updatedMember.setEmail(member.getEmail());
                    } else {
                        throw new NotUniqException("Email", member.getEmail());
                    }
                }
            }
            if (member.getPhone() != null) {
                if (!member.getPhone().isEmpty()){
                    updatedMember.setPhone(member.getPhone());
                }
            }
            if (member.getDateOfBirth() != null) {
                if(isDateOfBirthValid(member.getDateOfBirth())) {
                    updatedMember.setDateOfBirth(member.getDateOfBirth());
                }
            }
            if (member.getAddress() != null){
                //TODO lägg till validering av adress

            }
            return memberRepository.save(updatedMember);
        }

        throw new ResourceNotFoundException("Member", "id", member.getId());
    }

    @Override
    public Member addMember(Member member) {

        //TODO Kontrollera om adressen redan finns och inte är NULL. Om den finns använd det id annars skapa ny adress

        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            throw new InvalidInputException("Member", "email", member.getEmail());
        }
        if (isEmailTaken(member.getEmail())) {
            throw new NotUniqException("Email", member.getEmail());
        }

        if (member.getFirstName() == null || member.getFirstName().isEmpty()) {
            throw new InvalidInputException("Member", "firstName", member.getFirstName());
        }

        if (member.getLastName() == null || member.getLastName().isEmpty()) {
           throw new InvalidInputException("Member", "lastName", member.getLastName());
        }

        if (!isDateOfBirthValid(member.getDateOfBirth()) || member.getDateOfBirth() == null) {
            throw new InvalidInputException("Member", "Date of birth", member.getDateOfBirth());
        }
        return memberRepository.save(member);
    }

    @Override
    public ResponseEntity<String> deleteMember(Long id) {
        //Ska jag använda string för konfermation? delited/not deleted
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            memberRepository.delete(result.get());
            return new ResponseEntity<>("Member with id " + id + " deleted", HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Member", "id", id);
    }

    //Kontroll-metoder
    private boolean isEmailTaken(String email) {
        return memberRepository.existsByEmail(email);
    }

    private boolean isDateOfBirthValid(LocalDate dateOfBirth) {
        return dateOfBirth.isBefore(LocalDate.now()) && dateOfBirth.isAfter(LocalDate.now().minusYears(115));
    }


}
