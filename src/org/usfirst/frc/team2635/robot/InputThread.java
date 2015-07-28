package org.usfirst.frc.team2635.robot;


public class InputThread<InputType> extends Thread
{
	IInput inputStrategy;
	InputType input;
	public InputThread(IInput inputStrategy)
	{
		this.inputStrategy = inputStrategy;
	}
	
	public IInput getInputStrategy()
	{
		return inputStrategy;
	}
	public void setInputStrategy(IInput inputStrategy)
	{
		this.inputStrategy = inputStrategy;
	}
	public void setInput(InputType input)
	{
		this.input = input;
	}
	
}
