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
  public static final int SCALE = 10;
  public static final int RELOAD_TIME_MS = 200;

  private long lastShotTime = System.currentTimeMillis();
  private List<Bullet> bullets = new ArrayList<>();

  public Ship() {
    super(new ImageIcon("src\\resources\\images\\ship\\ship.png").getImage(), (int) Main.SCREEN_WIDTH / 2, (int) Main.SCREEN_HEIGHT - 200, Ship.SCALE);
  }

  public void shoot() {
    if (System.currentTimeMillis() > lastShotTime + RELOAD_TIME_MS) {
      bullets.add(new Bullet(getxPosition() + 28, getyPosition()));
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
