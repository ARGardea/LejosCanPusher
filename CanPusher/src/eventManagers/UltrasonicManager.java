package eventManagers;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import eventListeners.UltrasonicListener;

public class UltrasonicManager extends Thread{
	List<UltrasonicListener> listeners;
	
	public UltrasonicManager(){
		listeners = new ArrayList<UltrasonicListener>();
		start();
	}
	
	@Override
	public void run(){
		UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S2);
		boolean itemWasDetected = false;
		boolean itemIsDetected = false;
		while(true){
//			try {
//				sleep((long)1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			itemIsDetected = (sensor.getDistance() < 50);
			if(itemWasDetected != itemIsDetected){
				itemWasDetected = itemIsDetected;
				if(itemWasDetected){
					Notify();
				}
			}
		}
	}
	
	public synchronized void Notify(){
		for(UltrasonicListener listener: listeners){
			listener.objectDetected();
		}
	}
	
	public synchronized void register(UltrasonicListener listener){
		listeners.add(listener);
	}
	
	public synchronized void unRegister(UltrasonicListener listener){
		listeners.remove(listener);
	}
}
