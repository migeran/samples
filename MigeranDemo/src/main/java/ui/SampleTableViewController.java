package ui;

import ios.foundation.NSBundle;
import ios.foundation.NSIndexPath;
import ios.foundation.NSNotification;
import ios.foundation.NSNotificationCenter;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIDevice;
import ios.uikit.UINavigationController;
import ios.uikit.UIPopoverController;
import ios.uikit.UISplitViewController;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;
import ios.uikit.UIViewController;
import ios.uikit.enums.UIUserInterfaceIdiom;
import ios.uikit.protocol.UISplitViewControllerDelegate;

import java.util.ArrayList;

import com.migeran.demo.Sample;
import com.migeran.demo.SampleCategory;
import com.migeran.demo.sample.graphics.opengl1.OpenGLBoxController;
import com.migeran.demo.sample.graphics.planets.PlanetsBenchmarkController;
import com.migeran.demo.sample.graphics.planets.PlanetsController;
import com.migeran.demo.sample.map.simplemap.SimpleMapController;
import com.migeran.demo.sample.stress.natj.NatJStressTestController;
import com.migeran.demo.sample.utility.calculator.CalcController;
import com.migeran.demo.sample.utility.wordsearch.WordSearchController;
import com.migeran.demo.sample.web.browser.BrowserController;
import com.migeran.demo.sample.web.rssreader.RSSReaderController;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.general.ann.NUInt;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.RegisterOnStartup;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.ObjCClassName;
import com.migeran.natj.objc.ann.Selector;
import com.migeran.natj.general.ann.Mapped;

import java.lang.String;

@Generated
@ObjCClassName("SampleTableViewController")
@Runtime(ObjCRuntime.class)
@RegisterOnStartup
public class SampleTableViewController extends UITableViewController implements
		UISplitViewControllerDelegate {
	static {
		NatJ.register();
	}

	public static final String DISABLE_USER_INTERACTION = "DISABLE_USER_INTERACTION";
	public static final String ENABLE_USER_INTERACTION = "ENABLE_USER_INTERACTION";

	private boolean enableUserInteraction = true;

	private final ArrayList<SampleCategory> sampleCategories = new ArrayList<SampleCategory>();

	@Generated
	protected SampleTableViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native SampleTableViewController alloc();

	@Generated
	@Selector("sampleNavigationController")
	public native UINavigationController sampleNavigationController();

	@Generated
	@Selector("setSampleNavigationController:")
	public native void setSampleNavigationController(
			UINavigationController value);

	@Generated
	@Selector("init")
	public native SampleTableViewController init();

	@Generated
	@Selector("initWithNibName:bundle:")
	public native SampleTableViewController initWithNibNameBundle(
			String nibNameOrNil, NSBundle nibBundleOrNil);

	@Generated
	@Selector("initWithStyle:")
	public native SampleTableViewController initWithStyle(@NInt long style);

	@Override
	@Selector("awakeFromNib")
	public void awakeFromNib() {
		if (sampleCategories.size() == 0) {
			insertSample(SampleCategory.GRAPHICS, "Planets",
					PlanetsController.class);
			insertSample(SampleCategory.GRAPHICS, "Planets - Benchmark",
					PlanetsBenchmarkController.class);
			insertSample(SampleCategory.UTILITY, "Calculator",
					CalcController.class);
			insertSample(SampleCategory.WEB, "Web Browser",
					BrowserController.class);
			insertSample(SampleCategory.WEB, "RSS Reader",
					RSSReaderController.class);
			insertSample(SampleCategory.MAP, "Simple Map",
					SimpleMapController.class);
			insertSample(SampleCategory.UTILITY, "Word Search",
					WordSearchController.class);
			insertSample(SampleCategory.STRESS_TEST, "NatJ Stress Test",
					NatJStressTestController.class);
			insertSample(SampleCategory.GRAPHICS, "OpenGL Box",
					OpenGLBoxController.class);
		}

		NSNotificationCenter dc = (NSNotificationCenter) NSNotificationCenter
				.defaultCenter();
		dc.addObserverSelectorNameObject(this, new SEL("processNotification:"),
				ENABLE_USER_INTERACTION, null);
		dc.addObserverSelectorNameObject(this, new SEL("processNotification:"),
				DISABLE_USER_INTERACTION, null);
	}

	@Override
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Samples");

		if (sampleNavigationController() == null) {
			setSampleNavigationController(navigationController());
		}

		tableView().reloadData();
	}

	private void insertSample(String category, String name,
			Class<? extends UIViewController> clazz) {
		SampleCategory cat = SampleCategory.getCategory(category);
		if (!sampleCategories.contains(cat)) {
			sampleCategories.add(cat);
		}
		cat.add(new Sample(name, clazz));
	}

	@Override
	@Selector("numberOfSectionsInTableView:")
	public @NInt long numberOfSectionsInTableView(UITableView tableView) {
		return sampleCategories.size();
	}

	@Override
	@Selector("tableView:titleForHeaderInSection:")
	public String tableViewTitleForHeaderInSection(UITableView tableView,
			@NInt long section) {
		return sampleCategories.get((int)section).getName();
	}

	@Override
	@Selector("tableView:numberOfRowsInSection:")
	public @NInt long tableViewNumberOfRowsInSection(UITableView tableView, @NInt long section) {
		return sampleCategories.get((int)section).size();
	}

	@Override
	@Selector("tableView:cellForRowAtIndexPath:")
	public UITableViewCell tableViewCellForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath) {
		UITableViewCell cell = (UITableViewCell) tableView
				.dequeueReusableCellWithIdentifierForIndexPath("SampleCell",
						indexPath);

		cell.textLabel().setText(
				sampleCategories.get((int)indexPath.section()).get((int)indexPath.row())
						.getName());

		return cell;
	}

	@Override
	@Selector("tableView:didSelectRowAtIndexPath:")
	public void tableViewDidSelectRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath) {
		if (!enableUserInteraction) {
			return;
		}
		Sample sample = sampleCategories.get((int)indexPath.section()).get(
				(int)indexPath.row());
		UIViewController vc = sample.instantiate();

		boolean animate = UIDevice.currentDevice().userInterfaceIdiom() == UIUserInterfaceIdiom.Phone;

		sampleNavigationController().popToRootViewControllerAnimated(animate);
		sampleNavigationController().pushViewControllerAnimated(vc, animate);
	}

	@Selector("processNotification:")
	public void processNotification(NSNotification notif) {
		if (notif.name().equals(ENABLE_USER_INTERACTION)) {
			enableUserInteraction = true;
		} else if (notif.name().equals(DISABLE_USER_INTERACTION)) {
			enableUserInteraction = false;
		}
	}

	@NotImplemented
	@Override
	@Selector("splitViewController:popoverController:willPresentViewController:")
	@IsOptional
	public native void splitViewControllerPopoverControllerWillPresentViewController(
			UISplitViewController svc, UIPopoverController pc,
			UIViewController aViewController);

	@NotImplemented
	@Override
	@Selector("splitViewController:shouldHideViewController:inOrientation:")
	@IsOptional
	public native boolean splitViewControllerShouldHideViewControllerInOrientation(
			UISplitViewController svc, UIViewController vc, @NInt long orientation);

	@Override
	@Selector("splitViewController:willHideViewController:withBarButtonItem:forPopoverController:")
	@IsOptional
	public void splitViewControllerWillHideViewControllerWithBarButtonItemForPopoverController(
			UISplitViewController svc, UIViewController aViewController,
			UIBarButtonItem barButtonItem, UIPopoverController pc) {

	}

	@NotImplemented
	@Override
	@Selector("splitViewController:willShowViewController:invalidatingBarButtonItem:")
	@IsOptional
	public native void splitViewControllerWillShowViewControllerInvalidatingBarButtonItem(
			UISplitViewController svc, UIViewController aViewController,
			UIBarButtonItem barButtonItem);

	@NotImplemented
	@Override
	@Selector("splitViewControllerPreferredInterfaceOrientationForPresentation:")
	@IsOptional
	@NInt
	public native long splitViewControllerPreferredInterfaceOrientationForPresentation(
			UISplitViewController splitViewController);

	@NotImplemented
	@Override
	@Selector("splitViewControllerSupportedInterfaceOrientations:")
	@IsOptional
	public native @NUInt long splitViewControllerSupportedInterfaceOrientations(
			UISplitViewController splitViewController);

	@NotImplemented
	@Override
	@Selector("primaryViewControllerForCollapsingSplitViewController:")
	@IsOptional
	public native UIViewController primaryViewControllerForCollapsingSplitViewController(
			UISplitViewController splitViewController);

	@NotImplemented
	@Override
	@Selector("primaryViewControllerForExpandingSplitViewController:")
	@IsOptional
	public native UIViewController primaryViewControllerForExpandingSplitViewController(
			UISplitViewController splitViewController);

	@NotImplemented
	@Override
	@Selector("splitViewController:collapseSecondaryViewController:ontoPrimaryViewController:")
	@IsOptional
	public native boolean splitViewControllerCollapseSecondaryViewControllerOntoPrimaryViewController(
			UISplitViewController splitViewController,
			UIViewController secondaryViewController,
			UIViewController primaryViewController);

	@NotImplemented
	@Override
	@Selector("splitViewController:separateSecondaryViewControllerFromPrimaryViewController:")
	@IsOptional
	public native UIViewController splitViewControllerSeparateSecondaryViewControllerFromPrimaryViewController(
			UISplitViewController splitViewController,
			UIViewController primaryViewController);

	@NotImplemented
	@Override
	@Selector("splitViewController:showDetailViewController:sender:")
	@IsOptional
	public native boolean splitViewControllerShowDetailViewControllerSender(
			UISplitViewController splitViewController,
			UIViewController vc,
			@Mapped(com.migeran.natj.objc.map.ObjCObjectMapper.class) Object sender);

	@NotImplemented
	@Override
	@Selector("splitViewController:showViewController:sender:")
	@IsOptional
	public native boolean splitViewControllerShowViewControllerSender(
			UISplitViewController splitViewController,
			UIViewController vc,
			@Mapped(com.migeran.natj.objc.map.ObjCObjectMapper.class) Object sender);

	@NotImplemented
	@Override
	@Selector("splitViewController:willChangeToDisplayMode:")
	@IsOptional
	public native void splitViewControllerWillChangeToDisplayMode(
			UISplitViewController svc, @NInt long displayMode);

	@NotImplemented
	@Override
	@Selector("targetDisplayModeForActionInSplitViewController:")
	@IsOptional
	@NInt
	public native long targetDisplayModeForActionInSplitViewController(
			UISplitViewController svc);
}
