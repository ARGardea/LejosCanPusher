package Robot;

import java.util.Iterator;
import java.util.List;

import lejos.nxt.Sound;

/*
 * SoundPlayer, implements IntervalTimerListener
	SoundType playedSound
	IntervalTimerManager timer
	[override]Fire()
		playSound()
	getTimerManager()
		return timer
	SoundPlayer()
		let playedSound be SoundType Mute
	setSoundType(SoundType sound)
		let playedSound be equal to sound
	startPlaying()
	playSound()
		call SoundType’s playSound
	stopPlaying()
		let playedSound be equal to SoundType Mute
 */

public class SoundPlayer {
	SoundType currentSound;
	public SoundPlayer(){
		
	}
	public void Fire(){
		currentSound.playSound();
	}
	public void stopPlaying(){
		
	}
	public void setSoundType(SoundType sound){
		this.currentSound = sound;
		currentSound.startUp();
	}
	
}

enum SoundType{
	
	/*
	    Enumeration SoundType
		Mute
			[override]playSound
			[override]startUp
				stop timer
		SolidTone,
			[override] playSound 	
				play low, second-long tone
			[override]startUp
				set timer interval to 1 second
				start timer
		Beeping,
			[override] playSound
				play high, brief tone every half-second
			[override] startUp
				set timer interval to ½ second
				start timer
		Celebratory,
			[override] playSound
				play tone sequence
			[override] startUp
				stop timer

		abstract playSound
		abstract startUp

	 */
	
	Mute{

		@Override
		public void playSound() {
			// TODO Auto-generated method stub
			//null behavior
		}

		@Override
		public void startUp() {
			// TODO Auto-generated method stub
			//stop the timer
		}},
	SolidTone{

		@Override
		public void playSound() {
			// TODO Auto-generated method stub
			//play a second-long solid tone
		}

		@Override
		public void startUp() {
			// TODO Auto-generated method stub
			//start .8 second timer
		}},
	Beeping{

		@Override
		public void playSound() {
			// TODO Auto-generated method stub
			//short, high tone
		}

		@Override
		public void startUp() {
			// TODO Auto-generated method stub
			//set timer to 1/4, 1/3, or 1/2 second and start
		}},
	Celebratory{

		@Override
		public void playSound() {
			// TODO Auto-generated method stub
			 Sound.playTone(300,500);
		     Sound.playTone(400,500);
		     Sound.playTone(500,1000);
		}

		@Override
		public void startUp() {
			// TODO Auto-generated method stub
			//stop timer
		}};
	
	public abstract void playSound();
	public abstract void startUp();
}

class soundSequence implements Iterable{
	List<Integer>  noteSequence;
	long timeIntervalMillis = 500;
	public void setSequence(List<Integer> sequence){
		
	}
	
	public void setTimeIntervalMillis(long timeIntervalMillis){
		this.timeIntervalMillis = timeIntervalMillis;
	}
	
	public long getTimeIntervalMillis(){
		return timeIntervalMillis;
	}
	
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class soundSequenceIterator implements Iterator{
	List<Integer> noteSequenceReference;
	int currentIndex = 0;
	
	public void setNoteSequenceReference(List<Integer> noteSequence){
		this.noteSequenceReference = noteSequence;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
