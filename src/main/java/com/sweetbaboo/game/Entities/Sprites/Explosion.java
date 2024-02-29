package com.sweetbaboo.game.Entities.Sprites;

import javax.swing.ImageIcon;

import com.sweetbaboo.game.Entities.Entity;

public class Explosion extends Entity{
  public static final int DURATION = 200;
  public static final int SCALE = 10;

  private long initTime;

  public Explosion(int x, int y) {
    super(new ImageIcon("src\\resources\\images\\explosions\\explosion.png").getImage(), x, y, Explosion.SCALE);
    initTime = System.currentTimeMillis();
  }

  public boolean shouldExist() {
    return System.currentTimeMillis() - DURATION < initTime;
  }
}
