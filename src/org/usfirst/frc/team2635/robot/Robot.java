
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
	
	CANTalon motor;
	OutputThread<JoystickData> xboxController;
	InputThread<Double> singleMotor;
	
	public void robotInit() 
    {
		//Note that these need to be initialized before hdrive is initialized, to avoid null pointer pain
		//TODO: make the channel numbers correct

    	motor = new CANTalon(5);
    	xboxController= new OutputThread<JoystickData>(new JoystickScalable(0), 1.0);
    	singleMotor = new InputThread<Double>(new SingleMotorTest(motor));
    	xboxController.start();
    	singleMotor.start();
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
        
        //Update HDrive movement
        singleMotor.setInput(xboxController.getOutput().axes.get(1));
        
        
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
