package com.migeran.demo.sample.graphics.planets;

import java.util.ArrayList;

public class Simulation {

	public static final double PI = Math.PI;

	private final int planetCount;

	private final ArrayList<Planet> planets;

	private final ArrayList<Vector2> forces;

	public Simulation(int planet_count) {
		planetCount = planet_count;
		forces = new ArrayList<Vector2>(planet_count);
		planets = new ArrayList<Planet>(planet_count);
		for (int i = 0; i < planet_count; ++i) {
			forces.add(new Vector2());
			planets.add(new Planet());
		}
	}

	public void tick(double time) {
		for (int i = 0; i < planetCount; ++i) {
			for (int o = 0; o < planetCount; ++o) {
				if (i != o) {
					_tick(time, i, o);
				}
			}
		}
		for (int i = 0; i < planetCount; ++i) {
			Planet p = planets.get(i);
			Vector2 f = forces.get(i);

			p.getMomentum().add(f);
			p.getLocation().add(p.getMomentum().copy().mul(time));
		}
		for (Vector2 force : forces) {
			force.zero();
		}
	}

	private void _tick(double time, int i, int o) {
		final Planet a = planets.get(i);
		final Planet b = planets.get(o);

		Vector2 diff = b.getLocation().diff(a.getLocation());
		double d = diff.length();

		if (d > a.getRadius()) {
			double ra = a.getRadius();
			double fa = 4.0 * ra * ra * ra * PI / 3.0;
			double rb = b.getRadius();
			double fb = 4.0 * rb * rb * rb * PI / 3.0;

			double e = 0.2 * (fa * fb) / (d * d) / fa * time;

			forces.get(i).add(diff.mul(e));
		}
	}

	public ArrayList<Planet> getPlanets() {
		return planets;
	}

}
