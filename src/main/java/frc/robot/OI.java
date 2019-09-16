/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.CommandChangeSpeed;
import frc.robot.commands.changeColourLeft;
import frc.robot.commands.changeColourRight;
import frc.robot.commands.phaseOff;
import frc.robot.commands.phaseOn;
import frc.robot.commands.speedDown;
import frc.robot.commands.speedUp;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  
  public Joystick driveStick = new Joystick(0); //Controller 1
  Button phaseController = new JoystickButton(driveStick, 1); //Xbox: A || DS4: X
  Button chgSpeed = new JoystickButton(driveStick, 4); //Xbox: Y || DS4: Triangle
  Button square = new JoystickButton(driveStick, 2); //Square Button
  Button circle = new JoystickButton(driveStick, 3); //Circle Button

  double dPadDir = driveStick.getPOV(); //gets pos of dpad 
  POVButton changeSpeedUp = new POVButton(driveStick , 0); //Up Dpad
  POVButton changeSpeedDown = new POVButton(driveStick, 180); //Down Dpad
  POVButton colourRight = new POVButton(driveStick, 90); //Right Dpad
  POVButton colourLeft = new POVButton(driveStick, 270); //Left Dpad

public OI() {
  chgSpeed.whenPressed(new CommandChangeSpeed()) ;
  phaseController.whenPressed(new phaseOn());
  //square.whenPressed(new phaseOff());
  circle.whenPressed(new phaseOn());

  //Commands to change colours manually via dpad
  colourRight.whenPressed(new changeColourRight());
  colourLeft.whenPressed(new changeColourLeft());

  //Change speed manually via dpad
  changeSpeedUp.whenPressed(new speedUp());
  changeSpeedDown.whenPressed(new speedDown());

 

}

  // private class POVButton extends Button {
  //   POVBu
  // }
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}