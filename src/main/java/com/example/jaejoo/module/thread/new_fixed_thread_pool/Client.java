package com.example.jaejoo.module.thread.new_fixed_thread_pool;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Client implements CommandLineRunner {

  private final List<Validator> validators;

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Client.class);
    app.setWebApplicationType(WebApplicationType.NONE);
    app.run(args);
  }

  @Override
  public void run(String... args)
      throws IOException, InterruptedException, ExecutionException, TimeoutException {
//    invokeAllTest();
    submitTest();
  }

  private void submitTest()  {
    int MAX_THREAD_NUM = 10;
    int threadNum = validators.size() > MAX_THREAD_NUM ? MAX_THREAD_NUM : validators.size();
    ExecutorService threadPool = Executors.newFixedThreadPool(threadNum);

    List<Future<String>> threads = new ArrayList<>();
    for (Validator validator : validators) {
      threads.add(threadPool.submit(validator));
    }
    String result="";
    for (Future<String> thread : threads) {
      try {
       result = thread.get(1500, TimeUnit.MILLISECONDS);
      } catch (Exception e) {
        System.out.println(e);
      }
      System.out.println(result);
    }
    threadPool.shutdown();
  }
  private void invokeAllTest() throws InterruptedException {
    LocalDateTime start = LocalDateTime.now();
    System.out.println("strart: " + start);
    // 운용하는 Trhead 갯수가 고정되어있는 Thread Pool
    // invokeAll 하면 어떤 validator가 timeout난지 알 수가 없으므로 submit 을 loop 돌려서 처리하자
    int MAX_THREAD_NUM = 10;
    int threadNum = validators.size() > MAX_THREAD_NUM ? MAX_THREAD_NUM : validators.size();
    ExecutorService threadPool = Executors.newFixedThreadPool(threadNum);
    List<Future> futures = threadPool.invokeAll((Collection) validators, 1500,
        TimeUnit.MILLISECONDS);

    try (Closeable close = threadPool::shutdown) {
      for (Future<String> future : futures) {
        String result = future.isCancelled() ? "cancled" : future.get();
        System.out.println(result);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    LocalDateTime end = LocalDateTime.now();
    System.out.println("end: " + end);

    BigDecimal abc;
  }
}
