/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.Command_Joystick_Drive;
import frc.robot.commands.phaseOff;
import frc.robot.commands.phaseOn;


/**
 * Add your docs here.
 */
public class driveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final WPI_TalonSRX leftMotor = RobotMap.leftMotor;
  private final WPI_TalonSRX rightMotor = RobotMap.rightMotor;
  DifferentialDrive drRobotDrive = new DifferentialDrive(leftMotor, rightMotor);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Command_Joystick_Drive());
  }

  static boolean gearState = true; //default starts the bot at low speed
  static double driveNerf = 0.5; //default scaler on drive value

  public static void nerfDown() {
    if(driveNerf > 0.2) {
      driveNerf = driveNerf - 0.05;
    } 
  }

  public static void nerfUp() {
    if(driveNerf < 0.8) {
      driveNerf = driveNerf + 0.05;
    }  
  }

  public static void toggleGear() {
    gearState = !gearState;
    //new phaseOff();
  }

  public void driveWithJoystick(Joystick input) {
    double xSpeed = input.getY();
    double zRotation = input.getRawAxis(4);

    if (gearState == true) { //High = low speed
      drRobotDrive.arcadeDrive(driveNerf * xSpeed, (driveNerf + 0.05) * zRotation);
      //System.out.println("slow speed");
      if(LED.notPhasing == false) {
        // new phaseOff();
        //LED.purple();
      }
     
    }

    else if(gearState == false) { //Low = high speed
      drRobotDrive.arcadeDrive(xSpeed, zRotation);
      //System.out.println("high speed");
      if(LED.notPhasing == false) {
        //new phaseOff();
        //LED.setBlue();
      }
      
    }

  }



  public void stop() {
    drRobotDrive.tankDrive(0, 0);
  }

}
