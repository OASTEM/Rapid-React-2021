// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.CargoManipulation;
import frc.robot.commands.ChangeDriveMode;
import frc.robot.commands.ChangeRegularMode;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final Shooter shooter = new Shooter();
  private final Intake intake = new Intake();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final LogitechGamingPad drivePad = new LogitechGamingPad(0);
  private final Climber climber = new Climber();
  private final DriveTrain driveTrain = new DriveTrain();

  // private final Intake intake = new Intake();
  // private final JoystickButton buttonX = new JoystickButton(drivePad, 3);
  //private final JoystickButton rightBumper = new JoystickButton(drivePad, 10);
  // private final JoystickButton startButton = new JoystickButton(drivePad, 8);
  // private final JoystickButton buttonY = new JoystickButton(drivePad, 4);
  // private final JoystickButton buttonA = new JoystickButton(drivePad, 1);

  private final JoystickButton buttonB = new JoystickButton(drivePad, 2);
  private final JoystickButton leftBumper = new JoystickButton(drivePad, 5);
  private final JoystickButton rightBumper = new JoystickButton(drivePad, 6);

  // private final JoystickButton buttonB = new JoystickButton(drivePad, 2);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    driveTrain.setDefaultCommand(new ArcadeDrive(driveTrain, drivePad));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // buttonX.whenPressed(new IntakeCargo(intake, false));
    // rightBumper.whileHeld(new CargoManipulation(intake, shooter, true));
    // leftBumper.whileHeld(new CargoManipulation(intake, shooter, false));

    // buttonY.whileHeld(new ClimbUp(climber));
    // buttonA.whileHeld(new ClimbDown(climber));

    // buttonA.whenPressed(new ArcadeDrive(driveTrain, drivePad));
    leftBumper.whenPressed(new ChangeDriveMode(driveTrain));
    rightBumper.whenPressed(new ChangeRegularMode(driveTrain));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand; //in inches
    //return new TurnToAngle(driveTrain, navX, 90);
  }
}
