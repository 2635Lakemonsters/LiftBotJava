package org.usfirst.frc.team2635.robot;




public class OutputThread<OutputType> extends Thread
{
	private IOutput<OutputType> outputStrategy;
	private OutputType output;
	private Object parameter;

	public OutputThread(IOutput<OutputType> outputStrategy, Object parameter)
	{
		this.outputStrategy = outputStrategy;
		this.parameter = parameter;
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
			synchronized(this)
			{
				output = outputStrategy.getOutput(parameter);
			}
		
			
		}
	}
	
}
