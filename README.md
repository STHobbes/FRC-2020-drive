# 6831 - A05 Annex - FRC 2020 - Drive Speed Tuning Using Encoders

This branch is for tuning the PID controllers in the TalonSRX motor controllers so that wem can use speed control to reliably
drive straight forward - specifically, if the right and left drive train are running at the same speed (wheel RPM) the robot
should go straight. When using power control we learned that equal power to left and right drive trains generally does not
result it straight robot motion. This branch tests/tunes drive using the drive encoders and PID built into the Talon SRX
motor controllers.

## Using the Program

Build and load this github code branch and you are ready to tune for robot-specific drive characteristics. This is the
tuning sequence:
* **Put the robot on blocks** - we are doing some static tests:
  * **Verify encoders are working** - push the stick forward. Verify that the wheels for left and right drive are turning
    forward. Verify there is an encoder reading for right and left and that it increases positively with forward stick. Note:
    on the driver console these are the `right encoder` and `left encoder` dashboard values.
  * **Determine maximum speed (RPM)** - and reset `MAX_SPEED` constant in code. Advance the stick to full speed, record the
    left and right maximum speeds and average them. These speeds are with no load on the drive train. Assume a real
    load like 5&percent; when the robot is running, and multiply the max observed speeds (these are `left speed` and
    `right speed` dashboard valus) by perhaps 0.95 and use that as the `MAX_SPEED` (we found 230 to be the best value
    for the practice bot - if you are close to that, just move on to the next step).
  * **tune Kf** - this is the factor that is multiplied with requested speed to set the initial power to the drive. Hold
    the stick at about half speed and adjust Kf until the the actual speed is the same as the target speed (hold the thumb
    button while you do this to remove any turn)
  * **tune the bias**
    * Set Kp to 0.0 - we do not want corrections applied while we are trying to discover the bias of the robot.
    * Use the throttle to set the bias to 0.0
    * Again hold the speed at about half ot maximum speed and adjust the bias so the right and left speeds are the same.
* **take the robot off blocks** - we are now really running the robot.
  * **re-tune Kf** - now that the robot is off the blocks, drive it a little and observe how close the real speed is to
    the requested speed under load. Adjust Kf if necessary (it generally needs to be increased a bit).
  * **re-tune the bias** - If the robot is not driving straight, adjust the bias until it is going as straight as possible.
  * reset Kp to 2.5 and things should be pretty good.

## Encoder Testing Controls