package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.SpeedController;

public abstract class Lift
{

	protected SpeedController lift1;
	//protected SpeedController lift2;
	protected Double setPoint = 0.0;
	public Lift(SpeedController lift1, boolean reverse)
	{
		super();
		this.lift1 = lift1;
		
		//this.lift2 = lift2;
		
	}
	public void setSetPoint(Double setPoint)
	{
		this.setPoint=setPoint;
		setElevation(setPoint);
	}
	
	public Double getSetPoint()
	{
		return setPoint;
	}
	//set this in extended classes
	protected abstract void setElevation(Double setPoint);
	
	
}
