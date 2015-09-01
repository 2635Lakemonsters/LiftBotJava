package org.usfirst.frc.team2635.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedController;

public class HDrivePneumatic
{
	enum DriveState
	{
		MIDDLEWHEELUP,
		MIDDLEWHEELDOWN
	}
	double setpoint = 0;
	IActuator middleWheelStrategy;
	SpeedController middleWheel;
	IDrive driveStrategy;
	RobotDrive drive;
	DoubleSolenoid depressor;
	AHRS gyroscope;
	PIDController middleWheelPid;
	DriveState state = DriveState.MIDDLEWHEELUP;
	double MIDDLE_WHEEL_JOYSTICK_TOLERANCE = 0.1;
	public HDrivePneumatic(AHRS navx, RobotDrive drive, IDrive driveStrategy, SpeedController middleWheel, IActuator middleWheelStrategy,
			DoubleSolenoid depressor, double scaler)
	{
		this.middleWheel = middleWheel;
		this.middleWheelStrategy = middleWheelStrategy;

		this.depressor = depressor;

		this.drive = drive;
		this.driveStrategy = driveStrategy;
		this.MIDDLE_WHEEL_JOYSTICK_TOLERANCE = 0.1 * scaler;
		this.gyroscope = navx;
	    this.middleWheelPid = new PIDController(0.08,0.0,0.0, new PIDSourceGetNavXHeading(gyroscope), new PIDSourceSetDrive(drive)  );
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
		
		switch(state)
		{
		case MIDDLEWHEELDOWN:
			depressor.set(Value.kForward);
			middleWheelStrategy.actuate(middleWheel, X);
			if(!(X > MIDDLE_WHEEL_JOYSTICK_TOLERANCE || X < -MIDDLE_WHEEL_JOYSTICK_TOLERANCE))
			{
				middleWheelPid.disable();
				state = DriveState.MIDDLEWHEELUP;
			
			}
			break;
		case MIDDLEWHEELUP:
			depressor.set(Value.kReverse);
			middleWheelStrategy.actuate(middleWheel, 0);
			driveStrategy.drive(drive, Y, rotation);
			
			
			if(X > MIDDLE_WHEEL_JOYSTICK_TOLERANCE || X < -MIDDLE_WHEEL_JOYSTICK_TOLERANCE)
			{
				

				//middleWheelPid.setSetpoint(Utilities.unwrap(gyroscope.getYaw()));
				middleWheelPid.enable();
				state = DriveState.MIDDLEWHEELDOWN;
			
			}
			break;
		default:
			break;
		
		}
		
		
	}
	
}
