package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class SimpleSpike implements IInput<Boolean> 
{
	Relay relay;
	public SimpleSpike(Relay relay)
	{
		this.relay = relay;
	}
	@Override
	public void setInput(Boolean input)
	{
		
		relay.set(input?Value.kForward:Value.kOff);
	}
	
}
