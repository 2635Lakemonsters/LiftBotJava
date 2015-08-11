package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.SpeedController;

public interface IActuator 
{
	void actuate(SpeedController controller, double input);

}
