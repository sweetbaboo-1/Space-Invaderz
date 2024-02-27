package com.sweetbaboo.game.Entities.Aliens;

import javax.swing.ImageIcon;

import com.sweetbaboo.game.Main;
import com.sweetbaboo.game.Entities.Entity;

public class Alien extends Entity{
  private int increment = 10;

  public Alien() {
    super(new ImageIcon("src\\resources\\images\\aliens\\greenAlien.png").getImage(), 1920 / 2, 150);    
  }

  // this will probably get removed when levels are created.
  public void move() {
    if (this.getxPosition() > Main.SCREEN_WIDTH - this.getWidth() / 10) {
      increment *= -1;
    } else if (this.getxPosition() < 0) {
      increment *= -1;
    }
    setxPosition(getxPosition() + increment);
  }
}
