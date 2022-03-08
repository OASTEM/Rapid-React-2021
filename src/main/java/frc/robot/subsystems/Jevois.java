package frc.robot.subsystems;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoMode;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Jevois extends SubsystemBase {

  private SerialPort camPort;
  private UsbCamera jevoisUSBCamera;
  public double x = -1;
  public double y = -1;

  public Jevois() {
    jevoisUSBCamera = new UsbCamera("cam0", 1);
    jevoisUSBCamera.setVideoMode(VideoMode.PixelFormat.kYUYV, 640, 480, 20);
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

  @Override
  public void periodic(){
    // String data = camPort.readString();
    // System.out.println(data);
    // JSONObject obj = new JSONObject(data);
    // x = data.getString("x");
    // y = camPort.getString("y");
    try {
      String data = camPort.readString();
      System.out.println(data);
      JSONParser parser = new JSONParser();
      JSONObject jsonData;

      if (data.length() > 0) {
        jsonData = (JSONObject) parser.parse(data);
        x = (Double) jsonData.get("x");
        y = (Double) jsonData.get("y");
        
      } else {
        x = -1;
        y = -1;
      }
    } catch (Exception e) {
      x = -1;
      y = -1;
      System.out.println("Jevois Error ****");
      System.out.println(e);
    }
  }

  public double getY(){
    return y;
  }
  
  public double getX(){
    return x;
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