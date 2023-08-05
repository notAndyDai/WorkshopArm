// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Util.Constants;

public class Arm extends SubsystemBase {

  private WPI_TalonFX pivotMotor;
  private DigitalInput homeSW;

  private PIDController angleController;

  /** Creates a new Arm. */
  protected Arm() {
    pivotMotor = new WPI_TalonFX(0);
    pivotMotor.setSelectedSensorPosition(0);
    pivotMotor.setNeutralMode(NeutralMode.Brake);

    homeSW = new DigitalInput(9);

    angleController = new PIDController(Constants.ARM_KP_TELEMETRY, Constants.ARM_KI_TELEMETRY,
        Constants.ARM_KD_TELEMETRY);
  }

  // Control methods
  protected void setPowerCL(double setpoint) {
    double power = angleController.calculate(getPosition(), setpoint);
    setPowerOL(power);
  }

  protected void setPowerOL(double power) {
    pivotMotor.set(ControlMode.PercentOutput, power);
  }

  public void setCoast() {
    pivotMotor.setNeutralMode(NeutralMode.Coast);
  }

  public void setBrake() {
    pivotMotor.setNeutralMode(NeutralMode.Brake);
  }

  // Sensor Methods

  protected double getPositionRaw() {
    return pivotMotor.getSelectedSensorPosition();
  }

  protected void resetArmPosition(double pos) {
    pivotMotor.setSelectedSensorPosition(angleToTicks(pos));
  }

  protected void resetArmPosition() {
    pivotMotor.setSelectedSensorPosition(angleToTicks(Constants.ARM_RESET_ANGLE_DEGREES));
  }

  protected boolean atHome() {
    return !homeSW.get();
  }

  // calculations

  protected double angleToTicks(double angle) {
    return (Constants.MOTOR_TICKS_PER_REV / 360) * angle * Constants.ARM_GEAR_RATIO;
  }

  protected double ticksToAngle(double ticks) {
    double angle = ticks / ((Constants.MOTOR_TICKS_PER_REV / 360) * Constants.ARM_GEAR_RATIO);
    return angle - 0.124486 * (angle - 57);
  }

  protected double getPosition() {
    return ticksToAngle(getPositionRaw());
  }

  // PID controller config
  public void setPID() {
    Constants.updateTelemetry();
    setPID(Constants.ARM_KP_TELEMETRY, Constants.ARM_KI_TELEMETRY, Constants.ARM_KD_TELEMETRY);
  }

  protected void setPID(double kP, double kI, double kD) {
    angleController.setPID(kP, kI, kD);
  }

}
