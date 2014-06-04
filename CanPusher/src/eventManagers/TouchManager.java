package eventManagers;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.*;
import eventListeners.TouchListener;

public class TouchManager extends Thread{
	private List<TouchListener> listeners;
	
	public TouchManager() { 
		listeners = new ArrayList<TouchListener>();
		start();
	}
	
	public synchronized void register(TouchListener listener) {
		listeners.add(listener);
	}
	
	public synchronized void unregister(TouchListener listener) {
		listeners.remove(listener);
	}
	
	public synchronized void notify(boolean isPressed) {
		if(isPressed) {
			for(TouchListener l : listeners) {
				l.pressed();
			}
		} else {
			for(TouchListener l : listeners) { 
				l.released();
			}
		}
	}
	
	@Override
	public void run() {
		boolean wasPressed = false;
		TouchSensor sensor = new TouchSensor(SensorPort.S4);
		boolean isPressed = false;
		
		while(true) {
//			try {
//				sleep((long)1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			isPressed = sensor.isPressed();
			if(wasPressed != isPressed) {
				notify(isPressed);
			}
		}
	}
	
}

	
