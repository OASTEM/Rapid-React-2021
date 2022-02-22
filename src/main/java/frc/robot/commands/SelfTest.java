// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class SelfTest extends CommandBase {
  /** Creates a new SelfTest. */
  DriveTrain driveTrain;
  Climber climber;
  Shooter shooter;
  Intake intake;
  public SelfTest(DriveTrain driveTrain, Climber climber, Shooter shooter, Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain, climber, shooter, intake);
    this.driveTrain = driveTrain;
    this.climber = climber;
    this.shooter = shooter;
    this.intake = intake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // SmartDashboard.putBoolean("Self Test DriveTrain", driveTrain.selfTest());
    // SmartDashboard.putBoolean("Self Test Climber", climber.selfTest());
    // SmartDashboard.putBoolean("Self Test Shooter", shooter.selfTest());
    // SmartDashboard.putBoolean("Self Test Intake", intake.selfTest());
    System.out.println("self test initalized");
    driveTrain.selfTest();
    climber.selfTest();
    shooter.selfTest();
    intake.selfTest();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.selfTestExecute();
    climber.selfTestExecute();
    shooter.selfTestExecute();
    intake.selfTestExecute();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
    climber.stop();
    intake.stop();
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //climber.selfTestGood;
    //return false;
    System.out.println(climber.selfTestGood && driveTrain.selfTestGood && intake.selfTestGood && shooter.selfTestGood);
    return climber.selfTestGood && driveTrain.selfTestGood && intake.selfTestGood && shooter.selfTestGood;
  }
}
