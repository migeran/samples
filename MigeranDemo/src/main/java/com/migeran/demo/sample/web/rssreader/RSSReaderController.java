package com.migeran.demo.sample.web.rssreader;

import ios.c.Globals;
import ios.c.Globals.Block_dispatch_sync;
import ios.coregraphics.struct.CGRect;
import ios.coregraphics.struct.CGSize;
import ios.foundation.NSBundle;
import ios.foundation.NSDictionary;
import ios.foundation.NSIndexPath;
import ios.foundation.NSMutableDictionary;
import ios.foundation.NSString;
import ios.foundation.NSURL;
import ios.foundation.struct.NSRange;
import ios.uikit.NSMutableParagraphStyle;
import ios.uikit.NSTextStorage;
import ios.uikit.UIApplication;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIDevice;
import ios.uikit.UIFont;
import ios.uikit.UINib;
import ios.uikit.UIPopoverController;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.c.UIKit;
import ios.uikit.enums.NSLineBreakMode;
import ios.uikit.enums.NSStringDrawingOptions;
import ios.uikit.enums.UIBarButtonItemStyle;
import ios.uikit.enums.UIPopoverArrowDirection;
import ios.uikit.enums.UIUserInterfaceIdiom;
import ios.uikit.struct.UIEdgeInsets;
import ui.RSSCell;

import com.migeran.demo.helper.CustomCellTableController;
import com.migeran.demo.helper.SimpleTableController;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NFloat;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.Selector;

public class RSSReaderController extends CustomCellTableController {

	private static final double textViewHPadding;
	private static final double textViewVPadding;
	private static final long textViewDrawingOptions;
	private static final NSDictionary textViewAttributes;

	private Thread loaderThread;
	private static final String[] bookmarks = new String[] {
			"http://www.apple.com/main/rss/hotnews/hotnews.rss",
			"http://www.phoronix.com/rss.php", };

	static {
		NatJ.register();

		// Some consts
		final UIEdgeInsets textContainerInsets = new UIEdgeInsets(8.0, 0.0,
				8.0, 0.0);
		final UIEdgeInsets contentInsets = new UIEdgeInsets(0.0, 0.0, 0.0,
				0.0);
		final double lineFragmentPadding = 5.0;

		// Calculate paddings
		textViewHPadding = textContainerInsets.left()
				+ textContainerInsets.right() + 2.0 * lineFragmentPadding
				+ contentInsets.left() + contentInsets.right();
		textViewVPadding = textContainerInsets.top()
				+ textContainerInsets.bottom() + contentInsets.top()
				+ contentInsets.bottom();

		// Set options
		textViewDrawingOptions = NSStringDrawingOptions.UsesLineFragmentOrigin
				| NSStringDrawingOptions.UsesFontLeading;

		NSMutableParagraphStyle paragraphStyle = NSMutableParagraphStyle
				.alloc().init();
		paragraphStyle.setLineBreakMode(NSLineBreakMode.ByWordWrapping);

		NSTextStorage textStorage = NSTextStorage.alloc().initWithString("-");
		NSDictionary textStorageAttrs = textStorage
				.attributesAtIndexEffectiveRange(0, new NSRange(0, 1));
		UIFont font = (UIFont) textStorageAttrs
				.get(UIKit.NSFontAttributeName());

		NSMutableDictionary attributes = NSMutableDictionary
				.dictionaryWithCapacity(2);
		attributes.put(UIKit.NSFontAttributeName(), font);
		attributes.put(UIKit.NSParagraphStyleAttributeName(), paragraphStyle);
		textViewAttributes = attributes;
	}

	@Generated("NatJ")
	@Selector("alloc")
	public static native RSSReaderController alloc();

	@Generated("NatJ")
	protected RSSReaderController(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("RSS Reader");

		UIBarButtonItem bmbutton = UIBarButtonItem.alloc()
				.initWithTitleStyleTargetAction("Bookmarks",
						UIBarButtonItemStyle.Plain, this,
						new SEL("showBookmarks"));
		navigationItem().setRightBarButtonItem(bmbutton);

		loadURL(bookmarks[0]);
	}

	@Override
	protected void prepareController() {
		UINib nib = UINib
				.nibWithNibNameBundle("RSSCell", NSBundle.mainBundle());
		tableView().registerNibForCellReuseIdentifier(nib, CELL_IDENTIFIER);
	}

	private synchronized void loadURL(final String url) {
		if (loaderThread != null) {
			return;
		}

		loaderThread = new Thread(new Runnable() {

			@Override
			public void run() {
				final RSSFeed feed = new RSSFeed(url);
				
				Globals.dispatch_sync(Globals.dispatch_get_main_queue(), new Block_dispatch_sync() {
					
					@Override
					public void call_dispatch_sync() {
						getOptions().clear();
						for (RSSFeedItem item : feed.items) {
							add(item);
						}
						System.out.println("Finished loading url " + url);
						tableView().reloadData();
						loaderThread = null;
					}
				});
			}
		});
		loaderThread.start();
	}

	@Override
	protected void setupCellAtIndex(UITableViewCell cell, Object rowData) {
		RSSCell c = (RSSCell) cell;
		RSSFeedItem i = (RSSFeedItem) rowData;

		c.rssTitle().setText(i.getTitle());
		c.rssDescription().setText(i.getDescription());
		c.rssDate().setText(i.getPubDate());
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
	@Selector("tableView:heightForRowAtIndexPath:")
	@NFloat
	public double tableViewHeightForRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath) {
		RSSFeedItem item = (RSSFeedItem) getOptions().get((int)indexPath.row());
		String ttm = item.getDescription();
		if (ttm.endsWith("\n")) {
			ttm += "-";
		}
		NSString measure = NSString.stringWithString(ttm);

		// Width: 2 * 10 pixel margin + 5 pixel padding
		CGSize size = new CGSize(tableView.bounds().size().width() - 10.0
				* 2.0 - textViewHPadding, 1000.0);
		CGRect bounding = measure.boundingRectWithSizeOptionsAttributesContext(
				size, textViewDrawingOptions, textViewAttributes, null);

		return Math.ceil(bounding.size().height() + textViewVPadding
				+ 1.0) + 39.0 + 34.0;
	}

	@Override
	@Selector("tableView:didSelectRowAtIndexPath:")
	public void tableViewDidSelectRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath) {
		RSSFeedItem item = (RSSFeedItem) getOptions().get((int)indexPath.row());
		String url = item.getUrl();
		if (url != null) {
			UIApplication.sharedApplication().openURL(NSURL.URLWithString(url));
		}
	}
}
