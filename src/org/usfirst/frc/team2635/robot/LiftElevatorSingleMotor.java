package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.CANTalon;


public class LiftElevatorSingleMotor extends LiftPositionSingleMotor
{

	public LiftElevatorSingleMotor(CANTalon lift1, boolean reverse,
			double initialP, double initialI, double initialD,
			double upperLimit, double lowerLimit)
	{
		super(lift1, reverse, initialP, initialI, initialD, upperLimit, lowerLimit);
		// TODO Auto-generated constructor stub
	}
	

}
