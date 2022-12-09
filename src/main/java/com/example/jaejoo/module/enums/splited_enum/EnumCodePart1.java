package com.example.jaejoo.module.enums.splited_enum;

import lombok.Getter;

@Getter
public enum EnumCodePart1 implements KCode {
  HELLO(1,"hello"),
  WORLD(2,"world")
  ;

  private Integer code;
  private String message;

  EnumCodePart1(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public EnumType getEnumValue() {
    return new EnumType(this.getClass(), this.message);
  }
}
