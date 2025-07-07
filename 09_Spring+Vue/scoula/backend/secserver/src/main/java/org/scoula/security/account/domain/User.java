package org.scoula.security.account.domain;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class User implements UserDetails, CredentialsContainer {

    // 필드
    private String password;                              // 비밀번호
    private final String username;                        // 사용자 ID
    private final Set<GrantedAuthority> authorities;      // 권한 집합
    private final boolean accountNonExpired;              // 계정 만료 여부
    private final boolean accountNonLocked;               // 계정 잠김 여부
    private final boolean credentialsNonExpired;          // 신임장 만료 여부
    private final boolean enabled;                        // 계정 활성화 여부

    // 생성자

    // User 클래스 기본 생성자 - 모든 계정 상태를 true로 설정
    public User(String username, String password,
                Collection<? extends GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
    }

    // 상세 생성자 - 모든 계정 상태를 직접 설정
    public User(String username, String password, boolean enabled,
                boolean accountNonExpired, boolean credentialsNonExpired,
                boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    // authorities 정렬 메서드 (선택사항이지만 종종 필요)
    private Set<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .sorted((a1, a2) -> a1.getAuthority().compareTo(a2.getAuthority()))
                .collect(Collectors.toSet());
    }

    // UserDetails 인터페이스 구현 메서드

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // CredentialsContainer 인터페이스 구현
    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
