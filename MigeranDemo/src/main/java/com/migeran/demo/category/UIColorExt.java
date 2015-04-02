package com.migeran.demo.category;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.ann.ObjCCategory;
import com.migeran.natj.objc.ann.Selector;
import ios.quartzcore.CALayer;
import ios.uikit.UIColor;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCCategory(UIColor.class)
public final class UIColorExt {
	static {
		NatJ.register();
	}

	@Generated
	private UIColorExt() {
	}

	@Generated
	@Selector("setAsCALayerBackgroundColor:")
	public static native void setAsCALayerBackgroundColor(UIColor _object,
			CALayer layer);

	@Generated
	@Selector("setAsCALayerBorderColor:")
	public static native void setAsCALayerBorderColor(UIColor _object,
			CALayer layer);
}