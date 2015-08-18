package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class HDrivePneumatic
{
	IActuator middleWheelStrategy;
	SpeedController middleWheel;
	IDrive driveStrategy;
	RobotDrive drive;
	DoubleSolenoid depressor;
	double MIDDLE_WHEEL_JOYSTICK_TOLERANCE = 0.1;
	public HDrivePneumatic(RobotDrive drive, IDrive driveStrategy, SpeedController middleWheel, IActuator middleWheelStrategy,
			DoubleSolenoid depressor, double scaler)
	{
		this.middleWheel = middleWheel;
		this.middleWheelStrategy = middleWheelStrategy;

		this.depressor = depressor;

		this.drive = drive;
		this.driveStrategy = driveStrategy;
		this.MIDDLE_WHEEL_JOYSTICK_TOLERANCE = 0.1 * scaler;
	}
	
	/**
	 * Actuates motors using the IActuator and IDrive strategies defined by the programmer. 
	 * Will automatically depress the middle wheel solenoid depending on X axis input.
	 * 
	 * @param X Middle wheel rotation
	 * @param Y Chassis Forward and backward
	 * @param rotation Chassis rotation 
	 */
	public void setScaler(double scaler)
	{
		MIDDLE_WHEEL_JOYSTICK_TOLERANCE = 0.1 * scaler;
		
	}
	public void drive(double X, double Y, double rotation)
	{
		if(X > MIDDLE_WHEEL_JOYSTICK_TOLERANCE || X < -MIDDLE_WHEEL_JOYSTICK_TOLERANCE)
		{
			depressor.set(Value.kForward);
			middleWheelStrategy.actuate(middleWheel, X);
			driveStrategy.drive(drive, 0, 0);
		}
		else
		{
			depressor.set(Value.kReverse);
	
			middleWheelStrategy.actuate(middleWheel, 0);
			driveStrategy.drive(drive, Y, rotation);
		}
	}
	
}
