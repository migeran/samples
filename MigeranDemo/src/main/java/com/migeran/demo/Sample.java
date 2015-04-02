package com.migeran.demo;

import ios.uikit.UIViewController;

public class Sample implements Comparable<Sample> {

	private final String name;
	private final Class<? extends UIViewController> clazz;

	public Sample(String name, Class<? extends UIViewController> clazz) {
		this.name = name;
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public Class<? extends UIViewController> getClazz() {
		return clazz;
	}

	@Override
	public int compareTo(Sample obj) {
		if (obj == null) {
			return 1;
		}
		return name.compareTo(obj.name);
	}

	public UIViewController instantiate() {
		UIViewController vc;
		try {
			vc = (UIViewController) clazz.getMethod("alloc").invoke(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		vc = vc.init();
		return vc;
	}

}
