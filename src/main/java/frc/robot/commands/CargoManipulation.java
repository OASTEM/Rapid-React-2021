// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;

public class CargoManipulation extends CommandBase {
  private final Intake intake;
  private Shooter shooter;
  public boolean isIntaking;
  public boolean isAuto = false;
  private Timer timer;
  public double shooterVelocity = Constants.SHOOTER_VELOCITY;
  public int pulseShooter;

  public CargoManipulation(Intake intake, Shooter shooter, boolean isIntaking, boolean isAuto) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, shooter);
    this.intake = intake;
    this.shooter = shooter;
    this.isIntaking = isIntaking;
    this.isAuto = isAuto;
    timer = new Timer();
    pulseShooter = 0;
  }

  public CargoManipulation(Intake intake, Shooter shooter, boolean isIntaking, boolean isAuto, double shooterVelocity) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, shooter);
    this.intake = intake;
    this.shooter = shooter;
    this.isIntaking = isIntaking;
    this.isAuto = true;
    timer = new Timer();
    this.shooterVelocity = shooterVelocity;
    pulseShooter = 0;

  }

  // Called when the command is initially scheduled.
  @Override

  public void initialize() {
    if (isAuto) {
      timer.reset();
      timer.start();
    }

    if (isIntaking == true) {
      intake.intakeTopMotor(Constants.INTAKE_TOP_SPEED);
      intake.intakeBottomMotor(Constants.INTAKE_BOTTOM_SPEED);
      intake.intakeOutsideMotor(Constants.INTAKE_OUTSIDE_SPEED);
    } else {
      shooter.setVelocity(shooterVelocity);
    }
    pulseShooter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled
  double error = 0;

  @Override
  public void execute() {
    error = Math.abs(shooterVelocity - shooter.getLeftVelocity());
    if (isIntaking == false && error <= Constants.SHOOTER_RPM_TOLERANCE) {
      pulseShooter++;
      if(pulseShooter < Constants.SHOOTER_PULSE_COUNT){
        intake.intakeTopMotor(Constants.INTAKE_SPEED * -1);
        intake.intakeBottomMotor(Constants.INTAKE_SPEED);
      } else {
        intake.intakeTopMotor(0);
        intake.intakeBottomMotor(0);
      }

      if(pulseShooter > Constants.SHOOTER_PULSE_COUNT*2){
        pulseShooter = 0;
      }
      
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
    if (isAuto && timer.get() > 5) {
      timer.stop();
      return true;
    }
    return false;
  }
}
