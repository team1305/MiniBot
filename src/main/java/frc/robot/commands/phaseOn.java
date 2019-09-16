/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.LED;

public class phaseOn extends Command {
  public phaseOn() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.led);
  }

  boolean returnValue = false;
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Only want this to run once hense otherwise rio will run out of memory as it will create many timer objects (hense in init)
  }

  

  public static Timer timer = new Timer();
  long delay = 0; //delay from boot to when the timer starts
  long perodic = 1000; //how much time there is before the timer calls the method again

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(LED.notPhasing == true) {
      LED.notPhasing = false;

      timer.schedule(new TimerTask(){
      
        @Override
        public void run() {
          LED.nextColour();
        }
      }, delay, perodic);
    }


    else {
     // new phaseOff();
      //This block is called by phaseOff()

      //Trying to move this code to a seperate command/method causes weird issues
      //So to turn it off call . syntax into phaseOn modidy notPhasing to true

      try { //IF the timer has already been cancelled and you try to re-cancel it it will throw an illegal state exception
        timer.cancel(); //cancels the timer
      } catch (Exception e) {
        //TODO: handle exception
        //Just ignore it if it throws exception
        System.out.println("Threw Exception in timer caller");
      }

     LED.notPhasing = true; //the leds are no longer phasing.
     timer = new Timer(); //If you try to call schedule on timer after it have already been cancelled it will throw an exception
     //therefore reassign timer to a new timer object so that 

     LED.off();
    }
    

    returnValue = true; //ensuring it will only run once per call
    //led.nextColour();
      
  }

  //  public static void phaseLow() {
  // //   //IF the timer has already been cancelled and you try to re-cancel it it will throw an illegal state exception
  //     try {
  //       timer.cancel();
  //     } catch (Exception e) {
  //       //TODO: handle exception
  //       //Just ignore it if it throws exception
  //     }

  //     notPhasing = true;
  //     timer = new Timer();
  //  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return returnValue;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
