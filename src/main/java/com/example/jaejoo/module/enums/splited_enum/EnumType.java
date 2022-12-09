package com.example.jaejoo.module.enums.splited_enum;

import lombok.Builder.Default;
import lombok.Getter;

/**
 * 전역적으로 1개만 필요
 */
@Getter
class EnumType {

  private Class<? extends Enum> clz;
  private String codeName;

  EnumType(Class<? extends Enum> clz, String codeName) {
    this.clz = clz;
    this.codeName = codeName;
  }

}