package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.CANTalon;


public class LiftElevatorSingleMotor extends LiftPositionSingleMotor
{
	
	public LiftElevatorSingleMotor(CANTalon lift1, boolean reverse,
			double initialP, double initialI, double initialD, double[] levels)
	{
		super(lift1, reverse, initialP, initialI, initialD, levels[levels.length - 1], levels[0]);
		// TODO Auto-generated constructor stub
	}
	

}
