// Where should we hide the body? Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class WaitAutoClimb extends CommandBase {
  private final int timeToWait;
  /** Creates a new WaitAutoClimb. */
  public WaitAutoClimb(int timeToWait) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.timeToWait = timeToWait;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    try {
      Thread.sleep(timeToWait);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      // e.printStackTrace();
      System.out.println("WaitAutoClimb failed. i guess thread.sleep kills stuff");
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
