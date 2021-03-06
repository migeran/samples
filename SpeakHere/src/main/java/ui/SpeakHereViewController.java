package ui;

import ios.avfoundation.AVAudioSession;
import ios.avfoundation.c.AVFoundation;
import ios.avfoundation.enums.AVAudioSessionRouteChangeReason;
import ios.avfoundation.protocol.AVAudioSessionDelegate;
import ios.coreaudio.struct.AudioStreamBasicDescription;
import ios.foundation.NSBundle;
import ios.foundation.NSNotification;
import ios.foundation.NSNotificationCenter;
import ios.foundation.NSNumber;
import ios.foundation.c.Foundation;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIColor;
import ios.uikit.UILabel;
import ios.uikit.UIViewController;
import ios.uikit.c.UIKit;

import java.io.File;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NUInt;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.RegisterOnStartup;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.general.ptr.impl.PtrFactory;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.ObjCClassName;
import com.migeran.natj.objc.ann.Selector;
import com.migeran.speakhere.AQPlayer;
import com.migeran.speakhere.AQRecorder;
import com.migeran.speakhere.CAXException;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("SpeakHereViewController")
@RegisterOnStartup
public class SpeakHereViewController extends UIViewController implements AVAudioSessionDelegate {
	private static final boolean YES = true;
	private static final boolean NO = false;
	private static final byte FALSE = 0;
	private static final byte TRUE = 1;
	private static final int noErr = 0;

	static {
		NatJ.register();
	}

	@Generated
	protected SpeakHereViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native SpeakHereViewController alloc();

	@Generated
	@Selector("btn_play")
	public native UIBarButtonItem btn_play();

	@Generated
	@Selector("btn_record")
	public native UIBarButtonItem btn_record();

	@Generated
	@Selector("fileDescription")
	public native UILabel fileDescription();

	@Generated
	@Selector("init")
	public native SpeakHereViewController init();

	@Generated
	@Selector("initWithNibName:bundle:")
	public native SpeakHereViewController initWithNibNameBundle(String nibNameOrNil, NSBundle nibBundleOrNil);

	@Generated
	@Selector("lvlMeter_in")
	public native AQLevelMeter lvlMeter_in();

	@Generated
	@Selector("setBtn_play:")
	public native void setBtn_play_unsafe(UIBarButtonItem value);

	@Generated
	public void setBtn_play(UIBarButtonItem value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) btn_play();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setBtn_play_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setBtn_record:")
	public native void setBtn_record_unsafe(UIBarButtonItem value);

	@Generated
	public void setBtn_record(UIBarButtonItem value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) btn_record();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setBtn_record_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setFileDescription:")
	public native void setFileDescription_unsafe(UILabel value);

	@Generated
	public void setFileDescription(UILabel value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) fileDescription();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setFileDescription_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setLvlMeter_in:")
	public native void setLvlMeter_in_unsafe(AQLevelMeter value);

	@Generated
	public void setLvlMeter_in(AQLevelMeter value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) lvlMeter_in();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setLvlMeter_in_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime.unassociateObjCObject(this, __old);
		}
	}

	private static String OSTypeToStr(int t) {
		byte[] str = new byte[4];
		PtrFactory.newIntReference(t).getBytePtr().copyTo(str);

		StringBuilder builder = new StringBuilder(16);
		for (int i = 0; i < 4; ++i) {
			char c = (char) str[3 - i];
			if (!Character.isISOControl(c) && c != '\\') {
				builder.append(c);
			} else {
				builder.append(String.format("\\x%02x", (int) c));
			}
		}
		return builder.toString();
	}

	private void setFileDescriptionForFormat(AudioStreamBasicDescription format) {
		String dataFormat = OSTypeToStr(format.mFormatID());
		String text = "(" + format.mChannelsPerFrame() + " ch. " + dataFormat + " @ " + format.mSampleRate() + " Hz)";
		fileDescription().setText(text);
	}

	private AQPlayer player;
	private AQRecorder recorder;
	private boolean playbackWasInterrupted;
	private boolean playbackWasPaused;
	private boolean inBackground;

	private void stopPlayQueue() {
		player.StopQueue();
		lvlMeter_in().setAq(null);
		btn_record().setEnabled(YES);
	}

	private void pausePlayQueue() {
		player.PauseQueue();
		playbackWasPaused = YES;
	}

	private void stopRecord() throws CAXException {
		// Disconnect our level meter from the audio queue
		lvlMeter_in().setAq(null);

		recorder.StopRecord();

		// dispose the previous playback queue
		player.DisposeQueue(true);

		// now create a new queue for the recorded file
		File recordFilePath = new File(Foundation.NSTemporaryDirectory(), "recordedFile.caf");
		player.CreateQueueForFile(recordFilePath.getAbsolutePath());

		// Set the button's state back to "record"
		btn_record().setTitle("Record");
		btn_play().setEnabled(YES);
	}

	@Selector("play:")
	public void play(UIBarButtonItem sender) {
		if (player.IsRunning()) {
			if (playbackWasPaused) {
				int result = player.StartQueue(true);
				playbackWasPaused = false;
				if (result == noErr) {
					NSNotificationCenter.defaultCenter().postNotificationNameObject("playbackQueueResumed", this);
				}
			} else {
				stopPlayQueue();
			}
		} else {
			int result = player.StartQueue(false);
			if (result == noErr) {
				NSNotificationCenter.defaultCenter().postNotificationNameObject("playbackQueueResumed", this);
			}
		}
	}

	@Selector("record:")
	public void record(UIBarButtonItem sender) throws CAXException {
		if (recorder.IsRunning()) {
			// If we are currently recording, stop and save the file.
			stopRecord();
		} else {
			// If we're not recording, start.
			btn_play().setEnabled(NO);

			// Set the button's state to "stop"
			btn_record().setTitle("Stop");

			// Start the recorder
			recorder.StartRecord("recordedFile.caf");

			setFileDescriptionForFormat(recorder.DataFormat());

			// Hook the level meter up to the Audio Queue for the recorder
			lvlMeter_in().setAq(recorder.Queue());
		}
	}

	@Selector("awakeFromNib")
	public void awakeFromNib() {
		super.awakeFromNib();

		NSNotificationCenter center = NSNotificationCenter.defaultCenter();

		// Allocate our singleton instance for the recorder & player object
		recorder = new AQRecorder();
		player = new AQPlayer();

		AVAudioSession session = AVAudioSession.sharedInstance();
		session.setDelegate(this);
		if (!session.setCategoryError(AVFoundation.AVAudioSessionCategoryPlayAndRecord(), null)) {
			System.out.format("couldn't set audio category!");
		}
		center.addObserverSelectorNameObject(this, new SEL("propListener:"), AVFoundation.AVAudioSessionRouteChangeNotification(), null);

		if (!session.setActiveError(YES, null)) {
			System.out.println("AudioSessionSetActive (true) failed");
		}

		center.addObserverSelectorNameObject(this, new SEL("playbackQueueStopped:"), "playbackQueueStopped", null);
		center.addObserverSelectorNameObject(this, new SEL("playbackQueueResumed:"), "playbackQueueResumed", null);

		// disable the play button since we have no recording to play yet
		playbackWasInterrupted = NO;
		playbackWasPaused = NO;

		registerForBackgroundNotifications();
	}

	@Selector("propListener:")
	private void propListener(NSNotification notif) {
		if (notif.name().equals(AVFoundation.AVAudioSessionRouteChangeNotification())) {
			Object value = notif.userInfo().objectForKey(AVFoundation.AVAudioSessionRouteChangeReasonKey());
			int reason = ((NSNumber) value).intValue();
			if (reason == AVAudioSessionRouteChangeReason.CategoryChange) {
				return;
			}
			if (reason == AVAudioSessionRouteChangeReason.OldDeviceUnavailable) {
				if (player.IsRunning()) {
					pausePlayQueue();
					NSNotificationCenter.defaultCenter().postNotificationNameObject("playbackQueueStopped", this);
				}
			}
			if (recorder.IsRunning()) {
				try {
					stopRecord();
				} catch (CAXException e) {
					System.err.println("Error: " + e.getMessage() + " (" + e.getError() + ")");
				}
			}
		}
	}

	@Selector("viewDidLoad")
	@Override
	public void viewDidLoad() {
		super.viewDidLoad();

		AVAudioSession session = AVAudioSession.sharedInstance();
		btn_record().setEnabled(session.isInputAvailable());

		btn_play().setEnabled(NO);

		UIColor bgColor = UIColor.alloc().initWithRedGreenBlueAlpha(0.39f, 0.44f, 0.57f, 0.5f);
		lvlMeter_in().setBackgroundColor(bgColor);
		lvlMeter_in().setBorderColor(bgColor);
	}

	@Selector("playbackQueueStopped:")
	public void playbackQueueStopped(NSNotification note) {
		btn_play().setTitle("Play");
		lvlMeter_in().setAq(null);
		btn_record().setEnabled(YES);
	}

	@Selector("playbackQueueResumed:")
	public void playbackQueueResumed(NSNotification note) {
		btn_play().setTitle("Stop");
		btn_record().setEnabled(NO);
		lvlMeter_in().setAq(player.Queue());
	}

	private void registerForBackgroundNotifications() {
		NSNotificationCenter center = NSNotificationCenter.defaultCenter();
		center.addObserverSelectorNameObject(this, new SEL("resignActive"), UIKit.UIApplicationWillResignActiveNotification(), null);
		center.addObserverSelectorNameObject(this, new SEL("enterForeground"), UIKit.UIApplicationWillEnterForegroundNotification(), null);
	}

	@Selector("resignActive")
	public void resignActive() {
		if (recorder.IsRunning()) {
			try {
				stopRecord();
			} catch (CAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (player.IsRunning())
			stopPlayQueue();
		inBackground = true;
	}

	@Selector("enterForeground")
	public void enterForeground() {
		AVAudioSession session = AVAudioSession.sharedInstance();
		if (!session.setActiveError(true, null)) {
			System.out.println("AudioSessionSetActive (true) failed");
		}
		inBackground = false;
	}

	@NotImplemented
	@Override
	@Selector("beginInterruption")
	@IsOptional
	public native void beginInterruption();

	@NotImplemented
	@Override
	@Selector("endInterruption")
	@IsOptional
	public native void endInterruption();

	@NotImplemented
	@Override
	@Selector("endInterruptionWithFlags:")
	@IsOptional
	public native void endInterruptionWithFlags(@NUInt long flags);

	@Override
	@Selector("inputIsAvailableChanged:")
	public void inputIsAvailableChanged(boolean isInputAvailable) {
		btn_record().setEnabled(isInputAvailable);
	}
}