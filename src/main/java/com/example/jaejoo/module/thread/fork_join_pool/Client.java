package com.example.jaejoo.module.thread.fork_join_pool;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Client implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Client.class);
    app.setWebApplicationType(WebApplicationType.NONE);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    submitTest2();
    System.out.println("run End");
  }

  private void submitTest2() throws ExecutionException, InterruptedException {
    List<Integer> jobs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    List<Integer> result = new ArrayList<>();

    ForkJoinPool pool = new ForkJoinPool(10);
    pool.submit(() -> {
      jobs.parallelStream().forEach( job -> {
        try {
          result.add(job);
          throw new RuntimeException("hi exception");
        }catch (Exception e){
          pool.shutdown();
          throw new AssertionError(e);
        }
      });
    }).get();
    pool.shutdown();
    System.out.println(result.toString());
    System.out.println("submitTest2 End");
  }

  private void submitTest() {
    ForkJoinPool pool = new ForkJoinPool(10);
    List<Integer> jobs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    List<Integer> result = new ArrayList<>();

    jobs.stream().parallel().forEach(job -> {
      try {
        result.add(processer(job, pool));
      } catch (Exception e) {
        pool.shutdown();
      }
    });

    pool.shutdown();
  }

  private Integer processer(Integer job, ForkJoinPool pool)
      throws ExecutionException, InterruptedException {
    Integer result = pool.submit(() -> {
      return runner(job);
    }).get();

    return result;
  }

  private Integer runner(Integer job) throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + "Start : " + job + LocalDateTime.now());
    Thread.sleep(ThreadLocalRandom.current().nextInt(1, 5) * 1000);
    System.out.println(Thread.currentThread().getName() + "End : " + job + LocalDateTime.now());
    return job + 1;
  }
}
