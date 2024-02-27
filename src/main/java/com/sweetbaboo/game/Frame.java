package com.sweetbaboo.game;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.sweetbaboo.game.Entities.Aliens.Alien;
import com.sweetbaboo.game.Entities.Bullets.Bullet;
import com.sweetbaboo.game.Entities.Ship.Ship;

public class Frame extends JFrame {

  Panel panel;
  Ship ship = new Ship();
  Alien alien = new Alien();

  boolean leftKeyPressed = false;
  boolean rightKeyPressed = false;
  boolean spaceBarPressed = false;

  Frame() {
    panel = new Panel(ship, alien);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.add(panel);
    this.pack();
    this.setLocationRelativeTo(null);

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          leftKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          rightKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
          spaceBarPressed = true;
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          leftKeyPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          rightKeyPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
          spaceBarPressed = false;
        }
      }
    });

    Timer timer = new Timer(16, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        List<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : ship.getBullets()) {
          bullet.move();
          if (bullet.getyPosition() < -50) {
            bulletsToRemove.add(bullet);
          }
        }

        for (Bullet bullet : bulletsToRemove) {
          ship.removeBullet(bullet);
        }

        if (leftKeyPressed) {
          ship.move(Ship.LEFT);
        }
        
        if (rightKeyPressed) {
          ship.move(Ship.RIGHT);
        }

        if (spaceBarPressed) {
          ship.shoot();
        }

        alien.move();
      }
    });
    timer.start();

    this.setVisible(true);
  }
}
