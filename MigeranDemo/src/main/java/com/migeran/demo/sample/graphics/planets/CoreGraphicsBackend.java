package com.migeran.demo.sample.graphics.planets;

import ios.coregraphics.struct.CGRect;
import ios.uikit.UIBezierPath;
import ios.uikit.UIColor;
import ios.uikit.UIView;

import com.migeran.natj.general.Pointer;
import com.migeran.natj.objc.ann.Selector;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.NatJ;

public class CoreGraphicsBackend extends UIView {

	static {
		NatJ.register();
	}

	private Simulation simulation;

	@Generated("NatJ")
	@Selector("alloc")
	public static native CoreGraphicsBackend alloc();

	@Generated("NatJ")
	@Selector("init")
	public native CoreGraphicsBackend init();

	@Generated("NatJ")
	@Selector("initWithFrame:")
	public native CoreGraphicsBackend initWithFrame(CGRect frame);

	@Generated("NatJ")
	protected CoreGraphicsBackend(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("drawRect:")
	public void drawRect(CGRect rect) {
		UIColor.blackColor().set();
		UIBezierPath.bezierPathWithRect(this.bounds()).fill();

		if (simulation == null) {
			return;
		}

		UIColor.orangeColor().set();
		for (Planet p : simulation.getPlanets()) {
			UIBezierPath
					.bezierPathWithArcCenterRadiusStartAngleEndAngleClockwise(p
							.getLocation().getCGPoint(), p.getRadius(), 0,
							2 * Simulation.PI, true).fill();
		}
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}
