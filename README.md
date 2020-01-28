# 6831 - A05 Annex - FRC 2020 - Driver Control Tuning

This branch has 1 goal - letting drivers tune control for best drivability. Tuning involves one *driver* with a 3
axis joystick, and buttons on the joystick to alter the drive
tuning parameters. At the end of a session the the *best drive* parameters are recorded
and the software is modified to start with those settings. **REMEMBER TO RECORD YOUR FAVORITE SETTINGS SO WE
CAN UPDATE THE PROGRAMMED DEFAULTS FOR THE COMPETITION SOFTWARE, DIFFERENT DRIVERS CAN HAVE DIFFERENT SETTINGS.**

## Using this Program

This program helps a driver set robot response to the control stick motions to match his/her expected response to
those control motions. This response profile is saved, and can be instantly loaded into the competition robot so
the driver always gets the expected response from the robot. The basic thing is that you drive this program and tune
settings until you feel most comfortable driving the robot - write you idea settings down - and then we make this your
driving profile for competition.

When the program is first loaded and initialized, there is a reasonable set of defaults for most drivers (i.e. the
behaviour of the robot will not be too stupid). We think this is the best strategy for tuning (see the next section
for the mapping between drive parameters and the controls that modify them):
* Use stick twist or stick X (side-to-side) for turn - depends on the games you play and controllers you use. Try
  them both
  and pick the one you like best for the rest of the tuning.
* Start with speed gain, turn gain, and drive at speed gain (all on the) little hat (POV control) on the top of
  the control stick.
  * **Speed Gain** is, by default, set to 1.0 resulting in the fastest speed the robot can achieve when the stick
    is full forward or full back.
  * **Turn Gain** is, by default, set to 0.5 resulting in about half the fastest turn rate possible when you are
    turning without forward motion. The robot can spin really fast - tune this to what you are comfortable with.
  * **Turn at Speed Gain** is, by default, set to 0.1. This is the amount of the maximum possible turn rate that you
    can get with the robot running full speed. Test by going straight at full speed and  the applying maximum turn
    while you are still going full speed. If the robot turns, skids, and falls over; the gain is too high. If the
    robot does  not turn as much as you expect, the gain is too low.
* Next, adjust **sensitivity**. Higher sensitivity means you have more fine control around the centered stick.
* Next, adjust **deadband**. A higher deadband means you can have a little more unintended pressure on
  the stick before the robot reacts. For example, if you want to mve full speed ahead and you have no deadband,
  then you will constantly adjusting for little turn signals you have inadvertently applied. A little deadband
  filters out those unintended turns.
  
**PLEASE REMEMBER TO RECORD YOUR IDEAL SETTINGS AND REPORT THEM TO THE PROGRAMMING TEAM SO YOUR PROFILE CAN BE UPDATED.**
    
## Drive Tuning Controls

We use the main joystick for driving. All other buttons are mapped to changing drive parameters, so a single
driver can control everything from one stick.

The current control mappings:
* joystick (driver)
  * **driving:** 
    - joystick Y (forward backward)to forward-backward speed
    - joystick twist or X (left-right) to turn speed. Look ad the dashboard to see if twist is enabled.
    - joystick thumb button - ignores turn. This is useful when testing whether the robot moves straight when there is
      no turn requested.
  * **shifting:** - there is not shifting yet.
  * **turn by twist or side-to-side:** - The trigger toggles between turn by twist or side-to-side. 
  * **speed gain:** - POV(hat) forward/backward - adjusts the maximum speed. `speed gain:` in
    the parameter display - range: 0.1 (almost no forward speed at full stick) to 1.0 (fastest speed
    possible at full stick).
  * **turn gain:** - POV(hat) right/left - adjusts the maximum turn rate when the robot has no forward/backward
    speed. `turn gain:` in the parameter display - range: 0.1 (almost no turn at full twist) to 1.0 (fastest turn
    possible at full twist).
  * **turn gain at speed**:- POV(hat) 45&deg; forward/45&deg; backward - adjusts the maximum
    turn rate at full speed. `turn@speed gain:` in
    the parameter display - range: 0.05 (almost no turn at full stick and full twist) to 0.5 (faster than reasonable
    turn at full stick and full twist). The intent here is to make full twist a control option that will give you the
    maximum reasonable turn for the speed you are travelling (so we don't flip the robot on its side.
  * **speed sensitivity at center** - buttons 3 & 5 (top of stick to the left) - give you more/less sensitivity at slow
    speeds. `speed senstvty:` in the
    display - range: 1.0 (linear stick position to speed) to 3.0 (way more sensitivity at center).
  * **turn sensitivity at center** - buttons 4 & 6 (top of stick to the left) - give you more/less sensitivity at slow
    turn rates. `turn senstvty:` in the
    display - range: 1.0 (linear stick position to speed) to 3.0 (way more sensitivity at center).
  * **speed dead band** - button 11 & 12 - how much the speed stick can move before power is applied. This is helpful in
    making sa little inadvertent speed movement of the stick does not also result in unwanted
    rotation. `speed deadband:` in the display - range: 0 (no deadband) to 0.1 (need to move
    10% of the stick/twist range before power is applied)
  * **turn dead band** - button 9 & 10 - drive dead band - how much the stick can move/twist before power is applied. This
    is helpful in making sure pushing a button does not also result in unwanted movement. `turn deadband:` in the
    display - range: 0 (no deadband) to 0.1 (need to move 10% of the stick/twist range before power is applied)
