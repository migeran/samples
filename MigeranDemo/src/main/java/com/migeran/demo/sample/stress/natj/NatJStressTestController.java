package com.migeran.demo.sample.stress.natj;

import ios.c.Globals;
import ios.c.Globals.Block_dispatch_async;
import ios.foundation.NSBundle;
import ios.foundation.NSError;
import ios.foundation.NSIndexPath;
import ios.foundation.NSNumber;
import ios.foundation.NSNumberFormatter;
import ios.foundation.enums.NSNumberFormatterStyle;
import ios.messageui.MFMailComposeViewController;
import ios.messageui.enums.MFMailComposeResult;
import ios.messageui.protocol.MFMailComposeViewControllerDelegate;
import ios.uikit.UIActionSheet;
import ios.uikit.UIAlertView;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UINib;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;
import ios.uikit.enums.UIBarButtonItemStyle;
import ios.uikit.protocol.UIActionSheetDelegate;

import java.util.ArrayList;
import java.util.Date;

import ui.NatJTestCell;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NFloat;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.Selector;

public class NatJStressTestController extends UITableViewController implements
		UIActionSheetDelegate, MFMailComposeViewControllerDelegate {

	private static final String IDENTIFIER = "NatJTestCell";

	static {
		NatJ.register();
	}

	@Generated("NatJ")
	@Owned
	@Selector("alloc")
	public static native NatJStressTestController alloc();

	@Generated("NatJ")
	@Selector("init")
	public native NatJStressTestController init();

	@Generated("NatJ")
	protected NatJStressTestController(Pointer peer) {
		super(peer);
	}

	static class Box {
		public static native Box box(int value);

		public static native Box unbox(Integer value);
	}

	@Selector("viewDidLoad")
	@Override
	public void viewDidLoad() {
		super.viewDidLoad();

		UINib nib = UINib.nibWithNibNameBundle("NatJTestCell",
				NSBundle.mainBundle());
		tableView().registerNibForCellReuseIdentifier(nib, IDENTIFIER);

		setTitle("NatJ Tests");

		UIBarButtonItem rbbi = UIBarButtonItem.alloc()
				.initWithTitleStyleTargetAction("Actions",
						UIBarButtonItemStyle.Plain, this,
						new SEL("showActions:"));
		navigationItem().setRightBarButtonItem(rbbi);

		CFunctionTests.add(CFunctions.FVoidReturn.TEST);
		CFunctionTests.add(CFunctions.FIntReturn.TEST);
		CFunctionTests.add(CFunctions.FLongReturn.TEST);
		CFunctionTests.add(CFunctions.FFloatReturn.TEST);
		CFunctionTests.add(CFunctions.FDoubleReturn.TEST);
		CFunctionTests.add(CFunctions.FNilReturn.TEST);
		CFunctionTests.add(CFunctions.FBindingReturn.TEST);
		CFunctionTests.add(CFunctions.FBindingWithHiddenImplClassReturn.TEST);
		CFunctionTests.add(CFunctions.FInheritedReturn.TEST);
		CFunctionTests.add(CFunctions.FProxyReturn.TEST);
		CFunctionTests.add(CFunctions.FHybridReturn.TEST);
		CFunctionTests.add(CFunctions.FStringReturn.TEST);
	}

	@Selector("showActions:")
	public void showActions(Object sender) {
		for (Test test : CFunctionTests) {
			test.interrupt();
		}
		UIActionSheet sheet = UIActionSheet.alloc().init();
		sheet.setTitle("Select an action.");
		sheet.addButtonWithTitle("Run Tests (Short)");
		sheet.addButtonWithTitle("Run Tests (Normal)");
		sheet.addButtonWithTitle("Run Tests (Long)");
		sheet.addButtonWithTitle("Send Results");
		sheet.addButtonWithTitle("Cancel");
		sheet.setCancelButtonIndex(4);
		sheet.setDelegate(this);
		sheet.showFromBarButtonItemAnimated(navigationItem()
				.rightBarButtonItem(), true);
	}

	@Selector("viewWillDisappear:")
	@Override
	public void viewWillDisappear(boolean animated) {
		super.viewWillDisappear(animated);
		for (Test test : CFunctionTests) {
			test.interrupt();
		}
	}

	private ArrayList<Test> CFunctionTests = new ArrayList<>();

	@NInt
	@Selector("numberOfSectionsInTableView:")
	@Override
	public long numberOfSectionsInTableView(UITableView tableView) {
		return 2;
	}

	@NInt
	@Selector("tableView:numberOfRowsInSection:")
	@Override
	public long tableViewNumberOfRowsInSection(UITableView tableView,
			@NInt long section) {
		if (section == 0) {
			return 1;
		} else if (section == 1) {
			return CFunctionTests.size();
		}
		return 0;
	}

	@Selector("tableView:titleForHeaderInSection:")
	@Override
	public String tableViewTitleForHeaderInSection(UITableView tableView,
			@NInt long section) {
		if (section == 0) {
			return "Help";
		} else if (section == 1) {
			return CFunctions.CATEGORY;
		}
		return "";
	}

	@Selector("tableView:cellForRowAtIndexPath:")
	@Override
	public UITableViewCell tableViewCellForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath) {
		NatJTestCell cell = (NatJTestCell) tableView
				.dequeueReusableCellWithIdentifierForIndexPath(IDENTIFIER,
						indexPath);

		Test test = null;
		if (indexPath.section() == 0) {
			cell.titleLabel().setText("Test name");
			cell.timeLabel().setHidden(false);
			cell.timeLabel().setText("Single-threaded execution time");
			cell.mtTimeLabel().setHidden(false);
			cell.mtTimeLabel().setText("Multi-threaded execution time");
			cell.indicatorView().setHidden(true);
			return cell;

		} else if (indexPath.section() == 1) {
			test = CFunctionTests.get((int) indexPath.row());
		}
		cell.titleLabel().setText(test.getName());
		if (test.getSingleThreadedTime() == null) {
			cell.timeLabel().setHidden(true);
			cell.mtTimeLabel().setHidden(true);
		} else {
			cell.timeLabel().setHidden(false);
			cell.timeLabel().setText(test.getSingleThreadedTime());
			cell.mtTimeLabel().setHidden(false);
			cell.mtTimeLabel().setText(test.getMultiThreadedTime());
		}
		cell.indicatorView().setHidden(true);
		return cell;
	}

	@NFloat
	@Selector("tableView:heightForRowAtIndexPath:")
	@Override
	public double tableViewHeightForRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath) {
		return 56.0;
	}

	@Override
	@Selector("actionSheet:clickedButtonAtIndex:")
	public void actionSheetClickedButtonAtIndex(UIActionSheet actionSheet,
			@NInt final long buttonIndex) {
		if (buttonIndex == actionSheet.cancelButtonIndex()) {
			return;
		}
		if (buttonIndex == 0 || buttonIndex == 1 || buttonIndex == 2) {
			if (buttonIndex == 0) {
				Test.DEFAULT_ITERATION_COUNT = Test.ITERATION_COUNT_SHORT;
			} else if (buttonIndex == 1) {
				Test.DEFAULT_ITERATION_COUNT = Test.ITERATION_COUNT_NORMAL;
			} else if (buttonIndex == 2) {
				Test.DEFAULT_ITERATION_COUNT = Test.ITERATION_COUNT_LONG;
			}
			for (Test test : CFunctionTests) {
				test.reset();
			}
			tableView().reloadData();
			Globals.dispatch_async(Globals.dispatch_get_global_queue(2, 0),
					new Block_dispatch_async() {

						@Override
						public void call_dispatch_async() {
							boolean interrupted = false;
							for (final Test test : CFunctionTests) {
								if (test.isInterrupted()) {
									interrupted = true;
									break;
								}
								test.run();
								if (test.isInterrupted()) {
									interrupted = true;
									break;
								}
								Globals.dispatch_async(
										Globals.dispatch_get_main_queue(),
										new Block_dispatch_async() {

											@Override
											public void call_dispatch_async() {
												if (test.isInterrupted()) {
													return;
												}
												tableView().reloadData();
											}
										});
							}
							final boolean hadBeenInterrupted = interrupted;
							Globals.dispatch_async(
									Globals.dispatch_get_main_queue(),
									new Block_dispatch_async() {

										@Override
										public void call_dispatch_async() {
											UIAlertView alert = UIAlertView
													.alloc().init();
											if (hadBeenInterrupted) {
												alert.setTitle("Testing was interrupted!");
											} else {
												alert.setTitle("Testing completed.");
											}
											alert.addButtonWithTitle("OK");
											alert.show();
										}
									});
						}
					});
			return;
		}
		if (buttonIndex == 3) {
			MFMailComposeViewController mvc = MFMailComposeViewController
					.alloc().init();
			mvc.setMailComposeDelegate(this);
			mvc.setSubject("NatJ Tests " + new Date().toGMTString());

			StringBuilder b = new StringBuilder();
			// Header
			b.append("<!DOCTYPE html>\n<html><table><body>");
			NSNumberFormatter formater = NSNumberFormatter.alloc().init();
			formater.setNumberStyle(NSNumberFormatterStyle.DecimalStyle);
			b.append("<p>Tested with "
					+ formater.stringFromNumber(NSNumber
							.numberWithInt(Test.DEFAULT_ITERATION_COUNT))
					+ " iterations/thread.</p>");
			b.append("<p>Multi-threaded tests ran on  "
					+ Test.MULTITHREAD_COUNT + " threads.</p>");
			b.append("<thead>");
			b.append("<tr><td>Name</td><td>Single-threaded</td><td>Multi-threaded</td></tr>");
			b.append("</thead>");
			b.append("<tbody>");
			for (Test test : CFunctionTests) {
				b.append("<tr><td>" + test.getName() + "</td><td>"
						+ test.getSingleThreadedTime() + "</td><td>"
						+ test.getMultiThreadedTime() + "</td></tr>");
			}
			b.append("</tbody>");
			b.append("</table></html></body>");
			mvc.setMessageBodyIsHTML(b.toString(), true);
			presentViewControllerAnimatedCompletion(mvc, true, null);
		}
	}

	@NotImplemented
	@Override
	@Selector("actionSheet:didDismissWithButtonIndex:")
	@IsOptional
	public native void actionSheetDidDismissWithButtonIndex(
			UIActionSheet actionSheet, @NInt long buttonIndex);

	@NotImplemented
	@Override
	@Selector("actionSheet:willDismissWithButtonIndex:")
	@IsOptional
	public native void actionSheetWillDismissWithButtonIndex(
			UIActionSheet actionSheet, @NInt long buttonIndex);

	@NotImplemented
	@Override
	@Selector("actionSheetCancel:")
	@IsOptional
	public native void actionSheetCancel(UIActionSheet actionSheet);

	@NotImplemented
	@Override
	@Selector("didPresentActionSheet:")
	@IsOptional
	public native void didPresentActionSheet(UIActionSheet actionSheet);

	@NotImplemented
	@Override
	@Selector("willPresentActionSheet:")
	@IsOptional
	public native void willPresentActionSheet(UIActionSheet actionSheet);

	@Override
	@Selector("mailComposeController:didFinishWithResult:error:")
	public void mailComposeControllerDidFinishWithResultError(
			MFMailComposeViewController controller, int result, NSError error) {
		if (result == MFMailComposeResult.Failed) {
			UIAlertView alert = UIAlertView.alloc().init();
			alert.setTitle("Failed to send email!");
			alert.setMessage(error.localizedFailureReason());
			alert.addButtonWithTitle("OK");
			alert.show();
		}
		controller.dismissViewControllerAnimatedCompletion(true, null);
	}
}
