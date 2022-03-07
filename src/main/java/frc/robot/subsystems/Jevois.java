package frc.robot.subsystems;

// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoMode;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Jevois extends SubsystemBase {

  private SerialPort camPort;
  private UsbCamera jevoisUSBCamera;

  public Jevois() {
    jevoisUSBCamera = new UsbCamera("cam0", 1);
    jevoisUSBCamera.setVideoMode(VideoMode.PixelFormat.kYUYV, 640, 480, 20);
  }

  @Override
  public void periodic() {
  }

  public void initializeSerialPort() {
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

  public void readJevoisData(){
    String data = camPort.readString();
    System.out.println(data);
    
  }

  // public void backgroundUpdate() {
  //   try {
  //     String data = camPort.readString();

  //     JSONParser parser = new JSONParser();
  //     JSONObject jsonData;

  //     if (data.length() > 0) {
  //       jsonData = (JSONObject) parser.parse(data);
  //       System.out.println(jsonData.toString());
  //     }
  //   } catch (Exception e) {
  //     System.out.println("Error: " + e);
  //   }
  // }

  public void destroy() {
    jevoisUSBCamera.close();
  }

}