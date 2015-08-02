package org.usfirst.frc.team2635.robot;

import java.util.concurrent.locks.Lock;



public class OutputThread<OutputType> extends Thread
{
	private IOutput<OutputType> outputStrategy;
	private OutputType output;
	private Object parameter;
	private Lock outputLock;

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
	public OutputType getOutput(Object parameter)
	{
		this.parameter = parameter;
		return output;
	}
	public void setParameter(Object parameter)
	{
		this.parameter=parameter;
	}
	public Object getParameter()
	{
		return parameter;
	}
	@Override
	public void run()
	{
		while(true)
		{
			//This will prevent the outside from grabbing output before output computation is finished
			outputLock.lock();
			try
			{
				output = outputStrategy.getOutput(parameter);
			}
			finally
			{
				outputLock.unlock();
			}
			
		}
	}
	
}
