package org.usfirst.frc.team2635.robot;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class LiftPositionSingleMotor extends Lift
{
	CANTalon CANLift1;
	double upperLimit;
	double lowerLimit;
	boolean overrideLimits = false;
	public boolean isOverrideLimits()
	{
		return overrideLimits;
	}
	public void setOverrideLimits(boolean overrideLimits)
	{
		this.overrideLimits = overrideLimits;
	}
	public LiftPositionSingleMotor(CANTalon lift1, double initialP, double initialI, double initialD, double upperLimit, double lowerLimit)
	{
		super(lift1);
		//Convert lift motors from SpeedController to CANTalon to access pid functions
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		CANLift1 = (CANTalon) lift1;
		
		
		setPID(initialP, initialI, initialD);
		CANLift1.changeControlMode(ControlMode.Position);
	}
	public double getUpperLimit()
	{
		return upperLimit;
	}
	public void setUpperLimit(double upperLimit)
	{
		this.upperLimit = upperLimit;
	}
	public double getLowerLimit()
	{
		return lowerLimit;
	}
	public void setLowerLimit(double lowerLimit)
	{
		this.lowerLimit = lowerLimit;
	}
	public void setPID(double p, double i, double d)
	{
		CANLift1.setPID(p, i, d);
	}
	protected double restrain(double setPoint, double upperLimit, double lowerLimit)
	{

		if(setPoint > upperLimit)
		{
			return upperLimit;
		}
		else if(setPoint < lowerLimit)
		{
			return lowerLimit;	
		}
		return setPoint;
	}
	@Override
	public void setSetPoint(Double setPoint) 
	{
		if(overrideLimits)
		{
			this.setPoint=setPoint;
		}
		else
		{
			this.setPoint = restrain(setPoint, this.upperLimit, this.lowerLimit);
		}
		setElevation(this.setPoint);
	}
	@Override
	protected void setElevation(Double setPoint)
	{
		
		CANLift1.set(setPoint);
		
	}
	
}
