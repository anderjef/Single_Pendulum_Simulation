import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Single_Pendulum_Simulation extends PApplet {

//Jeffrey Andersen

float gravityMagnitude = random(2, 8);
float frictionMultiplier = 0.9999999f;

Pendulum p;

public void setup() {
  
  float maxBobRadius = sqrt(width * width + height * height) / 8;
  float actualBobRadius = random(maxBobRadius / 4, maxBobRadius);
  p = new Pendulum(random(TWO_PI), random(actualBobRadius, height / 2 - actualBobRadius), width / 2, height / 2, actualBobRadius, gravityMagnitude);
  stroke(255);
  strokeWeight(8);
  fill(127);
}

public void draw() {
  background(0);
  if (mousePressed) {
    p.armAngle = atan2(mouseY - height / 2, mouseX - width / 2) + PI / 2;
    p.armLength = sqrt(pow(mouseX - width / 2, 2) + pow(mouseY - height / 2, 2));
    p.bobPos = new PVector(mouseX, mouseY);
    p.angularVelocity = 0;
    p.angularVelocityMultiplier = 1; 
  }
  else {
    p.update(frictionMultiplier);
  }
  p.show();
}
class Pendulum {
  float armAngle;
  float angularVelocity = 0;
  float angularAcceleration;
  float angularVelocityMultiplier;
  float forceMagnitude;
  float armLength;
  PVector fulcrumPos;
  PVector bobPos;
  float bobRadius;
  
  Pendulum(float _armAngle, float _armLength, float fulcrumX, float fulcrumY, float _bobRadius, float _forceMagnitude) {
    armAngle = _armAngle;
    armLength = _armLength;
    fulcrumPos = new PVector(fulcrumX, fulcrumY);
    bobPos = new PVector(fulcrumPos.x + armLength * sin(armAngle), fulcrumPos.y + armLength * -cos(armAngle));
    bobRadius = _bobRadius;
    forceMagnitude = _forceMagnitude; //future consideration: make dependent on bobRadius
    angularVelocityMultiplier = 1;
  }
  
  public void update(float frictionMultiplier) {
    angularAcceleration = forceMagnitude * sin(armAngle) / armLength;
    angularVelocity += angularAcceleration;
    angularVelocity *= angularVelocityMultiplier;
    armAngle += angularVelocity;
    bobPos.x = fulcrumPos.x + armLength * sin(armAngle);
    bobPos.y = fulcrumPos.y + armLength * -cos(armAngle);
    angularVelocityMultiplier *= frictionMultiplier;
  }
  
  public void show() {
    line(fulcrumPos.x, fulcrumPos.y, fulcrumPos.x + armLength * sin(armAngle), fulcrumPos.y + armLength * -cos(armAngle));
    circle(bobPos.x, bobPos.y, bobRadius);
  }
}
  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Single_Pendulum_Simulation" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
