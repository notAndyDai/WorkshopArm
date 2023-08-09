// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Util;

import edu.wpi.first.wpilibj.Preferences;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final int ARM_MOTOR_ID = 0;
  public static final int LIMIT_SWITCH_PORT = 9;

  public static final int MOTOR_TICKS_PER_REV = 2048;
  public static final double ARM_GEAR_RATIO = 129.26;

  public static final double TICKS_PER_DEGREE = 674.789;
  public static final double ARM_RESET_ANGLE_DEGREES = 57;

  public static final double ARM_DEFAULT_KP = 0;
  public static final double ARM_DEFAULT_KI = 0;
  public static final double ARM_DEFAULT_KD = 0;

  public static final double HOMING_POWER = -0.3;

  public static double ARM_KP_TELEMETRY = ARM_DEFAULT_KP;
  public static double ARM_KI_TELEMETRY = ARM_DEFAULT_KI;
  public static double ARM_KD_TELEMETRY = ARM_DEFAULT_KD;

  // p: 0.086 i: 0.00001

  public static void initTelemetry() {
    // PID constants
    if (!Preferences.containsKey("KP")) {
      Preferences.initDouble("KP", ARM_KP_TELEMETRY);
    }

    if (!Preferences.containsKey("KI")) {
      Preferences.initDouble("KI", ARM_KI_TELEMETRY);
    }

    if (!Preferences.containsKey("KD")) {
      Preferences.initDouble("KD", ARM_KD_TELEMETRY);
    }
  }

  public static void updateTelemetry() {
    ARM_KP_TELEMETRY = Preferences.getDouble("KP", ARM_DEFAULT_KP);
    ARM_KI_TELEMETRY = Preferences.getDouble("KI", ARM_DEFAULT_KI);
    ARM_KD_TELEMETRY = Preferences.getDouble("KD", ARM_DEFAULT_KD);
  }

}
