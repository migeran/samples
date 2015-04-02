package ui;


import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.ByValue;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.RegisterOnStartup;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.ann.ObjCClassName;
import com.migeran.natj.objc.ann.Selector;
import ios.coregraphics.struct.CGRect;
import ios.uikit.UIActivityIndicatorView;
import ios.uikit.UILabel;
import ios.uikit.UITableViewCell;
import java.lang.String;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("NatJTestCell")
@RegisterOnStartup
public class NatJTestCell extends UITableViewCell {
	static {
		NatJ.register();
	}

	@Generated
	protected NatJTestCell(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native NatJTestCell alloc();

	@Generated
	@Selector("indicatorView")
	public native UIActivityIndicatorView indicatorView();

	@Generated
	@Selector("init")
	public native NatJTestCell init();

	@Generated
	@Selector("initWithFrame:")
	public native NatJTestCell initWithFrame(@ByValue CGRect frame);

	@Generated
	@Deprecated
	@Selector("initWithFrame:reuseIdentifier:")
	public native NatJTestCell initWithFrameReuseIdentifier(
			@ByValue CGRect frame, String reuseIdentifier);

	@Generated
	@Selector("initWithStyle:reuseIdentifier:")
	public native NatJTestCell initWithStyleReuseIdentifier(@NInt long style,
			String reuseIdentifier);

	@Generated
	@Selector("setIndicatorView:")
	public native void setIndicatorView_unsafe(UIActivityIndicatorView value);

	@Generated
	public void setIndicatorView(UIActivityIndicatorView value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) indicatorView();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setIndicatorView_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setTimeLabel:")
	public native void setTimeLabel_unsafe(UILabel value);

	@Generated
	public void setTimeLabel(UILabel value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) timeLabel();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setTimeLabel_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setTitleLabel:")
	public native void setTitleLabel_unsafe(UILabel value);

	@Generated
	public void setTitleLabel(UILabel value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) titleLabel();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setTitleLabel_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("timeLabel")
	public native UILabel timeLabel();

	@Generated
	@Selector("titleLabel")
	public native UILabel titleLabel();

	@Generated
	@Selector("mtTimeLabel")
	public native UILabel mtTimeLabel();

	@Generated
	@Selector("setMtTimeLabel:")
	public native void setMtTimeLabel_unsafe(UILabel value);

	@Generated
	public void setMtTimeLabel(UILabel value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) mtTimeLabel();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setMtTimeLabel_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}
}