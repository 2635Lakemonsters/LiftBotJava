package org.usfirst.frc.team2635.robot;

import java.util.ArrayList;
import java.util.Arrays;

public class OneShotRising<T>
{
	ArrayList<T> risingValues;
	T fallingValue;
	boolean triggered = false;
	public OneShotRising(ArrayList<T> risingValues, T fallingValue)
	{
		this.risingValues = risingValues;
		this.fallingValue = fallingValue;
	}
	/**
	 * Will perform one shot logic on the value passed to it. 
	 * 
	 * @param currentValue
	 * @return
	 */
	public T getValue(T currentValue)
	{
		if(triggered)
		{	
			for(T value : risingValues)
			{
				//trigger has been hit, and is still being hit. Return falling value
				if(value == currentValue)
				{
					return fallingValue;
				}
				
			}
			//trigger has been hit, and is not longer being hit. Reset the one shot.
			triggered = false;
			return fallingValue;

		}
		else
		{
			for(T value : risingValues)
			{
				//trigger has been hit, return rising value and set triggered to true
				if(value == currentValue)
				{
					triggered = true;
					return value;
				}
			}
			//trigger has not been hit
			return fallingValue;
		}
	}
}
