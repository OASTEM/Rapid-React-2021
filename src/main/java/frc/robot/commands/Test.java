// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Test extends CommandBase {
  /** Creates a new Test. */
  //Timer timer;
  DriveTrain driveTrain;
  double goal;
  double error;

  public Test(DriveTrain driveTrain) {
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    goal = 95000;
    //timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Test has been initiated");
    //timer.reset();
    driveTrain.resetEncoders();
    //timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = goal-driveTrain.getLeftEncoderCount();
    driveTrain.arcadeDrive(0, (-error/goal)*.4);
    System.out.println(driveTrain.getLeftEncoderCount());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return timer.get() > 1;
    return false;
    //return driveTrain.getLeftEncoderCount() > goal;
  }
}
