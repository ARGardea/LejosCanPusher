package eventManagers;

import java.util.ArrayList;
import java.util.List;

import eventListeners.TimerListener;

public class IntervalTimeManager extends Thread {

	private long targetTime;
	private long intervalSeconds;
	List<TimerListener> listeners;
	
	public IntervalTimeManager(long numSeconds) {
		setIntervalSeconds(numSeconds);
		
		listeners = new ArrayList<TimerListener>();
		start();
	}
	
	public void setIntervalSeconds(long numSeconds) {
		intervalSeconds = numSeconds;
	}
	
	public void updateTargetTime() { 
		long currenttime = System.currentTimeMillis();
		long numMiliSeconds = intervalSeconds * 1000;
		targetTime = currenttime + numMiliSeconds;
	}
	
	public void register(TimerListener listener) { 
		listeners.add(listener);
	}
	
	public void unregister(TimerListener listener) {
		listeners.add(listener);
	}
	
	public void Notify() {
		for(TimerListener l : listeners) {
			l.timeUp();
		}
	}
	
	@Override 
	public void run() {
		long currentTime = 0;
		boolean running = true;
		while(running) {
			currentTime = System.currentTimeMillis();
			if(currentTime >= targetTime) {
				Notify();
				updateTargetTime();
			}
		}
	}
}

