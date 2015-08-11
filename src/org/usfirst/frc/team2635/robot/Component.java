package org.usfirst.frc.team2635.robot;



public class Component<InputType>
{
	IInput<InputType> inputStrategy;
	InputType input;
	public Component(IInput<InputType> inputStrategy, InputType initialInput)
	{
		this.input = initialInput;
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
		inputStrategy.setInput(input);
	}

}
