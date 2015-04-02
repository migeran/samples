package com.migeran.demo.sample.graphics.planets;

import ios.foundation.NSArray;
import ios.foundation.NSDictionary;
import ios.foundation.NSTimer;
import ios.uikit.NSLayoutConstraint;
import ios.uikit.UIColor;
import ios.uikit.UIView;
import ios.uikit.UIViewController;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.Selector;

public class PlanetsController extends UIViewController {

	static {
		NatJ.register();
	}

	private Simulation simulation;

	private NSTimer timer;

	private UIView renderer;

	@Generated("NatJ")
	@Selector("alloc")
	public static native PlanetsController alloc();

	@Selector("init")
	public native PlanetsController init();

	@Generated("NatJ")
	protected PlanetsController(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Planets");

		view().setBackgroundColor(UIColor.whiteColor());

		renderer = CoreGraphicsBackend.alloc().initWithFrame(view().bounds());
		view().addSubview(renderer);

		renderer.setTranslatesAutoresizingMaskIntoConstraints(false);
		NSDictionary views = NSDictionary.dictionaryWithObjectForKey(renderer,
				"renderer");
		NSArray constrs = NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews(
						"|-0-[renderer]-0-|", 0, NSDictionary.dictionary(),
						views);
		view().addConstraints(constrs);
		constrs = NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews(
						"V:|-0-[renderer]-0-|", 0, NSDictionary.dictionary(),
						views);
		view().addConstraints(constrs);
		view().layoutSubviews();
	}

	@Override
	@Selector("viewWillAppear:")
	public void viewWillAppear(boolean animated) {
		super.viewWillAppear(animated);

		if (timer != null) {
			timer.invalidate();
		}
		timer = NSTimer
				.scheduledTimerWithTimeIntervalTargetSelectorUserInfoRepeats(
						1.0 / 30.0, this, new SEL("tick:"), null, true);
	}

	@Override
	@Selector("viewDidDisappear:")
	public void viewDidDisappear(boolean animated) {
		if (timer != null) {
			timer.invalidate();
			timer = null;
		}

		super.viewDidDisappear(animated);
	}

	@Selector("tick:")
	public void tick(final NSTimer timer) {
		if (simulation == null) {
			Planet.MAX_LOCATION.setCG(view().bounds().size());
			simulation = new Simulation(30);
			((CoreGraphicsBackend) renderer).setSimulation(simulation);
		}
		simulation.tick(timer.timeInterval());
		renderer.setNeedsDisplay();
	}

}
