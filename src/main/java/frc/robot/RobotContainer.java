package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.drivetrain.DrivetrainIO;
import frc.robot.subsystems.drivetrain.DrivetrainIOSim;
import frc.robot.subsystems.drivetrain.DrivetrainIOSparkMax;
import frc.robot.subsystems.drivetrain.DrivetrainSubsystem;

public class RobotContainer {
  private final Joystick driveJoystick = new Joystick(0);

  private final DrivetrainSubsystem drivetrain;
  
  public RobotContainer() {
    if (Constants.currentMode == Constants.Mode.REAL) {
      drivetrain = new DrivetrainSubsystem(new DrivetrainIOSparkMax());
    } else if (Constants.currentMode == Constants.Mode.SIM) {
      drivetrain = new DrivetrainSubsystem(new DrivetrainIOSim());
    } else {
      drivetrain = new DrivetrainSubsystem(new DrivetrainIO());
    }

    drivetrain.setDefaultCommand(new TeleopDrive(drivetrain, driveJoystick::getY, driveJoystick::getX));

    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
