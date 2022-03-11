// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

// import edu.wpi.cscore.UsbCamera;  
// import edu.wpi.cscore.VideoMode;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoMode;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class AxisCamera extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Thread m_axisCamThread;

  private SerialPort camPort;

  private static double centerX = 0.0;
  private static double distance = 0.0;
  private static double offsetAngle = 0.0;

  // @Override
  // public void initDefaultCommand() {
  //   // Set the default command for a subsystem here.
  //   // setDefaultCommand(new MySpecialCommand());
  // }

  public void startJevoisCamStream(){
    UsbCamera jevoisUSBCamera = new UsbCamera("cam0", 1);
    CameraServer jevoisCamServer = CameraServer.getInstance();
    jevoisUSBCamera = jevoisCamServer.startAutomaticCapture();

    jevoisUSBCamera.setVideoMode(VideoMode.PixelFormat.kYUYV, 640, 480, 20);
  }

  public void initializeSerialPort(){
    try {
      System.out.println("1st Try");
      camPort = new SerialPort(921600, SerialPort.Port.kUSB);
    } catch (Exception e) {
      System.out.println("Error - 2nd Try");
      try {
        camPort = new SerialPort(921600, SerialPort.Port.kUSB1);
      } catch (Exception j) {
        try {
          System.out.println("Error - 3rd Try");
          camPort = new SerialPort(921600, SerialPort.Port.kUSB2);
        } catch (Exception k) {
          System.out.println("Could not connect robot to jevois");
        }
      }
    }
  }

  public void sendSerial(String message) {
    try {
      camPort.writeString(message + "\n");
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  public void drivingMode() {
    sendSerial("setcam brightness 0");
    sendSerial("setcam autoexp 0");
    sendSerial("setcam autogain 1");
  }

  public void visionMode() {
    sendSerial("setcam brightness -2");
    sendSerial("setcam autoexp 1");
    sendSerial("setcam absexp 150");
    sendSerial("setcam autogain 0");
    sendSerial("setcam gain 16");
  }

  public void backgroundUpdate(){
    try {
      String data = camPort.readString();

      JSONParser parser = new JSONParser();
      JSONObject jsonData;

      if (data.length() > 0) {
        jsonData = (JSONObject) parser.parse(data);
        if (jsonData.size() == 1) {
          // Error: No targets found.
          String error = (String) jsonData.get("Error");

          centerX = -1;
          distance = -1;
          offsetAngle = -1;
        } else {
          // Target Found
          centerX = (Double) jsonData.get("Target_Center_X");
          distance = (Double) jsonData.get("Distance");
          offsetAngle = (Double) jsonData.get("Angle");
        }

        SmartDashboard.putNumber("CenterX: ", centerX);
        SmartDashboard.putNumber("Distance: ", distance);
        SmartDashboard.putNumber("Offset Angle: ", offsetAngle);
      }

    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
    Timer.delay(0.005);
  }

  public double getCenterX(){
    return centerX;
  }

  public double getDistance(){
    return distance;
  }

  public double getOffsetAngle(){
    return offsetAngle;
  }
}

