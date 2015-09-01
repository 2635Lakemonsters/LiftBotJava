package org.usfirst.frc.team2635.robot;

import java.util.ArrayList;
import java.util.Arrays;

public class OneShotRising<T>
{
	ArrayList<T> risingValues;
	T fallingValue;
	boolean triggered = false;
	public OneShotRising(T risingValue, T fallingValue)
	{
		this.risingValues = new ArrayList<T>(Arrays.asList(risingValue));
		this.fallingValue = fallingValue;
	}
	public OneShotRising(ArrayList<T> risingValues, T fallingValue)
	{
		this.risingValues = risingValues;
		this.fallingValue = fallingValue;
	}
	/**
	 * Will perform one shot logic on the value passed to it. Values that are in between rising
	 * and falling will be treated as falling values
	 * @param currentValue 
	 * @return The rising or falling value
	 */
	public T getValue(T currentValue)
	{
		if(triggered)                                     
		{	
			for(T value : risingValues)
			{
				System.out.println("Rising" + value);
				System.out.println("Rising Current" + currentValue);
				//trigger has been hit, and is still being hit. Return falling value
				if(value == currentValue)
				{
					System.out.println("Rising, hit");
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
					System.out.println(value);
					System.out.println("Fallen, hit");
					triggered = true;
					return value;
				}
			}
			//trigger has not been hit
			return fallingValue;
		}
	}
}
