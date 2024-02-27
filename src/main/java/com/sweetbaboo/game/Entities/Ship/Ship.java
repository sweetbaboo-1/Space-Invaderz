package com.sweetbaboo.game.Entities.Ship;

import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.Toolkit;

import com.sweetbaboo.game.Main;
import com.sweetbaboo.game.Entities.Entity;
import com.sweetbaboo.game.Entities.Bullets.Bullet;

public class Ship extends Entity {

  public static final int RIGHT = 10;
  public static final int LEFT = -10;
  public static final int RELOAD_TIME_MS = 200;

  private long lastShotTime = System.currentTimeMillis();
  private List<Bullet> bullets = new ArrayList<>();

  public Ship() {
    super(new ImageIcon("src\\resources\\images\\ship\\ship.png").getImage(), (int) Main.SCREEN_WIDTH / 2, (int) Main.SCREEN_HEIGHT - 200);
  }

  public void shoot() {
    if (System.currentTimeMillis() > lastShotTime + RELOAD_TIME_MS) {
      bullets.add(new Bullet(getxPosition() + 28, getyPosition()));
      lastShotTime = System.currentTimeMillis();
    }
  }

  public void move(int increment) {
    setxPosition(getxPosition() + increment);
  }

  public List<Bullet> getBullets() {
    return bullets;
  }

  public void removeBullet(Bullet bullet) {
    bullets.remove(bullet);
  }
}
