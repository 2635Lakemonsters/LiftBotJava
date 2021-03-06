package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class LiftPositionTwoMotor extends LiftPositionSingleMotor
{
	CANTalon CANLift2;
	public LiftPositionTwoMotor(CANTalon lift1, CANTalon lift2,
			double initialP, double initialI, double initialD,
			double upperLimit, double lowerLimit)
	{
		super(lift1, initialP, initialI, initialD, upperLimit, lowerLimit);
		CANLift2 = (CANTalon) lift2;
		CANLift2.setPID(initialP, initialI, initialD);
		CANLift2.changeControlMode(ControlMode.Follower);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void setElevation(Double setPoint)
	{
		// TODO Auto-generated method stub
		super.setElevation(setPoint);
		CANLift2.set(CANLift1.getDeviceID());
		
	}

}
