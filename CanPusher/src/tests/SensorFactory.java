package tests;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import eventManagers.ITouchable;
import eventManagers.SensorWrappers.TouchWrapper;

public class SensorFactory {
	public static ITouchable getTouchable(){
		TouchSensor touch = new TouchSensor(SensorPort.S4);
		TouchWrapper wrapper = new TouchWrapper(touch);
		return wrapper;
	}
}
