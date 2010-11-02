package algorithms;



public class Point {
	/** The x coordinate at the given step */
	private int x;
	/** The y coordinate at the given step */
	private int y;
	
	/**
	 * Create a new step
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x coordinate of the new step
	 * 
	 * @return The x coodindate of the new step
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y coordinate of the new step
	 * 
	 * @return The y coodindate of the new step
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @see Object#equals(Object)
	 */
	public boolean equals(Point other) {
			return (other.x == x) && (other.y == y);
	}
}
