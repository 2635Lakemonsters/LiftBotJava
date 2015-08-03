package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Servo;

public class SimpleServo implements IInput<Double>
{
	Servo servo;
	public SimpleServo(Servo servo) 
	{
		super();
		this.servo = servo;
	}
	@Override
	public void setInput(Double input)
	{
		servo.set(input);
	}
	
}
