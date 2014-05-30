package Robot;

import eventListeners.BoundaryListener;
import eventListeners.TimeLimitListener;
import eventListeners.TimerListener;
import eventListeners.TouchListener;
import eventListeners.UltrasonicListener;

public class Robot implements BoundaryListener, TimeLimitListener,
		TimerListener, TouchListener, UltrasonicListener {

	@Override
	public void objectdetected()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Pressed()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Released()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void timeUp()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void timerOut()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boundaryDetected()
	{
		// TODO Auto-generated method stub
		
	}
	
}

enum RobotState{
	
}
