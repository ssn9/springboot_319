package com.aaa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

// 认证生成器:生成身份信息
@Component
public class AuthenticationProviderConfig implements AuthenticationProvider {

    @Autowired
    UserServiceConfig userServiceConfig;

    // 认证
    // authentication:待认证的身份信息
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取身份名称（表单提交的用户名）
        String name = authentication.getName();
        // 获取凭证（表单提交的密码）
        Object credentials = authentication.getCredentials();

        // 根据用户名获取用户的真实用户信息
        UserDetails userDetails = userServiceConfig.loadUserByUsername(name);

        if(null == userDetails){
            System.out.println("用户不存在");
            return null;
        }else{
            if(userDetails.getPassword().equals(credentials)){
                System.out.println("认证成功");
                // principal:证书（用户身份信息）   credentials:凭证（密码）   authorities:角色
                return new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
            }
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
