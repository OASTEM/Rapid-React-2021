package frc.robot.subsystems;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Jevois extends SubsystemBase {
  /**
   * Creates a new Jevois.
   */
  private double depth = 0.0;
  private double offsetAngle = 0.0;

  private SerialPort camPort;

  public Jevois() {
    UsbCamera jevoisUSBCamera = new UsbCamera("cam0", 1);
    CameraServer jevoisCamServer = CameraServer.getInstance();
    jevoisUSBCamera = jevoisCamServer.startAutomaticCapture();

    jevoisUSBCamera.setVideoMode(VideoMode.PixelFormat.kYUYV, 640, 480, 20);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //backgroundUpdate();
    //System.out.println("depth: " + getdepth());
    //System.out.println("Offset Angle: " +getOffsetAngle());
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

  public void backgroundUpdate() {
    try {
      String data = camPort.readString();

      JSONParser parser = new JSONParser();
      JSONObject jsonData;

      if (data.length() > 0) {
        jsonData = (JSONObject) parser.parse(data);

        if (jsonData.size() == 1) {
          depth = -1;
          offsetAngle = -1;
        } else {
          depth = (Double) jsonData.get("Depth");
          offsetAngle = (Double) jsonData.get("Offset_angle");
        }
      }
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  public double getDepth() {
    return depth;  
  }

  public double getOffsetAngle() {
    return offsetAngle;
  }

}