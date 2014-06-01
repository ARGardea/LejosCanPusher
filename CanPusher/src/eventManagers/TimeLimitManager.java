package eventManagers;

import java.util.ArrayList;
import java.util.List;

import eventListeners.TimeLimitListener;
public class TimeLimitManager extends Thread{
	private long targetTime;
	private List<TimeLimitListener> listeners;
	
	public TimeLimitManager(long numSeconds) {
		long currentTime = System.currentTimeMillis();
		long numMiliSeconds = numSeconds * 1000;
		targetTime = currentTime + numMiliSeconds;
		listeners = new ArrayList<TimeLimitListener>();
		
		start();
	}
	
	public void register(TimeLimitListener listener) { 
		listeners.add(listener);
	}
	
	public void unregister(TimeLimitListener listener) { 
		listeners.remove(listener);
	}
	
	public void Notify() {
		for(TimeLimitListener l : listeners) {
			l.timerOut();
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
				running = false;
			}
		}
	}
	
}
