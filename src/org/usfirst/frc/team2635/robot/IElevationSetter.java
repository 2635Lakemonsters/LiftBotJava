package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.SpeedController;

public interface IElevationSetter 
{
	void setLiftElevation(Double setPoint, SpeedController lift1, SpeedController lift2);
}
