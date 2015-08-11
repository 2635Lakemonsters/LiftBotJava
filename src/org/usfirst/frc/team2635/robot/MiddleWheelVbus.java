package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class MiddleWheelVbus implements IActuator
{

	@Override
	public void actuate(SpeedController controller, double input) 
	{
		controller.set(input);
		
	}
	
}
