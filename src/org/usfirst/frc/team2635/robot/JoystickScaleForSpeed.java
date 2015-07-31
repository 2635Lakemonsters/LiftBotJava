package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickScaleForSpeed extends Joystick implements IOutput<JoystickData>
{

	public JoystickScaleForSpeed(int port)
	{
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JoystickData getOutput()
	{
		JoystickData joystickData = new JoystickData();
		for(int i = 0; i < getButtonCount(); i++)
		{
			joystickData.buttons.add(getRawButton(i));
		}
		for(int i = 0; i < getAxisCount(); i++)
		{
			joystickData.axes.add(getRawAxis(i) * 100.0);
		}
		return joystickData;

		
	}


	
}
