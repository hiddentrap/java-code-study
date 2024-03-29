package com.example.jaejoo.module.thread.new_fixed_thread_pool;

import org.springframework.stereotype.Component;

@Component
public class ApiValidator implements Validator {

  @Override
  public String call() throws Exception {
    System.out.println(Thread.currentThread().getName() + ": " + this.getClass().getName());
    Thread.sleep(2000);
    return "Api ok";
  }
}
