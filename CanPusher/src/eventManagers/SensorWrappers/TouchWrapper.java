package eventManagers.SensorWrappers;

import lejos.nxt.TouchSensor;
import eventManagers.ITouchable;

public class TouchWrapper implements ITouchable{
	private TouchSensor touch;
	
	public TouchWrapper(TouchSensor touch){
		this.touch = touch;
	}
	
	@Override
	public boolean isPressed() {
		// TODO Auto-generated method stub
		return touch.isPressed();
	}

}
