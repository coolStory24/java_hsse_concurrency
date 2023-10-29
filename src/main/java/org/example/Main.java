package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.example.threads.JudgeThread;
import org.example.threads.PlayerThread;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    int n = 100;
    var judge = new Judge(n);
    var player1 = new Player("Алексей Михайлович");
    var player2 = new Player("Петр Алексеевич");
    var player3 = new Player("Елизавета Петровна");
    var player4 = new Player("Павел Петрович");

    judge.addPlayer(player1).addPlayer(player2).addPlayer(player3).addPlayer(player4);

    var executor = Executors.newCachedThreadPool();
    var latch = new CountDownLatch(n);

    executor.submit(new JudgeThread(judge, latch));
    executor.submit(new PlayerThread(player1, latch));
    executor.submit(new PlayerThread(player2, latch));
    executor.submit(new PlayerThread(player3, latch));
    executor.submit(new PlayerThread(player4, latch));

    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.HOURS);
  }
}
