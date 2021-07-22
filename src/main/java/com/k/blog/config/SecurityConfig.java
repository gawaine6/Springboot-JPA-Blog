package com.k.blog.config;

import com.k.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 빈 등록, 스프링 컨테이너에서 객체를 관리할 수 있다 이말이야
@Configuration // 빈 등록(IoC)
@EnableWebSecurity // 필터 추가, 스프링 시큐리티 활성화 되어있고 설정을 어떤 파일에서 하겠다
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // IoC ok
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 로그인할 때 username, password 가로챈다
    // 해당 password가 어떤 해쉬로 회원가입 되어이는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교 가능
    @Autowired
    private PrincipalDetailService principalDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(passwordEncoder());
        // passwordEncoder가 사용할 매개변수가 위에 있는 BCryptPasswordEncoder라는 걸 알려줘야한다
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 토큰 비활성화
                .authorizeRequests()
                    .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/img/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc")
                    .defaultSuccessUrl("/");
    }
}
