package letsCode.sweater.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        // 'name()' method returns name of this enum constant in 'String' type
        return name();
    }

}
