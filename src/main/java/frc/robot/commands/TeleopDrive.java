package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.DrivetrainSubsystem;

public class TeleopDrive extends CommandBase {
    private final DrivetrainSubsystem drive;
    private final DoubleSupplier throttle;
    private final DoubleSupplier turn;

    public TeleopDrive(DrivetrainSubsystem drive, DoubleSupplier throttle, DoubleSupplier turn) {
        this.drive = drive;
        this.throttle = throttle;
        this.turn = turn;

        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.curvatureDrive(throttle.getAsDouble(), turn.getAsDouble());
    }
}
