package org.usfirst.frc.team2635.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScaledJoystickOneShot extends ScaledJoystick
{
	int[] oneShotIndexes;
	HashMap<Integer, Boolean> oneShotStates = new HashMap<Integer, Boolean>();
	public ScaledJoystickOneShot(int port, int[] oneShotIndexes) 
	{
		super(port);
		this.oneShotIndexes = oneShotIndexes;
		//Set up map for one shots
		for(int i : oneShotIndexes)
		{
			oneShotStates.put(i, false);
		}
	}
	@Override
	protected ArrayList<Boolean> getRawButtons()
	{
		ArrayList<Boolean> buttons = new ArrayList<Boolean>();
		for(int i = 1; i < getButtonCount(); i++)
		{
			
			if(oneShotStates.containsKey(i))
			{
				//This button has already been triggered, register it as false
				if(oneShotStates.get(i) == true)
				{
					
					buttons.add(false);
					//This button has been triggered, and has stopped being pressed, register
					//as false and set its state to false
					if(getRawButton(i) == false)
					{
						oneShotStates.put(i, false);
					}
				}
		
				else if(oneShotStates.get(i) == false)
				{
					//This button has not been triggered, and is now being triggered
					if(getRawButton(i) == true)
					{	
						oneShotStates.put(i, true);
						buttons.add(true);
					}
					//This button has not been pressed
					else
					{
						buttons.add(false);
					}
				}
			}
			else
			{
				buttons.add(getRawButton(i));
			}
		}
		return buttons;
	}

}
