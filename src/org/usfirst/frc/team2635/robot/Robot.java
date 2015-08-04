
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
	final int REARLEFTCHANNEL = 3;
	final int REARRIGHTCHANNEL= 5;
	final int FRONTRIGHTCHANNEL = 4;
	final int FRONTLEFTCHANNEL = 2;
	final int MIDDLECHANNEL = 6;
	final int DEPRESSORFORWARD = 4;
	final int DEPRESSORBACKWARD = 5;
	
	final int TOTELIFT1CHANNEL = 7;
	final int TOTELIFT2CHANNEL = 8;
	
	final int CANLIFT1CHANNEL = 9;
	
	final int YAXIS = 1;
	final int XAXIS = 4;
	final int ROTATION = 0;
	
	final int VBUSLIFTDOWN = 2;
	final int VBUSLIFTUP = 3;
	
	final int POVUP = 180;
	final int POVDOWN = 0;
	
	final int BUTTONUP = 0;
	final int BUTTONDOWN = 3;
	
	CANTalon rearLeft;
	CANTalon rearRight;
	CANTalon frontRight;
	CANTalon frontLeft;
	CANTalon middle;
	DoubleSolenoid depressor;
	
	CANTalon liftMotor1;
	CANTalon liftMotor2;
	//DigitalInput buttonInput;
//	Servo servo;
	//Relay lightSpike;
	
	Sensor<JoystickData> xboxController;
	Component<HDriveInput> hdrive;
	
	Lift toteLift;
	Lift canLift;
//	InputThread<Double> singleMotorThread;
//	InputThread<Double> servoThread;
//	InputThread<Boolean> lightSpikeThread;
	public void robotInit() 
    {
	
		//TODO: make the channel numbers correct
    	rearLeft = new CANTalon(REARLEFTCHANNEL);
    	rearRight = new CANTalon(REARRIGHTCHANNEL);
    	frontRight = new CANTalon(FRONTRIGHTCHANNEL);
    	frontLeft = new CANTalon(FRONTLEFTCHANNEL);
    	middle = new CANTalon(MIDDLECHANNEL);
    	depressor = new DoubleSolenoid(DEPRESSORFORWARD, DEPRESSORBACKWARD);
    	
    	liftMotor1 = new CANTalon(TOTELIFT1CHANNEL);
    	liftMotor2 = new CANTalon(TOTELIFT2CHANNEL);
    	
    	//ROBOT PARTS:
    	toteLift = new Lift(liftMotor1, liftMotor2, new VBusLift());
    	canLift = new Lift(liftMotor1, null, new VBusLift());
    	
    	xboxController= new Sensor<JoystickData>(new JoystickScalable(0));
    	hdrive = new Component<HDriveInput>(new HDrivePneumatic(frontLeft, frontRight, rearLeft, rearRight, middle, depressor), new HDriveInput(0,0,0));
    	
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
        HDriveInput input = new HDriveInput();
        //Update HDrive movement
//        singleMotorThread.setInput(xboxController.getOutput().axes.get(1));
        JoystickData joystickOut = xboxController.getOutput(1.0);
        input.X=joystickOut.axes.get(XAXIS);
        input.Y=joystickOut.axes.get(YAXIS);
        input.rotation = joystickOut.axes.get(ROTATION);
        hdrive.setInput(input);
        
        if(joystickOut.POVDirection == POVDOWN)
        {
        	toteLift.setSetPoint(-0.5);
        }
        else if(joystickOut.POVDirection == POVUP)
        {
        	toteLift.setSetPoint(0.5);
        }
        else
        {
        	toteLift.setSetPoint(0.0);
        }
        
        if(joystickOut.buttons.get(BUTTONDOWN))
        {
        	canLift.setSetPoint(-0.5);
        }
        else if (joystickOut.buttons.get(BUTTONUP))
        {
        	canLift.setSetPoint(0.5);
        }
        else
        {
        	canLift.setSetPoint(0.0);
        }
        
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
