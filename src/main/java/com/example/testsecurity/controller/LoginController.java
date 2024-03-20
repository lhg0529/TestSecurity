package com.example.testsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ss/login")
public class LoginController {

  @GetMapping("/home")
  public String login() {

    System.out.println("로그인 컨트롤러");
    return "/ss/login/login";
  }
}
