package com.dosol.focusfrogs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //이게 시큐리티 설정해주는거다. 이제 이 클래스가 시큐리에 관리된다.
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean //이 빈으로 커스텀으로 시큐리티 관리함
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        //리퀘스트매처는 이 주소에 조건을 주겠다는거다
                        .requestMatchers( "/login", "/join", "/joinProc").permitAll()
                        //퍼밋올 로그인 안한 애도 접근 가능
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // 특정 롤이 있어야 가능
                        .requestMatchers("/","/my/**").hasAnyRole("ADMIN", "USER")
                        //애니롤은 저거 두개 가능, 여러가지 롤 설정이다.
                        .anyRequest().authenticated()
                        //어덴틱은 로그인하면 다 가능
                );

        http
                .formLogin((auth)->auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        //http
        //        .csrf((auth) -> auth.disable());
        //토근 검사안한다고 하는거임. 이게 주석되면 토근 검사해서
        //들어오기때문에 로그인 안된다. 토근 없이는

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());

        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));

        return http.build();
    }
}