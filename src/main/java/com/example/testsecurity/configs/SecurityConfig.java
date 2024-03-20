package com.example.testsecurity.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean //시큐리티 옵션을 커스텀할 수 있는 곳
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(AbstractHttpConfigurer::disable) // ajax 사용하려면 토큰 필요. 우선은 disable 해둠.  Cross-Site Request Forgery (CSRF) 보호 기능을 비활성화
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/ss/**").permitAll()
//            .requestMatchers("/**").authenticated() // **밑이랑 중복** 나머지 모든 경로는 로그인한 사용자에게 접근 허용
            .anyRequest().authenticated()) // 그 외 모든 요청은 로그인한 사용자만 접근 가능
        .formLogin(form -> form
            .loginPage("/ss/login/home") //로그인 하는 페이지
            .defaultSuccessUrl("/", true) //로그인 성공 시 메인 페이지로
            .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login") // 로그아웃 성공 후 리디렉트할 URL 지정
            .permitAll());
    return http.build();
  }


  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return w -> w.ignoring().requestMatchers(
        "/images/**", "/css/**", "/js/**"
    );
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
