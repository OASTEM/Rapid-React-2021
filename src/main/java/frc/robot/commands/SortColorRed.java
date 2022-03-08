// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ColorSorter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
// We are red alliance
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SortColorRed extends ParallelCommandGroup {
  /** Creates a new SortColorRed. */
  public SortColorRed(ColorSorter colorSorter, Intake intake, Shooter shooter) {
    addCommands(new SortColor(colorSorter, intake, true, "Red"), new CargoManipulation(intake, shooter, true));
  }
}
