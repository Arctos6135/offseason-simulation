package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.AutoLog;

public class DrivetrainIO {
    @AutoLog
    public static class Inputs {
        public double leftEncoderMeters;
        public double rightEncoderMeters;
        public double gyroYaw;
    }

    public void setVoltages(double left, double right) {}

    public void resetGyro() {}

    public void updateInputs(Inputs inputs) {}
}
