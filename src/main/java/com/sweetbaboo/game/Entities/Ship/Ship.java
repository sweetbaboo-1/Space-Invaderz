package com.sweetbaboo.game.Entities.Ship;

import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.sweetbaboo.game.Main;
import com.sweetbaboo.game.Panel;
import com.sweetbaboo.game.Entities.Entity;
import com.sweetbaboo.game.Entities.Bullets.Bullet;

public class Ship extends Entity {

  public static final int DX = 10;
  public static final int DY = 0;
  public static final int SCALE = 3; // * Main.SCALE;
  public static final int BULLET_SCORE_COST = 0; // negative numbers add to the score
  public static final int RELOAD_TIME_MS = 200;
  public static final int SHIP_GAP_FROM_BOTTOM = 100;

  private long lastShotTime = System.currentTimeMillis();
  private List<Bullet> bullets = new ArrayList<>();

  public Ship() {
    super(new ImageIcon("src\\resources\\images\\ship\\ship.png").getImage(), Main.SCREEN_WIDTH / 2,
        Main.SCREEN_HEIGHT - SHIP_GAP_FROM_BOTTOM, Ship.SCALE);
  }

  @Override
  public void move(int dx, int dy) {
    int xPosition = getxPosition();
    int width = getScaledWidth();

    xPosition += dx;
    if (xPosition > Main.SCREEN_WIDTH) {
      xPosition = -width;
    } else if (xPosition < -width) {
      xPosition = Main.SCREEN_WIDTH;
    }
    setxPosition(xPosition);
  }

  public void shoot() {
    if (System.currentTimeMillis() > lastShotTime + RELOAD_TIME_MS) {
      Bullet bullet = new Bullet(getxPosition() + getScaledWidth() / 2, getyPosition() + getScaledHeight() / 2);
      bullet.centerOnPoint();
      bullets.add(bullet);
      Panel.modifyScore(-BULLET_SCORE_COST);
      lastShotTime = System.currentTimeMillis();
    }
  }

  public List<Bullet> getBullets() {
    return bullets;
  }

  public void removeBullet(Bullet bullet) {
    bullets.remove(bullet);
  }
}
