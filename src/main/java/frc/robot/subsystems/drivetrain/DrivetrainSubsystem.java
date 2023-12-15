package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
    private final DrivetrainIO io;
    private final InputsAutoLogged inputs = new InputsAutoLogged();
    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(new Rotation2d(), 0, 0);

    public DrivetrainSubsystem(DrivetrainIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Drivetrain", inputs);

        odometry.update(Rotation2d.fromRadians(inputs.gyroYaw), inputs.leftEncoderMeters, inputs.rightEncoderMeters);

        Logger.getInstance().recordOutput("Odometry", odometry.getPoseMeters());
    }

    public void curvatureDrive(double throttle, double turn) {
        if (Math.abs(throttle) < 0.1) {
            turn *= throttle;
        }
        
        double left = throttle + turn;
        double right = throttle - turn;

        if (Math.abs(left) > 1) {
            left /= Math.abs(left);
            right /= Math.abs(left);
        } else if (Math.abs(right) > 1) {
            left /= Math.abs(right);
            right /= Math.abs(right);
        }

        tankDrive(left, right);
    }

    public void tankDrive(double left, double right) {
        io.setVoltages(left * 12, right * 12);
    }
}
