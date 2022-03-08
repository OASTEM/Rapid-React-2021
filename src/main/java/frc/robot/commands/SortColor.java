// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSorter;
import frc.robot.subsystems.Intake;

public class SortColor extends CommandBase {
  private final ColorSorter colorSorter;
  private Intake intake;
  public boolean isIntaking;
  public String color;
  public String chosenColor;
  /** Creates a new SortColor. */
  public SortColor(ColorSorter colorSorter, Intake intake, boolean isIntaking, String chosenColor) {
    addRequirements(colorSorter);
    addRequirements(intake);
    this.colorSorter = colorSorter;
    this.intake = intake;
    this.isIntaking = isIntaking;
    this.chosenColor = chosenColor; 
    color = colorSorter.colorString;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (isIntaking == true){
      color = colorSorter.colorString;
    }
    // System.out.println(colorSorter.getColor());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
