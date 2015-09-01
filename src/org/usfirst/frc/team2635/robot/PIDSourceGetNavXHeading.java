package org.usfirst.frc.team2635.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDSourceGetNavXHeading implements PIDSource
{

	double previousAngle = 0.0;
	AHRS navx;
	public PIDSourceGetNavXHeading(AHRS navx) 
	{
		this.navx = navx;
	}
	@Override
	public double pidGet() 
	{	
		double angle = Utilities.unwrap(previousAngle, navx.getYaw(), 180.0);
		previousAngle = angle;
		return angle;
	}

}
