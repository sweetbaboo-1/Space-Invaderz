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
  List<Alien> aliens = new ArrayList<>();

  boolean leftKeyPressed = false;
  boolean rightKeyPressed = false;
  boolean spaceBarPressed = false;

  Frame() {
    generateAliens();
    panel = new Panel(ship, aliens);
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
          bullet.move(Bullet.DX, Bullet.DY);
          if (bullet.getyPosition() < -50) {
            bulletsToRemove.add(bullet);
          }
        }

        for (Bullet bullet : bulletsToRemove) {
          ship.removeBullet(bullet);
        }

        if (leftKeyPressed) {
          ship.move(Ship.DX * -1, Ship.DY);
        }
        
        if (rightKeyPressed) {
          ship.move(Ship.DX, Ship.DY);
        }

        if (spaceBarPressed) {
          ship.shoot();
        }
      }
    });
    timer.start();

    this.setVisible(true);
  }

  private void generateAliens() {
    for (int i = 0; i < 10; i++) {
      Alien alien = new Alien(i);
      aliens.add(alien);
    }
  }
}
