#include <Mouse.h>

void setup() {
     // initialize digital pin LED_BUILTIN as an output.
     pinMode(LED_BUILTIN, OUTPUT);

    // initialize mouse control:
    Mouse.begin();
}

const int MOVE_DELAY = 1000;

void loop() {

  digitalWrite(LED_BUILTIN, HIGH);   // turn the LED on (HIGH is the voltage level)
  delay(100);                       // wait for a second
  digitalWrite(LED_BUILTIN, LOW);    // turn the LED off by making the voltage LOW
  delay(100);                       // wait for a second
  
  //Right
  for (int i = 0; i < 5; i++) {
    delay(MOVE_DELAY);
    Mouse.move(50, 0);
  }

  //Down
  for (int i = 0; i < 5; i++) {
    delay(MOVE_DELAY);
    Mouse.move(0, 50);
  }

  //Left
  for (int i = 0; i < 5; i++) {
    delay(MOVE_DELAY);
    Mouse.move(-50, 0);
  }

  //Up
  for (int i = 0; i < 5; i++) {
    delay(MOVE_DELAY);
    Mouse.move(0, -50);
  }

}