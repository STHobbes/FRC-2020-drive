package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Support for the NavX navigation board
 */
public class NavX {

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

  public class NavInfo {
    /**
     * The pitch (lean forward or backward) of the robot from when the robot was
     * first initialized,, measured in degrees.
     */
    public final double pitch;
    /**
     * The yaw (rotation or turn) of the robot with positive being clockwise (to the right) from when the robot was
     * first initialized, measured in degrees.
     */
    public final double yaw;
    /**
     * The roll (lean sideways) of the robot with positive being the robot falling over on it's right side from when the robot was
     * first initialized, measured in degrees.
     */
    public final double roll;

    NavInfo(double pitch, double yaw, double roll) {
      this.pitch = pitch;
      this.yaw = yaw;
      this.roll = roll;
    }
  }

  // ===============================================================================================================================
  // Dealing wth the idea that this is a singleton
  // -------------------------------------------------------------------------------------------------------------------------------
  private static NavX s_instance = null;

  public static synchronized NavX getInstance() {
    if (null == s_instance) {
      s_instance = new NavX();
    }
    return s_instance;
  }

  // ===============================================================================================================================
  // -------------------------------------------------------------------------------------------------------------------------------
  private AHRS m_ahrs;
  private double m_expectedHeading;
  private double m_updateCt;
  private double m_headingRawLast;
  private int m_headingRevs = 0;

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
  }

  /**
   * Sets the reference start heading and navigation reference positions to the current values. This should be called immediately
   * at the start of autonomous.
   */
  public void initializeHeadingAndNav() {

  }

  /**
   * @param setExpectedToCurrent (boolean) {@code true} if the expected heading should be set to the current
   *                             heading, {@code false} otherwise. This would normally be {@code true} during driving
   *                             when the driver is turning (the expected heading is where the driver is turning to). This
   *                             would normally be {@code false} during autonomous when th program is setting a target
   *                             heading and the robot is the expected to move along, or turn towards, the expected
   *                             heading.
   * @return Returns the heading info, returns {@code null} if there is a problem with the NavX.
   */
  public HeadingInfo getHeadingInfo(boolean setExpectedToCurrent) {
    if (null == m_ahrs) {
      return null;
    }
    double updateCt = m_ahrs.getUpdateCount();
    if (updateCt <= m_updateCt) {
      // there is a problem communication with the NavX - the results we would get from NavX queries are unreliable.
      return null;
    }
    return new HeadingInfo(0.0, m_expectedHeading, setExpectedToCurrent);
  }

  /**
   * @return Returns the navigation info, returns {@code null} if there is a problem with the NavX.
   */
  public NavInfo getNavInfo() {
    if (null == m_ahrs) {
      return null;
    }
    return new NavInfo(m_ahrs.getPitch(), m_ahrs.getYaw(), m_ahrs.getRoll());
  }

  /**
   * @param expectedHeading (double) The new expected heading
   */
  public void setExpectedHeading(double expectedHeading) {

  }
}
