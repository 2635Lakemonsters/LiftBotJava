package org.usfirst.frc.team2635.robot;

public class PID
{
	public PID(double p, double i, double d)
	{
		this.p = p;
		this.i = i;
		this.d = d;
	}
	public PID()
	{
		this.p = 0;
		this.i = 0;
		this.d = 0;
	}
	public double p;
	public double i;
	public double d;
}
