
package Robot;

import eventListeners.BoundaryListener;
import eventListeners.TimeLimitListener;
import eventListeners.TimerListener;
import eventListeners.TouchListener;
import eventListeners.UltrasonicListener;
import lejos.nxt.*;
import main.Startup;

public class Robot implements BoundaryListener, TimeLimitListener,
		TimerListener, TouchListener, UltrasonicListener {

	private RobotState currentState;
	private SoundPlayer soundPlayer;
	
	int cansPushed = 0;
	
	public Robot() { 
		currentState = RobotState.DORMANT;
		soundPlayer = new SoundPlayer();
		setSpeed(250);
		soundPlayer.setSoundType(SoundType.CELEBRATORY);
		soundPlayer.start();
	}
	
	public int getCansPushed(){
		return cansPushed;
	}
	
	public void setCansPushed(int cansPushed){
		this.cansPushed = cansPushed;
	}
	
	public void incrementCansPushed(){
		cansPushed++;
	}
	
	public void start() {
		currentState = RobotState.SEARCHING;
		rotate();
	}
	
	public SoundPlayer getSoundPlayer(){
		return soundPlayer;
	}
	
	public void setSpeed(int speed){
		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);
	}
	
	public void rotate() {
		Motor.B.forward();
		Motor.C.backward();
	}

	public void forward() { 
		Motor.B.backward();
		Motor.C.backward();
	}
	
	public void backward() {
		Motor.B.forward();
		Motor.C.forward();
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
		System.out.println("robot time up!");
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
	DORMANT {
		@Override
		public void enter(Robot robot) {
			System.out.println(this.toString());
			// TODO Auto-generated method stub
			
		}

		@Override
		public void exit(Robot robot) {
			// TODO Auto-generated method stub
			
		}
	}, 
	FORWARD {
		@Override
		public void enter(Robot robot) {
			System.out.println(this.toString());
			// TODO Auto-generated method stub
			robot.forward();
		}

		@Override
		public void exit(Robot robot) {
			// TODO Auto-generated method stub
			robot.stop();
		}
		
		@Override
		public RobotState pressed(Robot robot){
			this.exit(robot);
			RobotState newState = RobotState.PUSHING;
			newState.enter(robot);
			return newState;
		}
		
		@Override
		public RobotState boundaryDetected(Robot robot){
			this.exit(robot);
			RobotState newState = RobotState.BACKWARD;
			Startup.robotTimer.setNumberOfSeconds(2);
			newState.enter(robot);
			return newState;
		}
	}, 
	BACKWARD {
		@Override
		public void enter(Robot robot) {
			System.out.println(this.toString());
			// TODO Auto-generated method stub
			robot.getSoundPlayer().setSoundType(SoundType.BEEPING);
			robot.backward();
		}

		@Override
		public void exit(Robot robot) {
			// TODO Auto-generated method stub
			robot.stop();
			robot.getSoundPlayer().stopPlaying();
		}
		
		@Override
		public RobotState timeUp(Robot robot){
			this.exit(robot);
			RobotState newState = RobotState.SEARCHING;
			newState.enter(robot);
			return newState;
		}
		
//		@Override
//		public RobotState boundaryDetected(Robot robot){
//			this.exit(robot);
//			RobotState newState = RobotState.FORWARD;
//			newState.enter(robot);
//			Startup.robotTimer.setNumberOfSeconds(1);
//			return newState;
//		}
		
	}, 
	SEARCHING {
		@Override
		public void enter(Robot robot) {
			// TODO Auto-generated method stub
			Startup.robotTimer.setNumberOfSeconds(5);
			robot.rotate();
		}

		@Override
		public void exit(Robot robot) {
			// TODO Auto-generated method stub
			robot.stop();
		}

		@Override
		public RobotState timeUp(Robot robot) {
			this.exit(robot);
			RobotState newState = RobotState.FINISHED;
			newState.enter(robot);
			return newState;
		}
		
		@Override
		public RobotState objectDetected(Robot robot){
			this.exit(robot);
			RobotState newState = RobotState.FORWARD;
			newState.enter(robot);
			return newState;
		}
	}, 
	PUSHING {
		@Override
		public void enter(Robot robot) {
			// TODO Auto-generated method stub
			robot.forward();
			robot.getSoundPlayer().setSoundType(SoundType.SOLIDTONE);
		}
		
		@Override
		public RobotState boundaryDetected(Robot robot){
			this.exit(robot);
			RobotState newState;
			robot.incrementCansPushed();
			if(robot.getCansPushed() >= 3){
				newState = RobotState.FINISHED;
			}else{
				newState = RobotState.BACKWARD;
				Startup.robotTimer.setNumberOfSeconds(1);
			}
			newState.enter(robot);
			return newState;
		}
		
//		@Override
//		public RobotState released(Robot robot){
//			this.exit(robot);
//			System.out.println("released!");
//			RobotState newState = RobotState.FORWARD;
//			newState.enter(robot);
//			return newState;
//		}
		
//		@Override
//		public RobotState pressed(Robot robot){
//			this.exit(robot);
//			RobotState newState = RobotState.PUSHING;
//			newState.enter(robot);
//			return newState;
//		}

		@Override
		public void exit(Robot robot) {
			// TODO Auto-generated method stub
			robot.getSoundPlayer().stopPlaying();
			robot.stop();
		}
	},
	FINISHED {
		@Override
		public void enter(Robot robot) {
			// TODO Auto-generated method stub
			Startup.robotTimer.setNumberOfSeconds(5);
			robot.getSoundPlayer().setSoundType(SoundType.CELEBRATORY);
		}

		@Override
		public void exit(Robot robot) {
			// TODO Auto-generated method stub
		}

		@Override
		public RobotState timeUp(Robot robot) {
			System.exit(0);
			return this;
		}
	}, 
	FAILED {
		@Override
		public void enter(Robot robot) {
			// TODO Auto-generated method stud
			System.out.println("Mission Failed. . .");
			Startup.robotTimer.setNumberOfSeconds(5);
			robot.getSoundPlayer().setSoundType(SoundType.BEEPING);
		}
		
		@Override
		public RobotState timeUp(Robot robot){
			System.exit(0);
			return this;
		}

		@Override
		public void exit(Robot robot) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public abstract void enter(Robot robot);
	public abstract void exit(Robot robot);
	
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
		RobotState newState = this;
		if(this != RobotState.FINISHED){
			this.exit(robot);
			newState = RobotState.FAILED;
			newState.enter(robot);
		}
		return newState;
	}
	
	public RobotState boundaryDetected(Robot robot) {
		return this;
	}
	
	public RobotState objectDetected(Robot robot) {
		return this;
	}
}
