package org.usfirst.frc.team2635.robot;




public class Sensor<OutputType> 
{
	private IOutput<OutputType> outputStrategy;

	public Sensor(IOutput<OutputType> outputStrategy)
	{
		this.outputStrategy = outputStrategy;
		
	}

	public IOutput<OutputType> getOutputStrategy()
	{
		return outputStrategy;
	}

	public void setOutputStrategy(IOutput<OutputType> outputStrategy)
	{
		this.outputStrategy = outputStrategy;
	}

	public OutputType getOutput(Object parameter)
	{
		return outputStrategy.getOutput(parameter);
	}
	
	
	
}
