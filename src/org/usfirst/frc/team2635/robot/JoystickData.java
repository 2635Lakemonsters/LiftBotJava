package org.usfirst.frc.team2635.robot;

import java.util.ArrayList;

public class JoystickData
{
	public JoystickData()
	{
		axes = new ArrayList<Double>();
		buttons = new ArrayList<Boolean>();
		POVDirection = -1;
		connected = true;
	}
	ArrayList<Double> axes;
	ArrayList<Boolean> buttons;
	int POVDirection;
	boolean connected;
}
