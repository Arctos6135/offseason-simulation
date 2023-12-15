package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;

public class DrivetrainIOSim extends DrivetrainIO {
    private final DifferentialDrivetrainSim drive = DifferentialDrivetrainSim.createKitbotSim(
        KitbotMotor.kDoubleNEOPerSide, KitbotGearing.k10p71, KitbotWheelSize.kSixInch, null
    );

    @Override
    public void setVoltages(double left, double right) {
        Logger.getInstance().recordOutput("Left Voltage", left);
        Logger.getInstance().recordOutput("Right Voltage", right);

        drive.setInputs(left, right);
    }

    public void resetGyro() {
        Pose2d old = drive.getPose();
        drive.setPose(new Pose2d(old.getX(), old.getY(), new Rotation2d()));
    }

    public void updateInputs(Inputs inputs) {
        inputs.leftEncoderMeters = drive.getLeftPositionMeters();
        inputs.rightEncoderMeters = drive.getRightPositionMeters();
        inputs.gyroYaw = drive.getHeading().getRadians();

        drive.update(0.02);
    }
}
