package Robot;

import eventListeners.BoundaryListener;
import eventListeners.TimeLimitListener;
import eventListeners.TimerListener;
import eventListeners.TouchListener;
import eventListeners.UltrasonicListener;

public class Robot implements BoundaryListener, TimeLimitListener,
		TimerListener, TouchListener, UltrasonicListener {

	private RobotState currentState;
	private SoundPlayer soundPlayer;
	
	public Robot() { 
		currentState = RobotState.DORMANT;
		soundPlayer = new SoundPlayer();
		
	}
	
	public void start() {
		currentState = RobotState.SEARCHING;
	}
	
	@Override
	public void objectDetected()
	{
		
	}

	@Override
	public void pressed()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void released()
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
	DORMANT, 
	FORWARD, 
	BACKWARD, 
	SEARCHING, 
	PUSHING,
	FINISHED, 
	FAILED;
	
	public RobotState pressed(Robot robot) {
		return this;
	}
}
