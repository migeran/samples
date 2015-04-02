package com.migeran.demo.sample.graphics.planets;

import ios.c.Globals;

public class Planet {

	public static final Vector2 MIN_LOCATION = new Vector2();
	public static final Vector2 MAX_LOCATION = new Vector2();
	public static final double MIN_RADIUS = 1.0f;
	public static final double MAX_RADIUS = 10.0f;
	public static final double MIN_SPEED = 0.0f;
	public static final double MAX_SPEED = 0.0f;

	private final double radius = newRandomFloat(MIN_RADIUS, MAX_RADIUS);

	private final Vector2 momentum = new Vector2();

	private final Vector2 location = newRandomVector2(MIN_LOCATION,
			MAX_LOCATION);

	private static double newRandomFloat(double min, double max) {
		return min + (max - min)
				* ((double) (Globals.random() % 16384) / 16384.0);
	}

	private static Vector2 newRandomVector2(Vector2 min, Vector2 max) {
		double x = newRandomFloat(min.getX(), max.getX());
		double y = newRandomFloat(min.getY(), max.getY());
		return new Vector2(x, y);
	}

	public double getRadius() {
		return radius;
	}

	public Vector2 getMomentum() {
		return momentum;
	}

	public Vector2 getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "{ " + radius + ", " + location + ", " + momentum + " }";
	}

}
