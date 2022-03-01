// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class ColorSorter extends SubsystemBase {
  private final Color blueBall = ColorMatch.makeColor(0.185, 0.420, 0.395); 
  private final Color redBall = ColorMatch.makeColor(0.627, 0.310, 0.063);
  /** Creates a new ColorSorter. */
  public ColorSorter() {
    m_colorMatcher.addColorMatch(blueBall);
    m_colorMatcher.addColorMatch(redBall);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
