// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private TalonFX frontR;
  private TalonFX frontL;
  private TalonFX backR;
  private TalonFX backL;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontR = new TalonFX(Constants.FRONT_RIGHT_ID);
    frontL = new TalonFX(Constants.FRONT_LEFT_ID);
    backR = new TalonFX(Constants.BACK_RIGHT_ID);
    backL = new TalonFX(Constants.BACK_LEFT_ID);

    frontL.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    frontR.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);

    backL.follow(frontL);
    backR.follow(frontR);
    frontL.setInverted(true);
    backL.setInverted(true);
    frontR.setInverted(false);
    backR.setInverted(false);

    frontL.configPeakOutputForward(1);
    frontL.configPeakOutputReverse(-1);
    frontR.configPeakOutputForward(1);
    frontR.configPeakOutputReverse(-1);

    setLeftPID(Constants.SLOT_ID, Constants.kP, Constants.kI, Constants.kD); //make into constants
    setRightPID(Constants.SLOT_ID, Constants.kP, Constants.kI, Constants.kD);
  
    frontL.configMotionCruiseVelocity(Constants.CRUISE_VELOCITY, 0);
    frontL.configMotionAcceleration(Constants.ACCELERATION, 0);
    frontR.configMotionCruiseVelocity(Constants.CRUISE_VELOCITY, 0);
    frontR.configMotionAcceleration(Constants.ACCELERATION, 0);

    resetEncoders();
  }

  public void arcadeDrive(double x, double y) {
    frontL.set(ControlMode.PercentOutput, y-x);
    frontR.set(ControlMode.PercentOutput, y+x);
  }

  //for testing purposes
  public void tankDrive(double left, double right) {
    frontL.set(ControlMode.PercentOutput, left);
    frontR.set(ControlMode.PercentOutput, right);
  }

  public void stop(){
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }

  public double getLeftEncoderCount() {
    return frontL.getSensorCollection().getIntegratedSensorPosition();
  }

  public double getRightEncoderCount() {
    return frontR.getSensorCollection().getIntegratedSensorPosition();
  }

  public void resetEncoders() {
    frontL.getSensorCollection().setIntegratedSensorPosition(0, 0);
    frontR.getSensorCollection().setIntegratedSensorPosition(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // System.out.println(getLeftEncoderCount());
    // System.out.println(getRightEncoderCount());
  }

  public void setPosition(double pos) {
    // frontL.set(ControlMode.Position, pos);
    // frontR.set(ControlMode.Position, pos);
    frontL.set(TalonFXControlMode.MotionMagic, pos);
    frontR.set(TalonFXControlMode.MotionMagic, pos);
  }

  public void setLeftPID(int slotID, double p, double i, double d){
    frontL.selectProfileSlot(0, 0);
    frontL.config_kP(slotID, p);
    frontL.config_kI(slotID, i);
    frontL.config_kD(slotID, d);
  }

  public void setRightPID(int slotID, double p, double i, double d){
    frontR.selectProfileSlot(0, 0);
    frontR.config_kP(slotID, p);
    frontR.config_kI(slotID, i);
    frontR.config_kD(slotID, d);
  }
}
