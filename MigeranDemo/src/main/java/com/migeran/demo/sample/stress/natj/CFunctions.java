package com.migeran.demo.sample.stress.natj;

import java.util.ArrayList;

import ui.RSSCell;

import com.migeran.demo.c.Globals;

public abstract class CFunctions {

	public static final int THREAD_COUNT = 2;
	static public String CATEGORY = "C Functions";

	static private abstract class FunctionTestBase extends Test {
		protected FunctionTestBase(String name) {
			super(name);
		}

		@Override
		protected void setUp() {
			super.setUp();
			Globals.NST_Init();
		}
	}

	static public final class FVoidReturn extends FunctionTestBase {
		static public FVoidReturn TEST = new FVoidReturn();
		
		protected FVoidReturn() {
			super("Void Return");
		}

		@Override
		protected void doStep() {
			Globals.NST_VoidReturn();
		}
	}

	static public final class FIntReturn extends FunctionTestBase {
		static public FIntReturn TEST = new FIntReturn();
		
		protected FIntReturn() {
			super("Int Return");
		}

		@Override
		protected void doStep() {
			Globals.NST_IntReturn();
		}
	}

	static public final class FLongReturn extends FunctionTestBase {
		static public FLongReturn TEST = new FLongReturn();
		
		protected FLongReturn() {
			super("Long Return");
		}

		@Override
		protected void doStep() {
			Globals.NST_LongReturn();
		}
	}

	static public final class FFloatReturn extends FunctionTestBase {
		static public FFloatReturn TEST = new FFloatReturn();
		
		protected FFloatReturn() {
			super("Float Return");
		}

		@Override
		protected void doStep() {
			Globals.NST_FloatReturn();
		}
	}

	static public final class FDoubleReturn extends FunctionTestBase {
		static public FDoubleReturn TEST = new FDoubleReturn();
		
		protected FDoubleReturn() {
			super("Double Return");
		}

		@Override
		protected void doStep() {
			Globals.NST_DoubleReturn();
		}
	}

	static public final class FNilReturn extends FunctionTestBase {
		static public FNilReturn TEST = new FNilReturn();
		
		protected FNilReturn() {
			super("Nil Return");
		}

		@Override
		protected void doStep() {
			Globals.NST_NilReturn();
		}
	}

	static public final class FBindingReturn extends FunctionTestBase {
		static public FBindingReturn TEST = new FBindingReturn();
		
		protected FBindingReturn() {
			super("Binding Return");
		}

		@Override
		protected void doStep() {
			Globals.NST_BindingReturn();
		}
	}

	static public final class FBindingWithHiddenImplClassReturn extends FunctionTestBase {
		static public FBindingWithHiddenImplClassReturn TEST = new FBindingWithHiddenImplClassReturn();
		
		protected FBindingWithHiddenImplClassReturn() {
			super("Binding + Hidden Implementing Class Return");
		}

		@Override
		protected void doStep() {
			Globals.NST_BindingWithHiddenImplClassReturn();
		}
	}

	static public final class FInheritedReturn extends FunctionTestBase {
		static public FInheritedReturn TEST = new FInheritedReturn();
		
		protected FInheritedReturn() {
			super("Inherited Return");
		}

		@Override
		protected void setUp() {
			super.setUp();
			Globals.NST_setInheritedReturn(NatJStressTestController.alloc().init());
		}

		@Override
		protected void doStep() {
			Globals.NST_InheritedReturn();
		}
	}

	static public final class FProxyReturn extends FunctionTestBase {
		static public FProxyReturn TEST = new FProxyReturn();
		
		protected FProxyReturn() {
			super("Proxy Return");
		}

		@Override
		protected void setUp() {
			super.setUp();
			Globals.NST_setProxyReturn(new ArrayList<>());
		}
		
		@Override
		protected void doStep() {
			Globals.NST_ProxyReturn();
		}
	}

	static public final class FHybridReturn extends FunctionTestBase {
		static public FHybridReturn TEST = new FHybridReturn();
		
		protected FHybridReturn() {
			super("Hybrid Return");
		}

		@Override
		protected void setUp() {
			super.setUp();
			Globals.NST_setHybridReturn(RSSCell.alloc().init());
		}

		@Override
		protected void doStep() {
			Globals.NST_HybridReturn();
		}
	}

	static public final class FStringReturn extends FunctionTestBase {
		static public FStringReturn TEST = new FStringReturn();
		
		protected FStringReturn() {
			super("String Return");
		}

		@Override
		protected void doStep() {
			Globals.NST_StringReturn();
		}
	}
}
