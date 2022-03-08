// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Jevois;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;

public class CargoManipulation extends CommandBase {
  private final Intake intake; 
  private Shooter shooter;
  public boolean isIntaking;
  public Jevois jevois;
  public DriveTrain driveTrain;
  public NavX navX;
  public boolean usingJevois = false;

  public CargoManipulation(Intake intake, Shooter shooter, boolean isIntaking) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, shooter, jevois, navX, driveTrain);
    this.intake = intake;
    this.shooter = shooter;
    this.isIntaking = isIntaking;
  }

  public CargoManipulation(Intake intake, Shooter shooter, boolean isIntaking, Jevois jevois, DriveTrain driveTrain, NavX navX) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, shooter, jevois, navX, driveTrain);
    this.intake = intake;
    this.shooter = shooter;
    this.isIntaking = isIntaking;
    this.jevois = jevois;
    this.driveTrain = driveTrain;
    this.navX = navX;
    usingJevois = true;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    if (isIntaking == true) {
      intake.intakeTopMotor(Constants.INTAKE_TOP_SPEED);
      intake.intakeBottomMotor(Constants.INTAKE_BOTTOM_SPEED);
    } else if (jevois.getY() != -1 && usingJevois){
      double goal = Constants.JEVOIS_FOV_X/Constants.JEVOIS_PX_WIDTH*jevois.getY();
      double errorAngle = goal - navX.getAngle();
      double power = errorAngle / goal;
      driveTrain.tankDrive(power, -power);

      shooter.setVelocity(jevois.getY()*50+3000);
    } else {
      System.out.println("Jevois could not find target");
      shooter.setVelocity(Constants.SHOOTER_VELOCITY);
    }

  }

  // Called every time the scheduler runs while the command is scheduled
  double error = 0;
  @Override
  public void execute() {
    error = Math.abs(Constants.SHOOTER_VELOCITY-shooter.getLeftVelocity());
    if (usingJevois){
      double goal = Constants.JEVOIS_FOV_X/Constants.JEVOIS_PX_WIDTH*jevois.getY();
      double errorAngle = goal - navX.getAngle();
      double power = errorAngle / goal;
      driveTrain.tankDrive(power, -power);
      if (isIntaking == false && error<= Constants.SHOOTER_RPM_TOLERANCE && errorAngle <= Constants.JEVOIS_ERROR_ANGLE_TOLERANCE) {
        intake.intakeTopMotor(Constants.INTAKE_SPEED*-1);
        intake.intakeBottomMotor(Constants.INTAKE_SPEED);
      }
    } else if (isIntaking == false && error<= Constants.SHOOTER_RPM_TOLERANCE) {
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
