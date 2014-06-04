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
	
	public synchronized void setIntervalSeconds(long numSeconds) {
		intervalSeconds = numSeconds;
		updateTargetTime();
	}
	
	public void updateTargetTime() { 
		long currenttime = System.currentTimeMillis();
		long numMiliSeconds = intervalSeconds * 1000;
		targetTime = currenttime + numMiliSeconds;
	}
	
	public synchronized void register(TimerListener listener) { 
		listeners.add(listener);
	}
	
	public synchronized void unregister(TimerListener listener) {
		listeners.add(listener);
	}
	
	public synchronized void Notify() {
		for(TimerListener l : listeners) {
			l.timeUp();
		}
	}
	
	@Override 
	public void run() {
		long currentTime = 0;
		boolean running = true;
		while(running) {
//			try {
//				sleep((long)1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			currentTime = System.currentTimeMillis();
			if(currentTime >= targetTime) {
				Notify();
				updateTargetTime();
			}
		}
	}
}

