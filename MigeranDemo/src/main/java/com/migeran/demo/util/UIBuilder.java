package com.migeran.demo.util;

import ios.foundation.NSDictionary;
import ios.foundation.NSMutableDictionary;
import ios.uikit.UIButton;
import ios.uikit.UITextField;
import ios.uikit.UIView;
import ios.uikit.UIViewController;
import ios.uikit.enums.UIButtonType;
import ios.uikit.enums.UIControlState;

public class UIBuilder {

	private final UIView root;

	private final NSMutableDictionary views = NSMutableDictionary.alloc()
			.init();

	private UIButtonCusomisation defaultButtonCustomisation = null;

	private UITextFieldCusomisation defaultTextFieldCustomisation = null;

	public UIBuilder(UIViewController controller) {
		if (controller == null) {
			throw new NullPointerException();
		}
		this.root = controller.view();
		bind(controller.bottomLayoutGuide(), "bottomGuide");
		bind(controller.topLayoutGuide(), "topGuide");
	}

	public UILayout newLayout() {
		return new UILayout(this);
	}

	UIView getRoot() {
		return root;
	}

	NSDictionary getViews() {
		return views;
	}

	UIView get(String id) {
		if ("|".equals(id)) {
			return root;
		}
		return (UIView) views.get(id);
	}

	public UIButton addButton(String title, String id) {
		return addButton(title, id, UIButtonType.RoundedRect,
				defaultButtonCustomisation);
	}

	public UIButton addButton(String title, String id,
			UIButtonCusomisation customisation) {
		return addButton(title, id, UIButtonType.RoundedRect, customisation);
	}

	public UIButton addButton(String title, String id, int type) {
		return addButton(title, id, type, defaultButtonCustomisation);
	}

	public UIButton addButton(String title, String id, long type,
			UIButtonCusomisation customisation) {
		UIButton button = UIButton.buttonWithType(type);

		button.setTranslatesAutoresizingMaskIntoConstraints(false);
		button.setTitleForState(title, UIControlState.Normal);

		views.put(id, button);
		root.addSubview(button);

		if (customisation != null) {
			customisation.cusomiseButton(button);
		}

		return button;
	}

	public <T extends UIButton> T addButton(Class<T> clazz, String title,
			String id) {
		return addButton(clazz, title, id, defaultButtonCustomisation);
	}

	@SuppressWarnings("unchecked")
	public <T extends UIButton> T addButton(Class<T> clazz, String title,
			String id, UIButtonCusomisation customisation) {
		T button;
		try {
			button = (T) clazz.getMethod("alloc").invoke(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		button = (T) button.init();

		button.setTranslatesAutoresizingMaskIntoConstraints(false);
		button.setTitleForState(title, UIControlState.Normal);

		views.put(id, button);
		root.addSubview(button);

		if (customisation != null) {
			customisation.cusomiseButton(button);
		}

		return button;
	}

	public UIButtonCusomisation getDefaultButtonCustomisation() {
		return defaultButtonCustomisation;
	}

	public void setDefaultButtonCustomisation(
			UIButtonCusomisation defaultButtonCustomisation) {
		this.defaultButtonCustomisation = defaultButtonCustomisation;
	}

	public static interface UIButtonCusomisation {
		public void cusomiseButton(UIButton button);
	}

	public UITextField addTextField(String id) {
		return addTextField(id, getDefaultTextFieldCustomisation());
	}

	public UITextField addTextField(String id,
			UITextFieldCusomisation customisation) {
		UITextField field = UITextField.alloc().init();

		field.setTranslatesAutoresizingMaskIntoConstraints(false);

		views.put(id, field);
		root.addSubview(field);

		if (customisation != null) {
			customisation.cusomiseTextField(field);
		}

		return field;
	}

	public UITextFieldCusomisation getDefaultTextFieldCustomisation() {
		return defaultTextFieldCustomisation;
	}

	public void setDefaultTextFieldCustomisation(
			UITextFieldCusomisation defaultTextFieldCustomisation) {
		this.defaultTextFieldCustomisation = defaultTextFieldCustomisation;
	}

	public static interface UITextFieldCusomisation {
		public void cusomiseTextField(UITextField textfield);
	}

	public <T extends UIView> T add(T view, String id) {
		view.setTranslatesAutoresizingMaskIntoConstraints(false);
		views.put(id, view);
		root.addSubview(view);
		return view;
	}

	public void bind(Object view, String id) {
		views.put(id, view);
	}

}
