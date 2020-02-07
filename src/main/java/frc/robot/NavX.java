package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Support for the NavX navigation board
 */
public class NavX {

  // ===============================================================================================================================
  // Dealing wth the idea that this is a singleton
  // -------------------------------------------------------------------------------------------------------------------------------
  private static NavX s_instance = null;
  // ===============================================================================================================================
  // -------------------------------------------------------------------------------------------------------------------------------
  private AHRS m_ahrs;
  private double m_expectedHeading = 0.0;
  private double m_updateCt = -1;
  private double m_headingRawLast = 0.0;
  private double m_heading = 0.0;
  private boolean m_setExpectedToCurrent = false;
  private int m_headingRevs = 0;
  private double m_refPitch = 0.0;
  private double m_refYaw = 0.0;
  private double m_refRoll = 0.0;
  private NavX() {
    // So, if there is no navx, there is no error - it just keeps trying to connect forever, so this
    // needs to be on a thread that can be killed if it doesn't connect in time ......
    m_ahrs = new AHRS(SerialPort.Port.kUSB1);
    m_ahrs.reset();
    while (m_ahrs.isCalibrating()) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        break;
      }
    }
    m_updateCt = m_ahrs.getUpdateCount();
    initializeHeadingAndNav();
  }

  public static synchronized NavX getInstance() {
    if (null == s_instance) {
      s_instance = new NavX();
    }
    return s_instance;
  }

  /**
   * Sets the reference start heading and navigation reference positions to the current values. This should be called immediately
   * at the start of autonomous.
   */
  public void initializeHeadingAndNav() {
    m_refPitch = m_ahrs.getPitch();
    m_refYaw = m_ahrs.getYaw();
    m_refRoll = m_ahrs.getRoll();
    m_headingRawLast = 0.0;
    m_expectedHeading = 0.0;
    m_headingRevs = 0;
  }

  /**
   * Recompute the heading as reported by the NavX and adjusted to be always increasing when rotation is clockwise. This
   * heading computation was introduce by Jason Barringer to the FRC 6831 AO5 Annex code base in the 2017 season to make
   * using PID loops to control heading with the IMU easier to write, and more predictable. If there is a discontinuity
   * in the sensor output, this means there needs to be special logic in the PID code to deal with the discontinuity. This
   * handles the discontinuity in a single place where the heading is computed.
   *
   * @param setExpectedToCurrent (boolean) {@code true} if the expected heading should be set to the current
   *                             heading, {@code false} otherwise. This would normally be {@code true} during driving
   *                             when the driver is turning (the expected heading is where the driver is turning to). This
   *                             would normally be {@code false} during autonomous when the program is setting a target
   *                             heading and the robot is the expected to move along, or turn towards, the expected
   *                             heading; or when driving without any turn.
   */
  public void recomputeHeading(boolean setExpectedToCurrent) {
    double heading_raw = m_ahrs.getYaw() - m_refYaw;
    m_setExpectedToCurrent = setExpectedToCurrent;
    // This is the logic for detecting and correcting for the IMU discontinuity at +180degrees and -180degrees.
    if (m_headingRawLast < -150.0 && heading_raw > 0.0) {
      // The previous raw IMU heading was negative and close to the discontinuity, and it is now positive. We have
      // gone through the discontinuity so we decrement the heading revolutions by 1 (we completed a negative
      // revolution). NOTE: the initial check protects from the case that the heading is near 0 and goes continuously
      // through 0, which is not the completion of a revolution.
      m_headingRevs--;
    } else if (m_headingRawLast > 150.0 && heading_raw < 0.0) {
      // The previous raw IMU heading was positive and close to the discontinuity, and it is now negative. We have
      // gone through the discontinuity so we increment the heading revolutions by 1 (we completed a positive
      // revolution). NOTE: the initial check protects from the case that the heading is near 0 and goes continuously
      // through 0, which is not the completion of a revolution.
      m_headingRevs++;
    }
    m_headingRawLast = heading_raw;
    m_heading = (m_headingRevs * 360.0) + heading_raw;
    if (setExpectedToCurrent) {
      m_expectedHeading = m_heading;
    }
  }

  /**
   * @return Returns the heading info, returns {@code null} if there is a problem with the NavX.
   */
  public HeadingInfo getHeadingInfo() {
    if (null == m_ahrs) {
      return null;
    }
    double updateCt = m_ahrs.getUpdateCount();
    if (updateCt <= m_updateCt) {
      // there is a problem communication with the NavX - the results we would get from NavX queries are unreliable.
      return null;
    }
    return new HeadingInfo(m_heading, m_expectedHeading, m_setExpectedToCurrent);
  }

  /**
   * @return Returns the navigation info, returns {@code null} if there is a problem with the NavX.
   */
  public NavInfo getNavInfo() {
    if (null == m_ahrs) {
      return null;
    }
    // The subtraction of the ref values adjusts for the construction bias of not having the NavX perfectly mounted, or
    // there being some bias in the NavX - i.e. the ref represents the value first reported when the reference position is set,
    // see initializeHeadingAndNav().
    return new NavInfo(m_ahrs.getPitch() - m_refPitch, m_ahrs.getYaw() - m_refYaw, m_ahrs.getRoll() - m_refRoll);
  }

  public class HeadingInfo {
    /**
     * The current heading of the robot relative to the position at the last call to {@link NavX#initializeHeadingAndNav()}.
     */
    public final double heading;
    public final double expectedHeading;
    public final boolean isExpectedTrackingCurrent;

    HeadingInfo(double heading, double expectedHeading, boolean isExpectedTrackingCurrent) {
      this.heading = heading;
      this.expectedHeading = expectedHeading;
      this.isExpectedTrackingCurrent = isExpectedTrackingCurrent;
    }
  }

  /**
   * The data class for the 'raw' navigation info from the NavX, corrected by when the reference was last set.
   */
  public class NavInfo {
    /**
     * The pitch (lean forward or backward) of the robot, with negative being forwards, from when the robot was first
     * initialized. Measured in degrees.
     */
    public final double pitch;
    /**
     * The yaw (rotation or turn) of the robot, with positive being clockwise (to the right), from when the robot was
     * first initialized. Measured in degrees.
     */
    public final double yaw;
    /**
     * The roll (lean sideways) of the robot, with positive being the robot falling over on it's left side, from when the
     * robot was first initialized. Measured in degrees.
     */
    public final double roll;

    NavInfo(double pitch, double yaw, double roll) {
      this.pitch = pitch;
      this.yaw = yaw;
      this.roll = roll;
    }
  }
}
