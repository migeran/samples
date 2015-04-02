package com.migeran.demo.sample.graphics.planets;

import ios.coregraphics.struct.CGPoint;
import ios.coregraphics.struct.CGSize;

public class Vector2 {

	private double x, y;

	public Vector2() {
		x = y = 0.0f;
	}

	public Vector2(double e) {
		x = y = e;
	}

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void zero() {
		x = y = 0.0f;
	}

	public Vector2 diff(Vector2 v) {
		return new Vector2(x - v.getX(), y - v.getY());
	}

	public double length() {
		return Math.sqrt(x * x + y * y);
	}

	public Vector2 mul(double e) {
		x *= e;
		y *= e;
		return this;
	}

	public Vector2 add(Vector2 v) {
		x += v.x;
		y += v.y;
		return this;
	}

	public CGPoint getCGPoint() {
		return new CGPoint(x, y);
	}

	public void setCG(CGSize size) {
		x = size.width();
		y = size.height();
	}

	@Override
	public String toString() {
		return "( " + x + ", " + y + " )";
	}

	public Vector2 copy() {
		return new Vector2(x, y);
	}

}
