package eventManagers;

import java.util.ArrayList;
import java.util.List;

import eventListeners.TouchListener;

public class TouchManager extends Thread{
	private List<TouchListener> listeners;
	
	public TouchManager() { 
		listeners = new ArrayList<TouchListener>();
		start();
	}
	
	public void register(TouchListener listener) {
		listeners.add(listener);
	}
	
	public void unregister(TouchListener listener) {
		listeners.remove(listener);
	}
	
	public void notify(boolean isPressed) {
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
		
		//TOUCH SENSOR? 
		boolean isPressed = false;
		
		while(true) {
			isPressed = touchSensor.isPressed();
			if(wasPressed != isPressed) {
				notify(isPressed);
			}
		}
	}
	
}

	
