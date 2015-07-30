package org.usfirst.frc.team2635.robot;

import java.util.ArrayList;

public class JoystickData
{
	public JoystickData()
	{
		axes = new ArrayList<Double>();
		buttons = new ArrayList<Boolean>();
	}
	ArrayList<Double> axes;
	ArrayList<Boolean> buttons;
}
