package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class Button implements IOutput<Boolean>
{
	DigitalInput button;
	public Button(DigitalInput button)
	{
		this.button = button;
	}
	@Override
	public Boolean getOutput(Object parameter) {
		// TODO Auto-generated method stub
		return button.get();
	}
	
}
