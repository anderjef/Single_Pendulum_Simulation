//Jeffrey Andersen

float gravityMagnitude = random(2, 8);
float frictionMultiplier = 0.9999999;

Pendulum p;

void setup() {
  size(800, 800);
  float maxBobRadius = sqrt(width * width + height * height) / 8;
  float actualBobRadius = random(maxBobRadius / 4, maxBobRadius);
  p = new Pendulum(random(TWO_PI), random(actualBobRadius, height / 2 - actualBobRadius), width / 2, height / 2, actualBobRadius, gravityMagnitude);
  stroke(255);
  strokeWeight(8);
  fill(127);
}

void draw() {
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
