package com.example.testsecurity.service;

import com.example.testsecurity.entity.User;
import com.example.testsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService{

  private final UserRepository userRepository;

  public UserSecurityService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findById(username) //findbyusername을 따로 리포지토리에 만들어줘야하나?
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    return org.springframework.security.core.userdetails.User
        .builder()
        .username(user.getUserId())
        .password(user.getUserPw())
        .authorities("ROLE_USER") // 간단한 예제이므로, 모든 사용자에게 'ROLE_USER' 권한을 부여합니다.
        .build();
  }


}
