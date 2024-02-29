package com.sweetbaboo.game.Entities;

import java.awt.Image;

import com.sweetbaboo.game.Collisions.HitBox;

public abstract class Entity {
  private Image image;
  private int width;
  private int height;
  private int xPosition;
  private int yPosition;
  private int scaledWidth;
  private int scaledHeight;

  public Entity(Image image, int xPosition, int yPosition, int scale) {
    this.image = image;
    this.width = image.getWidth(null);
    this.height = image.getHeight(null);
    this.scaledHeight = height / scale;
    this.scaledWidth = width / scale;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }

  public HitBox getHitBox() {
    int x = getxPosition();
    int y = getyPosition();
    return new HitBox(x, y, getScaledWidth(), getScaledHeight());
  }

  public void move(int dx, int dy) {
    setxPosition(xPosition + dx);
    setyPosition(yPosition + dy);
  }

  public void centerOnPoint() {
    setCenterX(xPosition);
    setCenterY(yPosition);
  }

  public int getCenterX() {
    return xPosition + scaledWidth / 2;
  }
  
  public int getCenterY() {
    return yPosition + scaledHeight / 2;
  }

  public void setCenterX(int x) {
    xPosition = x - scaledWidth / 2;
  }
  
  public void setCenterY(int y) {
    yPosition = y - scaledHeight / 2;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getxPosition() {
    return xPosition;
  }

  public void setxPosition(int xPosition) {
    this.xPosition = xPosition;
  }

  public int getyPosition() {
    return yPosition;
  }

  public void setyPosition(int yPosition) {
    this.yPosition = yPosition;
  }

  public int getScaledWidth() {
    return scaledWidth;
  }

  public int getScaledHeight() {
    return scaledHeight;
  }
}
