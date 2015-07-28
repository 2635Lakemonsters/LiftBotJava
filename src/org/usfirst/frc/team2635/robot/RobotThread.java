package org.usfirst.frc.team2635.robot;



public class RobotThread extends Thread
{
	private IOutput outputStrategy;
	private Object output;
	
	public RobotThread()
	{
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			output = outputStrategy.getOutput();
		}
		
	}

	public IOutput getOutputStrategy()
	{
		return outputStrategy;
	}

	public void setOutputStrategy(IOutput outputStrategy)
	{
		this.outputStrategy = outputStrategy;
	}

	public Object getOutput()
	{
		return output;
	}

	public void setOutput(Object output)
	{
		this.output = output;
	}

	
	
}
