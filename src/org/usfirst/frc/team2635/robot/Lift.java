package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class Lift
{

	SpeedController lift1;
	SpeedController lift2;
	IElevationSetter elevationSetStrategy;
	private Double setPoint = 0.0;
	public Lift(SpeedController lift1, SpeedController lift2, IElevationSetter elevationSetStrategy)
	{
		super();
		this.lift1 = lift1;
		this.lift2 = lift2;
		this.elevationSetStrategy = elevationSetStrategy;
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
	private void setElevation(Double setPoint)
	{
		elevationSetStrategy.setLiftElevation(setPoint, lift1, lift2);
	}
	
	
}
