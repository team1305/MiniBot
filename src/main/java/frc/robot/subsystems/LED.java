/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class LED extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
//Defining Physical Ports on PCM for led leads
  static Solenoid port0 = new Solenoid(0); //Green
  static Solenoid port1 = new Solenoid(1); //Blue
  static Solenoid port2 = new Solenoid(2); //Red
  


static int cycle = 0; //Counts the colour the loop is on
public static void nextColour() { //Method to change the colour of leds //Used in phase mode only
  switch(cycle) {
    case 4:
      setBlue();
      break;

    case 2:
      setRed();
      break;
    
    case 0:
      setGreen();
      break;
    
    case 3 :
      yellow();
      break;

    case 1:
      lightBlue();
      break;

    case 5:
      purple();
      break;

    default: //case 6:
      white();
      cycle = -1; //set to -1 as at the end of the switch it will always increment it and thus go back to 0
      break;
  }
  cycle++;

}

public static boolean notPhasing = true; //Wheather or not the led is in phasing mode

static int colourIndex = 0; //Colour index used for switch in colourDPAD method
public static void colourDPAD(boolean dir) {
  //Depending on the param passed increment/decrements colourIndex which displays what colour is diplayed
  if(dir == true) colourIndex++;
  else if(dir == false) colourIndex--;

  switch(colourIndex) {
    case 4:
      setBlue();
      break;

    case 2:
      setRed();
      break;
    
    case 0:
      setGreen();
      break;
    
    case 3 :
      yellow();
      break;

    case 1:
      lightBlue();
      break;

    case 5:
      purple();
      break;

    default: //when in doubt display white
      white();
      break;
  }

}

//Methods to set colours
public static void setBlue() {
  port0.set(false);
  port1.set(true);
  port2.set(false);
}

public static void setRed() {
  port0.set(false);
  port1.set(false);
  port2.set(true);
}

public static void setGreen() {
  port0.set(true);
  port1.set(false);
  port2.set(false);
}

public static void lightBlue() {
  port0.set(true);
  port1.set(true);
  port2.set(false);
}

public static void yellow(){
  port0.set(true);
  port1.set(false);
  port2.set(true);
}

public static void purple() {
  port0.set(false);
  port1.set(true);
  port2.set(true);
}

public static void white() {
  port0.set(true);
  port1.set(true);
  port2.set(true);
}

public static void off() {
  port0.set(false);
  port1.set(false);
  port2.set(false);
}



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }
}
