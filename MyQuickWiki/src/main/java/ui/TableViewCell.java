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
import ios.uikit.UILabel;
import ios.uikit.UITableViewCell;
import java.lang.String;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("TableViewCell")
@RegisterOnStartup
public class TableViewCell extends UITableViewCell {
	static {
		NatJ.register();
	}

	@Generated
	protected TableViewCell(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native TableViewCell alloc();

	@Generated
	@Selector("categoryLabel")
	public native UILabel categoryLabel();

	@Generated
	@Selector("init")
	public native TableViewCell init();

	@Generated
	@Selector("initWithFrame:")
	public native TableViewCell initWithFrame(@ByValue CGRect frame);

	@Generated
	@Deprecated
	@Selector("initWithFrame:reuseIdentifier:")
	public native TableViewCell initWithFrameReuseIdentifier(
			@ByValue CGRect frame, String reuseIdentifier);

	@Generated
	@Selector("initWithStyle:reuseIdentifier:")
	public native TableViewCell initWithStyleReuseIdentifier(@NInt long style,
			String reuseIdentifier);

	@Generated
	@Selector("setCategoryLabel:")
	public native void setCategoryLabel_unsafe(UILabel value);

	@Generated
	public void setCategoryLabel(UILabel value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) categoryLabel();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setCategoryLabel_unsafe(value);
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
	@Selector("titleLabel")
	public native UILabel titleLabel();
}