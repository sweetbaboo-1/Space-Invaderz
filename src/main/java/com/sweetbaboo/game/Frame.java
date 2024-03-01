package com.sweetbaboo.game;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.sweetbaboo.game.Entities.Aliens.Alien;
import com.sweetbaboo.game.Entities.Bullets.Bullet;
import com.sweetbaboo.game.Entities.Ship.Ship;

public class Frame extends JFrame {


  public static final int ALIEN_COLUMNS = 10;
  public static final int ALIEN_ROWS = 4;
  public static final int ALIEN_GAP_BETWEEN_ROWS = 25;
  public static final int ALIEN_GAP_FROM_TOP = 50;

Panel panel;
  Ship ship = new Ship();
  List<Alien> aliens = new ArrayList<>();

  boolean leftKeyPressed = false;
  boolean rightKeyPressed = false;
  boolean spaceBarPressed = false;

  Frame() {
    generateAliens(ALIEN_COLUMNS, ALIEN_ROWS);
    ship.centerOnPoint();
    panel = new Panel(ship, aliens);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Space Invaderz");
    this.setIconImage(ship.getImage());
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

    Timer timer = new Timer(Main.FRAME_DELAY, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        List<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : ship.getBullets()) {
          bullet.move(Bullet.DX, Bullet.DY);
          if (bullet.getyPosition() < -100) {
            bulletsToRemove.add(bullet);
          }
        }

        ship.getBullets().removeAll(bulletsToRemove);

        if (leftKeyPressed) {
          ship.move(-Ship.DX, Ship.DY);
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

  private void generateAliens(int n, int rows) {
    // aliens should cover the center 2/3 (4/6) of the screen
    // leaving 1/6 of the display on each side clean
    int oneSixthScreenWidth = Main.SCREEN_WIDTH / 6;

    // the offset between each alien
    int offset = (oneSixthScreenWidth * 4) / (n + 1);

    for (int r = 0; r < rows; r++) {
      for (int i = 1; i <= n; i++) {
        Alien alien = new Alien(offset * i + oneSixthScreenWidth + 4); // not sure why but aliens draw 4px to the left of center.
        alien.centerOnPoint();
        alien.setCenterY(ALIEN_GAP_FROM_TOP + r * (alien.getScaledHeight() + ALIEN_GAP_BETWEEN_ROWS));
        aliens.add(alien);
      }
    }
  }
}
