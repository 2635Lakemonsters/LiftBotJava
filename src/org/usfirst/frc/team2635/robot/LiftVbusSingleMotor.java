package org.usfirst.frc.team2635.robot;


import edu.wpi.first.wpilibj.SpeedController;

public class LiftVbusSingleMotor extends Lift
{
	

	public LiftVbusSingleMotor(SpeedController lift1, boolean reverse)
	{
		super(lift1);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setElevation(Double setPoint)
	{
		
		lift1.set(setPoint);
		
		
	}

}
