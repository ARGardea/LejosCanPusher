
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
		rotate();
	}
	
	public void rotate() {
		Motor.B.forward();
		Motor.C.backward();
	}

	public void forward() { 
		Motor.B.forward();
		Motor.C.forward();
	}
	
	public void backward() {
		Motor.B.backward();
		Motor.C.backward();
	}
	
	public void stop() {
		Motor.B.stop();
		Motor.C.stop();
	}
	@Override
	public void objectDetected() {
		currentState = currentState.objectDetected(this);
	}

	@Override
	public void pressed() {
		currentState = currentState.pressed(this);
	}

	@Override
	public void released() {
		currentState = currentState.released(this);
	}

	@Override
	public void timeUp() {
		currentState = currentState.timeUp(this);
	}

	@Override
	public void timerOut() {
		currentState = currentState.timerOut(this);
	}

	@Override
	public void boundaryDetected() {
		currentState = currentState.boundaryDetected(this);
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
	
	public RobotState released(Robot robot) {
		return this;
	}
	
	public RobotState timeUp(Robot robot) {
		return this;
	}
	
	public RobotState timerOut(Robot robot) {
		return this;
	}
	
	public RobotState boundaryDetected(Robot robot) {
		return this;
	}
	
	public RobotState objectDetected(Robot robot) {
		return this;
	}
}
