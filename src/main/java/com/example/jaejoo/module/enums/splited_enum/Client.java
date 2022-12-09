package com.example.jaejoo.module.enums.splited_enum;

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
  }

  private void submitTest()
      throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    KCode code = EnumCodePart2.WING;
    validateEnumCode(code);

    code = KCode.of(4);
    validateEnumCode(code);

    System.out.println("END");
  }

  private static void validateEnumCode(KCode code) {
    if (code == EnumCodePart1.HELLO) log.info("KCode and HELLO are equal!");
    if (code == EnumCodePart1.WORLD) log.info("KCode and WORLD are equal!");
    if (code == EnumCodePart2.LEG) log.info("KCode and LEG are equal!");
    if (code == EnumCodePart2.WING) log.info("KCode and WING are equal!");
  }
}
