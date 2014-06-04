package eventManagers;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.ColorSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;
import eventListeners.*;

public class BoundaryManager extends Thread{
	List<BoundaryListener> listeners;
	LightSensor light;
	
	public BoundaryManager(){
		light = new LightSensor(SensorPort.S1);
		listeners = new ArrayList<BoundaryListener>();
		start();
	}
	
	@Override
	public void run(){
		int currentLightValue = light.readNormalizedValue();
		int lastLightValue = currentLightValue;
		int desiredLightValue = currentLightValue;
		int lightUpdateCounter = 0;
		while(true){
//			try {
//				sleep((long)1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			currentLightValue = light.readNormalizedValue();
//			System.out.println("current: " + currentLightValue);
//			System.out.println("last: " + lastLightValue);
//			System.out.println("desired: " + desiredLightValue);
//			System.out.println(Math.abs(currentLightValue) - lastLightValue);
//			System.out.println(Math.abs(currentLightValue) - desiredLightValue);
			//System.out.println(Math.abs(currentLightValue - lastLightValue));
			if(Math.abs(currentLightValue - desiredLightValue) > 45){
				//if(Math.abs(currentLightValue - desiredLightValue) > 20){
					Notify();
					//System.out.println("boundary spotted!");
					//lightUpdateCounter = 0;
				//}
			}
//			lightUpdateCounter++;
//			if(lightUpdateCounter > 100){
//				System.out.println("light update!");
//				lightUpdateCounter = 0;
//				lastLightValue = currentLightValue;
//			}
		}
	}
	
	public synchronized void Notify(){
		for(BoundaryListener listener: listeners){
			listener.boundaryDetected();
		}
	}
	
	public synchronized void register(BoundaryListener listener){
		listeners.add(listener);
	}
	
	public synchronized void unRegister(BoundaryListener listener){
		listeners.remove(listener);
	}
}
