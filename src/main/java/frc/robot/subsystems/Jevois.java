package frc.robot.subsystems;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.cscore.MjpegServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoMode;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Jevois extends SubsystemBase {

  /**
   *
   */
  private static final int BAUDRATE = 921600;
  private SerialPort camPort;
  private UsbCamera cam;
  // private CameraServer jevoisCameraServer;
  public double x = -1;
  public double y = -1;


  public Jevois() {
    cam = CameraServer.startAutomaticCapture();

    cam.setVideoMode(VideoMode.PixelFormat.kYUYV, 640, 480, 20);


    // stuff = CameraServer.addAxisCamera();
    // boolean setVidMode = jevoisUSBCamera.setVideoMode(VideoMode.PixelFormat.kYUYV, 640, 480, 20);

    // System.out.println("setvidmode: " + setVidMode);
    System.out.println("INITALIZING JEVOIS ***** YAYAYAYAAYAYA");
    try {
      System.out.println("1st Try");
      // 921600

      camPort = new SerialPort(BAUDRATE, SerialPort.Port.kUSB2);

    } catch (Exception e) {

      System.out.println("Error - 2nd Try");

      try {

        camPort = new SerialPort(BAUDRATE, SerialPort.Port.kUSB);

      } catch (Exception j) {

        try {

          System.out.println("Error - 3rd Try");

          camPort = new SerialPort(BAUDRATE, SerialPort.Port.kUSB1);

        } catch (Exception k) {

          System.out.println("Could not connect robot to jevois");

        }

      }
    }
  }

  @Override
  public void periodic() {
    // String data = camPort.readString();
    // System.out.println(data);
    // JSONObject obj = new JSONObject(data);
    // x = data.getString("x");
    // y = camPort.getString("y");
    System.out.println("JEVOIS PERIODIC IS ON *****");
    try {
      String data = camPort.readString();
      System.out.println("trying to print and get data ");
      System.out.println(data);
      // JSONParser parser = new JSONParser();
      // JSONObject jsonData;

      // int stuff = camPort.getBytesReceived();
      // System.out.println(stuff);
      // byte[] otherStuff = camPort.read(100);
      // System.out.println(otherStuff[0]);

      // if (data.length() > 0) {
      // jsonData = (JSONObject) parser.parse(data);
      // x = (Double) jsonData.get("x");
      // y = (Double) jsonData.get("y");
      // System.out.println("x is ");
      // System.out.println(x);
      // } else {
      // x = -1;
      // y = -1;
      // }
    } catch (Exception e) {
      x = -1;
      y = -1;
      System.out.println("Jevois Error ****");
      System.out.println(e);
    }
  }

  public double getY() {
    return y;
  }

  public double getX() {
    return x;
  }
  // public void backgroundUpdate() {
  // try {
  // String data = camPort.readString();

  // JSONParser parser = new JSONParser();
  // JSONObject jsonData;

  // if (data.length() > 0) {
  // jsonData = (JSONObject) parser.parse(data);
  // System.out.println(jsonData.toString());
  // }
  // } catch (Exception e) {
  // System.out.println("Error: " + e);
  // }
  // }

  public void destroy() {
    cam.close();
  }

}