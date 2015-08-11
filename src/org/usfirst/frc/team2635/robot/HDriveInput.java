package org.usfirst.frc.team2635.robot;

public class HDriveInput
{
	public HDriveInput(double y, double x, double rotation)
	{
		super();
		Y = y;
		X = x;
		this.rotation = rotation;
	}
	public HDriveInput()
	{
		Y = 0;
		X = 0;
		rotation = 0;
	}
	public double Y;
	public double X;
	public double rotation;

	//TODO: add sensors for pid when ready
}
