package org.example.threads;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;
import org.example.Player;
import org.example.exceptions.JudgeNotFoundException;

public class PlayerThread implements Runnable {
  private static final Logger logger = Logger.getLogger(PlayerThread.class.getSimpleName());
  private final Player player;
  private final CountDownLatch latch;

  public PlayerThread(Player player, CountDownLatch latch) {
    this.player = player;
    this.latch = latch;
  }

  @Override
  public void run() {
    logger.info("Player '" + player.getName() + "' starts");

    while (latch.getCount() > 0) {
      try {
        int number = player.generate();
        if (player.getJudge().guess(number)) {
          player.addPoint();
          latch.countDown();
          logger.info(player.getName() + " guessed the number");
        }
        Thread.sleep(50);

      } catch (JudgeNotFoundException e) {
        logger.warning("No judge provided for player" + player.getName());
      } catch (InterruptedException e) {
        logger.warning("Player '" + player.getName() + "' was interrupted while sleeping");
      } catch (Exception e) {
        logger.warning(e.getMessage());
      }
    }
  }
}
