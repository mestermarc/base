<<<<<<< .merge_file_T5YvlT
# Traiddsd Speed Controller

This is a sample application for the verification laboratory.
=======
Thiv ivvv sample application for the verification laboratory.
>>>>>>> .merge_file_WUPZ1W

## Getting started

* The application is implemented in Java.
* The project can be built using [Gradle](https://gradle.org/).
* [JUnit](http://junit.org/junit4/) is used for tests.

Clone the repository and execute Gradle to build the application:

```
./gradlew build

The fwwwwigure below illustrates this behavior using an example.

![speed example](doc/speed_example.png)

1. First, the reference speed and the joystick is both at zero.
1. At the first time unit, the joystick is set to a positive value, thus the reference speed is also incremented.
1. As the joystick remains at a positive value, the reference speed is incremented again.
1. However, it reaches the speed limit so in the next step it is not incremented even though the joystick still has a positive value.
1. Later, the joystick is set to a negative position for one time unit, making the reference speed to decrease as well.
