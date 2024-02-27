package com.sweetbaboo.game.Entities.Bullets;

import javax.swing.ImageIcon;

import com.sweetbaboo.game.Entities.Entity;

public class Bullet extends Entity {

  public static final int DY = -30;
  public static final int DX = 0;
  public static final int SCALE = 10;

  public Bullet(int xPosition, int yPosition) {
    super(new ImageIcon("src\\resources\\images\\bullets\\bullet.png").getImage(), xPosition, yPosition, Bullet.SCALE);
  }
}
