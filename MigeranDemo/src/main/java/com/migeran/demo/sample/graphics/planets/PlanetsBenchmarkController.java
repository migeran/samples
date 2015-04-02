package com.migeran.demo.sample.graphics.planets;

import ios.foundation.NSNotificationCenter;
import ios.foundation.NSThread;
import ios.uikit.UIAlertView;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UIDevice;
import ios.uikit.UIFont;
import ios.uikit.UIViewController;
import ios.uikit.enums.NSLayoutAttribute;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UIControlState;
import ios.uikit.enums.UIDeviceOrientation;
import ios.uikit.enums.UIRectEdge;
import ios.uikit.enums.UIUserInterfaceIdiom;

import java.util.Date;

import ui.SampleTableViewController;

import com.migeran.demo.util.UIBuilder;
import com.migeran.demo.util.UIBuilder.UIButtonCusomisation;
import com.migeran.demo.util.UILayout;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.Selector;

public class PlanetsBenchmarkController extends UIViewController {

	static {
		NatJ.register();
	}

	private int DIFFICULTY = 1000;

	private UIButton rb_1k;
	private UIButton rb_2k;
	private UIButton rb_4k;
	private UIButton rb_8k;

	private UILayout vertical_layout;
	private UILayout horizontal_layout;

	private Date start;
	private Date end;
	private boolean isRunning = false;

	@Generated("NatJ")
	@Selector("alloc")
	public static native PlanetsBenchmarkController alloc();

	@Selector("init")
	public native PlanetsBenchmarkController init();

	@Generated("NatJ")
	protected PlanetsBenchmarkController(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Planets - Benchmark");

		setEdgesForExtendedLayout(UIRectEdge.None);
		view().setBackgroundColor(UIColor.whiteColor());

		UIBuilder builder = new UIBuilder(this);

		builder.setDefaultButtonCustomisation(new UIButtonCusomisation() {

			@Override
			public void cusomiseButton(UIButton button) {
				button.setBackgroundColor(UIColor.lightGrayColor());
				button.setTitleColorForState(UIColor.blackColor(),
						UIControlState.Normal);
				button.layer().setCornerRadius(3.0);
			}
		});

		rb_1k = builder.addButton("Run Benchmark - 1k", "b1k");
		rb_2k = builder.addButton("Run Benchmark - 2k", "b2k");
		rb_4k = builder.addButton("Run Benchmark - 4k", "b4k");
		rb_8k = builder.addButton("Run Benchmark - 8k", "b8k");

		rb_1k.addTargetActionForControlEvents(this, new SEL("benchmark_1k"),
				UIControlEvents.TouchUpInside);
		rb_2k.addTargetActionForControlEvents(this, new SEL("benchmark_2k"),
				UIControlEvents.TouchUpInside);
		rb_4k.addTargetActionForControlEvents(this, new SEL("benchmark_4k"),
				UIControlEvents.TouchUpInside);
		rb_8k.addTargetActionForControlEvents(this, new SEL("benchmark_8k"),
				UIControlEvents.TouchUpInside);

		vertical_layout = horizontal_layout = builder.newLayout();

		horizontal_layout.add("H:|-[b1k]-[b2k]-|");
		horizontal_layout.add("H:|-[b4k]-[b8k]-|");

		horizontal_layout.add("V:[topGuide]-[b1k]-[b4k]-[bottomGuide]");
		horizontal_layout.add("V:[topGuide]-[b2k]-[b8k]-[bottomGuide]");

		horizontal_layout.setEqual(NSLayoutAttribute.Width, "b1k", "b2k");
		horizontal_layout.setEqual(NSLayoutAttribute.Width, "b4k", "b8k");

		horizontal_layout.setEqual(NSLayoutAttribute.Height, "b1k", "b4k");
		horizontal_layout.setEqual(NSLayoutAttribute.Height, "b2k", "b8k");

		if (UIDevice.currentDevice().userInterfaceIdiom() == UIUserInterfaceIdiom.Phone) {
			vertical_layout = builder.newLayout();

			vertical_layout.add("H:|-[b1k]-|");

			vertical_layout.add("V:[topGuide]-[b1k]-[b2k]-[b4k]-[b8k]-[bottomGuide]");

			vertical_layout.setEqual(NSLayoutAttribute.Width, "b1k", "b2k",
					"b4k", "b8k");
			vertical_layout.setEqual(NSLayoutAttribute.Height, "b1k", "b2k",
					"b4k", "b8k");
			vertical_layout.setEqual(NSLayoutAttribute.CenterX, "b1k", "b2k",
					"b4k", "b8k");
		} else {
			rb_1k.titleLabel().setFont(UIFont.boldSystemFontOfSize(32.0));
			rb_2k.titleLabel().setFont(UIFont.boldSystemFontOfSize(32.0));
			rb_4k.titleLabel().setFont(UIFont.boldSystemFontOfSize(32.0));
			rb_8k.titleLabel().setFont(UIFont.boldSystemFontOfSize(32.0));
		}

		if (interfaceOrientation() == UIDeviceOrientation.Portrait
				|| interfaceOrientation() == UIDeviceOrientation.PortraitUpsideDown) {
			vertical_layout.activate(null);
		} else {
			horizontal_layout.activate(null);
		}
	}

	@Override
	@Selector("willRotateToInterfaceOrientation:duration:")
	public void willRotateToInterfaceOrientationDuration(
			@NInt long toInterfaceOrientation, double duration) {
		// TODO: Deprecated, use viewWillTransitionToSize:withTransitionCoordinator:
		super.willRotateToInterfaceOrientationDuration(toInterfaceOrientation, duration);

		if (vertical_layout == horizontal_layout) {
			return;
		}
		if (toInterfaceOrientation == UIDeviceOrientation.Portrait) {
			vertical_layout.activate(horizontal_layout);
		} else {
			horizontal_layout.activate(vertical_layout);
		}
	}

	@Selector("benchmark_1k")
	public void benchmark_1k() {
		DIFFICULTY = 1000;
		benchmark();
	}

	@Selector("benchmark_2k")
	public void benchmark_2k() {
		DIFFICULTY = 2000;
		benchmark();
	}

	@Selector("benchmark_4k")
	public void benchmark_4k() {
		DIFFICULTY = 4000;
		benchmark();
	}

	@Selector("benchmark_8k")
	public void benchmark_8k() {
		DIFFICULTY = 8000;
		benchmark();
	}

	public synchronized void benchmark() {
		disableInteraction();
		NSThread thread = NSThread.alloc().initWithTargetSelectorObject(this,
				new SEL("_bench"), null);
		thread.setName("com.migeran.planets.bench");
		thread.start();
	}

	@Selector("_bench")
	public void _bench() {
		start = new Date();
		Simulation simulation = new Simulation(DIFFICULTY);
		simulation.tick(1.0 / 30.0);
		end = new Date();
		this.performSelectorOnMainThreadWithObjectWaitUntilDone(new SEL(
				"_benchEnded"), null, false);
	}

	@Selector("_benchEnded")
	public synchronized void _benchEnded() {
		double duration = (double) (end.getTime() - start.getTime()) / 1000.0;

		UIAlertView alert = UIAlertView.alloc().init();
		alert.setMessage("Score: " + (1.0 / (duration / (double) DIFFICULTY)));
		alert.addButtonWithTitle("Close");
		alert.setCancelButtonIndex(0);
		alert.show();

		enableInteraction();
	}

	private void disableInteraction() {
		navigationController().navigationBar().setUserInteractionEnabled(false);
		rb_1k.setUserInteractionEnabled(false);
		rb_2k.setUserInteractionEnabled(false);
		rb_4k.setUserInteractionEnabled(false);
		rb_8k.setUserInteractionEnabled(false);

		switch (DIFFICULTY) {
		case 1000:
			rb_1k.setTitleForState("Benchmarking...", UIControlState.Normal);
			break;
		case 2000:
			rb_2k.setTitleForState("Benchmarking...", UIControlState.Normal);
			break;
		case 4000:
			rb_4k.setTitleForState("Benchmarking...", UIControlState.Normal);
			break;
		case 8000:
			rb_8k.setTitleForState("Benchmarking...", UIControlState.Normal);
			break;

		default:
			break;
		}

		NSNotificationCenter dc = (NSNotificationCenter) NSNotificationCenter
				.defaultCenter();
		dc.postNotificationNameObject(
				SampleTableViewController.DISABLE_USER_INTERACTION, null);
	}

	private void enableInteraction() {
		NSNotificationCenter dc = (NSNotificationCenter) NSNotificationCenter
				.defaultCenter();
		dc.postNotificationNameObject(
				SampleTableViewController.ENABLE_USER_INTERACTION, null);

		switch (DIFFICULTY) {
		case 1000:
			rb_1k.setTitleForState("Run Benchmark - 1k", UIControlState.Normal);
			break;
		case 2000:
			rb_2k.setTitleForState("Run Benchmark - 2k", UIControlState.Normal);
			break;
		case 4000:
			rb_4k.setTitleForState("Run Benchmark - 4k", UIControlState.Normal);
			break;
		case 8000:
			rb_8k.setTitleForState("Run Benchmark - 8k", UIControlState.Normal);
			break;

		default:
			break;
		}

		navigationController().navigationBar().setUserInteractionEnabled(true);
		rb_1k.setUserInteractionEnabled(true);
		rb_2k.setUserInteractionEnabled(true);
		rb_4k.setUserInteractionEnabled(true);
		rb_8k.setUserInteractionEnabled(true);
	}

}
