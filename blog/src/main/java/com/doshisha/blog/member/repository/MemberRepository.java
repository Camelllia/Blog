package com.doshisha.blog.member.repository;

import com.doshisha.blog.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member findByEmailAndPassword(String email, String password);
}
