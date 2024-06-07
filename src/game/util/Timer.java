package game.util;

import game.entity.Entity;
import game.state.GamestateManager;

public class Timer {
	
	private static GamestateManager gsm = GamestateManager.getInstance();

	private double elapsed;
	private double period;
	
	private Object object;
	
	public enum TimerState {
		START,
		END;
	}
	
	public Timer(double period, Object object, TimerState timerState) {
		this.period = period;
		this.object = object;
		
		switch(timerState) {
		case START:
			this.elapsed = 0;
			break;
		case END:
			this.elapsed = period;
			break;
		}
		
		gsm.getState().getTimers().add(this);
	}
	
	public Timer(double period, Object object) {
		this(period, object, TimerState.START);
	}

	public void update(double dt) {
		if (object == null) {
			gsm.getState().getTimers().remove(this);
		} else {
			elapsed += dt;
		}
	}
	
	public void reset() {
		elapsed = 0;
	}
	
	public boolean isOver() {
		return elapsed >= period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}
}
