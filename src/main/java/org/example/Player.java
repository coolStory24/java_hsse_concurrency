package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.example.exceptions.JudgeNotFoundException;

public class Player {
  private static final Random random = new Random();
  private final String name;
  private final AtomicInteger points = new AtomicInteger(0);
  private Judge judge;

  public Player(String name) {
    this.name = name;
  }

  public int generate() {
    return Player.random.nextInt(100) + 1;
  }

  public String getName() {
    return this.name;
  }

  public void addPoint() {
    this.points.incrementAndGet();
  }

  public int getPoints() {
    return this.points.get();
  }

  public void setJudge(Judge judge) {
    this.judge = judge;
  }

  public Judge getJudge() throws JudgeNotFoundException {
    if (judge == null) throw new JudgeNotFoundException();
    return judge;
  }
}
