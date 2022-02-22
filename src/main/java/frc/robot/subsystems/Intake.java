// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private CANSparkMax topMotor; 
  private CANSparkMax bottomMotor; 
  private RelativeEncoder topEncoder;
  private RelativeEncoder bottomEncoder;
  public boolean selfTestGood = false;

  public Intake() {
    topMotor = new CANSparkMax(Constants.INTAKE_TOP_SPARK, MotorType.kBrushless);
    bottomMotor = new CANSparkMax(Constants.INTAKE_BOTTOM_SPARK, MotorType.kBrushless);
    System.out.println("Intake top motor"+topMotor.toString());
    topEncoder = topMotor.getEncoder();
    bottomEncoder = bottomMotor.getEncoder();
    
  }

  public void intakeTopMotor(double speed){
    topMotor.set(speed);
  }

  public void intakeBottomMotor(double speed){
    bottomMotor.set(speed);
  }

  public void stop(){
    topMotor.set(0);
    bottomMotor.set(0);
  }

  public void selfTest(){
    topMotor.set(Constants.INTAKE_SELFTEST);
    bottomMotor.set(Constants.INTAKE_SELFTEST);
    
  }

  public void selfTestExecute(){
    selfTestGood = (Math.abs(topEncoder.getVelocity()) > 0 && Math.abs(bottomEncoder.getVelocity()) > 0);
    SmartDashboard.putBoolean("Self Test Intake", selfTestGood);
    this.stop();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}