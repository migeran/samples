package com.migeran.demo.sample.utility.calculator;

import ios.foundation.struct.NSRange;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UIDevice;
import ios.uikit.UIFont;
import ios.uikit.UITextField;
import ios.uikit.UIView;
import ios.uikit.UIView.Block_animateWithDurationAnimations;
import ios.uikit.UIViewController;
import ios.uikit.enums.NSLayoutAttribute;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UIControlState;
import ios.uikit.enums.UIDeviceOrientation;
import ios.uikit.enums.UIRectEdge;
import ios.uikit.enums.UITextAlignment;
import ios.uikit.enums.UIUserInterfaceIdiom;
import ios.uikit.protocol.UITextFieldDelegate;

import com.migeran.demo.category.UIColorExt;
import com.migeran.demo.util.UIBuilder;
import com.migeran.demo.util.UIBuilder.UIButtonCusomisation;
import com.migeran.demo.util.UIBuilder.UITextFieldCusomisation;
import com.migeran.demo.util.UILayout;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.ByValue;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.Selector;

public class CalcController extends UIViewController implements
		UITextFieldDelegate {

	static {
		NatJ.register();
	}

	private double mem = 0;
	private double tmp = 0;
	private String op = "=";

	private UITextField field_mem;
	private UITextField field_tmp;
	private UITextField field_op;

	private UILayout vertical_layout;
	private UILayout horizontal_layout;

	@Generated("NatJ")
	@Selector("alloc")
	public static native CalcController alloc();

	@Generated("NatJ")
	@Selector("init")
	public native CalcController init();

	@Generated("NatJ")
	protected CalcController(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Calculator");

		setEdgesForExtendedLayout(UIRectEdge.None);
		view().setBackgroundColor(UIColor.whiteColor());

		UIBuilder builder = new UIBuilder(this);

		builder.setDefaultButtonCustomisation(new UIButtonCusomisation() {

			@Override
			public void cusomiseButton(UIButton button) {
				button.setTitleColorForState(UIColor.blackColor(),
						UIControlState.Normal);
				button.titleLabel().setFont(UIFont.boldSystemFontOfSize(32.0f));
				button.addTargetActionForControlEvents(CalcController.this,
						new SEL("action:"), UIControlEvents.TouchUpInside);
			}
		});

		builder.setDefaultTextFieldCustomisation(new UITextFieldCusomisation() {

			@Override
			public void cusomiseTextField(UITextField textfield) {
				textfield.setBackgroundColor(UIColor.whiteColor());
				textfield.layer().setCornerRadius(3.0f);
				UIColorExt.setAsCALayerBorderColor(UIColor.lightGrayColor(),
						textfield.layer());
				textfield.layer().setBorderWidth(1.0f);
			}
		});

		field_mem = builder.addTextField("field_mem");
		field_tmp = builder.addTextField("field_tmp");
		field_op = builder.addTextField("field_op");

		field_mem.setTextAlignment(UITextAlignment.Right);
		field_tmp.setTextAlignment(UITextAlignment.Right);
		field_op.setTextAlignment(UITextAlignment.Center);

		field_mem.setDelegate_unsafe(this);
		field_tmp.setDelegate_unsafe(this);
		field_op.setDelegate_unsafe(this);

		_clr_mem();
		_clr_tmp();

		builder.addButton(CalcButton.class, "1", "n1").setHighlightColor(
				UIColor.orangeColor());
		builder.addButton(CalcButton.class, "2", "n2").setHighlightColor(
				UIColor.orangeColor());
		builder.addButton(CalcButton.class, "3", "n3").setHighlightColor(
				UIColor.orangeColor());
		builder.addButton(CalcButton.class, "4", "n4").setHighlightColor(
				UIColor.orangeColor());
		builder.addButton(CalcButton.class, "5", "n5").setHighlightColor(
				UIColor.orangeColor());
		builder.addButton(CalcButton.class, "6", "n6").setHighlightColor(
				UIColor.orangeColor());
		builder.addButton(CalcButton.class, "7", "n7").setHighlightColor(
				UIColor.orangeColor());
		builder.addButton(CalcButton.class, "8", "n8").setHighlightColor(
				UIColor.orangeColor());
		builder.addButton(CalcButton.class, "9", "n9").setHighlightColor(
				UIColor.orangeColor());
		builder.addButton(CalcButton.class, "0", "n0").setHighlightColor(
				UIColor.orangeColor());

		builder.addButton(CalcButton.class, "+", "add").setHighlightColor(
				UIColor.blueColor());
		builder.addButton(CalcButton.class, "-", "sub").setHighlightColor(
				UIColor.blueColor());
		builder.addButton(CalcButton.class, "*", "mul").setHighlightColor(
				UIColor.blueColor());
		builder.addButton(CalcButton.class, "/", "div").setHighlightColor(
				UIColor.blueColor());
		builder.addButton(CalcButton.class, "=", "eq").setHighlightColor(
				UIColor.blueColor());

		vertical_layout = horizontal_layout = builder.newLayout();
		_setupVerticalLayout(vertical_layout);

		if (UIDevice.currentDevice().userInterfaceIdiom() == UIUserInterfaceIdiom.Phone) {
			horizontal_layout = builder.newLayout();
			_setupHorizontalLayout(horizontal_layout);
		}

		if (interfaceOrientation() == UIDeviceOrientation.Portrait
				|| interfaceOrientation() == UIDeviceOrientation.PortraitUpsideDown) {
			vertical_layout.activate(null);
		} else {
			horizontal_layout.activate(null);
		}
	}

	private void _setupHorizontalLayout(UILayout layout) {
		layout.add("H:|-[field_mem]-|");
		layout.add("H:|-[field_op]-[field_tmp]-|");
		layout.add("H:|-[n7]-[n8]-[n9]-[div]-[mul]-|");
		layout.add("H:|-[n4]-[n5]-[n6]-[add]-[sub]-|");
		layout.add("H:|-[n1]-[n2]-[n3]-[n0]-[eq]-|");

		layout.add("V:[topGuide]-[field_mem]-[field_tmp]-[n7]-[n4]-[n1]-[bottomGuide]");
		layout.add("V:[topGuide]-[field_mem]-[field_tmp]-[n8]-[n5]-[n2]-[bottomGuide]");
		layout.add("V:[topGuide]-[field_mem]-[field_tmp]-[n9]-[n6]-[n3]-[bottomGuide]");
		layout.add("V:[topGuide]-[field_mem]-[field_tmp]-[div]-[add]-[n0]-[bottomGuide]");
		layout.add("V:[topGuide]-[field_mem]-[field_tmp]-[mul]-[sub]-[eq]-[bottomGuide]");

		layout.setEqual(NSLayoutAttribute.Width, "n7", "n8", "n9", "div", "mul");
		layout.setEqual(NSLayoutAttribute.Width, "n4", "n5", "n6", "add", "sub");
		layout.setEqual(NSLayoutAttribute.Width, "n1", "n2", "n3", "n0", "eq");
		layout.setConstant(NSLayoutAttribute.Width, 30.0f, field_op);

		layout.setEqual(NSLayoutAttribute.Height, "n1", "n4", "n7");
		layout.setEqual(NSLayoutAttribute.Height, "n2", "n5", "n8");
		layout.setEqual(NSLayoutAttribute.Height, "n3", "n6", "n8");
		layout.setEqual(NSLayoutAttribute.Height, "n0", "add", "div");
		layout.setEqual(NSLayoutAttribute.Height, "eq", "sub", "mul");
		
		layout.setConstant(NSLayoutAttribute.Height, 30.0f, field_mem,
				field_tmp, field_op);

		layout.setEqual(NSLayoutAttribute.Baseline, field_tmp, field_op);
	}

	private void _setupVerticalLayout(UILayout layout) {
		layout.add("H:|-[field_mem]-|");
		layout.add("H:|-[field_op]-[field_tmp]-|");
		layout.add("H:|-[n7]-[n8]-[n9]-[div]-|");
		layout.add("H:|-[n4]-[n5]-[n6]-[mul]-|");
		layout.add("H:|-[n1]-[n2]-[n3]-[add]-|");
		layout.add("H:|-[n0]-[eq]-[sub]-|");

		layout.add("V:[topGuide]-[field_mem]-[field_tmp]-[n7]-[n4]-[n1]-[n0]-[bottomGuide]");
		layout.add("V:[topGuide]-[field_mem]-[field_tmp]-[n8]-[n5]-[n2]-[n0]-[bottomGuide]");
		layout.add("V:[topGuide]-[field_mem]-[field_tmp]-[n9]-[n6]-[n3]-[eq]-[bottomGuide]");
		layout.add("V:[topGuide]-[field_mem]-[field_tmp]-[div]-[mul]-[add]-[sub]-[bottomGuide]");

		layout.setEqual(NSLayoutAttribute.Width, "n7", "n8", "n9", "div");
		layout.setEqual(NSLayoutAttribute.Width, "n4", "n5", "n6", "mul");
		layout.setEqual(NSLayoutAttribute.Width, "n1", "n2", "n3", "add");
		layout.setEqual(NSLayoutAttribute.Width, "eq", "sub");
		layout.setEqual(NSLayoutAttribute.Right, "n0", "n2");
		layout.setConstant(NSLayoutAttribute.Width, 30.0f, field_op);

		layout.setEqual(NSLayoutAttribute.Height, "n0", "n1", "n4", "n7");
		layout.setEqual(NSLayoutAttribute.Height, "n0", "n2", "n5", "n8");
		layout.setEqual(NSLayoutAttribute.Height, "eq", "n3", "n6", "n8");
		layout.setEqual(NSLayoutAttribute.Height, "sub", "add", "mul", "div");
		
		layout.setConstant(NSLayoutAttribute.Height, 30.0f, field_mem,
				field_tmp, field_op);

		layout.setEqual(NSLayoutAttribute.Baseline, field_tmp, field_op);
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

	@Selector("action:")
	public void action(final UIButton sender) {
		String btn = sender.currentTitle();
		/*  */if ("0".equals(btn)) {
			_push_tmp(0.0);
		} else if ("1".equals(btn)) {
			_push_tmp(1.0);
		} else if ("2".equals(btn)) {
			_push_tmp(2.0);
		} else if ("3".equals(btn)) {
			_push_tmp(3.0);
		} else if ("4".equals(btn)) {
			_push_tmp(4.0);
		} else if ("5".equals(btn)) {
			_push_tmp(5.0);
		} else if ("6".equals(btn)) {
			_push_tmp(6.0);
		} else if ("7".equals(btn)) {
			_push_tmp(7.0);
		} else if ("8".equals(btn)) {
			_push_tmp(8.0);
		} else if ("9".equals(btn)) {
			_push_tmp(9.0);
		} else if ("+".equals(btn)) {
			_do_calc();
			_set_op(btn);
		} else if ("-".equals(btn)) {
			_do_calc();
			_set_op(btn);
		} else if ("*".equals(btn)) {
			_do_calc();
			_set_op(btn);
		} else if ("/".equals(btn)) {
			_do_calc();
			_set_op(btn);
		} else if ("=".equals(btn)) {
			_do_calc();
		} else {
			// Ignore
		}
	}

	private void _do_calc() {
		if ("+".equals(op)) {
			_set_mem(mem + tmp);
			_clr_tmp();
		} else if ("-".equals(op)) {
			_set_mem(mem - tmp);
			_clr_tmp();
		} else if ("*".equals(op)) {
			_set_mem(mem * tmp);
			_clr_tmp();
		} else if ("/".equals(op)) {
			_set_mem(mem / tmp);
			_clr_tmp();
		} else {
			if (tmp != 0.0) {
				_set_mem(tmp);
				_clr_tmp();
			}
		}
		_set_op("");
	}

	private void _set_op(String new_op) {
		op = new_op;
		field_op.setText(op);
	}

	private void _set_mem(double d) {
		mem = d;
		field_mem.setText(Double.toString(mem));
		field_mem.setBackgroundColor(UIColor.lightGrayColor());
		UIView.animateWithDurationAnimations(0.3,
				new Block_animateWithDurationAnimations() {

					@Override
					public void call_animateWithDurationAnimations() {
						field_mem.setBackgroundColor(UIColor.whiteColor());
					}
				});
	}

	private void _push_tmp(double d) {
		tmp *= 10.0;
		tmp += d;
		field_tmp.setText(Double.toString(tmp));
	}

	private void _clr_mem() {
		mem = 0.0;
		field_mem.setText(Double.toString(mem));
	}

	private void _clr_tmp() {
		tmp = 0.0;
		field_tmp.setText(Double.toString(tmp));
	}

	@NotImplemented
	@Override
	@Selector("textField:shouldChangeCharactersInRange:replacementString:")
	@IsOptional
	public native boolean textFieldShouldChangeCharactersInRangeReplacementString(
			UITextField textField, @ByValue NSRange range, String string);

	@NotImplemented
	@Override
	@Selector("textFieldDidBeginEditing:")
	@IsOptional
	public native void textFieldDidBeginEditing(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldDidEndEditing:")
	@IsOptional
	public native void textFieldDidEndEditing(UITextField textField);

	@Override
	@Selector("textFieldShouldBeginEditing:")
	@IsOptional
	public boolean textFieldShouldBeginEditing(UITextField textField) {
		return false;
	}

	@NotImplemented
	@Override
	@Selector("textFieldShouldClear:")
	@IsOptional
	public native boolean textFieldShouldClear(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldShouldEndEditing:")
	@IsOptional
	public native boolean textFieldShouldEndEditing(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldShouldReturn:")
	@IsOptional
	public native boolean textFieldShouldReturn(UITextField textField);

}
