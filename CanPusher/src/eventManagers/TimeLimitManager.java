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
	}
	
	public synchronized void register(TimeLimitListener listener) { 
		listeners.add(listener);
	}
	
	public synchronized void unregister(TimeLimitListener listener) { 
		listeners.remove(listener);
	}
	
	public synchronized void Notify() {
		for(TimeLimitListener l : listeners) {
			l.timerOut();
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
				running = false;
			}
		}
	}
	
}
