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
   * @param accelerationMin  (double) The value that should be returned when the initial position is {@code pathStart}, in
   *                         the range 0.0 to {@code maxValue}.
   * @param pathAcceleration (double) The distance along the path through which the value should accelerate from
   *                         {@code accelerationMin} to 1.0. If ({@code maxValue} < 1.0), then acceleration will stop when
   *                         {@code maxValue} is reached.
   * @param decelerationMin  (double)
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
//      if (currentTics <= 0) {
//        return accelMin;
//      }
//      if (currentTics >= targetTics) {
//        return 0.0;
//      }
//      double mtrPower = maxPower;
//      if (currentTics < accelTics) {
//        double accelPower = accelMin + ((1 - accelMin) * (currentTics / accelTics));
//        if (accelPower < mtrPower) {
//          mtrPower = accelPower;
//        }
//      }
//      if (currentTics > targetTics - decelTics) {
//        double decelPower = decelMin + ((1 - decelMin) * ((targetTics - currentTics) / decelTics));
//        if (decelPower < mtrPower) {
//          mtrPower = decelPower;
//        }
//      }
//      return mtrPower;
      return 0.0;
    } else if (m_pathStart > m_pathEnd) {
      return 0.0;
    }
    return 0.0;
  }
}
