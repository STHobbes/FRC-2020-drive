package frc.robot;

/**
 * This is the implementation of a ramp in-out function for controlling something in the range 0.0-1.0 that should start at
 * some minimum (>=0.0) value, ramp up to some maximum (<=1.0) over a specified acceleration distance, continue at that
 * maximum speed, and then decelerate to some minimum (>=0.0) until the target is reached.
 */
public class RampInOut {

  double m_pathStart;
  double m_pathEnd;
  double m_maxValue;
  double m_accelerationMin;
  double m_pathAcceleration;
  double m_decelerationMin;
  double m_pathDeceleration;

  /**
   * @param pathStart        (double) The start position on the path. This is typically encoder tics, potentiometer position, degrees,
   *                         or some other measure of position on a path
   * @param pathEnd          (double) The start position on the path. This is typically encoder tics, potentiometer position, degrees,
   *                         or some other measure of position on a path
   * @param maxValue         (double) The maximum value that will be returned by this function in the range 0.0 to 1.0.
   * @param accelerationMin  (double) The value that should be returned when the position is {@code pathStart}, in
   *                         the range 0.0 to {@code maxValue}.
   * @param pathAcceleration (double) The distance along the path through which the value should accelerate from
   *                         {@code accelerationMin} to 1.0. If ({@code maxValue} < 1.0), then acceleration will stop when
   *                         {@code maxValue} is reached.
   * @param decelerationMin  (double) The value that should be returned as the position approaches is {@code pathEnd}, in
   *                         the range 0.0 to {@code maxValue}.
   * @param pathDeceleration (double) The distance along the path through which the value should decelerate from
   *                         1.0 to {@code decelerationMin}. If ({@code maxValue} < 1.0), then deceleration will start
   *                         when the deceleration value reaches {@code maxValue}.
   */
  public RampInOut(double pathStart, double pathEnd, double maxValue,
                   double accelerationMin, double pathAcceleration, double decelerationMin, double pathDeceleration) {
    m_pathStart = pathStart;
    m_pathEnd = pathEnd;
    m_maxValue = maxValue;
    m_accelerationMin = accelerationMin;
    m_pathAcceleration = pathAcceleration;
    m_decelerationMin = decelerationMin;
    m_pathDeceleration = pathDeceleration;
  }

  /**
   * Compute the value (in the range 0.0 to 1.0) for the value along the path.
   *
   * @param pathCurrent (double) The current position relative to the {@code pathStart} and
   *                    {@code pathEnd} positions.
   * @return (double) Returns the value of the ramp in-out function at the specified position on the path.
   */
  private double getValueAtPosition(double pathCurrent) {
    if (m_pathStart < m_pathEnd) {
      // looking for the
      if (pathCurrent <= m_pathStart) {
        // behind the start, use the minimum
        return m_accelerationMin;
      }
      if (pathCurrent >= m_pathEnd) {
        // past the end, STOP.
        return 0.0;
      }
      double pathValue = m_maxValue;
      if (pathCurrent <= (m_pathStart + m_pathAcceleration)) {
        // In the ramp up zone
        double accelValue = m_accelerationMin + ((1 - m_accelerationMin) * ((pathCurrent - m_pathStart) / m_pathAcceleration));
        if (accelValue < pathValue) {
          pathValue = accelValue;
        }
      }
      if (pathCurrent > (m_pathEnd - m_pathDeceleration)) {
        // In the ramp down zone
        double decelValue = m_decelerationMin + ((1 - m_decelerationMin) * ((m_pathEnd - pathCurrent) / m_pathAcceleration));
        if (decelValue < pathValue) {
          pathValue = decelValue;
        }
      }
      return pathValue;
    } else if (m_pathStart > m_pathEnd) {
      if (pathCurrent >= m_pathStart) {
        // behind the start, use the minimum
        return m_accelerationMin;
      }
      if (pathCurrent <= m_pathEnd) {
        // past the end, STOP.
        return 0.0;
      }
      double pathValue = m_maxValue;
      if (pathCurrent >= (m_pathStart - m_pathAcceleration)) {
        // In the ramp up zone
        double accelValue = m_accelerationMin + ((1 - m_accelerationMin) * ((m_pathStart - pathCurrent) / m_pathAcceleration));
        if (accelValue < pathValue) {
          pathValue = accelValue;
        }
      }
      if (pathCurrent < (m_pathEnd + m_pathDeceleration)) {
        // In the ramp down zone
        double decelValue = m_decelerationMin + ((1 - m_decelerationMin) * ((pathCurrent - m_pathEnd) / m_pathAcceleration));
        if (decelValue < pathValue) {
          pathValue = decelValue;
        }
      }
      return pathValue;
    }
    return 0.0;
  }
}
