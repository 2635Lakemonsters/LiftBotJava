package org.usfirst.frc.team2635.robot;

public class Utilities 
{
	  
	  public static double unwrap(double previousAngle, double currentAngle, double tolerance) 
	  {
		  double delta = currentAngle - previousAngle;
		  if(Math.abs(delta) > tolerance)
		  {
			  return currentAngle + (delta - tolerance);
		  }
		  return currentAngle + delta;

	  }
}
