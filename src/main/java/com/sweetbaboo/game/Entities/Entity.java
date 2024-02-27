package com.sweetbaboo.game.Entities;

import java.awt.Image;

public abstract class Entity {
  private Image image;
  private int width;
  private int height;
  private int xPosition;
  private int yPosition;
  
  public Entity(Image image, int xPosition, int yPosition) {
    this.image = image;
    this.width = image.getWidth(null);
    this.height = image.getHeight(null);
    this.xPosition = xPosition;
    this.yPosition = yPosition;
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
}
