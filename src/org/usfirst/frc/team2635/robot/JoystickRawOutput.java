package org.usfirst.frc.team2635.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickRawOutput extends Joystick implements IOutput<JoystickData>
{

	public JoystickRawOutput(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JoystickData getOutput() {
		// TODO Auto-generated method stub
		JoystickData joystickData = new JoystickData();
		for(int i = 0; i < getButtonCount(); i++)
		{
			joystickData.buttons.add(getRawButton(i));
		}
		for(int i = 0; i < getAxisCount(); i++)
		{
			joystickData.axes.add(getRawAxis(i));
		}
		return joystickData;
	}

}
