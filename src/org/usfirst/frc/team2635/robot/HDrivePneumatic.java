package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class HDrivePneumatic extends RobotDrive implements IInput<HDriveInput>
{
	SpeedController middleWheel;
	DoubleSolenoid depressor;
	final double MIDDLE_WHEEL_JOYSTICK_TOLERANCE = 0.1;
	public HDrivePneumatic(SpeedController frontLeft, SpeedController frontRight,
			SpeedController rearLeft, SpeedController rearRight, SpeedController middle, DoubleSolenoid depressor)
	{
		super(frontLeft, frontRight, rearLeft, rearRight);
		this.middleWheel = middle;
		this.depressor = depressor;
	}
	@Override
	public void setInput(HDriveInput input)
	{
		if(input.X > MIDDLE_WHEEL_JOYSTICK_TOLERANCE || input.X < -MIDDLE_WHEEL_JOYSTICK_TOLERANCE)
		{
			depressor.set(Value.kForward);
			middleWheel.set(input.X);
			arcadeDrive(0, 0);
		}
		else
		{
			depressor.set(Value.kReverse);
			middleWheel.set(0);
			arcadeDrive(input.Y, input.rotation);
		}
	}
	
}