package com.migeran.demo.sample.utility.calculator;

import ios.foundation.NSMutableArray;
import ios.foundation.NSNumber;
import ios.quartzcore.CAGradientLayer;
import ios.quartzcore.CALayer;
import ios.quartzcore.CATransaction;
import ios.uikit.UIButton;
import ios.uikit.UIColor;

import com.migeran.demo.TouchTools;
import com.migeran.demo.category.UIColorExt;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.objc.ann.Selector;

public class CalcButton extends UIButton {

	static {
		NatJ.register();
	}

	private CAGradientLayer backgroundLayer;
	private CALayer tintLayer;

	@Generated("NatJ")
	@Selector("alloc")
	public static native CalcButton alloc();

	@Selector("init")
	public CalcButton init() {
		CalcButton self = (CalcButton) super.init();

		layer().setCornerRadius(3.0f);
		layer().setBorderWidth(1.0f);
		UIColorExt.setAsCALayerBorderColor(UIColor.grayColor(), layer());
		layer().setMasksToBounds(true);

		backgroundLayer = CAGradientLayer.layer();
		NSMutableArray colors = NSMutableArray.alloc().initWithCapacity(4);
		colors.add(UIColor.colorWithWhiteAlpha(0.9f, 1.0f));
		colors.add(UIColor.colorWithWhiteAlpha(0.8f, 1.0f));
		colors.add(UIColor.colorWithWhiteAlpha(0.7f, 1.0f));
		colors.add(UIColor.colorWithWhiteAlpha(0.9f, 1.0f));
		NSMutableArray locations = NSMutableArray.alloc().initWithCapacity(4);
		locations.add(NSNumber.numberWithFloat(0.0f));
		locations.add(NSNumber.numberWithFloat(0.5f));
		locations.add(NSNumber.numberWithFloat(0.51f));
		locations.add(NSNumber.numberWithFloat(1.0f));
		backgroundLayer.setColors(TouchTools
				.convertArrayOfUIColorToCGColor(colors));
		backgroundLayer.setLocations(locations);
		layer().insertSublayerAtIndex(backgroundLayer, 0);

		tintLayer = CALayer.layer();
		UIColorExt.setAsCALayerBackgroundColor(UIColor.whiteColor(), tintLayer);
		tintLayer.setOpacity(0.5f);
		tintLayer.setHidden(true);
		layer().insertSublayerAtIndex(tintLayer, 1);

		return self;
	}

	@Generated("NatJ")
	protected CalcButton(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("layoutSubviews")
	public void layoutSubviews() {
		backgroundLayer.setFrame(bounds());
		tintLayer.setFrame(bounds());
		super.layoutSubviews();
	}

	@Override
	@Selector("setHighlighted:")
	public void setHighlighted(boolean value) {
		CATransaction.begin();
		CATransaction.setDisableActions(true);

		tintLayer.setHidden(!value);
		CATransaction.commit();

		super.setHighlighted(value);
	}

	public void setHighlightColor(UIColor color) {
		if (color != null) {
			UIColorExt.setAsCALayerBackgroundColor(color, tintLayer);
		}
	}
}
