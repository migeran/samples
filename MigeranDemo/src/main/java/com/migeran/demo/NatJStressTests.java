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

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class NatJStressTests extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected NatJStressTests(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native NatJStressTests alloc();

	@Generated
	@Selector("init")
	public native NatJStressTests init();
}