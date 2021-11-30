package Utilities;
import java.util.Timer;
import java.util.TimerTask;

public class TimerCountDown {

	private static int time = 0;
	private static Timer t = new Timer();
	private static TimerCountDown timerCountdown;
	private static boolean ispaused = false;

	public static void main(String[] args) {
		t.schedule(new TimerTask() {
			public void run() {
				if(ispaused) {
					return;
				}
				time++;
			}
		}, 0, 1000);

		timerCountdown = new TimerCountDown();

		try {
			timerCountdown.startGame(); Thread.sleep(5000); timerCountdown.endGame();
			timerCountdown.startGame(); Thread.sleep(5000); timerCountdown.endGame();
		} catch(Exception e){
			
		}
	}

	private void startGame()
	{
		time = 0;
		ispaused = false;
	}

	private void endGame()
	{
		time = 0;
		ispaused = true;
	}
}
