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
  private int score;

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

    // draw things in this order to get the layers correct
    drawAliens(g2D);
    drawBullets(g2D);
    drawShip(g2D);

    // kill aliens and remove bullets
    handleBullets();
    
    drawScore(g2D);
  }


  private void handleBullets() {
    List<Pair<Alien, Bullet>> hits = CollisionChecking.checkBulletHit(aliens, ship.getBullets());
    for (Pair<Alien, Bullet> hit : hits) {
      hit.getFirst().kill();
      ship.removeBullet(hit.getSecond());
      score += 100;
    }
  }

  private void drawShip(Graphics2D g2D) {
    g2D.drawImage(ship.getImage(), ship.getxPosition(), ship.getyPosition(), ship.getScaledWidth(), ship.getScaledHeight(), null);
  }

  private void drawAliens(Graphics2D g2D) {
    List<Alien> markedAliens = new ArrayList<>();
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
    aliens.removeAll(markedAliens);
    if (DEBUG) {
      g2D.drawString("Aliens: " + aliens.size(), 20, 60);
    }
  }

  private void drawBullets(Graphics2D g2D) {
    for (Bullet bullet : ship.getBullets()) {
      g2D.drawImage(bullet.getImage(), bullet.getxPosition(), bullet.getyPosition(), bullet.getScaledWidth(),
          bullet.getScaledHeight(), null);
      if (DEBUG) {
        HitBox hitBox = bullet.getHitBox();
        g2D.drawRect(hitBox.getX(), hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());
      }
    }

    if (DEBUG) {
      g2D.drawString("Bullets: " + ship.getBullets().size(), 20, 40);
    }
  }

  
  private void drawScore(Graphics2D g2D) {
    g2D.drawString("SCORE: " + score, 20, 20);
  }
}
