package org.agency04.software.moneyheist.security;

import org.agency04.software.moneyheist.entity.member.Member;
import org.agency04.software.moneyheist.service.MemberService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    public DomainUserDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return memberService
                .findMemberByEmail(s)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + s + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(Member user) {
        // one day should give user multiple roles, but for now asList
        List<GrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
