package frc.robot;

import frc.robot.loops.ArmLoop;

public class RobotContainer {
    private ArmLoop armLoop;

    public RobotContainer() {
        armLoop = ArmLoop.getInstance();
    }

    public void initAuto() {
        armLoop.setPID();
        armLoop.setBrake();
    }

    public void initTeleop() {
        armLoop.setPID();
        armLoop.setBrake();
    }

    public void initDisabled() {
        armLoop.setCoast();
    }
}
