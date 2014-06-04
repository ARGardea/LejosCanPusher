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
	}
	
	public synchronized void setNumberOfSeconds(long numSeconds) {
		long currentTime = System.currentTimeMillis();
		long numMiliSeconds = numSeconds * 1000;
		targetTime = currentTime + numMiliSeconds;
	}
	
	public synchronized void register(TimerListener listener) {
		listeners.add(listener);
	}
	
	public synchronized void unregister(TimerListener listener) {
		listeners.remove(listener);
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
				System.out.println("hit targetTime!");
			}
		}
	}
}
