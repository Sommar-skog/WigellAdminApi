package com.example.wigelladminapi.services;

import com.example.wigelladminapi.entities.Member;
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
        throw new ResourceNotFoundException(); //Lägg till rätt parametrar
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

        //TODO Kontrollera om email är uniq och inte NULL - om den redan finns chasta ett exception tex NotUniqException

        //TODO kontrollera så att inte förnamn är NULL
        if (member.getFirstName() == null || member.getFirstName().isEmpty()) {
            //Throw Exception
        }

        //TODO Kontrollera så att inte efternamn är NULL
        if (member.getLastName() == null || member.getLastName().isEmpty()) {
            // Throw Exception
        }

        //TODO Kontrollera så att inte dateOfBirth är NULL även att det är ett datum som har varit och inte ligger i framtiden
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

    //Kontroll-metoder
    private boolean isEmailTaken(String email) {
        return memberRepository.existsByEmail(email);
    }

    private boolean isDateOfBirthValid(LocalDate dateOfBirth) {
        return dateOfBirth.isBefore(LocalDate.now()) && dateOfBirth.isAfter(LocalDate.now().minusYears(115));
    }


}
