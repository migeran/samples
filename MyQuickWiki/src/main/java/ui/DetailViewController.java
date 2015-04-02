package ui;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.RegisterOnStartup;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.ann.ObjCClassName;
import com.migeran.natj.objc.ann.Selector;

import ios.foundation.NSBundle;
import ios.foundation.NSURL;
import ios.foundation.NSURLRequest;
import ios.uikit.UIViewController;
import ios.uikit.UIWebView;

import java.lang.String;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("DetailViewController")
@RegisterOnStartup
public class DetailViewController extends UIViewController {
	static {
		NatJ.register();
	}

	@Generated
	protected DetailViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native DetailViewController alloc();

	@Generated
	@Selector("detailView")
	public native UIWebView detailView();

	@Generated
	@Selector("init")
	public native DetailViewController init();

	@Generated
	@Selector("initWithNibName:bundle:")
	public native DetailViewController initWithNibNameBundle(
			String nibNameOrNil, NSBundle nibBundleOrNil);

	@Generated
	@Selector("setDetailView:")
	public native void setDetailView_unsafe(UIWebView value);

	@Generated
	public void setDetailView(UIWebView value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) detailView();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setDetailView_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	private NSURL loadURL = null;
	
	public void setLoadURL(String title) {
		if (title == null) {
			loadURL = null;
		} else {
			loadURL = NSURL.URLWithString("http://en.wikipedia.org/wiki/" + title.replace(' ', '_'));
		}
	}
	
	@Selector("viewDidLoad")
	@Override
	public void viewDidLoad() {
		super.viewDidLoad();
		
		if (loadURL != null) {
			System.out.println("Loading " + loadURL);
			detailView().loadRequest(NSURLRequest.requestWithURL(loadURL));
		}
	}
}