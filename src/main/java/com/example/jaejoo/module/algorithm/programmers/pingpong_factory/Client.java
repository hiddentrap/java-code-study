package com.example.jaejoo.module.algorithm.programmers.pingpong_factory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Client implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(
        com.example.jaejoo.module.algorithm.programmers.car_parking.Client.class);
    app.setWebApplicationType(WebApplicationType.NONE);
    app.run(args);
  }


  @Override
  public void run(String... args) throws Exception {

  }

  public static class Solution {
    public int[] solution(int n, int k, int[] floors){

      return null;
    }
  }
}
