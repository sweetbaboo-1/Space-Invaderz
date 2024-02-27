package com.sweetbaboo.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.sweetbaboo.game.Entities.Aliens.Alien;
import com.sweetbaboo.game.Entities.Bullets.Bullet;
import com.sweetbaboo.game.Entities.Ship.Ship;

public class Panel extends JPanel {

  private Ship ship;
  private Alien alien;
  private long lastTime;

  Panel(Ship ship, Alien alien) {
    this.ship = ship;
    this.alien = alien;
    this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.setBackground(Color.BLACK);
    lastTime = System.nanoTime();
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
    g2D.drawImage(alien.getImage(), alien.getxPosition(), alien.getyPosition(), alien.getWidth() / 10, alien.getHeight() / 10, null);
    for (Bullet bullet : ship.getBullets()) {
      g2D.drawImage(bullet.getImage(), bullet.getxPosition(), bullet.getyPosition(), bullet.getWidth() / 10, bullet.getHeight() / 10, null);
    }
    g2D.drawImage(ship.getImage(), ship.getxPosition(), ship.getyPosition(), 100, 100, null);
    

    long currentTime = System.nanoTime();
    long elapsedTime = currentTime - lastTime;
    double fps = 1_000_000_000.0 / elapsedTime;

    g.setColor(Color.WHITE);
    g.drawString("FPS: " + (int) fps, 10, 20);
    g.drawString("Alien Width: " + alien.getWidth(), 10, 40);

    // draws a square around the border
    // g.drawLine(0, 0, 1920, 0);
    // g.drawLine(1920, 0, 1920, 1080);
    // g.drawLine(1920, 1080, 0, 1080);
    // g.drawLine(0, 0, 0, 1080);

    lastTime = currentTime;
  }
}
