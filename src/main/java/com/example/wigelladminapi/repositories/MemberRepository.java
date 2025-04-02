package com.example.wigelladminapi.repositories;

import com.example.wigelladminapi.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //CRUD får automatiskt - verkar inte som jag behöver lägga till fler metoder här

    boolean existsByEmail(String email);
}
