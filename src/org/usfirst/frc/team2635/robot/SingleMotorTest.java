package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class SingleMotorTest implements IInput<Double>
{
	SpeedController motor;
	public SingleMotorTest(SpeedController motor)
	{
		this.motor = motor;
	}
	@Override
	public void setInput(Double input)
	{
		motor.set((double)input);
	}

}
