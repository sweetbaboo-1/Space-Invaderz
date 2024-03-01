package com.sweetbaboo.game.Entities.Aliens;

import javax.swing.ImageIcon;

import com.sweetbaboo.game.Entities.Entity;

public class Alien extends Entity {
  public static final int DY = 30;
  public static final int DX = 0;
  public static final int SCALE = 7;
  
  private boolean isAlive = true;

  public Alien(int x) {
    super(new ImageIcon("src\\resources\\images\\aliens\\greenAlien.png").getImage(), x,
    0, Alien.SCALE); // yPosition doesn't matter because in all cases we set it manually
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
