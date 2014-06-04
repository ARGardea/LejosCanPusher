package Robot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eventListeners.IntervalTimerListener;
import eventManagers.IntervalTimeManager;
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

public class SoundPlayer extends Thread implements IntervalTimerListener{
	SoundType currentSound;
	IntervalTimeManager sequenceTimer;
	long intervalTime;
	public SoundPlayer(){
		currentSound = SoundType.MUTE;
		sequenceTimer = new IntervalTimeManager(0);
	}
	public IntervalTimeManager getTimer(){
		return sequenceTimer;
	}
	public void stopPlaying(){
		setSoundType(SoundType.MUTE);
	}
	
	public synchronized void setIntervalTime(long intervalTime){
		this.intervalTime = intervalTime;
	}
	
	public void setSoundType(SoundType sound){
		this.currentSound = sound;
		System.out.println("soundType " + sound.toString());
		currentSound.startUp(this);
	}
	
	public void run(){
		System.out.println("~MUUUUUUSIC!!");
		while(true){
			currentSound.playSound();
			try {
				this.sleep(intervalTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void fire() {
		// TODO Auto-generated method stub
		System.out.println("soundPlayer Fired!");
		currentSound.playSound();
	}
}

enum SoundType {

	/*
	 * Enumeration SoundType Mute [override]playSound [override]startUp stop
	 * timer SolidTone, [override] playSound play low, second-long tone
	 * [override]startUp set timer interval to 1 second start timer Beeping,
	 * [override] playSound play high, brief tone every half-second [override]
	 * startUp set timer interval to ½ second start timer Celebratory,
	 * [override] playSound play tone sequence [override] startUp stop timer
	 * 
	 * abstract playSound abstract startUp
	 */

	MUTE {

		@Override
		public void playSound() {
			// TODO Auto-generated method stub
			// null behavior
		}

		@Override
		public void startUp(SoundPlayer sound) {
			// TODO Auto-generated method stub
		}
	},
	SOLIDTONE {

		@Override
		public void playSound() {
			// TODO Auto-generated method stub
			// play a second-long solid tone
			Sound.playTone(200, 1000);
		}

		@Override
		public void startUp(SoundPlayer sound) {
			// TODO Auto-generated method stub
			sound.setIntervalTime(900);
		}
	},
	BEEPING {

		@Override
		public void playSound() {
			// TODO Auto-generated method stub
			// short, high tone
			Sound.playTone(500, 100);
		}

		@Override
		public void startUp(SoundPlayer sound) {
			// TODO Auto-generated method stub
			sound.setIntervalTime(500);
		}
	},
	CELEBRATORY {
		soundSequence seq;
		Iterator<Integer> ite;

		@Override
		public void playSound() {
			// TODO Auto-generated method stub
			if(ite.hasNext()){
				Sound.playTone(ite.next(), 100);
			}
		}

		@Override
		public void startUp(SoundPlayer sound) {
			// TODO Auto-generated method stub
			seq = new soundSequence();
			List<Integer> list = new ArrayList<Integer>();
			Integer[] l = {300, 400, 0, 600};
			for(Integer i: l){
				list.add(i);
			}
			seq.setSequence(list);
			ite = seq.iterator();
			sound.setIntervalTime(500);
		}
	};

	public abstract void playSound();

	public abstract void startUp(SoundPlayer sound);
}

class soundSequence implements Iterable<Integer>{
	List<Integer>  noteSequence;
	long timeIntervalMillis = 500;
	public void setSequence(List<Integer> sequence){
		this.noteSequence = sequence;
	}
	
	public void setTimeIntervalMillis(long timeIntervalMillis){
		this.timeIntervalMillis = timeIntervalMillis;
	}
	
	public long getTimeIntervalMillis(){
		return timeIntervalMillis;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		soundSequenceIterator iterator = new soundSequenceIterator();
		iterator.setNoteSequenceReference(noteSequence);
		return iterator;
	}
	
}

class soundSequenceIterator implements Iterator<Integer>{
	List<Integer> noteSequenceReference;
	int currentIndex = 0;
	
	public void setNoteSequenceReference(List<Integer> noteSequence){
		this.noteSequenceReference = noteSequence;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return currentIndex < noteSequenceReference.size() - 1;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		Integer note = noteSequenceReference.get(currentIndex);
		currentIndex++;
		return note;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
