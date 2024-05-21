package game.math;

public class Range<T> {
	private T start; 
	private T end; 
	
	public Range(T start, T end) {
		this.start = start;
		this.end = end;
	}
	
	public Range(T start) {
		this.start = start;
		this.end = start;
	}
}
