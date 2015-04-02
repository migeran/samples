package com.migeran.demo.util;

import ios.foundation.NSMutableArray;
import ios.uikit.NSLayoutConstraint;
import ios.uikit.UIView;
import ios.uikit.enums.NSLayoutRelation;

import java.util.List;

public class UILayout {

	private final UIBuilder builder;

	private final NSMutableArray constraints = NSMutableArray.alloc().init();

	UILayout(UIBuilder builder) {
		this.builder = builder;
	}

	public void activate(UILayout deactivate) {
		UIView root = builder.getRoot();
		if (deactivate != null) {
			root.removeConstraints(deactivate.constraints);
		}
		//System.err.println(root.constraints());
		//root.removeConstraints(root.constraints());
		root.addConstraints(constraints);
		root.layoutSubviews();
	}

	public void add(String formatString) {
		constraints.addObjectsFromArray(NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews(formatString,
						0, null, builder.getViews()));
	}

	public void setEqual(long attribute, String... ids) {
		if (ids == null || ids.length < 2) {
			return;
		}
		UIView views[] = new UIView[ids.length];
		int idx = 0;
		for (String id : ids) {
			views[idx++] = builder.get(id);
		}
		setEqual(attribute, views);
	}

	public void setEqual(long attribute, List<UIView> views) {
		if (views == null || views.size() < 2) {
			return;
		}
		UIView views_array[] = new UIView[views.size()];
		setEqual(attribute, views.toArray(views_array));
	}

	public void setEqual(long attribute, UIView... views) {
		if (views == null || views.length < 2) {
			return;
		}
		UIView view1 = views[0];
		for (int i = 1; i < views.length; ++i) {
			_setEqual(attribute, view1, views[i]);
		}
	}

	private void _setEqual(long attribute, UIView view1, UIView view2) {
		if (view1 == null || view2 == null) {
			throw new NullPointerException();
		}
		constraints
				.add(NSLayoutConstraint
						.constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(
								view1, attribute, NSLayoutRelation.Equal,
								view2, attribute, 1.0, 0.0));
	}

	public void setEqual(String id1, long attribute1, String id2, long attribute2) {
		setEqual(attribute1, attribute2, builder.get(id1), builder.get(id2));
	}

	public void setEqual(long attribute1, long attribute2, UIView view1,
			UIView view2) {
		if (view1 == null || view2 == null) {
			throw new NullPointerException();
		}
		constraints
				.add(NSLayoutConstraint
						.constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(
								view1, attribute1, NSLayoutRelation.Equal,
								view2, attribute2, 1.0, 0.0));
	}

	public void setConstant(long attribute, double value, String... ids) {
		if (ids == null || ids.length == 0) {
			return;
		}
		UIView views[] = new UIView[ids.length];
		int idx = 0;
		for (String id : ids) {
			views[idx++] = builder.get(id);
		}
		setConstant(attribute, value, views);
	}

	public void setConstant(long attribute, double value, List<UIView> views) {
		if (views == null || views.size() == 0) {
			return;
		}
		UIView views_array[] = new UIView[views.size()];
		setConstant(attribute, value, views.toArray(views_array));
	}

	public void setConstant(long attribute, double value, UIView... views) {
		if (views == null || views.length == 0) {
			return;
		}
		for (UIView view : views) {
			_setConstant(attribute, value, view);
		}
	}

	private void _setConstant(long attribute, double value, UIView view) {
		if (view == null) {
			throw new NullPointerException();
		}
		constraints
				.add(NSLayoutConstraint
						.constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(
								view, attribute, NSLayoutRelation.Equal, null,
								0, 1.0, value));
	}
}
