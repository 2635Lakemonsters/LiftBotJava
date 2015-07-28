package org.usfirst.frc.team2635.robot;



public class OutputThread<OutputType> extends Thread
{
	private IOutput outputStrategy;
	private OutputType output;
	
	public OutputThread(IOutput outputStrategy)
	{
		this.setOutputStrategy(outputStrategy);
	}
	

	public IOutput getOutputStrategy()
	{
		return outputStrategy;
	}

	public void setOutputStrategy(IOutput outputStrategy)
	{
		this.outputStrategy = outputStrategy;
	}

	public OutputType getOutput()
	{
		return output;
	}

	
	
	
}