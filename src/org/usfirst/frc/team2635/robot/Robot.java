
package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	//region CONSTANTS
	final int REARLEFTCHANNEL = 2;
	final int REARRIGHTCHANNEL= 3;
	final int FRONTRIGHTCHANNEL = 5;
	final int FRONTLEFTCHANNEL = 4;
	final int MIDDLECHANNEL = 6;
	final int DEPRESSORFORWARD = 4;
	final int DEPRESSORBACKWARD = 5;
	
	final int TOTELIFT1CHANNEL = 8;
	final int TOTELIFT2CHANNEL = 9;
	
	final double TOTELIFTINCREMENT = 100.0;
	
	final int CANLIFT1CHANNEL = 7;
	
	final double CANLIFTINCREMENT = 100.0;
	
	final int YAXIS = 1;
	final int XAXIS = 4;
	final int ROTATION = 0;
	
	final int VBUSLIFTDOWN = 2;
	final int VBUSLIFTUP = 3;
	
	final int POVUP = 0;
	final int POVDOWN = 180;
	
	final int BUTTONUP = 3;
	final int BUTTONDOWN = 0;
	//endregion
	
	CANTalon rearLeft;
	CANTalon rearRight;
	CANTalon frontRight;
	CANTalon frontLeft;
	CANTalon middle;
	DoubleSolenoid depressor;
	
	CANTalon toteLiftMotor1;
	CANTalon toteLiftMotor2;
	
	CANTalon canLiftMotor1;
	//DigitalInput buttonInput;
//	Servo servo;
	//Relay lightSpike;
	
	Sensor<JoystickData> xboxController;
	HDrivePneumatic hdrive;
	
	LiftPositionTwoMotor toteLift;
	LiftPositionSingleMotor canLift;
//	InputThread<Double> singleMotorThread;
//	InputThread<Double> servoThread;
//	InputThread<Boolean> lightSpikeThread;
	public void robotInit() 
    {
	
    	rearLeft = new CANTalon(REARLEFTCHANNEL);
    	rearRight = new CANTalon(REARRIGHTCHANNEL);
    	frontRight = new CANTalon(FRONTRIGHTCHANNEL);
    	frontLeft = new CANTalon(FRONTLEFTCHANNEL);
    	middle = new CANTalon(MIDDLECHANNEL);
    	depressor = new DoubleSolenoid(DEPRESSORFORWARD, DEPRESSORBACKWARD);
    	
    	toteLiftMotor1 = new CANTalon(TOTELIFT1CHANNEL);
    	//Zero out encoder
    	toteLiftMotor1.setPosition(0);
    	toteLiftMotor2 = new CANTalon(TOTELIFT2CHANNEL);
    	toteLiftMotor1.reverseSensor(true);
    	
    	canLiftMotor1 = new CANTalon(CANLIFT1CHANNEL);
    	//Zero out encoder
    	canLiftMotor1.setPosition(0);
    	
    	toteLift = new LiftPositionTwoMotor(toteLiftMotor1, toteLiftMotor2, false, 1.0, 0, 0, 7400.0, 0.0);
    	canLift = new LiftPositionSingleMotor(canLiftMotor1, true, 1.0, 0, 0, 7400.0, 0.0);
    	
    	xboxController= new Sensor<JoystickData>(new JoystickScalable(0));
    	hdrive = new HDrivePneumatic(frontLeft, frontRight, rearLeft, rearRight, middle, depressor);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        JoystickData joystickOut = xboxController.getOutput(1.0);
       
        hdrive.drive(joystickOut.axes.get(XAXIS), joystickOut.axes.get(YAXIS), joystickOut.axes.get(ROTATION));
        
        if(joystickOut.POVDirection == POVDOWN)
        {
        	toteLift.setSetPoint(toteLift.getSetPoint() - TOTELIFTINCREMENT);
        }
        else if(joystickOut.POVDirection == POVUP)
        {
        	toteLift.setSetPoint(toteLift.getSetPoint() + TOTELIFTINCREMENT);
        }
       
        
        if(joystickOut.buttons.get(BUTTONDOWN))
        {
        	canLift.setSetPoint(canLift.getSetPoint() - CANLIFTINCREMENT);
        }
        else if (joystickOut.buttons.get(BUTTONUP))
        {
        	canLift.setSetPoint(canLift.getSetPoint() + CANLIFTINCREMENT);
        }
        toteLift.setUpperLimit(canLift.getSetPoint());
        canLift.setLowerLimit(toteLift.getSetPoint());
        SmartDashboard.putNumber("CanLiftSetPoint:", canLift.getSetPoint());
        SmartDashboard.putNumber("ToteLiftSetPoint", toteLift.getSetPoint());
        
        SmartDashboard.putNumber("CanEncoder", canLiftMotor1.getEncPosition());
        SmartDashboard.putNumber("ToteEncoder", toteLiftMotor1.getEncPosition());
        for(int i = 0; i < joystickOut.axes.size(); i++)
        {
        	SmartDashboard.putNumber("axis " + i, joystickOut.axes.get(i));
        }
        for(int i = 0; i < xboxController.getOutput(1.0).buttons.size(); i++)
        {
        	SmartDashboard.putBoolean("button " + i, joystickOut.buttons.get(i));
        }
        SmartDashboard.putNumber("POV", (double)joystickOut.POVDirection);
//        SmartDashboard.putBoolean("DIO Button 1:", button.getOutput());
//        if(!button.getOutput())
//        {
//        	servoThread.setInput(1.0);wwwwwwwwwwwwwwwwwwwwwww
//        }
        
//        else
//        {
//        	servoThread.setInput(-1.0);
//        }
//        System.out.println("2 hit");
//        lightSpikeThread.setInput(!button.getOutput());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
