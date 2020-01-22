# 6831 - A05 Annex - FRC 2020 - Driver Control Tuning

This branch has 1 goal - letting drivers tune control for best drivability. Tuning involves one *driver* with a 3
axis joystick, and buttons on the joystick to alter the drive
tuning parameters. At the end of a session the the *best drive* parameters are recorded
and the software is modified to start with those settings. **REMEMBER TO RECORD YOUR FAVORITE SETTINGS SO WE
CAN UPDATE THE PROGRAMMED DEFAULTS FOR THE COMPETITION SOFTWARE, DIFFERENT DRIVERS CAN HAVE DIFFERENT SETTINGS.**

## Drive Tuning Controls
We use the main joystick for driving. All other buttons are mapped to changing drive parameters, so a single
driver can control everything from one stick.

The current control mappings:
* joystick (driver)
  * driving:  
    - joystick Y to forward-backward speed
    - joystick twist to turn speed
    - joystick thumb button - ignores twist. This is useful when setting bias.
  * shifting: - there is not shifting yet.
  * bias: - the wing thing under the stick. Adds a factor for machanical/electrical bias of the robot to naturally turn to
    one side. `turn bias:` in the display - range: -0.25 to 0.25 where 0.0 means no correction and assumes equal left and
    right power will make the robot go straight. ***NOTE: Please sent this to 0 before you start driving the robot!!!***
  * button 3 & 5 - speed gain - adjusts the maximum speed. `speed gain:` in the parameter display - range: 0.1 (almost no
    forward speed at full stick) to 1.0 (fastest speed possible at full stick).
  * button 4 & 6 - turn gain - adjusts the maximum turn rate when the robot has no forward/backward
    speed. `turn gain:` in the parameter display - range: 0.1 (almost no turn at full twist) to 1.0 (fastest turn
    possible at full twist).
  * button 7 & 8 - turn gain at speed - adjusts the maximum turn rate at full speed. `tas gain:` in
    the parameter display - range: 0.05 (almost no turn at full stick and full twist) to 0.5 (faster than reasonable
    turn at full stick and full twist). The intent here is to make full twist a control option that will give you the
    maximum reasonable turn for the speed you are travelling (so we don't flip the robot on its side.
  * button 9 & 10 - sensitivity at center. give you more sensitivity at slow speeds. `sensitivity:` in the
    display - range: 1.0 (linear stick position to speed) to 3.0 (way more sensitivity at center).
  * button 11 & 12 - drive dead band - how much the stick can move/twist before power is applied. This is helpful in
    making sure pushing a button does not also result in unwanted movement. `dead-band:` in the display - range: 0
    (no deadband) to 0.1 (need to move
    10% of the stick/twist range before power is applied)
