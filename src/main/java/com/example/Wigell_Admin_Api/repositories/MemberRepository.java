package com.example.Wigell_Admin_Api.repositories;

import com.example.Wigell_Admin_Api.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
