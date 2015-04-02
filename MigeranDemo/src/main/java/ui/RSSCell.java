package ui;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.ByValue;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.RegisterOnStartup;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.ann.ObjCClassName;
import com.migeran.natj.objc.ann.Selector;

import ios.coregraphics.struct.CGRect;
import ios.uikit.UILabel;
import ios.uikit.UITableViewCell;
import ios.uikit.UITextView;

import java.lang.String;
import com.migeran.natj.general.ann.NInt;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("RSSCell")
@RegisterOnStartup
public class RSSCell extends UITableViewCell {
	static {
		NatJ.register();
	}

	@Generated
	protected RSSCell(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native RSSCell alloc();

	@Generated
	@Selector("init")
	public native RSSCell init();

	@Generated
	@Selector("initWithFrame:")
	public native RSSCell initWithFrame(@ByValue CGRect frame);

	@Generated
	@Deprecated
	@Selector("initWithFrame:reuseIdentifier:")
	public native RSSCell initWithFrameReuseIdentifier(@ByValue CGRect frame,
			String reuseIdentifier);

	@Generated
	@Selector("initWithStyle:reuseIdentifier:")
	public native RSSCell initWithStyleReuseIdentifier(@NInt long style,
			String reuseIdentifier);

	@Generated
	@Selector("rssDate")
	public native UILabel rssDate();

	@Generated
	@Selector("rssDescription")
	public native UITextView rssDescription();

	@Generated
	@Selector("rssTitle")
	public native UILabel rssTitle();

	@Generated
	@Selector("setRssDate:")
	public native void setRssDate(UILabel value);

	@Generated
	@Selector("setRssDescription:")
	public native void setRssDescription(UITextView value);

	@Generated
	@Selector("setRssTitle:")
	public native void setRssTitle(UILabel value);
}