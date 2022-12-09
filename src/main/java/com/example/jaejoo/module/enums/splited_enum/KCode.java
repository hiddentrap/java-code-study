package com.example.jaejoo.module.enums.splited_enum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum 종류마다 1개씩 필요
 */
public interface KCode {

  Map<Integer, EnumType> CODE_MAP = KCode.initCodeMap();

  static Map<Integer, EnumType> initCodeMap() {
    Map<Integer, EnumType> result = new HashMap<>();
    try {
      result.putAll(makeCodeMap(EnumCodePart1.class));
      result.putAll(makeCodeMap(EnumCodePart2.class));
    } catch (Exception ex) {
      // Intended Exception Consuming
      ex.printStackTrace();
    }
    return Collections.unmodifiableMap(result);
  }

  static Map<Integer, EnumType> makeCodeMap(Class<? extends KCode> clz)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method values = clz.getMethod("values");
    return Stream.of((KCode[]) values.invoke(null))
        .collect(Collectors.toMap(KCode::getCode, KCode::getEnumValue));
  }

  static KCode of(final Integer code)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    EnumType codes = CODE_MAP.get(code);
    Class<? extends Enum> clz = codes.getClz();
    Method method = clz.getMethod("valueOf", String.class);
    return (KCode) method.invoke(null, codes.getCodeName().toUpperCase());

  }

  Integer getCode();

  EnumType getEnumValue();

}
