package main;

import eventManagers.*;
import Robot.Robot;

public class Startup {
	public static TimerManager robotTimer;
	
	public static void main(String[] args) {
		TouchManager touch = new TouchManager();
		UltrasonicManager sonic = new UltrasonicManager();
		BoundaryManager boundary = new BoundaryManager();
		TimeLimitManager minuteTimer = new TimeLimitManager(60);
		robotTimer = new TimerManager(100);
		Robot robot = new Robot();
		touch.register(robot);
		sonic.register(robot);
		boundary.register(robot);
		robotTimer.register(robot);
		minuteTimer.register(robot);
		minuteTimer.start();
		robotTimer.start();
		robot.start();
	}

}
