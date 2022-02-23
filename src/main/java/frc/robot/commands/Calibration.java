// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class Calibration extends CommandBase {
  /** Creates a new Calibration. */
  Climber climber;
  boolean leftDone;
  boolean rightDone;
  Timer timer;
  public Calibration(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
    this.climber = climber;
    leftDone = false;
    rightDone = false;
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    climber.climb(Constants.CALIBRATION_SPEED); // make constant
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(timer.get()>0.1){
      if(climber.getVelocityLeft()<=0.01){
        leftDone = true;
        climber.climbLeft(0);
      }
      if(climber.getVelocityRight()<=0.01){
        rightDone = true;
        climber.climbRight(0);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    climber.stop();
    climber.resetEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return rightDone && leftDone;
    //return false;
  }




  /*
  -- after all the self test(motor, rio tests, sensors)
  bring the climber down at 0.1 speed
  detect stall when climber is all the way down
  reset encoders
  */
}
