package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class LiftVbus implements IInput<LiftInput>
{

	SpeedController lift1;
	SpeedController lift2;
	public LiftVbus(SpeedController lift1, SpeedController lift2)
	{
		super();
		this.lift1 = lift1;
		this.lift2 = lift2;
	}
	
	@Override
	public void setInput(LiftInput input)
	{
		//In this case, setpoint is between -1 and +1
		lift1.set(input.setPoint);
		
		//Motors are mirrored, so negate one
		lift2.set(-input.setPoint);
		
	}
	
	
}
