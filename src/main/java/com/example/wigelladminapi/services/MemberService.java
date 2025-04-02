package com.example.wigelladminapi.services;

import com.example.wigelladminapi.entities.Member;
import com.example.wigelladminapi.exceptions.InvalidInputException;
import com.example.wigelladminapi.exceptions.NotUniqException;
import com.example.wigelladminapi.exceptions.ResourceNotFoundException;
import com.example.wigelladminapi.repositories.MemberRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
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
        throw new ResourceNotFoundException(); //TODO Lägg till rätt parametrar
    }

    @Override
    public Member updateMember(Member member) {
        //TODO kontrollera att member finns i databasen

        //TODO Gör NULL-kontroller och uppdatera endast fälten som skickas in som inte är null

        //TODO Kontrollera om eventuell adress redan finns, i så fall koppla till den adress som finns, annars skapa ny

        //TODO Kontrollera ev mail så att den är uniq, annars chasta ett exception tex NotUniqException

        return null;
    }

    @Override
    public Member addMember(Member member) {

        //TODO Kontrollera om adressen redan finns och inte är NULL. Om den finns använd det id annars skapa ny adress

        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            throw new InvalidInputException(); //TODO lägg in parametrar
        }
        if (isEmailTaken(member.getEmail())) {
            throw new NotUniqException(); //TODO lägg in parametrar
        }

        if (member.getFirstName() == null || member.getFirstName().isEmpty()) {
            throw new InvalidInputException(); //TODO lägg till parametrar
        }

        if (member.getLastName() == null || member.getLastName().isEmpty()) {
           throw new InvalidInputException(); //TODO lägg till parametrar
        }

        if (member.getDateOfBirth() == null) {
            throw new InvalidInputException(); //TODO lägg in parametrar
        }
        if (!isDateOfBirthValid(member.getDateOfBirth())) {
            throw new InvalidInputException(); //TODO Lägg till parametrar
        }
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long id) {
        //Ska jag använda string för konfermation? delited/not deleted
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            memberRepository.delete(result.get());
        }
        throw new ResourceNotFoundException(); //TODO Lägg till parametrar
    }

    //Kontroll-metoder
    private boolean isEmailTaken(String email) {
        return memberRepository.existsByEmail(email);
    }

    private boolean isDateOfBirthValid(LocalDate dateOfBirth) {
        return dateOfBirth.isBefore(LocalDate.now()) && dateOfBirth.isAfter(LocalDate.now().minusYears(115));
    }


}
