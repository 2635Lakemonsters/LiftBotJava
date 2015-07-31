
package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CANTalon;
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
	double scalingFactor = 1.0;
	CANTalon rearLeft;
	CANTalon rearRight;
	CANTalon frontLeft;
	CANTalon frontRight;
	CANTalon middle;
	DoubleSolenoid depressor = new DoubleSolenoid(0, 1);
	
	OutputThread<JoystickData> xboxController;
	InputThread<HDriveInput> hdrive;
	public void setDriveControlMode(ControlMode mode)
	{
		rearLeft.changeControlMode(ControlMode.Follower);
		rearRight.changeControlMode(ControlMode.Follower);
		frontLeft.changeControlMode(mode);
		frontRight.changeControlMode(mode);
		switch(mode)
		{
		case Current:
			scalingFactor = 0;
			break;
		case Disabled:
			scalingFactor = 0;
			break;
		case Follower:
			scalingFactor = 0;
			break;
		case PercentVbus:
			scalingFactor = 1.0;
			break;
		case Position:
			scalingFactor = 10;
			break;
		case Speed:
			scalingFactor = 100;
			break;
		case Voltage:
			scalingFactor = 1.0;
			break;
		default:
			scalingFactor = 0;
		}
	}
	public void robotInit() 
    {
		//Note that these need to be initialized before hdrive is initialized, to avoid null pointer pain
		//TODO: make the channel numbers correct

    	rearLeft = new CANTalon(0);
    	rearRight = new CANTalon(1);
    	frontLeft = new CANTalon(2);
    	frontRight = new CANTalon(3);
    	middle = new CANTalon(4);
    	setDriveControlMode(ControlMode.PercentVbus);
    	xboxController= new OutputThread<JoystickData>(new JoystickRawOutput(0));
    	hdrive = new InputThread<HDriveInput>(new HDrivePneumatic(frontLeft, frontRight, rearLeft, rearRight, middle, depressor));

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
     	HDriveInput joystickInput = new HDriveInput();
     	//TODO: set the axis numbers to the correct ones
        joystickInput.Y = xboxController.getOutput().axes.get(0);
        joystickInput.X = xboxController.getOutput().axes.get(1);
        joystickInput.rotation = xboxController.getOutput().axes.get(2);
        hdrive.setInput(joystickInput);
        for(int i = 0; i < xboxController.getOutput().axes.size(); i++)
        {
        	SmartDashboard.putNumber("axis " + i, xboxController.getOutput().axes.get(i));
        }
        for(int i = 0; i < xboxController.getOutput().buttons.size(); i++)
        {
        	SmartDashboard.putBoolean("button " + i, xboxController.getOutput().buttons.get(i));
        }
       
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
