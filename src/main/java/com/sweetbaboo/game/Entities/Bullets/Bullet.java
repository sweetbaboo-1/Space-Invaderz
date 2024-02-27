package com.sweetbaboo.game.Entities.Bullets;

import javax.swing.ImageIcon;

import com.sweetbaboo.game.Entities.Entity;

public class Bullet extends Entity {

  private int dy = 30;

  public Bullet(int xPosition, int yPosition) {
    super(new ImageIcon("src\\resources\\images\\bullets\\bullet.png").getImage(), xPosition, yPosition);
  }

  public void move() {
    setyPosition(getyPosition() - dy);
  }
}
