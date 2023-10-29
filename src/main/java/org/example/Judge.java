package org.example;

import java.util.ArrayList;

public class Judge {
  private final int[] guessed;
  private final ArrayList<Player> players = new ArrayList<>();

  public Judge(int numbers) {
    guessed = new int[numbers];
  }

  public synchronized boolean guess(int number) {
    number--;

    if (guessed[number] == 0) {
      guessed[number]++;
      return true;
    } else {
      return false;
    }
  }

  public Judge addPlayer(Player player) {
    players.add(player);
    player.setJudge(this);

    return this;
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }
}
