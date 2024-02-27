package com.sweetbaboo.game.Entities.Aliens;

import javax.swing.ImageIcon;

import com.sweetbaboo.game.Main;
import com.sweetbaboo.game.Entities.Entity;

public class Alien extends Entity {
  public static final int DY = 30;
  public static final int DX = 0;
  public static final int SCALE = 10;
  
  private boolean isAlive = true;

  public Alien() {
    super(new ImageIcon("src\\resources\\images\\aliens\\greenAlien.png").getImage(), (int) Main.SCREEN_WIDTH / 2, 150, Alien.SCALE);
  }

  public Alien(int i) {
    super(new ImageIcon("src\\resources\\images\\aliens\\greenAlien.png").getImage(), (int) Main.SCREEN_WIDTH / 10 * i,
        150, Alien.SCALE);
  }

  public void kill() {
    // some form of death animation?
    isAlive = false;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean isAlive) {
    this.isAlive = isAlive;
  }
}
