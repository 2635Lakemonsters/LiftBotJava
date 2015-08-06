package lift;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Arms
{
	DoubleSolenoid armSolenoid;
	public Arms(DoubleSolenoid solenoid)
	{
		armSolenoid = solenoid;
	}
	
	public void set(Value direction)
	{
		armSolenoid.set(direction);
	}
}
