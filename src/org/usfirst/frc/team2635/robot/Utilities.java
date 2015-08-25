package org.usfirst.frc.team2635.robot;

public class Utilities 
{

	  public static double wrapPosNeg180(double fAng) 
	  {
	     return fAng - 360 * Math.floor((fAng + 180) / 360);

	  }
}
