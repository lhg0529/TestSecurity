package com.example.testsecurity.controller;

import com.example.testsecurity.entity.User;
import com.example.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/ss/regist")
public class JoinController {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public JoinController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/join")
  public String showJoinForm() {
    return "/ss/join/join";
  }

  @PostMapping("/join")
  public String processRegistration(String username, String password) {
    User user = new User();
    user.setUserId(username);
    user.setUserPw(passwordEncoder.encode(password)); // 실제 사용 시, 패스워드는 암호화하여 저장해야 합니다.
    userRepository.save(user);

    System.out.println(username + password);
    return "redirect:/ss/login/home"; // 가입 후 로그인 페이지로 리디렉션
  }


}
