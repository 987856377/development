package com.spring.development.config;

import com.spring.development.jwt.JwtAuthenticationFilter;
import com.spring.development.jwt.JwtAuthenticationTokenFilter;
import com.spring.development.jwt.JwtLoginFilter;
import com.spring.development.security.CustomAccessDeniedHandler;
import com.spring.development.security.CustomUserDetailsService;
import com.spring.development.security.EntryPointUnauthorizedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.config
 * @Author xuzhenkui
 * @Date 2019/11/16 16:53
 */

/**
 *
 * 跨域资源共享(CORS) 是一种机制，它使用额外的 HTTP 头来告诉浏览器  让运行在一个 origin (domain) 上的Web应用被准许访问来自不同源服务器上的指定的资源。
 * 当一个资源从与该资源本身所在的服务器不同的域、协议或端口请求一个资源时，资源会发起一个跨域 HTTP 请求。
 *
 * CSRF跨站点请求伪造(Cross—Site Request Forgery)，跟XSS攻击一样，存在巨大的危害性
 *
 *
 *
 * @Secured({"ROLE_ADMIN","ROLE_DBA"})                  拥有 DBA 或者 ADMIN 角色可以访问。另外需要注意的是这里匹配的字符串角色必须添加前缀“ROLE_“。
 *
 * @PreAuthorize("hasAuthority('ADMIN') and hasRole('DBA')") 同时拥有 ADMIN 和 DBA 的角色才可访问
 * @PreAuthorize("hasAnyAuthority('ADMIN','DBA')")           拥有 DBA 或者 ADMIN 的角色可以访问
 *
 * @PostAuthorize 注解使用并不多，在方法执行后再进行权限验证，适合验证带有返回值的权限，Spring EL 提供 返回对象能够在表达式语言中获取返回的对象returnObject。
 * @PostAuthorize(" returnObject!=null &&  returnObject.username == authentication.name")
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)// 控制权限注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Resource
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Resource
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS,"/**")
                    .permitAll()
//                    .hasRole("ADMIN")//用hasRole时，在我们返回的UserDetails的Authority需要加ROLE_ADMIN
//                    .hasAuthority("read")//用户自定义的权限，返回的UserDetails的Authority只要与这里匹配就可以，这里不需要加ROLE_
//                    .access("hasRole('ADMIN') and hasIpAddress('192.168.0.1')")//指定有ADMIN权限并且匹配相应的IP
                    .antMatchers("/sendVerificationCode","/resetPassword")
                    .permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .successForwardUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .permitAll()//注销请求可直接访问
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(entryPointUnauthorizedHandler)
                    .accessDeniedHandler(customAccessDeniedHandler)
                .and()
//                父类 WebSecurityConfigurerAdapter 中拥有 authenticationManager 方法, 可直接调用
                    .addFilter(new JwtLoginFilter(authenticationManager()))
                    .addFilter(new JwtAuthenticationFilter(authenticationManager()));
        // 添加JWT filter
        // JwtAuthenticationTokenFilter 与 JwtAuthenticationFilter 功能大体一致, JwtAuthenticationTokenFilter 会增加请求调用时间, 先屏蔽
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //基于token，所以不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 禁用缓存
        http.headers().cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        在用户注册的时候将用户密码加密, 验证的时候使用默认配置进行登录验证, 数据库中用户密码为密文
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }
}
