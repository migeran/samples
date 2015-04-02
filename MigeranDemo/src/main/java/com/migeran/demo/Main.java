package com.migeran.demo;

import ios.NSObject;
import ios.coregraphics.struct.CGRect;
import ios.foundation.NSArray;
import ios.foundation.NSCoder;
import ios.foundation.NSData;
import ios.foundation.NSDictionary;
import ios.foundation.NSError;
import ios.foundation.NSURL;
import ios.foundation.NSUserActivity;
import ios.uikit.UIApplication;
import ios.uikit.UIDevice;
import ios.uikit.UILocalNotification;
import ios.uikit.UINavigationController;
import ios.uikit.UISplitViewController;
import ios.uikit.UIUserNotificationSettings;
import ios.uikit.UIViewController;
import ios.uikit.UIWindow;
import ios.uikit.c.UIKit;
import ios.uikit.enums.UIUserInterfaceIdiom;
import ios.uikit.protocol.UIApplicationDelegate;
import ui.SampleTableViewController;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.ByValue;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.Mapped;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.general.ann.NUInt;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.ObjCBlock;
import com.migeran.natj.objc.ann.Selector;

public class Main extends NSObject implements UIApplicationDelegate {

	static {
		NatJ.register();
	}

	private UIWindow window;
	
	public static void main(String[] args) {
		UIKit.UIApplicationMain(0, null, null, Main.class.getName());
	}

	@Generated("NatJ")
	@Selector("alloc")
	public static native Main alloc();

	@Generated("NatJ")
	protected Main(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("application:didFinishLaunchingWithOptions:")
	@IsOptional
	public boolean applicationDidFinishLaunchingWithOptions(
			UIApplication application, NSDictionary launchOptions) {
		
		if (UIDevice.currentDevice().userInterfaceIdiom() == UIUserInterfaceIdiom.Pad) {
			UISplitViewController svc = (UISplitViewController) window.rootViewController();
			
			UINavigationController mnc = (UINavigationController) svc.viewControllers().get(0);
			SampleTableViewController stvc = (SampleTableViewController) mnc.viewControllers().get(0);
			svc.setDelegate_unsafe(stvc);
			
			UINavigationController dnc = (UINavigationController) svc.viewControllers().get(1);
			stvc.setSampleNavigationController(dnc);
			
			UIViewController detroot = (UIViewController) dnc.viewControllers().get(0);
			detroot.setTitle("Migeran Demo");
		}
		
		return true;
	}

	@Override
	@Selector("setWindow:")
	@IsOptional
	public void setWindow(UIWindow value) {
		window = value;
	}

	@Override
	@Selector("window")
	@IsOptional
	public UIWindow window() {
		return window;
	}

	@NotImplemented
	@Override
	@Selector("application:didChangeStatusBarFrame:")
	@IsOptional
	public native void applicationDidChangeStatusBarFrame(
			UIApplication application, @ByValue CGRect oldStatusBarFrame);

	@NotImplemented
	@Override
	@Selector("application:didChangeStatusBarOrientation:")
	@IsOptional
	public native void applicationDidChangeStatusBarOrientation(
			UIApplication application, @NInt long oldStatusBarOrientation);

	@NotImplemented
	@Override
	@Selector("application:didDecodeRestorableStateWithCoder:")
	@IsOptional
	public native void applicationDidDecodeRestorableStateWithCoder(
			UIApplication application, NSCoder coder);

	@NotImplemented
	@Override
	@Selector("application:didFailToRegisterForRemoteNotificationsWithError:")
	@IsOptional
	public native void applicationDidFailToRegisterForRemoteNotificationsWithError(
			UIApplication application, NSError error);

	@NotImplemented
	@Override
	@Selector("application:didReceiveLocalNotification:")
	@IsOptional
	public native void applicationDidReceiveLocalNotification(
			UIApplication application, UILocalNotification notification);

	@NotImplemented
	@Override
	@Selector("application:didReceiveRemoteNotification:")
	@IsOptional
	public native void applicationDidReceiveRemoteNotification(
			UIApplication application, NSDictionary userInfo);

	@NotImplemented
	@Override
	@Selector("application:didReceiveRemoteNotification:fetchCompletionHandler:")
	@IsOptional
	public native void applicationDidReceiveRemoteNotificationFetchCompletionHandler(
			UIApplication application,
			NSDictionary userInfo,
			@ObjCBlock(name = "applicationDidReceiveRemoteNotificationFetchCompletionHandler") UIApplicationDelegate.Block_applicationDidReceiveRemoteNotificationFetchCompletionHandler completionHandler);

	@NotImplemented
	@Override
	@Selector("application:didRegisterForRemoteNotificationsWithDeviceToken:")
	@IsOptional
	public native void applicationDidRegisterForRemoteNotificationsWithDeviceToken(
			UIApplication application, NSData deviceToken);

	@NotImplemented
	@Override
	@Selector("application:handleEventsForBackgroundURLSession:completionHandler:")
	@IsOptional
	public native void applicationHandleEventsForBackgroundURLSessionCompletionHandler(
			UIApplication application,
			String identifier,
			@ObjCBlock(name = "applicationHandleEventsForBackgroundURLSessionCompletionHandler") UIApplicationDelegate.Block_applicationHandleEventsForBackgroundURLSessionCompletionHandler completionHandler);

	@NotImplemented
	@Override
	@Selector("application:handleOpenURL:")
	@IsOptional
	public native boolean applicationHandleOpenURL(UIApplication application,
			NSURL url);

	@NotImplemented
	@Override
	@Selector("application:openURL:sourceApplication:annotation:")
	@IsOptional
	public native boolean applicationOpenURLSourceApplicationAnnotation(
			UIApplication application,
			NSURL url,
			String sourceApplication,
			@Mapped(com.migeran.natj.objc.map.ObjCObjectMapper.class) Object annotation);

	@NotImplemented
	@Override
	@Selector("application:performFetchWithCompletionHandler:")
	@IsOptional
	public native void applicationPerformFetchWithCompletionHandler(
			UIApplication application,
			@ObjCBlock(name = "applicationPerformFetchWithCompletionHandler") UIApplicationDelegate.Block_applicationPerformFetchWithCompletionHandler completionHandler);

	@NotImplemented
	@Override
	@Selector("application:shouldRestoreApplicationState:")
	@IsOptional
	public native boolean applicationShouldRestoreApplicationState(
			UIApplication application, NSCoder coder);

	@NotImplemented
	@Override
	@Selector("application:shouldSaveApplicationState:")
	@IsOptional
	public native boolean applicationShouldSaveApplicationState(
			UIApplication application, NSCoder coder);

	@NotImplemented
	@Override
	@Selector("application:supportedInterfaceOrientationsForWindow:")
	@IsOptional
	@NUInt
	public native long applicationSupportedInterfaceOrientationsForWindow(
			UIApplication application, UIWindow window);

	@NotImplemented
	@Override
	@Selector("application:viewControllerWithRestorationIdentifierPath:coder:")
	@IsOptional
	public native UIViewController applicationViewControllerWithRestorationIdentifierPathCoder(
			UIApplication application, NSArray identifierComponents,
			NSCoder coder);

	@NotImplemented
	@Override
	@Selector("application:willChangeStatusBarFrame:")
	@IsOptional
	public native void applicationWillChangeStatusBarFrame(
			UIApplication application, @ByValue CGRect newStatusBarFrame);

	@NotImplemented
	@Override
	@Selector("application:willChangeStatusBarOrientation:duration:")
	@IsOptional
	public native void applicationWillChangeStatusBarOrientationDuration(
			UIApplication application, @NInt long newStatusBarOrientation,
			double duration);

	@NotImplemented
	@Override
	@Selector("application:willEncodeRestorableStateWithCoder:")
	@IsOptional
	public native void applicationWillEncodeRestorableStateWithCoder(
			UIApplication application, NSCoder coder);

	@NotImplemented
	@Override
	@Selector("application:willFinishLaunchingWithOptions:")
	@IsOptional
	public native boolean applicationWillFinishLaunchingWithOptions(
			UIApplication application, NSDictionary launchOptions);

	@NotImplemented
	@Override
	@Selector("applicationDidBecomeActive:")
	@IsOptional
	public native void applicationDidBecomeActive(UIApplication application);

	@NotImplemented
	@Override
	@Selector("applicationDidEnterBackground:")
	@IsOptional
	public native void applicationDidEnterBackground(UIApplication application);

	@NotImplemented
	@Override
	@Selector("applicationDidFinishLaunching:")
	@IsOptional
	public native void applicationDidFinishLaunching(UIApplication application);

	@NotImplemented
	@Override
	@Selector("applicationDidReceiveMemoryWarning:")
	@IsOptional
	public native void applicationDidReceiveMemoryWarning(
			UIApplication application);

	@NotImplemented
	@Override
	@Selector("applicationProtectedDataDidBecomeAvailable:")
	@IsOptional
	public native void applicationProtectedDataDidBecomeAvailable(
			UIApplication application);

	@NotImplemented
	@Override
	@Selector("applicationProtectedDataWillBecomeUnavailable:")
	@IsOptional
	public native void applicationProtectedDataWillBecomeUnavailable(
			UIApplication application);

	@NotImplemented
	@Override
	@Selector("applicationSignificantTimeChange:")
	@IsOptional
	public native void applicationSignificantTimeChange(
			UIApplication application);

	@NotImplemented
	@Override
	@Selector("applicationWillEnterForeground:")
	@IsOptional
	public native void applicationWillEnterForeground(UIApplication application);

	@NotImplemented
	@Override
	@Selector("applicationWillResignActive:")
	@IsOptional
	public native void applicationWillResignActive(UIApplication application);

	@NotImplemented
	@Override
	@Selector("applicationWillTerminate:")
	@IsOptional
	public native void applicationWillTerminate(UIApplication application);

	@NotImplemented
	@Override
	@Selector("application:continueUserActivity:restorationHandler:")
	public native boolean applicationContinueUserActivityRestorationHandler(
			UIApplication arg0, NSUserActivity arg1,
			Block_applicationContinueUserActivityRestorationHandler arg2);

	@NotImplemented
	@Override
	@Selector("application:didFailToContinueUserActivityWithType:error:")
	public native void applicationDidFailToContinueUserActivityWithTypeError(
			UIApplication arg0, String arg1, NSError arg2);

	@NotImplemented
	@Override
	@Selector("application:didRegisterUserNotificationSettings:")
	public native void applicationDidRegisterUserNotificationSettings(
			UIApplication arg0, UIUserNotificationSettings arg1);

	@NotImplemented
	@Override
	@Selector("application:didUpdateUserActivity:")
	public native void applicationDidUpdateUserActivity(UIApplication arg0,
			NSUserActivity arg1);

	@NotImplemented
	@Override
	@Selector("application:handleActionWithIdentifier:forLocalNotification:completionHandler:")
	public native void applicationHandleActionWithIdentifierForLocalNotificationCompletionHandler(
			UIApplication arg0,
			String arg1,
			UILocalNotification arg2,
			Block_applicationHandleActionWithIdentifierForLocalNotificationCompletionHandler arg3);

	@NotImplemented
	@Override
	@Selector("application:handleActionWithIdentifier:forRemoteNotification:completionHandler:")
	public native void applicationHandleActionWithIdentifierForRemoteNotificationCompletionHandler(
			UIApplication arg0,
			String arg1,
			NSDictionary arg2,
			Block_applicationHandleActionWithIdentifierForRemoteNotificationCompletionHandler arg3);

	@NotImplemented
	@Override
	@Selector("application:shouldAllowExtensionPointIdentifier:")
	public native boolean applicationShouldAllowExtensionPointIdentifier(
			UIApplication arg0, String arg1);

	@NotImplemented
	@Override
	@Selector("application:willContinueUserActivityWithType:")
	public native boolean applicationWillContinueUserActivityWithType(
			UIApplication arg0, String arg1);

}
