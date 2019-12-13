package com.aaa.security;

import com.aaa.entity.Users;
import com.aaa.service.UsersService;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

// 用户详细信息配置类：获取用户真实详细信息
@Component
public class UserServiceConfig implements UserDetailsService {

    @Resource
    UsersService usersService;

    /**
     * 通过用户名加载用户信息
     * @param  s
     * @return 用户详细身份信息
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("s:"+s);
        // 1.根据用户名获取用户信息
        Users users = usersService.selectByName(s);

        if(null != users){
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

            // 2.获取对应权限
            GrantedAuthority admin = new SimpleGrantedAuthority("admin");
            GrantedAuthority test = new SimpleGrantedAuthority("test");

            list.add(admin);
            list.add(test);
            return new User(users.getUname(),users.getPwd(),list);
        }
        return null;
    }
}
