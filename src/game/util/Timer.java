package game.util;

public class Timer {

	private double elapsed;
	private double period;
	
	public Timer(double period) {
		this.elapsed = 0;
		this.period = period;
	}

	public void update(double dt) {
		elapsed += dt;
	}
	
	public void reset() {
		elapsed = 0;
	}
	
	public boolean isOver() {
		if (elapsed > period) {
			elapsed = 0;
			return true;
		} else {
			return false;
		}
	}

	public void setPeriod(double period) {
		this.period = period;
	}
}
