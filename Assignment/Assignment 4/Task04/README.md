# Task 4 – Event handling forensics

Compile and run `DrawPad.java`. Click in the drawing area; the label count should increase and dots should remain after minimizing/restoring because the points are stored and redrawn in `paint()`. The original method name `mouseclicked` is wrong: Java requires `mouseClicked`. With `@Override`, the compiler reports that the method does not override a superclass method.
