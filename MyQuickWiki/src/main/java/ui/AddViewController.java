package ui;

import com.migeran.DataSource;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.ByValue;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.Mapped;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.RegisterOnStartup;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.ObjCClassName;
import com.migeran.natj.objc.ann.Selector;
import com.migeran.natj.objc.map.ObjCObjectMapper;

import ios.foundation.NSBundle;
import ios.foundation.struct.NSRange;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIButton;
import ios.uikit.UIStoryboardSegue;
import ios.uikit.UITextField;
import ios.uikit.UIViewController;
import ios.uikit.protocol.UITextFieldDelegate;

import java.lang.String;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("AddViewController")
@RegisterOnStartup
public class AddViewController extends UIViewController implements
		UITextFieldDelegate {
	static {
		NatJ.register();
	}

	@Generated
	protected AddViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native AddViewController alloc();

	@Generated
	@Selector("categoryField")
	public native UITextField categoryField();

	@Selector("doSave:")
	public void doSave(@Mapped(ObjCObjectMapper.class) Object sender) {
		DataSource.insert(titleField().text(), categoryField().text());
		titleField().setText("");
		categoryField().setText("");
		
		navigationController().popViewControllerAnimated(true);
	}

	@Generated
	@Selector("doneButton")
	public native UIBarButtonItem doneButton();

	@Generated
	@Selector("init")
	public native AddViewController init();

	@Generated
	@Selector("initWithNibName:bundle:")
	public native AddViewController initWithNibNameBundle(String nibNameOrNil,
			NSBundle nibBundleOrNil);

	@Generated
	@Selector("previewButton")
	public native UIButton previewButton();

	@Generated
	@Selector("setCategoryField:")
	public native void setCategoryField_unsafe(UITextField value);

	@Generated
	public void setCategoryField(UITextField value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) categoryField();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setCategoryField_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setDoneButton:")
	public native void setDoneButton_unsafe(UIBarButtonItem value);

	@Generated
	public void setDoneButton(UIBarButtonItem value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) doneButton();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setDoneButton_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setPreviewButton:")
	public native void setPreviewButton_unsafe(UIButton value);

	@Generated
	public void setPreviewButton(UIButton value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) previewButton();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setPreviewButton_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setTitleField:")
	public native void setTitleField_unsafe(UITextField value);

	@Generated
	public void setTitleField(UITextField value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) titleField();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setTitleField_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	@IsOptional
	@Selector("textField:shouldChangeCharactersInRange:replacementString:")
	public boolean textFieldShouldChangeCharactersInRangeReplacementString(
			UITextField textField, @ByValue NSRange range, String string) {
		if (textField == titleField()) {
			boolean enabled = textField.text().length() - range.length() + string.length() > 0;
			doneButton().setEnabled(enabled);
			previewButton().setEnabled(enabled);
		}
		return true;
	}

	@Generated
	@IsOptional
	@Selector("textFieldDidBeginEditing:")
	public native void textFieldDidBeginEditing(UITextField textField);

	@Generated
	@IsOptional
	@Selector("textFieldDidEndEditing:")
	public native void textFieldDidEndEditing(UITextField textField);

	@Generated
	@IsOptional
	@Selector("textFieldShouldBeginEditing:")
	public native boolean textFieldShouldBeginEditing(UITextField textField);

	@Generated
	@IsOptional
	@Selector("textFieldShouldClear:")
	public native boolean textFieldShouldClear(UITextField textField);

	@Generated
	@IsOptional
	@Selector("textFieldShouldEndEditing:")
	public native boolean textFieldShouldEndEditing(UITextField textField);

	@IsOptional
	@Selector("textFieldShouldReturn:")
	public boolean textFieldShouldReturn(UITextField textField) {
		textField.resignFirstResponder();
		return true;
	}

	@Generated
	@Selector("titleField")
	public native UITextField titleField();
	
	@Selector("prepareForSegue:sender:")
	@Override
	public void prepareForSegueSender(UIStoryboardSegue segue, Object sender) {
		if (segue.identifier().equals("showDetailsPreview")) {
			DetailViewController dest = (DetailViewController) segue.destinationViewController();
			dest.setTitle(titleField().text());
			dest.setLoadURL(titleField().text());
		}
	}
}