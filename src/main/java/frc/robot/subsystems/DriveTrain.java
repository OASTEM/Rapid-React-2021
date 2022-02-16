// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 

public class DriveTrain extends SubsystemBase {

  private TalonSRX frontL; 
  private TalonSRX frontR; 
  private TalonSRX backL; 
  private TalonSRX backR;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontL = new TalonSRX(Constants.DRIVETRAIN_FRONT_LEFT);
    frontR = new TalonSRX(Constants.DRIVETRAIN_FRONT_RIGHT);
    backL = new TalonSRX(Constants.DRIVETRAIN_BACK_LEFT);
    backR = new TalonSRX(Constants.DRIVETRAIN_BACK_RIGHT);

    backL.follow(frontL);
    backR.follow(frontR);

    frontL.setInverted(true);
    backL.setInverted(true);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    frontL.set(ControlMode.PercentOutput, leftSpeed);
    frontR.set(ControlMode.PercentOutput, rightSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stop() {
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }
}
