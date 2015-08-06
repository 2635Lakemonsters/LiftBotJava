package hdrive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class HDrivePneumatic extends RobotDrive
{
	IActuator middleWheelStrategy;
	SpeedController middleWheel;
	DoubleSolenoid depressor;
	final double MIDDLE_WHEEL_JOYSTICK_TOLERANCE = 0.1;
	public HDrivePneumatic(SpeedController frontLeft, SpeedController frontRight,
			SpeedController rearLeft, SpeedController rearRight, SpeedController middleWheel, IActuator middleWheelStrategy,
			DoubleSolenoid depressor)
	{
		super(frontLeft, frontRight, rearLeft, rearRight);
		this.middleWheel = middleWheel;
		this.depressor = depressor;
		this.middleWheelStrategy = middleWheelStrategy;
	}
	public void drive(double X, double Y, double rotation)
	{
		if(X > MIDDLE_WHEEL_JOYSTICK_TOLERANCE || X < -MIDDLE_WHEEL_JOYSTICK_TOLERANCE)
		{
			depressor.set(Value.kForward);
			middleWheel.set(X);
			arcadeDrive(0, 0);
		}
		else
		{
			depressor.set(Value.kReverse);
			middleWheel.set(0);
			arcadeDrive(Y, rotation);
		}
	}
	
}