package algorithms;

import java.util.Vector;

/**
 * a state in the search tree
 * @author amit ofer & liron katav
 *
 */
public class State implements Comparable<State>{
	
	private Vector<Point> _Coordinates;
	private State _parent;
	private float _cost;
	private float _heuristic;
	
	public State(Vector<Point> coordinates){
		this._Coordinates = coordinates;
		this._cost = 0;
		this._heuristic = 0;
		this._parent = null;
	}
	
	public void set_state(Vector<Point> _state) {
		this._Coordinates = _state;
	}
	public Vector<Point> get_Coordinates() {
		return _Coordinates;
	}
	public void set_parent(State _parent) {
		this._parent = _parent;
	}
	public State get_parent() {
		return _parent;
	}
	public void set_cost(float _cost) {
		this._cost = _cost;
	}
	public float get_cost() {
		return _cost;
	}
	public void set_heuristic(float _heuristic) {
		this._heuristic = _heuristic;
	}
	public float get_heuristic() {
		return _heuristic;
	}
	public float get_f(){
		return this._heuristic + this._cost;
	}
	@Override
	public int compareTo(State other) {
		float f = this.get_f();
		float of = other.get_f();
		if (f < of) {
			return -1;
		} else if (f > of) {
			return 1;
		} else {
			return 0;
		}
	}
	public boolean equals(State other){
		boolean ans = true;
		for (int i = 0;i < this._Coordinates.size();i++){
			if (!this._Coordinates.elementAt(i).equals(other.get_Coordinates().elementAt(i))){
				ans = false;
				break;
			}
		}
		return ans;
	}
}
