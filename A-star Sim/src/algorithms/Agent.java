package algorithms;

public class Agent {
	
	private Point _start;
	private Point _end;
	private Point _current;
	
	public void set_start(Point _start) {
		this._start = _start;
	}
	public Point get_start() {
		return _start;
	}
	public void set_end(Point _end) {
		this._end = _end;
	}
	public Point get_end() {
		return _end;
	}
	public void set_current(Point _current) {
		this._current = _current;
	}
	public Point get_current() {
		return _current;
	}
}
