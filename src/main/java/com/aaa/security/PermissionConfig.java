package com.aaa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

@Component("permissionConfig")
public class PermissionConfig {

    /**
     * 判断是否拥有某一个权限
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        // 获取用户身份
        Object principal = authentication.getPrincipal();

        System.out.println("principal:"+principal);
        System.out.println("principal.getClass:"+principal.getClass());

        if(principal instanceof UserDetails){
            // 认证已经通过
            // 获取用户对应的角色
            Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();

            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
            while(iterator.hasNext()){
                GrantedAuthority next = iterator.next();
                String roleName = next.getAuthority();
            }

            // 根据用户获取对应的权限
            String url = "/springboot319/demo01/select";

            System.out.println(request.getRequestURI());
            if(request.getRequestURI().equals(url)){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }

    }
}
