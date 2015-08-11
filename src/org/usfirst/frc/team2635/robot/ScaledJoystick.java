package org.usfirst.frc.team2635.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;

public class ScaledJoystick extends Joystick 
{

	public ScaledJoystick(int port)
	{
		super(port);
		// TODO Auto-generated constructor stub
	}

	protected ArrayList<Boolean>getRawButtons()
	{
		ArrayList<Boolean> buttons = new ArrayList<Boolean>();
		for(int i = 1; i < getButtonCount(); i++)
		{
			buttons.add(getRawButton(i));
		}
		return buttons;
	}
	protected ArrayList<Double> getScaledAxes(double scaler)
	{
		ArrayList<Double> scaledAxes = new ArrayList<Double>();
		for(int i = 0; i < getAxisCount(); i++)
		{
			scaledAxes.add(getRawAxis(i) * scaler);
		}
		return scaledAxes;
		
	}
	public JoystickData getOutput(double scaler) 
	{
		JoystickData joystickData = new JoystickData();
			
		if(getButtonCount() == 0 || getAxisCount() == 0)
		{
			joystickData.connected = false;
			return joystickData;
		}
		joystickData.buttons = getRawButtons();
		joystickData.axes = getScaledAxes(scaler);
		joystickData.POVDirection = getPOV();
		return joystickData;
		// TODO Auto-generated method stub
	}
	
	
}
