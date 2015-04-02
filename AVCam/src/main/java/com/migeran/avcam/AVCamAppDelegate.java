package com.migeran.avcam;

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
import ios.uikit.UILocalNotification;
import ios.uikit.UIUserNotificationSettings;
import ios.uikit.UIViewController;
import ios.uikit.UIWindow;
import ios.uikit.protocol.UIApplicationDelegate;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.ByValue;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.Mapped;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.general.ann.NUInt;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.RegisterOnStartup;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.ObjCBlock;
import com.migeran.natj.objc.ann.Selector;

@RegisterOnStartup
public class AVCamAppDelegate extends NSObject implements UIApplicationDelegate {
	
	private UIWindow window;
	
	@Selector("setDelegate:")
	public void setDelegate(Object o) {
		System.out.println("WTF" + o.getClass().getName());
	}
	
	@Override
	@Selector("setWindow:")
	public void setWindow(UIWindow arg0) {
		window = arg0;
	}

	@Override
	@Selector("window")
	public UIWindow window() {
		return window;
	}
	
	static {
		NatJ.register();
	}

	@Generated("NatJ")
	@Owned
	@Selector("alloc")
	public static native AVCamAppDelegate alloc();

	@Generated("NatJ")
	protected AVCamAppDelegate(Pointer peer) {
		super(peer);
	}

	@NotImplemented
	@Override
	@Selector("application:didChangeStatusBarFrame:")
	@IsOptional
	public native void applicationDidChangeStatusBarFrame(UIApplication arg0,
			@ByValue CGRect arg1);

	@NotImplemented
	@Override
	@Selector("application:didChangeStatusBarOrientation:")
	@IsOptional
	public native void applicationDidChangeStatusBarOrientation(
			UIApplication arg0, @NInt long arg1);

	@NotImplemented
	@Override
	@Selector("application:didDecodeRestorableStateWithCoder:")
	@IsOptional
	public native void applicationDidDecodeRestorableStateWithCoder(
			UIApplication arg0, NSCoder arg1);

	@NotImplemented
	@Override
	@Selector("application:didFailToRegisterForRemoteNotificationsWithError:")
	@IsOptional
	public native void applicationDidFailToRegisterForRemoteNotificationsWithError(
			UIApplication arg0, NSError arg1);

	@NotImplemented
	@Override
	@Selector("application:didFinishLaunchingWithOptions:")
	@IsOptional
	public native boolean applicationDidFinishLaunchingWithOptions(
			UIApplication arg0, NSDictionary arg1);

	@NotImplemented
	@Override
	@Selector("application:didReceiveLocalNotification:")
	@IsOptional
	public native void applicationDidReceiveLocalNotification(
			UIApplication arg0, UILocalNotification arg1);

	@NotImplemented
	@Override
	@Selector("application:didReceiveRemoteNotification:")
	@IsOptional
	public native void applicationDidReceiveRemoteNotification(
			UIApplication arg0, NSDictionary arg1);

	@NotImplemented
	@Override
	@Selector("application:didReceiveRemoteNotification:fetchCompletionHandler:")
	@IsOptional
	public native void applicationDidReceiveRemoteNotificationFetchCompletionHandler(
			UIApplication arg0,
			NSDictionary arg1,
			@ObjCBlock(name = "applicationDidReceiveRemoteNotificationFetchCompletionHandler") UIApplicationDelegate.Block_applicationDidReceiveRemoteNotificationFetchCompletionHandler arg2);

	@NotImplemented
	@Override
	@Selector("application:didRegisterForRemoteNotificationsWithDeviceToken:")
	@IsOptional
	public native void applicationDidRegisterForRemoteNotificationsWithDeviceToken(
			UIApplication arg0, NSData arg1);

	@NotImplemented
	@Override
	@Selector("application:handleEventsForBackgroundURLSession:completionHandler:")
	@IsOptional
	public native void applicationHandleEventsForBackgroundURLSessionCompletionHandler(
			UIApplication arg0,
			String arg1,
			@ObjCBlock(name = "applicationHandleEventsForBackgroundURLSessionCompletionHandler") UIApplicationDelegate.Block_applicationHandleEventsForBackgroundURLSessionCompletionHandler arg2);

	@NotImplemented
	@Override
	@Selector("application:handleOpenURL:")
	@IsOptional
	public native boolean applicationHandleOpenURL(UIApplication arg0,
			NSURL arg1);

	@NotImplemented
	@Override
	@Selector("application:openURL:sourceApplication:annotation:")
	@IsOptional
	public native boolean applicationOpenURLSourceApplicationAnnotation(
			UIApplication arg0,
			NSURL arg1,
			String arg2,
			@Mapped(com.migeran.natj.objc.map.ObjCObjectMapper.class) Object arg3);

	@NotImplemented
	@Override
	@Selector("application:performFetchWithCompletionHandler:")
	@IsOptional
	public native void applicationPerformFetchWithCompletionHandler(
			UIApplication arg0,
			@ObjCBlock(name = "applicationPerformFetchWithCompletionHandler") UIApplicationDelegate.Block_applicationPerformFetchWithCompletionHandler arg1);

	@NotImplemented
	@Override
	@Selector("application:shouldRestoreApplicationState:")
	@IsOptional
	public native boolean applicationShouldRestoreApplicationState(
			UIApplication arg0, NSCoder arg1);

	@NotImplemented
	@Override
	@Selector("application:shouldSaveApplicationState:")
	@IsOptional
	public native boolean applicationShouldSaveApplicationState(
			UIApplication arg0, NSCoder arg1);

	@NotImplemented
	@Override
	@Selector("application:supportedInterfaceOrientationsForWindow:")
	@IsOptional
	@NUInt
	public native long applicationSupportedInterfaceOrientationsForWindow(
			UIApplication arg0, UIWindow arg1);

	@NotImplemented
	@Override
	@Selector("application:viewControllerWithRestorationIdentifierPath:coder:")
	@IsOptional
	public native UIViewController applicationViewControllerWithRestorationIdentifierPathCoder(
			UIApplication arg0, NSArray arg1, NSCoder arg2);

	@NotImplemented
	@Override
	@Selector("application:willChangeStatusBarFrame:")
	@IsOptional
	public native void applicationWillChangeStatusBarFrame(UIApplication arg0,
			@ByValue CGRect arg1);

	@NotImplemented
	@Override
	@Selector("application:willChangeStatusBarOrientation:duration:")
	@IsOptional
	public native void applicationWillChangeStatusBarOrientationDuration(
			UIApplication arg0, @NInt long arg1, double arg2);

	@NotImplemented
	@Override
	@Selector("application:willEncodeRestorableStateWithCoder:")
	@IsOptional
	public native void applicationWillEncodeRestorableStateWithCoder(
			UIApplication arg0, NSCoder arg1);

	@NotImplemented
	@Override
	@Selector("application:willFinishLaunchingWithOptions:")
	@IsOptional
	public native boolean applicationWillFinishLaunchingWithOptions(
			UIApplication arg0, NSDictionary arg1);

	@NotImplemented
	@Override
	@Selector("applicationDidBecomeActive:")
	@IsOptional
	public native void applicationDidBecomeActive(UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("applicationDidEnterBackground:")
	@IsOptional
	public native void applicationDidEnterBackground(UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("applicationDidFinishLaunching:")
	@IsOptional
	public native void applicationDidFinishLaunching(UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("applicationDidReceiveMemoryWarning:")
	@IsOptional
	public native void applicationDidReceiveMemoryWarning(UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("applicationProtectedDataDidBecomeAvailable:")
	@IsOptional
	public native void applicationProtectedDataDidBecomeAvailable(
			UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("applicationProtectedDataWillBecomeUnavailable:")
	@IsOptional
	public native void applicationProtectedDataWillBecomeUnavailable(
			UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("applicationSignificantTimeChange:")
	@IsOptional
	public native void applicationSignificantTimeChange(UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("applicationWillEnterForeground:")
	@IsOptional
	public native void applicationWillEnterForeground(UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("applicationWillResignActive:")
	@IsOptional
	public native void applicationWillResignActive(UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("applicationWillTerminate:")
	@IsOptional
	public native void applicationWillTerminate(UIApplication arg0);

	@NotImplemented
	@Override
	@Selector("application:continueUserActivity:restorationHandler:")
	@IsOptional
	public native boolean applicationContinueUserActivityRestorationHandler(
			UIApplication arg0,
			NSUserActivity arg1,
			@ObjCBlock(name = "applicationContinueUserActivityRestorationHandler") UIApplicationDelegate.Block_applicationContinueUserActivityRestorationHandler arg2);

	@NotImplemented
	@Override
	@Selector("application:didFailToContinueUserActivityWithType:error:")
	@IsOptional
	public native void applicationDidFailToContinueUserActivityWithTypeError(
			UIApplication arg0, String arg1, NSError arg2);

	@NotImplemented
	@Override
	@Selector("application:didRegisterUserNotificationSettings:")
	@IsOptional
	public native void applicationDidRegisterUserNotificationSettings(
			UIApplication arg0, UIUserNotificationSettings arg1);

	@NotImplemented
	@Override
	@Selector("application:didUpdateUserActivity:")
	@IsOptional
	public native void applicationDidUpdateUserActivity(UIApplication arg0,
			NSUserActivity arg1);

	@NotImplemented
	@Override
	@Selector("application:handleActionWithIdentifier:forLocalNotification:completionHandler:")
	@IsOptional
	public native void applicationHandleActionWithIdentifierForLocalNotificationCompletionHandler(
			UIApplication arg0,
			String arg1,
			UILocalNotification arg2,
			@ObjCBlock(name = "applicationHandleActionWithIdentifierForLocalNotificationCompletionHandler") UIApplicationDelegate.Block_applicationHandleActionWithIdentifierForLocalNotificationCompletionHandler arg3);

	@NotImplemented
	@Override
	@Selector("application:handleActionWithIdentifier:forRemoteNotification:completionHandler:")
	@IsOptional
	public native void applicationHandleActionWithIdentifierForRemoteNotificationCompletionHandler(
			UIApplication arg0,
			String arg1,
			NSDictionary arg2,
			@ObjCBlock(name = "applicationHandleActionWithIdentifierForRemoteNotificationCompletionHandler") UIApplicationDelegate.Block_applicationHandleActionWithIdentifierForRemoteNotificationCompletionHandler arg3);

	@NotImplemented
	@Override
	@Selector("application:shouldAllowExtensionPointIdentifier:")
	@IsOptional
	public native boolean applicationShouldAllowExtensionPointIdentifier(
			UIApplication arg0, String arg1);

	@NotImplemented
	@Override
	@Selector("application:willContinueUserActivityWithType:")
	@IsOptional
	public native boolean applicationWillContinueUserActivityWithType(
			UIApplication arg0, String arg1);

}
