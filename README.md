# A05annex - 6831 - Competition Code for FRC 2020

***If you want to work on a feature, fork this and once it's finished and
tested, ask Aden or Henry to merge it.***

## Which Robot, Which Driver

This section describes how we can tune the code to different robots (i.e. tune to the differences between the *practice* and
*competition* robots); to different drivers (programmers, builder, and drivers): and how we set the robot and driver
at competition. 

### Robot Tuning

Every robot is a little bit different - we need to calibrate our software to the robot.

### Driver Tuning
Every driver comes with a different set of gaming baggage - we need to calibrate our software to the driver.


## Drive Subsystem Details

This section describes some details about the drive subsystem and how they evolved. The drive system uses right/left encoders
and the [TalonSRX](https://www.ctr-electronics.com/talon-srx.html) PID of the master right/left controllers for speed control
(see [Motor Controllers Closed Loop](https://phoenix-documentation.readthedocs.io/en/latest/ch16_ClosedLoop.html#motor-controller-closed-loop)
for how that happens). This give us reasonably (but not perfectly)
straight driving when no turn is applied. A [NavX](https://pdocs.kauailabs.com/navx-mxp/) is used to track robot heading
and correct fine deviation of straight and change in heading for both driver controlled and autonomous move/turn methods.
If the NavX fails (we've had that), we use encoders only for autonomous move/turn.

### Driver Control



#### Arcade by Power

#### Arcade by Speed

### NavX for Inertial Guidance

#### Initialization Failure

#### NavX failure during competition

### Autonomous Driving

#### NavX failure

#### Encoder Failure



