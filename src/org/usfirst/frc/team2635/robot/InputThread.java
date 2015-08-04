package org.usfirst.frc.team2635.robot;

import java.util.concurrent.locks.Lock;


public class InputThread<InputType> extends Thread
{
	IInput<InputType> inputStrategy;
	InputType input;
	public InputThread(IInput<InputType> inputStrategy)
	{
		this.inputStrategy = inputStrategy;
	}
	
	public IInput<InputType> getInputStrategy()
	{
		return inputStrategy;
	}
	public void setInputStrategy(IInput<InputType> inputStrategy)
	{
		this.inputStrategy = inputStrategy;
	}
	public void setInput(InputType input)
	{
		this.input = input;
	}
	@Override
	public void run()
	{
		while(true)
		{
			synchronized(this)
			{
				inputStrategy.setInput(input);
			}
			
		}
	}	
}
