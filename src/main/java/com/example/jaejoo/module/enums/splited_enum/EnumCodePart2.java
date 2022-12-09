package com.example.jaejoo.module.enums.splited_enum;

import lombok.Getter;

@Getter
public enum EnumCodePart2 implements KCode{
  LEG(3, "leg"),
  WING(4, "wing")
  ;

  private Integer code;
  private String message;

  EnumCodePart2(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public EnumType getEnumValue() {
    return new EnumType(this.getClass(), this.message);
  }
}
