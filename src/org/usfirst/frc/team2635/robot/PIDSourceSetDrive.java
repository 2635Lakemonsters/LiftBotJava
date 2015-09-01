package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

public class PIDSourceSetDrive implements PIDOutput
{

	RobotDrive drive;
	public PIDSourceSetDrive(RobotDrive drive)
	{
		this.drive = drive;
	}
	@Override
	public void pidWrite(double output) 
	{
		drive.arcadeDrive(0,-output);
	}

}
