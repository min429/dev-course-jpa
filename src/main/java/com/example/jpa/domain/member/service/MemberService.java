package com.example.jpa.domain.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(String username, String password, String nickname) {
        Member member = Member.builder()
            .username(username)
            .password(password)
            .nickname(nickname)
            .build();
        
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public long count(){
        return memberRepository.count();
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
