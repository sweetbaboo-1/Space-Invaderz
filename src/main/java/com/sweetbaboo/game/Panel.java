package com.sweetbaboo.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

import com.sweetbaboo.game.Collisions.CollisionChecking;
import com.sweetbaboo.game.Collisions.HitBox;
import com.sweetbaboo.game.Entities.Aliens.Alien;
import com.sweetbaboo.game.Entities.Bullets.Bullet;
import com.sweetbaboo.game.Entities.Ship.Ship;
import com.sweetbaboo.utils.Pair;

public class Panel extends JPanel {

  private Ship ship;
  private List<Alien> aliens;
  private static final boolean DEBUG = true;

  Panel(Ship ship, List<Alien> aliens) {
    this.ship = ship;
    this.aliens = aliens;
    this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.setBackground(Color.BLACK);
    startTimer();
  }

  private void startTimer() {
    Timer timer = new Timer(16, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        repaint();
      }
    });
    timer.start();
  }

  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2D = (Graphics2D) g;

    List<Alien> markedAliens = new ArrayList<>();

    // draw things in this order to get the layers correct
    for (Alien alien : aliens) {
      if (alien.isAlive()) {
        g2D.drawImage(alien.getImage(), alien.getxPosition(), alien.getyPosition(), alien.getScaledWidth(),
            alien.getScaledHeight(), null);
        if (DEBUG) {
          HitBox hitBox = alien.getHitBox();
          g2D.drawRect(hitBox.getX(), hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());
        }
      } else {
        markedAliens.add(alien);
      }
    }
    killAliens(markedAliens);
    for (Bullet bullet : ship.getBullets()) {
      g2D.drawImage(bullet.getImage(), bullet.getxPosition(), bullet.getyPosition(), bullet.getScaledWidth(),
          bullet.getScaledHeight(), null);
      if (DEBUG) {
        HitBox hitBox = bullet.getHitBox();
        g2D.drawRect(hitBox.getX(), hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());
      }
    }
    g2D.drawImage(ship.getImage(), ship.getxPosition(), ship.getyPosition(), 100, 100, null);
    Pair<Alien, Bullet> hits = CollisionChecking.checkBulletHit(aliens, ship.getBullets());
    if (hits != null) {
      hits.getFirst().kill();
    }
  }

  private void killAliens(List<Alien> markedAliens) {
    aliens.removeAll(markedAliens);
  }
}
