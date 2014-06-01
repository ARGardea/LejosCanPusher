package eventManagers;

import java.util.ArrayList;
import java.util.List;

import eventListeners.TimerListener;

public class TimerManager extends Thread{
	private long targetTime;
	private List<TimerListener> listeners;

	
	public TimerManager(long numSeconds) { 
		setNumberOfSeconds(numSeconds);
		listeners = new ArrayList<TimerListener>();
		
		start();
	}
	
	public void setNumberOfSeconds(long numSeconds) {
		long currentTime = System.currentTimeMillis();
		long numMiliSeconds = numSeconds * 1000;
		targetTime = currentTime + numMiliSeconds;
	}
	
	public void register(TimerListener listener) {
		listeners.add(listener);
	}
	
	public void unregister(TimerListener listener) {
		listeners.remove(listener);
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
			}
		}
	}
}
