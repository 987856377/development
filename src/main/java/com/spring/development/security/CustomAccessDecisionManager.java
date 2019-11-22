package com.spring.development.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
            if (collection == null){
                throw new AccessDeniedException("权限不足");
            }
            Iterator<ConfigAttribute> iterator = collection.iterator();
            while (iterator.hasNext()){
                ConfigAttribute configAttribute = iterator.next();

                String needRole = ((SecurityConfig)configAttribute).getAttribute();
                for (GrantedAuthority grantedAuthority : authentication.getAuthorities()){
                    if (needRole.trim().equals(grantedAuthority.getAuthority().trim())){
                        return;
                    }
                }
            }
            throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
