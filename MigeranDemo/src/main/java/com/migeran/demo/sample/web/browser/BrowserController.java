package com.migeran.demo.sample.web.browser;

import ios.foundation.NSError;
import ios.foundation.NSURL;
import ios.foundation.NSURLRequest;
import ios.foundation.struct.NSRange;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIColor;
import ios.uikit.UIDevice;
import ios.uikit.UIPopoverController;
import ios.uikit.UITableView;
import ios.uikit.UITextField;
import ios.uikit.UIViewController;
import ios.uikit.UIWebView;
import ios.uikit.enums.UIBarButtonItemStyle;
import ios.uikit.enums.UIDeviceOrientation;
import ios.uikit.enums.UIKeyboardType;
import ios.uikit.enums.UIPopoverArrowDirection;
import ios.uikit.enums.UIRectEdge;
import ios.uikit.enums.UIReturnKeyType;
import ios.uikit.enums.UITextAutocorrectionType;
import ios.uikit.enums.UITextBorderStyle;
import ios.uikit.enums.UIUserInterfaceIdiom;
import ios.uikit.protocol.UITextFieldDelegate;
import ios.uikit.protocol.UIWebViewDelegate;

import com.migeran.demo.helper.SimpleTableController;
import com.migeran.demo.util.UIBuilder;
import com.migeran.demo.util.UILayout;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.ByValue;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.Selector;

public class BrowserController extends UIViewController implements
		UIWebViewDelegate, UITextFieldDelegate {

	static {
		NatJ.register();
	}

	private UIWebView webview;
	private UITextField addressBar;

	private UILayout vertical_layout;
	private UILayout horizontal_layout;

	private static final String[] bookmarks = new String[] { "www.migeran.com",
			"www.google.com", "www.apple.com", "www.android.com",
			"www.yahoo.com", "www.nytimes.com", };

	@Generated("NatJ")
	@Selector("alloc")
	public static native BrowserController alloc();

	@Generated("NatJ")
	protected BrowserController(Pointer peer) {
		super(peer);
	}

	private void loadURL(String string) {
		if (string == null) {
			webview.loadHTMLStringBaseURL("", null);
		} else {
			if (!string.startsWith("http://") || !string.startsWith("https://")) {
				string = "http://" + string;
			}
			addressBar.setText(string);
			NSURL url = NSURL.URLWithString(string);
			NSURLRequest req = NSURLRequest.requestWithURL(url);
			webview.loadRequest(req);
		}
	}

	@Override
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Web Browser");

		setEdgesForExtendedLayout(UIRectEdge.None);
		view().setBackgroundColor(UIColor.whiteColor());

		UIBarButtonItem bmbutton = UIBarButtonItem.alloc()
				.initWithTitleStyleTargetAction("Bookmarks",
						UIBarButtonItemStyle.Plain, this,
						new SEL("showBookmarks"));
		navigationItem().setRightBarButtonItem(bmbutton);

		UIBuilder builder = new UIBuilder(this);

		webview = builder.add(UIWebView.alloc().init(), "web");
		webview.setDelegate_unsafe(this);
		webview.setScalesPageToFit(true);

		addressBar = builder.addTextField("address");
		addressBar.setDelegate_unsafe(this);
		addressBar.setKeyboardType(UIKeyboardType.URL);
		addressBar.setReturnKeyType(UIReturnKeyType.Go);
		addressBar.setAutocorrectionType(UITextAutocorrectionType.No);
		addressBar.setBorderStyle(UITextBorderStyle.RoundedRect);

		vertical_layout = horizontal_layout = builder.newLayout();

		vertical_layout.add("H:|-[address]-|");
		vertical_layout.add("H:|-0-[web]-0-|");
		vertical_layout.add("V:[topGuide]-[address]-[web]-0-[bottomGuide]");

		if (interfaceOrientation() == UIDeviceOrientation.Portrait
				|| interfaceOrientation() == UIDeviceOrientation.PortraitUpsideDown) {
			vertical_layout.activate(null);
		} else {
			horizontal_layout.activate(null);
		}
	}

	@Override
	@Selector("viewWillAppear:")
	public void viewWillAppear(boolean animated) {
		super.viewWillAppear(animated);

		if (addressBar.text().length() == 0) {
			loadURL(bookmarks[0]);
		}
	}

	@Override
	@Selector("viewDidDisappear:")
	public void viewDidDisappear(boolean animated) {
		if (webview != null) {
			loadURL(null);
		}

		super.viewDidDisappear(animated);
	}

	@Selector("showBookmarks")
	public void showBookmarks() {
		SimpleTableController bms = SimpleTableController.alloc().init();
		bms.setTitle("Bookmarks");
		for (String bm : bookmarks) {
			bms.add(bm);
		}
		if (UIDevice.currentDevice().userInterfaceIdiom() == UIUserInterfaceIdiom.Phone) {
			bms.setListener(new SimpleTableController.EventListener() {
				@Override
				public void tableViewDidSelectRow(UITableView tableView,
						String row) {
					loadURL(row);
					navigationController().popViewControllerAnimated(true);
				}
			});
			navigationController().pushViewControllerAnimated(bms, true);
		} else {
			final UIPopoverController ctrl = UIPopoverController.alloc()
					.initWithContentViewController(bms);
			bms.setListener(new SimpleTableController.EventListener() {
				@Override
				public void tableViewDidSelectRow(UITableView tableView,
						String row) {
					loadURL(row);
					ctrl.dismissPopoverAnimated(true);
				}
			});
			ctrl.presentPopoverFromBarButtonItemPermittedArrowDirectionsAnimated(
					navigationItem().rightBarButtonItem(),
					UIPopoverArrowDirection.Any, true);
		}
	}

	@Override
	@Selector("willRotateToInterfaceOrientation:duration:")
	public void willRotateToInterfaceOrientationDuration(
			@NInt long toInterfaceOrientation, double duration) {
		// TODO: Deprecated, use viewWillTransitionToSize:withTransitionCoordinator:
		super.willRotateToInterfaceOrientationDuration(toInterfaceOrientation, duration);
		
		if (vertical_layout == horizontal_layout) {
			return;
		}
		if (toInterfaceOrientation == UIDeviceOrientation.Portrait) {
			vertical_layout.activate(horizontal_layout);
		} else {
			horizontal_layout.activate(vertical_layout);
		}
	}

	@NotImplemented
	@Override
	@Selector("webView:didFailLoadWithError:")
	@IsOptional
	public native void webViewDidFailLoadWithError(UIWebView webView,
			NSError error);

	@Override
	@Selector("webView:shouldStartLoadWithRequest:navigationType:")
	@IsOptional
	public boolean webViewShouldStartLoadWithRequestNavigationType(
			UIWebView webView, NSURLRequest request, @NInt long navigationType) {
		addressBar.setText(request.mainDocumentURL().absoluteString());
		return true;
	}

	@NotImplemented
	@Override
	@Selector("webViewDidFinishLoad:")
	@IsOptional
	public native void webViewDidFinishLoad(UIWebView webView);

	@NotImplemented
	@Override
	@Selector("webViewDidStartLoad:")
	@IsOptional
	public native void webViewDidStartLoad(UIWebView webView);

	@NotImplemented
	@Override
	@Selector("textField:shouldChangeCharactersInRange:replacementString:")
	@IsOptional
	public native boolean textFieldShouldChangeCharactersInRangeReplacementString(
			UITextField textField, @ByValue NSRange range, String string);

	@NotImplemented
	@Override
	@Selector("textFieldDidBeginEditing:")
	@IsOptional
	public native void textFieldDidBeginEditing(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldDidEndEditing:")
	@IsOptional
	public native void textFieldDidEndEditing(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldShouldBeginEditing:")
	@IsOptional
	public native boolean textFieldShouldBeginEditing(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldShouldClear:")
	@IsOptional
	public native boolean textFieldShouldClear(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldShouldEndEditing:")
	@IsOptional
	public native boolean textFieldShouldEndEditing(UITextField textField);

	@Override
	@Selector("textFieldShouldReturn:")
	@IsOptional
	public boolean textFieldShouldReturn(UITextField textField) {
		loadURL(textField.text());
		textField.resignFirstResponder();
		return true;
	}

}
