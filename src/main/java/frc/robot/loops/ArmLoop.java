// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.loops;

import frc.robot.Util.OI;
import frc.robot.subsystems.Arm;

/** Add your docs here. */
public class ArmLoop extends Arm {

    // Enums ඞඞ
    public enum ArmStates {
        OPEN_LOOP, CLOSED_LOOP, DISABLED
    }

    // Variables ඞ
    private static ArmLoop mInstance;
    private ArmStates mCurrentState;
    private double mSetpoint;

    public static ArmLoop getInstance() {
        if (mInstance == null) {
            mInstance = new ArmLoop();
        }
        return mInstance;
    }

    // constructor ඞඞඞඞ月月月月
    private ArmLoop() {
        mCurrentState = ArmStates.DISABLED;
    }

    public void setState(ArmStates newState) {
        mCurrentState = newState;
    }

    public void setSetpoint(double newSetpoint) {
        mSetpoint = newSetpoint;
    }

    // periodic ඞ
    @Override
    public void periodic() {
        switch (mCurrentState) {
            case OPEN_LOOP:
                setPowerOL(OI.getInstance().getToolLeftY());
                break;

            case CLOSED_LOOP:
                setPowerCL(mSetpoint);
                break;

            case DISABLED:
                setPowerOL(0);
                break;
        }
    }

}
