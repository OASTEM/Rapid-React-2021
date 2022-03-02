// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class ColorSorter extends SubsystemBase {
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private VictorSPX cpMan = new VictorSPX(13);

  private I2C.Port i2cPort = I2C.Port.kOnboard;
  private ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private final Color blueBall = new Color(0.143, 0.427, 0.429);
  private final Color redBall = new Color(0.561, 0.232, 0.114);
  private final String[] colorList = { "Blue", "Red"};
  private String colorString;

  /** Creates a new ColorSorter. */
  public ColorSorter() {
    m_colorMatcher.addColorMatch(blueBall);
    m_colorMatcher.addColorMatch(redBall);
    colorString = "";

  }

  @Override
  public void periodic() {
    System.out.println("color sorter periodic");
    Color detectedColor = m_colorSensor.getColor();
    m_colorMatcher.setConfidenceThreshold(0.1);
    ColorMatchResult result = m_colorMatcher.matchClosestColor(detectedColor);
    //System.out.println("Result " + result.color);
    // System.out.println("Detected Color" + detectedColor);
    if (result.color == blueBall){
      colorString = "Blue";
    }
    else if (result.color == redBall) {
      colorString = "Red";
    }
    else {
      colorString = "Unknown";
    // This method will be called once per scheduler run
  }
  System.out.println(colorString);
}

//  public String getColor() {
//   int colorInt = 0;

//   for (int i = 0; i < 1; i++) {
//     if (colorString.equals(colorList[i])) {
//       colorInt = i;
//     }
//   }
//   return colorList[colorInt];
// }
}
