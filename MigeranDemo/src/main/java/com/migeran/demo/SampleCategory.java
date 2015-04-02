package com.migeran.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class SampleCategory implements Comparable<SampleCategory> {

	public static final String GRAPHICS = "Graphics";

	public static final String UTILITY = "Utilities";

	public static final String WEB = "Web";

	public static final String MAP = "Map";

	public static final String STRESS_TEST = "Stress Tests";

	private static final HashMap<String, SampleCategory> cache = new HashMap<String, SampleCategory>();
	private static int nextIndex = 0;

	private final String name;
	private final int index;

	private final ArrayList<Sample> samples = new ArrayList<Sample>();

	private SampleCategory(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static SampleCategory getCategory(String name) {
		SampleCategory cat = cache.get(name);
		if (cat == null) {
			cat = new SampleCategory(name, nextIndex++);
			cache.put(name, cat);
		}
		return cat;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(SampleCategory obj) {
		if (obj == null) {
			return 1;
		}
		return index - obj.index;
	}

	public static SampleCategory categoryForIndex(int index) {
		Iterator<Entry<String, SampleCategory>> it = cache.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, SampleCategory> e = it.next();
			if (e.getValue().index == index) {
				return e.getValue();
			}
		}
		return null;
	}

	public int size() {
		return samples.size();
	}

	public void add(Sample sample) {
		samples.add(sample);
	}

	public ArrayList<Sample> getSamples() {
		return samples;
	}

	public Sample get(int index) {
		return samples.get(index);
	}

}
