package org.usfirst.frc.team2635.robot;



public class OutputThread<OutputType> extends Thread
{
	private IOutput<OutputType> outputStrategy;
	private OutputType output;
	
	public OutputThread(IOutput<OutputType> outputStrategy)
	{
		this.setOutputStrategy(outputStrategy);
	}
	

	public IOutput<OutputType> getOutputStrategy()
	{
		return outputStrategy;
	}

	public void setOutputStrategy(IOutput<OutputType> outputStrategy)
	{
		this.outputStrategy = outputStrategy;
	}

	public OutputType getOutput()
	{
		return output;
	}

	
	
	
}
