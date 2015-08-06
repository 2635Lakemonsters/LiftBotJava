package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ScaledJoystick extends Joystick 
{

	public ScaledJoystick(int port)
	{
		super(port);
		// TODO Auto-generated constructor stub
	}

	
	public JoystickData getOutput(double scaler) 
	{
		JoystickData joystickData = new JoystickData();
			
		if(getButtonCount() == 0 || getAxisCount() == 0)
		{
			joystickData.connected = false;
			return joystickData;
		}
		for(int i = 1; i < getButtonCount(); i++)
		{
			joystickData.buttons.add(getRawButton(i));
		}
		for(int i = 0; i < getAxisCount(); i++)
		{
			joystickData.axes.add(getRawAxis(i) * scaler);
		}
		joystickData.POVDirection = getPOV();
		return joystickData;
		// TODO Auto-generated method stub
	}
	
	
}
