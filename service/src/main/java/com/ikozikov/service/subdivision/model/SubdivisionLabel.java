package com.ikozikov.service.subdivision.model;

public enum SubdivisionLabel {
  WHITE("white"),
  BLACK("black"),
  RED("red"),
  GREEN("green"),
  YELLOW("yellow"),
  PURPLE("purple"),
  PINK("pink"),
  ORANGE("orange"),
  GREY("grey"),
  MAROON("maroon"),
  NAVY("navy");

  private final String label;

  SubdivisionLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
