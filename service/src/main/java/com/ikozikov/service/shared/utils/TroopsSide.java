package com.ikozikov.service.shared.utils;

public enum TroopsSide {
  FRIENDLY("friendly"),
  ENEMY("enemy"),
  NEUTRAL("neutral"),
  UNKNOWN("unknown");


  private final String side;
  
  TroopsSide(String side) {
    this.side = side;
  }

  public String getSide() {
    return side;
  }
  
  
}
