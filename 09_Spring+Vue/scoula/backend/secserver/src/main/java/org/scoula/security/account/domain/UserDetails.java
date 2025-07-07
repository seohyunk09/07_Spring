package org.scoula.security.account.domain;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public interface UserDetails extends Serializable {

    Collection<? extends GrantedAuthority> getAuthorities();  // 권한 목록
    String getPassword();                                     // 비밀번호
    String getUsername();                                     // 사용자 ID

    // 계정 상태 확인 메서드 - 모두 true일 때만 인증 성공
    boolean isAccountNonExpired();                           // 계정 만료 여부
    boolean isAccountNonLocked();                            // 계정 잠김 여부
    boolean isCredentialsNonExpired();                       // 신임장 만료 여부
    boolean isEnabled();                                     // 계정 활성화 여부
}
