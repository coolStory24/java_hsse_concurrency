package org.example.threads;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;
import org.example.Judge;
import org.example.Player;

public class JudgeThread implements Runnable {
  private static final Logger logger = Logger.getLogger(PlayerThread.class.getSimpleName());
  private final Judge mainJudge;
  private final CountDownLatch latch;

  public JudgeThread(Judge judge, CountDownLatch latch) {
    this.latch = latch;
    this.mainJudge = judge;
  }

  @Override
  public void run() {
    logger.info("The game starts!");
    try {
      latch.await();
      List<Player> players = mainJudge.getPlayers();
      logger.info("The game ends, counting in progress...");

      Player bestPlayer = players.get(0);

      for (Player player : players) {
        if (bestPlayer.getPoints() < player.getPoints()) {
          bestPlayer = player;
        }
      }

      logger.info(
          "And the winner is "
              + bestPlayer.getName()
              + " score: "
              + bestPlayer.getPoints()
              + " points");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      logger.warning("Thread was unexpectedly interrupted");
      logger.info(e.getMessage());
    }
  }
}
