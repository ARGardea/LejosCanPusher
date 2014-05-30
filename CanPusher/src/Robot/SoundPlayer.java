package Robot;

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

public class SoundPlayer {

}

enum SoundType{
	
}
