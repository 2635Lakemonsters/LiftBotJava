package org.usfirst.frc.team2635.robot;


import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
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
	
	final double TOTELIFTINCREMENT = 2000.0;
	
	final int CANLIFT1CHANNEL = 7;
	
	final double CANLIFTINCREMENT = 2000.0;
	
	final int YAXIS = 1;
	final int XAXIS = 4;
	final int ROTATION = 0;
	
	final int VBUSLIFTDOWN = 2;
	final int VBUSLIFTUP = 3;
	
	final int POVUP = 0;
	final int POVDOWN = 180;
	final int POVLEFT = 270;
	final int POVRIGHT = 90;
	
	final int BUTTONUP = 3;
	final int BUTTONDOWN = 0;
	
	final int TOTESOLENOIDFORWARD = 0;
	final int TOTESOLENOIDREVERSE = 1;
	final int CANSOLENOIDFORWARD = 2;
	final int CANSOLENOIDREVERSE = 3;
	
	final int CANGRABOPEN = 1;
	final int CANGRABCLOSE = 2;
	
	//TODO: set button values to correct values
	final int CONTROLMODEVBUS = 0;
	final int CONTROLMODESPEED = 0;
	//endregion
	
	//Drive System declarations
	CANTalon rearLeft;
	CANTalon rearRight;
	CANTalon frontRight;
	CANTalon frontLeft;
	CANTalon middle;
	DoubleSolenoid depressor;
	
	//Tote Carriage declarations
	CANTalon toteLiftMotor1;
	CANTalon toteLiftMotor2;
	DoubleSolenoid toteArmsSolenoid;
	
	//Can Carriage declarations
	CANTalon canLiftMotor1;
	DoubleSolenoid canArmsSolenoid;
	

	
	ScaledJoystick xboxController;
	OneShotRising<Integer> toteLiftPOVOneShot;
	OneShotRising<Boolean> canLiftUpButtonOneShot;
	OneShotRising<Boolean> canLiftDownButtonOneShot;
	
	HDrivePneumatic hdrive;
	int scalingFactor;
	OneShotRising<Boolean> driveModeVbusOneShot;
	OneShotRising<Boolean> driveModeSpeedOneShot;
	
	Arms toteArms;
	Arms canArms;
	LiftPositionTwoMotor toteLift;
	LiftPositionSingleMotor canLift;

	public int setDriveMode(CANTalon rearLeft, CANTalon rearRight, CANTalon frontRight, CANTalon frontLeft, ControlMode controlMode)
	{
		frontRight.changeControlMode(controlMode);
		frontLeft.changeControlMode(controlMode);
		rearLeft.changeControlMode(ControlMode.Follower);
		rearRight.changeControlMode(ControlMode.Follower);
		//Return the scaling factor
		switch (controlMode)
		{
		case Current:
			return 0;
		case Disabled:
			return 0;
		case Follower:
			return 0;
		case PercentVbus:
			return 1;
		case Position:
			return 10;
		case Speed:
			return 100;
		case Voltage:
			return 13;
		default:
			return 0;
		}
	}
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
    	toteArmsSolenoid = new DoubleSolenoid(TOTESOLENOIDFORWARD, TOTESOLENOIDREVERSE);
    	
    	canLiftMotor1 = new CANTalon(CANLIFT1CHANNEL);
    	//Zero out encoder
    	canLiftMotor1.setPosition(0);
    	canArmsSolenoid = new DoubleSolenoid(CANSOLENOIDFORWARD, CANSOLENOIDREVERSE);
    	
    	toteLift = new LiftPositionTwoMotor(toteLiftMotor1, toteLiftMotor2, false, 1.0, 0, 0, 7400.0, 0.0);
    	canLift = new LiftPositionSingleMotor(canLiftMotor1, true, 1.0, 0, 0, 7400.0, 0.0);
    	
    	xboxController= new ScaledJoystick(0);
    	toteLiftPOVOneShot = new OneShotRising<Integer>(new Integer[]{POVUP, POVDOWN}, -1);
    	canLiftUpButtonOneShot = new OneShotRising<Boolean>(true, false); 
    	canLiftDownButtonOneShot = new OneShotRising<Boolean>(true, false);
    	
    	hdrive = new HDrivePneumatic(new RobotDrive(frontLeft, frontRight, rearLeft, rearRight), new StandardArcadeDrive(), middle, new MiddleWheelVbus(), depressor);
    	driveModeSpeedOneShot = new OneShotRising<Boolean>(true, false);
    	driveModeVbusOneShot = new OneShotRising<Boolean>(true, false);
    	
    	scalingFactor = setDriveMode(rearLeft,rearRight,frontRight,frontLeft,ControlMode.PercentVbus);
    	
    	toteArms = new Arms(toteArmsSolenoid);
    	canArms = new Arms(canArmsSolenoid);
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
    	
        JoystickData joystickOut = xboxController.getOutput(scalingFactor);
        
        if(joystickOut.connected)
        {
	        hdrive.drive(joystickOut.axes.get(XAXIS), joystickOut.axes.get(YAXIS), joystickOut.axes.get(ROTATION));
	        
	        Integer toteLiftPOVState = toteLiftPOVOneShot.getValue(joystickOut.POVDirection);
	        if(toteLiftPOVState == POVDOWN)
	        {
	        	toteLift.setSetPoint(toteLift.getSetPoint() - TOTELIFTINCREMENT);
	        }
	        else if(toteLiftPOVState == POVUP)
	        {
	        	toteLift.setSetPoint(toteLift.getSetPoint() + TOTELIFTINCREMENT);
	        }
	       
	        if(canLiftDownButtonOneShot.getValue(joystickOut.buttons.get(BUTTONDOWN)))
	        {
	        	canLift.setSetPoint(canLift.getSetPoint() - CANLIFTINCREMENT);
	        }
	        else if (canLiftDownButtonOneShot.getValue(joystickOut.buttons.get(BUTTONUP)))
	        {
	        	canLift.setSetPoint(canLift.getSetPoint() + CANLIFTINCREMENT);
	        }
	        //Offset for gearing differences
	        toteLift.setUpperLimit(canLift.getSetPoint() + 1000);
	        canLift.setLowerLimit(toteLift.getSetPoint() );
	                
	        if(joystickOut.POVDirection == POVRIGHT)
	        {
	        	toteArms.set(Value.kForward);
	        }
	        else if(joystickOut.POVDirection == POVLEFT)
	        {
	        	toteArms.set(Value.kReverse);
	        }
	        
	        if(joystickOut.buttons.get(CANGRABOPEN))
	        {
	        	canArms.set(Value.kForward);
	        }
	        else if (joystickOut.buttons.get(CANGRABCLOSE))
	        {
	        	canArms.set(Value.kReverse);
	        }
	        
	        if(driveModeSpeedOneShot.getValue(joystickOut.buttons.get(CONTROLMODESPEED)))
	        {
	        	scalingFactor = setDriveMode(rearLeft, rearRight, frontRight, frontLeft, ControlMode.Speed);
	        }
	        if(driveModeVbusOneShot.getValue(joystickOut.buttons.get(CONTROLMODEVBUS)))
	        {
	        	scalingFactor = setDriveMode(rearLeft, rearRight, frontRight, frontLeft, ControlMode.PercentVbus);

	        }
        	
	       
	        SmartDashboard.putNumber("CanLiftSetPoint", canLift.getSetPoint());
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
	        
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

/**
 * 
 * THIS
 * IS
 * A
 * CHANGE.
 * 
 * 
 * 
 **/
