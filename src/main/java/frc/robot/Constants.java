// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final int FRONT_LEFT_ID = 3;
  public static final int FRONT_RIGHT_ID = 1;
  public static final int BACK_LEFT_ID = 2;
  public static final int BACK_RIGHT_ID = 0;

  public static final double SHOOTER_SPEED = -0.6;

  public static final double SLOW_MODE = 0.25;
  public static final double REGULAR_MODE = 0.8;

  public static final int SLOT_ID = 0;
  public static final double kP = 0.1;
  public static final double kI = 0;
  public static final double kD = 0;

  public static final int SHOOTER_LEFT_ID = 6;
  public static final int SHOOTER_RIGHT_ID = 7;

  public static final int CRUISE_VELOCITY = 7000; //max velocity 21500 units per 100ms
  public static final int ACCELERATION = 3500; 

  public static final double ERROR_ANGLE_TOLERANCE = 5;

  public static final int TOP_SPARK = 4;
  public static final int BOTTOM_SPARK = 5;

  public static final double SHOOTER_P = 0.0001;
  public static final double SHOOTER_I = 0;
  public static final double SHOOTER_D = 0;
  public static final double SHOOTER_F = 0.000174;

  public static final int INTAKE_TOP_SPARK = 8;
  public static final int INTAKE_BOTTOM_SPARK = 9;
  public static final double INTAKE_SPEED = 0.4;
  public static final double INTAKE_tSPEED = 1;
  public static final double INTAKE_bSPEED = 0.25;

  public static final double SHOOTER_VELOCITY = -3500;
  public static final double SHOOTER_RPM_TOLERANCE = 50;
  
  //might need to add a driveStraightP vs turnAngleP
  //might also need leftPID vs rightPID bc of motor controllers 
  

}