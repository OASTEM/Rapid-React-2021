// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  /** Creates a new Shoot. */
  private final Shooter shooter;
  public Timer timer; 


  public Shoot(Shooter shooter) { //double timer
    // Use addRequirements() here to declare subsystem dependencies.
    
    addRequirements(shooter);
    this.shooter = shooter;
    timer = new Timer(); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){
    timer.reset();
    timer.set();
    shooter.setSpeed(1); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stop();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    return timer.get() > 0.5; 
  }
}
