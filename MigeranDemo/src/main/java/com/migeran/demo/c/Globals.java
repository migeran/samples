package com.migeran.demo.c;


import com.migeran.natj.c.CRuntime;
import com.migeran.natj.c.ann.CFunction;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.MappedReturn;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.objc.map.ObjCObjectMapper;
import com.migeran.natj.objc.map.ObjCStringMapper;
import java.lang.String;
import com.migeran.natj.general.ann.Mapped;

@Generated
@Runtime(CRuntime.class)
public final class Globals {
	static {
		NatJ.register();
	}

	@Generated
	private Globals() {
	}

	@Generated
	@CFunction
	public static native void NST_Init();

	@Generated
	@CFunction
	public static native void NST_VoidReturn();

	@Generated
	@CFunction
	public static native int NST_IntReturn();

	@Generated
	@CFunction
	public static native long NST_LongReturn();

	@Generated
	@CFunction
	public static native float NST_FloatReturn();

	@Generated
	@CFunction
	public static native double NST_DoubleReturn();

	@Generated
	@CFunction
	@MappedReturn(ObjCStringMapper.class)
	public static native String NST_StringReturn();

	@Generated
	@CFunction
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object NST_NilReturn();

	@Generated
	@CFunction
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object NST_BindingReturn();

	@Generated
	@CFunction
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object NST_BindingWithHiddenImplClassReturn();

	@Generated
	@CFunction
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object NST_InheritedReturn();

	@Generated
	@CFunction
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object NST_ProxyReturn();

	@Generated
	@CFunction
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object NST_HybridReturn();

	@Generated
	@CFunction
	public static native void NST_setInheritedReturn(
			@Mapped(ObjCObjectMapper.class) Object obj);

	@Generated
	@CFunction
	public static native void NST_setProxyReturn(
			@Mapped(ObjCObjectMapper.class) Object obj);

	@Generated
	@CFunction
	public static native void NST_setHybridReturn(
			@Mapped(ObjCObjectMapper.class) Object obj);
}