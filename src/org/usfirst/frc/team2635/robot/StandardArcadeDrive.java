package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class StandardArcadeDrive implements IDrive 
{

	@Override
	public void drive(RobotDrive drive, double Y, double rotation) 
	{
		drive.arcadeDrive(Y, rotation);
		
	}
	
}
