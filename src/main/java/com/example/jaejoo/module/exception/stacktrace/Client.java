package com.example.jaejoo.module.exception.stacktrace;

import com.example.jaejoo.module.enums.splited_enum.EnumCodePart1;
import com.example.jaejoo.module.enums.splited_enum.EnumCodePart2;
import com.example.jaejoo.module.enums.splited_enum.KCode;
import java.lang.reflect.InvocationTargetException;
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
    SpringApplication app = new SpringApplication(Client.class);
    app.setWebApplicationType(WebApplicationType.NONE);
    app.run(args);
  }


  @Override
  public void run(String... args) throws Exception {
    submitTest();
    System.out.println("Hello world Three");;
  }

  private void submitTest(){
    try {
      throwException();
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("hello World Two");
    }
  }

  private void throwException() {
    throw new IllegalStateException("hello world");
  }
}
