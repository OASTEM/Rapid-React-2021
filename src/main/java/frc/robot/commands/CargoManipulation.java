// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import pabeles.concurrency.ConcurrencyOps.Reset;

public class CargoManipulation extends CommandBase {
  private final Intake intake; 
  private Shooter shooter;
  public boolean isIntaking;
  private Timer timer;

  public CargoManipulation(Intake intake, Shooter shooter, boolean isIntaking) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, shooter);
    this.intake = intake;
    this.shooter = shooter;
    this.isIntaking = isIntaking;
  }

  // Called when the command is initially scheduled.
  @Override
  
  public void initialize() {

    if (isIntaking == true) {
      intake.intakeTopMotor(Constants.INTAKE_TOP_SPEED);
      intake.intakeBottomMotor(Constants.INTAKE_BOTTOM_SPEED);
      intake.intakeOutsideMotor(Constants.INTAKE_OUTSIDE_SPEED);
      intake.intakeFrontMotor(Constants.INTAKE_FRONT_SPEED);
      intake.intakeDownMotor(Constants.INTAKE_DOWN_SPEED);
    } else {
      shooter.setVelocity(Constants.SHOOTER_VELOCITY);
    }

  }

  // Called every time the scheduler runs while the command is scheduled
  double error = 0;
  @Override
  public void execute() {
    error = Math.abs(Constants.SHOOTER_VELOCITY-shooter.getLeftVelocity());
    if (isIntaking == false && error<= Constants.SHOOTER_RPM_TOLERANCE) {
      intake.intakeTopMotor(Constants.INTAKE_SPEED*-1);
      intake.intakeBottomMotor(Constants.INTAKE_SPEED);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stop(); 
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
