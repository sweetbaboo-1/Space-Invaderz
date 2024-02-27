package com.sweetbaboo.game.Collisions;

import com.sweetbaboo.game.Entities.Aliens.Alien;
import com.sweetbaboo.game.Entities.Bullets.Bullet;
import com.sweetbaboo.game.Entities.Ship.Ship;
import com.sweetbaboo.utils.Pair;
import java.util.List;

public class CollisionChecking {

  public static Pair<Alien, Bullet> checkBulletHit(List<Alien> aliens, List<Bullet> bullets) {
    for (Alien alien : aliens) {
      HitBox alienHitBox = alien.getHitBox();
      for (Bullet bullet : bullets) {
        HitBox bulletHitBox = bullet.getHitBox(); 
        if (hitBoxesIntersect(alienHitBox, bulletHitBox)) {
          return new Pair<>(alien, bullet);
        }
      }
    }
    return null;
  }

  public static boolean checkShipHit(Ship ship, List<Alien> aliens) {
    for (Alien alien : aliens) {
      HitBox alienHitBox = alien.getHitBox();
      if (hitBoxesIntersect(alienHitBox, ship.getHitBox())) {
        return true;
      }
    }
    return false;
  }

  private static boolean hitBoxesIntersect(HitBox hitBox1, HitBox hitBox2) {
    return hitBox1.getX() < hitBox2.getX() + hitBox2.getWidth() &&
        hitBox1.getX() + hitBox1.getWidth() > hitBox2.getX() &&
        hitBox1.getY() < hitBox2.getY() + hitBox2.getHeight() &&
        hitBox1.getY() + hitBox1.getHeight() > hitBox2.getY();
  }
}
