//Jeffrey Andersen

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
  
  void update(float frictionMultiplier) {
    angularAcceleration = forceMagnitude * sin(armAngle) / armLength;
    angularVelocity += angularAcceleration;
    angularVelocity *= angularVelocityMultiplier;
    armAngle += angularVelocity;
    bobPos.x = fulcrumPos.x + armLength * sin(armAngle);
    bobPos.y = fulcrumPos.y + armLength * -cos(armAngle);
    angularVelocityMultiplier *= frictionMultiplier;
  }
  
  void show() {
    line(fulcrumPos.x, fulcrumPos.y, fulcrumPos.x + armLength * sin(armAngle), fulcrumPos.y + armLength * -cos(armAngle));
    circle(bobPos.x, bobPos.y, bobRadius * 2);
  }
}
