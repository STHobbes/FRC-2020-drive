package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Support for the NavX navigation board
 */
public class NavX {

  static AHRS ahrs;
  private static boolean s_alreadyRun = false;
  private static NavX s_instance = null;

  private NavX() {
    // So, if there is no navx, there is no error - it just keeps retrying forever, so this
    // needs to be on a thread that can be killed if it doesn't connect in time ......
    ahrs = new AHRS(SerialPort.Port.kUSB1);
    ahrs.reset();
    while (ahrs.isCalibrating()) {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        break;
      }
    }
  }

  public static synchronized NavX getInstance() {
    if (!s_alreadyRun) {
      s_alreadyRun = true;
      s_instance = new NavX();
    }
    return s_instance;
  }
}
