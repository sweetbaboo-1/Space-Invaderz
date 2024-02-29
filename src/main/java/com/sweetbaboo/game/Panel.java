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
import com.sweetbaboo.game.Entities.Sprites.Explosion;
import com.sweetbaboo.utils.Pair;

public class Panel extends JPanel {

  private Ship ship;
  private List<Alien> aliens;
  private static final boolean DEBUG = false;
  private static int score;
  private List<Explosion> explosions;

  Panel(Ship ship, List<Alien> aliens) {
    this.ship = ship;
    this.aliens = aliens;
    this.explosions = new ArrayList<>();
    this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.setDoubleBuffered(true);
    startTimer();
  }
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2D = (Graphics2D) g;

    // draw things in this order to get the layers correct
    drawBackgroundImage(g2D);
    drawAliens(g2D);
    drawBullets(g2D);
    drawShip(g2D);

    // kill aliens remove bullets, make explosions
    handleBullets();
    explode(g2D);
    
    moveAliens();
    
    drawScore(g2D);
    
    if (DEBUG) g2D.drawLine((int)Main.SCREEN_WIDTH/2, 0, (int) Main.SCREEN_WIDTH / 2, (int) Main.SCREEN_HEIGHT);
  }

  private void moveAliens() {
    for (Alien alien : aliens) {
      break;
    }
  }
  private void explode(Graphics2D g2D) {
    for (Explosion explosion : explosions) {
      if (explosion.shouldExist()) {
        g2D.drawImage(explosion.getImage(), explosion.getxPosition(), explosion.getyPosition(), explosion.getScaledWidth(), explosion.getScaledHeight(), null);
      }
    }
  }
  private void drawBackgroundImage(Graphics2D g2D) {
    Image backgroundImage = new ImageIcon("src\\resources\\images\\backgrounds\\spaceImage.jpg").getImage();
    g2D.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
  }

  private void handleBullets() {
    List<Pair<Alien, Bullet>> hits = CollisionChecking.checkBulletHit(aliens, ship.getBullets());
    for (Pair<Alien, Bullet> hit : hits) {
      hit.getFirst().kill();
      ship.removeBullet(hit.getSecond());
      
      // create explosions
      Explosion explosion = new Explosion(hit.getFirst().getCenterX(), hit.getFirst().getCenterY());
      explosion.centerOnPoint();
      explosions.add(explosion);
      score += 100;
    }
  }

  private void drawShip(Graphics2D g2D) {
    g2D.drawImage(ship.getImage(), ship.getxPosition(), ship.getyPosition(), ship.getScaledWidth(), ship.getScaledHeight(), null);
    if (DEBUG) {
      drawHitBox(ship.getHitBox(), g2D);
    }
  }

  private void drawAliens(Graphics2D g2D) {
    List<Alien> markedAliens = new ArrayList<>();
    for (Alien alien : aliens) {
      if (alien.isAlive()) {
        g2D.drawImage(alien.getImage(), alien.getxPosition(), alien.getyPosition(), alien.getScaledWidth(),
            alien.getScaledHeight(), null);
        if (DEBUG) {
          drawHitBox(alien.getHitBox(), g2D);
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
        drawHitBox(bullet.getHitBox(), g2D);
      }
    }

    if (DEBUG) {
      g2D.drawString("Bullets: " + ship.getBullets().size(), 20, 40);
    }
  }

  private void drawScore(Graphics2D g2D) {
    g2D.setFont(new Font("Arial", Font.BOLD, 20));
    g2D.setColor(Color.RED);
    g2D.drawString("SCORE: " + score, 20, 20);
  }

  private void drawHitBox(HitBox hitBox, Graphics2D g2D) {
    g2D.drawRect(hitBox.getX(), hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());
    g2D.drawLine(hitBox.getX(), hitBox.getY(), hitBox.getX() + hitBox.getWidth(), hitBox.getY() + hitBox.getHeight());
    g2D.drawLine(hitBox.getX(),  hitBox.getY() + hitBox.getHeight(), hitBox.getX() + hitBox.getWidth(), hitBox.getY());
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

  public static void modifyScore(int value) {
    score += value;
  }
}
