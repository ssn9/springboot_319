package com.aaa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

// 1.当前类是一个配置类
@Configuration
// 2.将当前类作为Security的web配置类
@EnableWebSecurity
// 3.启用sercurity的全局方法配置，启用注解配置
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserServiceConfig userServiceConfig;

    // 拦截请求配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            // 认证请求
        http.authorizeRequests()
                .antMatchers("/demo01/test")
                .permitAll()
                .and()
                .authorizeRequests()
                // 所有请求
                .anyRequest()
                // 授权
                .authenticated()
                // 条件的拼接
                .and()
                // 使用自定义form表单进行登录
                .formLogin()
                // 登录页面的路径
                .loginPage("/html/login.html")
                // 登录提交的地址,表单的提交路径和配置的路径一致
                .loginProcessingUrl("/user/login-check")
                // 登录成功默认跳转的地址,没有访问资源时
                .defaultSuccessUrl("/user/login-success")
                // 失败的地址
                .failureUrl("/user/login-error")
                // form表单对应的用户名属性名，默认必须使用username,password
                .usernameParameter("uname")
                .passwordParameter("pwd")
                // 不进行认证
                .permitAll()
                .and()
                // 自动登录，默认表单提交时自动登录的name=remember-me
                // 1.第一种方式，sessionId保存在cookie中，有效期2周.rememberMe().userDetailsService(userServiceConfig)
                .rememberMe()
                // 2.使用数据库保存认证信息
                .tokenRepository(persistentTokenRepository())
                // 有效期
                .tokenValiditySeconds(60*2)
                .userDetailsService(userServiceConfig);

                // 配置退出
        http.logout()
                // 退出的地址
                .logoutUrl("/user/logout")
                // 退出成功的地址
                .logoutSuccessUrl("/demo01/test");

        // 权限配置
        // http.authorizeRequests().antMatchers("/demo01/select").hasAnyRole("admin").antMatchers("/demo01/query").hasRole("aaa");
        http.authorizeRequests().anyRequest().access("@permissionConfig.hasPermission(request,authentication)");
        // 禁用csrf跨域请求
        http.csrf().disable();
    }

    // application.yml中配置
    @Autowired
    DataSource dataSource;

    // 自动认证
    // <bean class=""></bean>
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 服务器启动时自动去创建token表，如果数据库中已经存在，会报错
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Autowired
    AuthenticationProviderConfig authenticationProviderConfig;

    // 认证管理配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 认证生成器
        auth.authenticationProvider(authenticationProviderConfig);
    }

    // web配置，忽略静态资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/demo01/test2");
    }
}
