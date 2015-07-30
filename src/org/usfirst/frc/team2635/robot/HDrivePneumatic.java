package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class HDrivePneumatic extends RobotDrive implements IInput<HDriveInput>
{
	SpeedController middleWheel;
	DoubleSolenoid depressor;
	public HDrivePneumatic(SpeedController frontLeft, SpeedController frontRight,
			SpeedController rearLeft, SpeedController rearRight, SpeedController middle, DoubleSolenoid depressor)
	{
		super(frontLeft, frontRight, rearLeft, rearRight);
		this.middleWheel = middle;
		this. depressor = depressor;
	}
	@Override
	public void setInput(HDriveInput input)
	{
		
	}
	
}
