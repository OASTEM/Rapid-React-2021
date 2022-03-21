// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private CANSparkMax topMotor; 
  private CANSparkMax bottomMotor;
  private CANSparkMax outsideMotor;
  private CANSparkMax frontMotor;


  public Intake() {
    topMotor = new CANSparkMax(Constants.INTAKE_TOP_SPARK, MotorType.kBrushless);
    bottomMotor = new CANSparkMax(Constants.INTAKE_BOTTOM_SPARK, MotorType.kBrushless);
    outsideMotor = new CANSparkMax(Constants.INTAKE_OUTSIDE_SPARK, MotorType.kBrushless);
    frontMotor = new CANSparkMax(Constants.INTAKE_FRONT_SPARK, MotorType.kBrushless);
  }

  public void intakeTopMotor(double speed){
    topMotor.set(speed);
  }

  public void intakeBottomMotor(double speed){
    bottomMotor.set(speed);
  }

  public void intakeOutsideMotor(double speed){
    outsideMotor.set(speed);
  }

  public void intakeFrontMotor(double speed){
    outsideMotor.set(speed);
  }

  public void stop(){
    topMotor.set(0);
    bottomMotor.set(0);
    outsideMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}