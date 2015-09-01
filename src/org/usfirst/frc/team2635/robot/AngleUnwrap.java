package org.usfirst.frc.team2635.robot;

public class AngleUnwrap 
{
     double previousAngle = 0.0;
     double accumulator = 0.0;
	 public double unwrap(double angle, double tolerance)
	 {
		 accumulator += getDelta(previousAngle, angle, tolerance);
		 previousAngle = angle;
		 return accumulator;
	 }
	 private double getDelta(double previousAngle, double currentAngle, double tolerance) 
	  {
		  double delta = currentAngle - previousAngle;
		  if(Math.abs(delta) > tolerance)
		  {
			  return delta - tolerance;
	      }
		  return  delta;

	  }
}
