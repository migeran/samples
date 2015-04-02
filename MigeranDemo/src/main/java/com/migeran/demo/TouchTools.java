package com.migeran.demo;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.ann.ObjCClassBinding;
import com.migeran.natj.objc.ann.Selector;
import ios.NSObject;
import ios.foundation.NSArray;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class TouchTools extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected TouchTools(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native TouchTools alloc();

	@Generated
	@Selector("convertArrayOfUIColorToCGColor:")
	public static native NSArray convertArrayOfUIColorToCGColor(NSArray colors);

	@Generated
	@Selector("init")
	public native TouchTools init();
}