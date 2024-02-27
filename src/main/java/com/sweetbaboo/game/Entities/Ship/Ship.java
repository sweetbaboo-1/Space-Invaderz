package com.sweetbaboo.game.Entities.Ship;

import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.sweetbaboo.game.Main;
import com.sweetbaboo.game.Entities.Entity;
import com.sweetbaboo.game.Entities.Bullets.Bullet;

public class Ship extends Entity {

  public static final int DX = 10;
  public static final int DY = 0;
  public static final int SCALE = 2;
  public static final int RELOAD_TIME_MS = 200;

  private long lastShotTime = System.currentTimeMillis();
  private List<Bullet> bullets = new ArrayList<>();

  public Ship() {
    super(new ImageIcon("src\\resources\\images\\ship\\ship.png").getImage(), (int) Main.SCREEN_WIDTH / 2,
        (int) Main.SCREEN_HEIGHT - 200, Ship.SCALE);
  }

  @Override
  public void move(int dx, int dy) {
    int xPosition = getxPosition();
    int width = getScaledWidth();

    xPosition += dx;
    if (xPosition > Main.SCREEN_WIDTH) {
      xPosition = -width;
    } else if (xPosition < -width) {
      xPosition = (int) Main.SCREEN_WIDTH;
    }
    setxPosition(xPosition);
  }

  public void shoot() {
    if (System.currentTimeMillis() > lastShotTime + RELOAD_TIME_MS) {
      // get the correct width to center the bullet in the ship
      Bullet bullet = new Bullet(getxPosition() + getScaledWidth() / 2, getyPosition());
      bullet.setxPosition(bullet.getxPosition() - bullet.getScaledWidth() / 2);
      bullets.add(bullet);
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
