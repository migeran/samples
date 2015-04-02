package ui;

import static ios.c.Globals.dispatch_async;
import static ios.c.Globals.dispatch_get_main_queue;
import static ios.c.Globals.dispatch_queue_create;
import ios.assetslibrary.ALAssetsLibrary;
import ios.assetslibrary.ALAssetsLibrary.Block_writeVideoAtPathToSavedPhotosAlbumCompletionBlock;
import ios.avfoundation.AVCaptureConnection;
import ios.avfoundation.AVCaptureDevice;
import ios.avfoundation.AVCaptureDevice.Block_requestAccessForMediaTypeCompletionHandler;
import ios.avfoundation.AVCaptureDeviceInput;
import ios.avfoundation.AVCaptureFileOutput;
import ios.avfoundation.AVCaptureMovieFileOutput;
import ios.avfoundation.AVCaptureSession;
import ios.avfoundation.AVCaptureStillImageOutput;
import ios.avfoundation.AVCaptureStillImageOutput.Block_captureStillImageAsynchronouslyFromConnectionCompletionHandler;
import ios.avfoundation.AVCaptureVideoPreviewLayer;
import ios.avfoundation.c.AVFoundation;
import ios.avfoundation.enums.AVCaptureDevicePosition;
import ios.avfoundation.enums.AVCaptureExposureMode;
import ios.avfoundation.enums.AVCaptureFlashMode;
import ios.avfoundation.enums.AVCaptureFocusMode;
import ios.avfoundation.protocol.AVCaptureFileOutputRecordingDelegate;
import ios.c.Globals;
import ios.c.Globals.Block_dispatch_async;
import ios.coregraphics.struct.CGPoint;
import ios.coremedia.opaque.CMSampleBufferRef;
import ios.foundation.NSArray;
import ios.foundation.NSBundle;
import ios.foundation.NSData;
import ios.foundation.NSDictionary;
import ios.foundation.NSError;
import ios.foundation.NSFileManager;
import ios.foundation.NSMutableDictionary;
import ios.foundation.NSMutableSet;
import ios.foundation.NSNotification;
import ios.foundation.NSNotificationCenter;
import ios.foundation.NSNotificationCenter.Block_addObserverForNameObjectQueueUsingBlock;
import ios.foundation.NSNumber;
import ios.foundation.NSSet;
import ios.foundation.NSURL;
import ios.foundation.c.Foundation;
import ios.foundation.enums.NSKeyValueObservingOptions;
import ios.uikit.UIAlertView;
import ios.uikit.UIApplication;
import ios.uikit.UIButton;
import ios.uikit.UIDevice;
import ios.uikit.UIGestureRecognizer;
import ios.uikit.UIImage;
import ios.uikit.UIView;
import ios.uikit.UIView.Block_animateWithDurationAnimations;
import ios.uikit.UIViewController;
import ios.uikit.c.UIKit;
import ios.uikit.enums.UIControlState;
import ios.uikit.enums.UIInterfaceOrientationMask;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.general.ann.NUInt;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.RegisterOnStartup;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.general.ptr.Ptr;
import com.migeran.natj.general.ptr.VoidPtr;
import com.migeran.natj.general.ptr.impl.PtrFactory;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.ObjCClassName;
import com.migeran.natj.objc.ann.Selector;

// If the Generated annotation is set the list of implemented interfaces will be overwritten
// by the Nat/J Generator when the UI bindings are updated. The actions and outlets are synchronized
// regardless whether this option is set. Check the Nat/J Generator user guide for details.
// @Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("AVCamViewController")
@RegisterOnStartup
public class AVCamViewController extends UIViewController implements AVCaptureFileOutputRecordingDelegate {
	static {
		NatJ.register();
	}

	@Generated
	protected AVCamViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native AVCamViewController alloc();

	@Generated
	@Selector("init")
	public native AVCamViewController init();

	@Generated
	@Selector("initWithNibName:bundle:")
	public native AVCamViewController initWithNibNameBundle(
			String nibNameOrNil, NSBundle nibBundleOrNil);

	@Generated
	@Selector("previewView")
	public native AVCamPreviewView previewView();

	@Generated
	@Selector("setPreviewView:")
	public native void setPreviewView_unsafe(AVCamPreviewView value);

	@Generated
	public void setPreviewView(AVCamPreviewView value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) previewView();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setPreviewView_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}
	
	@Generated
	@Selector("cameraButton")
	public native UIButton cameraButton();

	@Generated
	@Selector("recordButton")
	public native UIButton recordButton();

	@Generated
	@Selector("stillButton")
	public native UIButton stillButton();

	@Generated
	@Selector("setCameraButton:")
	public native void setCameraButton_unsafe(UIButton value);

	@Generated
	public void setCameraButton(UIButton value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) cameraButton();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setCameraButton_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setRecordButton:")
	public native void setRecordButton_unsafe(UIButton value);

	@Generated
	public void setRecordButton(UIButton value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) recordButton();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setRecordButton_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	@Generated
	@Selector("setStillButton:")
	public native void setStillButton_unsafe(UIButton value);

	@Generated
	public void setStillButton(UIButton value) {
		com.migeran.natj.objc.ObjCObject __old = (com.migeran.natj.objc.ObjCObject) stillButton();
		if (value != null) {
			com.migeran.natj.objc.ObjCRuntime.associateObjCObject(this, value);
		}
		setStillButton_unsafe(value);
		if (__old != null) {
			com.migeran.natj.objc.ObjCRuntime
					.unassociateObjCObject(this, __old);
		}
	}

	private static AVCaptureDevice deviceWithMediaType(String mediaType, /* TODO: Should be AVCaptureDevicePosition */ long position) {
		// TODO: Should support NSArray<AVCaptureDevice>
		NSArray devices = AVCaptureDevice.devicesWithMediaType(mediaType);
		for (Object device : devices) {
			AVCaptureDevice capDev = (AVCaptureDevice) device;
			if (capDev.position() == position) {
				return capDev;
			}
		}
		return (AVCaptureDevice) devices.firstObject();
	}

	// These are the observed keypaths
	private static final String MOVIE_FILE_OUTPUT_RECORDING = "movieFileOutput.recording";
	private static final String STILL_IMAGE_OUTPUT_CAPTURING_STILL_IMAGE = "stillImageOutput.capturingStillImage";
	private static final String SESSION_RUNNING_AND_DEVICE_AUTHORIZED = "sessionRunningAndDeviceAuthorized";
	
	AVCaptureDeviceInput videoDeviceInput;

	private AVCaptureSession _session;
	
	@Selector("session")
	public AVCaptureSession session() {
		return _session;
	}

	@Selector("setSession:")
	public void setSession(AVCaptureSession session) {
		willChangeValueForKey("session");
		this._session = session;
		didChangeValueForKey("session");
	}
	
	@Selector("isSessionRunningAndDeviceAuthorized")
	public boolean isSessionRunningAndDeviceAuthorized() {
		return session().isRunning() && isDeviceAuthorized();
	}
	
	@Selector("isDeviceAuthorized")
	public boolean isDeviceAuthorized() {
		return _deviceAuthorized;
	}

	@Selector("setDeviceAuthorized:")
	public void setDeviceAuthorized(boolean deviceAuthorized) {
		willChangeValueForKey("deviceAuthorized");
		this._deviceAuthorized = deviceAuthorized;
		didChangeValueForKey("deviceAuthorized");
	}

	@Selector("keyPathsForValuesAffectingSessionRunningAndDeviceAuthorized")
	public static NSSet keyPathsForValuesAffectingSessionRunningAndDeviceAuthorized() {
		NSMutableSet set = NSMutableSet.set();
		set.addObject("session.running");
		set.addObject("deviceAuthorized");
		return set;
	}
	
	AVCaptureMovieFileOutput movieFileOutput;

	@Selector("movieFileOutput")
	public AVCaptureMovieFileOutput movieFileOutput() {
		return movieFileOutput;
	}
	
	@Selector("setMovieFileOutput:")
	public void setMovieFileOutput(AVCaptureMovieFileOutput m) {
		movieFileOutput = m;
	}
		
	AVCaptureStillImageOutput stillImageOutput;

	@Selector("stillImageOutput")
	public AVCaptureStillImageOutput stillImageOutput() {
		return stillImageOutput;
	}
	
	@Selector("setStillImageOutput:")
	public void stillImageOutput(AVCaptureStillImageOutput s) {
		stillImageOutput = s;
	}
		
	// Utilities.
	long backgroundRecordingID = UIKit.UIBackgroundTaskInvalid();
	private boolean _deviceAuthorized;
	boolean lockInterfaceRotation;
	Object runtimeErrorHandlingObserver;
	
	private Object sessionQueue;
	
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();
				
		// Create the AVCaptureSession
		setSession(AVCaptureSession.alloc().init());

		// Setup the preview view
		previewView().setSession(session());
		
		// Check for device authorization
		checkDeviceAuthorizationStatus();
		
		// In general it is not safe to mutate an AVCaptureSession or any of its inputs, outputs, or connections from multiple threads at the same time.
		// Why not do all of this on the main queue?
		// -[AVCaptureSession startRunning] is a blocking call which can take a long time. We dispatch session setup to the sessionQueue so that the main queue isn't blocked (which keeps the UI responsive).
		sessionQueue = dispatch_queue_create("sesssion queue", null /* DISPATCH_QUEUE_SERIAL */);
		
		dispatch_async(sessionQueue, new Block_dispatch_async() {
			
			@Override
			public void call_dispatch_async() {
				backgroundRecordingID = UIKit.UIBackgroundTaskInvalid();
				
				Ptr<NSError> error = PtrFactory.newObjectReference(NSError.class);

				AVCaptureDevice videoDevice = AVCamViewController.deviceWithMediaType(AVFoundation.AVMediaTypeVideo(), AVCaptureDevicePosition.Back);
				AVCaptureDeviceInput videoDeviceInput = AVCaptureDeviceInput.deviceInputWithDeviceError(videoDevice, error);

				if (error.get() != null) {
					// TODO: Need a working NSLog wrapper
					System.err.println(error.get().localizedDescription());
				}
				
				if (session().canAddInput(videoDeviceInput)) {
					session().addInput(videoDeviceInput);
					// TODO: Use setter
					AVCamViewController.this.videoDeviceInput = videoDeviceInput;
					
					Globals.dispatch_async(dispatch_get_main_queue(), new Block_dispatch_async() {
						
						@Override
						public void call_dispatch_async() {
							
							// Why are we dispatching this to the main queue?
							// Because AVCaptureVideoPreviewLayer is the backing layer for AVCamPreviewView and UIView can only be manipulated on main thread.
							// Note: As an exception to the above rule, it is not necessary to serialize video orientation changes on the AVCaptureVideoPreviewLayer’s connection with other session manipulation.

							AVCaptureVideoPreviewLayer layer = (AVCaptureVideoPreviewLayer) previewView().layer();
							layer.connection().setVideoOrientation(AVCamViewController.this.interfaceOrientation());
							
						}
					});
				}

				AVCaptureDevice audioDevice = (AVCaptureDevice) AVCaptureDevice.devicesWithMediaType(AVFoundation.AVMediaTypeAudio()).firstObject();
				AVCaptureDeviceInput audioDeviceInput = AVCaptureDeviceInput.deviceInputWithDeviceError(audioDevice, error);

				if (error.get() != null) {
					System.err.println(error.get().localizedDescription());
				}
				
				if (session().canAddInput(audioDeviceInput)) {
					session().addInput(audioDeviceInput);
				}
				
				AVCaptureMovieFileOutput movieFileOutput = AVCaptureMovieFileOutput.alloc().init();
				if (session().canAddOutput(movieFileOutput)) {
					session().addOutput(movieFileOutput);
					AVCaptureConnection connection = movieFileOutput.connectionWithMediaType(AVFoundation.AVMediaTypeVideo());
					if (connection != null && connection.isVideoStabilizationSupported()) {
						connection.setEnablesVideoStabilizationWhenAvailable(true);
						// TODO: Use setter
						AVCamViewController.this.movieFileOutput = movieFileOutput;
					}
				}
				AVCaptureStillImageOutput stillImageOutput = AVCaptureStillImageOutput.alloc().init();
				if (session().canAddOutput(stillImageOutput)) {
					NSMutableDictionary outputSettings = NSMutableDictionary.alloc().init();
					outputSettings.put(AVFoundation.AVVideoCodecKey(), AVFoundation.AVVideoCodecJPEG());
					stillImageOutput.setOutputSettings(outputSettings);
					session().addOutput(stillImageOutput);
					// TODO: Use setter
					AVCamViewController.this.stillImageOutput = stillImageOutput;
				}				
			}
			
		});
	}

	private void checkDeviceAuthorizationStatus() {	
		String mediaType = AVFoundation.AVMediaTypeVideo();
			
		AVCaptureDevice.requestAccessForMediaTypeCompletionHandler(mediaType, new Block_requestAccessForMediaTypeCompletionHandler() {
			
			@Override
			public void call_requestAccessForMediaTypeCompletionHandler(boolean granted) {
				if (granted) {
					setDeviceAuthorized(true);
				} else {
					dispatch_async(dispatch_get_main_queue(), new Block_dispatch_async() {
						
						@Override
						public void call_dispatch_async() {
							// TODO: Why is the UIAlertView initWithTitle() constructor missing 
							UIAlertView alertView = UIAlertView.alloc().init();
							alertView.setTitle("AVCam!");
							alertView.setMessage("AVCam doesn't have permission to use Camera, please change privacy settings");
							alertView.addButtonWithTitle("OK");
							alertView.setCancelButtonIndex(0);
							alertView.setDelegate(AVCamViewController.this);
							alertView.show();
							setDeviceAuthorized(false);
						}
					});
				}
				
			}
		});
	}

	@Override
	@Selector("viewWillAppear:")
	public void viewWillAppear(boolean animated) {
		dispatch_async(sessionQueue, new Block_dispatch_async() {
			
			@Override
			public void call_dispatch_async() {
				AVCamViewController.this.addObserverForKeyPathOptionsContext(AVCamViewController.this, 
													SESSION_RUNNING_AND_DEVICE_AUTHORIZED, 
													NSKeyValueObservingOptions.Old | NSKeyValueObservingOptions.New, 
													null);
				addObserverForKeyPathOptionsContext(AVCamViewController.this, 
						STILL_IMAGE_OUTPUT_CAPTURING_STILL_IMAGE, 
						NSKeyValueObservingOptions.Old | NSKeyValueObservingOptions.New, 
						null);
				addObserverForKeyPathOptionsContext(AVCamViewController.this, 
						MOVIE_FILE_OUTPUT_RECORDING, 
						NSKeyValueObservingOptions.Old | NSKeyValueObservingOptions.New, 
						null);
				NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(AVCamViewController.this, 
										new SEL("subjectAreaDidChange:"), 
										AVFoundation.AVCaptureDeviceSubjectAreaDidChangeNotification(), 
										videoDeviceInput == null ? null : videoDeviceInput.device());
				
				AVCamViewController.this.runtimeErrorHandlingObserver = 
						NSNotificationCenter.defaultCenter().addObserverForNameObjectQueueUsingBlock(
								AVFoundation.AVCaptureSessionRuntimeErrorNotification(), 
								session(), 
								null, 
								new Block_addObserverForNameObjectQueueUsingBlock() {
									
									@Override
									public void call_addObserverForNameObjectQueueUsingBlock(NSNotification arg0) {
										// TODO: Change the method name in the Block interface to not clash with 
										// the name of dispatch_async
										dispatch_async(sessionQueue, new Block_dispatch_async() {
											
											@Override
											public void call_dispatch_async() {
												// Manually restarting the session since it must have been stopped due to an error.
												session().startRunning();
												// TODO: Add Java version of NSLocalizedString macros
												recordButton().setTitleForState("Record", UIControlState.Normal);
											}
										});
									}
								});
				session().startRunning();
			}
		});
	}


	@Override
	@Selector("viewDidDisappear:")
	public void viewDidDisappear(boolean animated) {
		dispatch_async(sessionQueue, new Block_dispatch_async() {
			
			@Override
			public void call_dispatch_async() {
				session().stopRunning();
				NSNotificationCenter.defaultCenter().removeObserverNameObject(
						AVCamViewController.this, 
						AVFoundation.AVCaptureDeviceSubjectAreaDidChangeNotification(), 
						videoDeviceInput == null ? null : videoDeviceInput.device());
				NSNotificationCenter.defaultCenter().removeObserver(runtimeErrorHandlingObserver);
				runtimeErrorHandlingObserver = null;
				removeObserverForKeyPath(AVCamViewController.this, SESSION_RUNNING_AND_DEVICE_AUTHORIZED);
				removeObserverForKeyPath(AVCamViewController.this, STILL_IMAGE_OUTPUT_CAPTURING_STILL_IMAGE);
				removeObserverForKeyPath(AVCamViewController.this, MOVIE_FILE_OUTPUT_RECORDING);
			}
		});
	}
	
	@Override
	@Selector("prefersStatusBarHidden")
	public boolean prefersStatusBarHidden() {
		return true;
	}
	
	@Override
	@Selector("shouldAutorotate")
	public boolean shouldAutorotate() {
		return !lockInterfaceRotation;
	}
	

	@Override
	@Selector("supportedInterfaceOrientations")
	@NUInt
	public long supportedInterfaceOrientations() {
		return UIInterfaceOrientationMask.All;
	}
	
	@Override
	@Selector("willRotateToInterfaceOrientation:duration:")
	public void willRotateToInterfaceOrientationDuration(@NInt long toInterfaceOrientation, double duration) {
		((AVCaptureVideoPreviewLayer) previewView().layer()).connection().setVideoOrientation(toInterfaceOrientation);
	}
	

	@Override
	@Selector("observeValueForKeyPath:ofObject:change:context:")
	public void observeValueForKeyPathOfObjectChangeContext(String keyPath,
			Object object, NSDictionary change, VoidPtr context) {
		if (STILL_IMAGE_OUTPUT_CAPTURING_STILL_IMAGE.equals(keyPath)) {
			boolean isCapturingStillImage = ((NSNumber) change.get(Foundation.NSKeyValueChangeNewKey())).boolValue();
			if (isCapturingStillImage) {
				runStillImageCaptureAnimation();
			}
		} else if (MOVIE_FILE_OUTPUT_RECORDING.equals(keyPath)) {
			final boolean isRecording = ((NSNumber) change.get(Foundation.NSKeyValueChangeNewKey())).boolValue();
			
			dispatch_async(dispatch_get_main_queue(), new Block_dispatch_async() {
				
				@Override
				public void call_dispatch_async() {
					if (isRecording) {
						cameraButton().setEnabled(false);
						// TODO: NSLocalizedString support
						recordButton().setTitleForState("Stop", UIControlState.Normal);
						recordButton().setEnabled(true);
					} else {
						cameraButton().setEnabled(true);
						// TODO: NSLocalizedString support
						recordButton().setTitleForState("Record", UIControlState.Normal);
						recordButton().setEnabled(true);						
					}
				}
			});
		} else if (SESSION_RUNNING_AND_DEVICE_AUTHORIZED.equals(keyPath)) {
			final boolean isRunning = ((NSNumber) change.get(Foundation.NSKeyValueChangeNewKey())).boolValue();
			
			dispatch_async(dispatch_get_main_queue(), new Block_dispatch_async() {
				
				@Override
				public void call_dispatch_async() {
					if (isRunning) {
						cameraButton().setEnabled(true);
						recordButton().setEnabled(true);
						stillButton().setEnabled(true);
					} else {
						cameraButton().setEnabled(false);
						recordButton().setEnabled(false);
						stillButton().setEnabled(false);
					}
				}
			});
			
		} else {
			super.observeValueForKeyPathOfObjectChangeContext(keyPath, object, change, context);
		}
	}

	@Selector("toggleMovieRecording:")
	public void toggleMovieRecording(UIButton sender) {
		recordButton().setEnabled(false);
		dispatch_async(sessionQueue, new Block_dispatch_async() {
			
			@Override
			public void call_dispatch_async() {
				if (!movieFileOutput.isRecording()) {
					lockInterfaceRotation = true;
					if (UIDevice.currentDevice().isMultitaskingSupported()) {
						// Setup background task. This is needed because the captureOutput:didFinishRecordingToOutputFileAtURL: 
						// callback is not received until AVCam returns to the foreground unless you request background execution time. 
						// This also ensures that there will be time to write the file to the assets library when AVCam is backgrounded. 
						// To conclude this background execution, -endBackgroundTask is called in -recorder:recordingDidFinishToOutputFileURL:error: 
						// after the recorded file has been saved.
						backgroundRecordingID = UIApplication.sharedApplication().beginBackgroundTaskWithExpirationHandler(null);
					}

					// Update the orientation on the movie file output video connection before starting recording.
					long videoOrientation = ((AVCaptureVideoPreviewLayer) previewView().layer()).connection().videoOrientation();
					movieFileOutput.connectionWithMediaType(AVFoundation.AVMediaTypeVideo()).setVideoOrientation(videoOrientation);

					// Turning OFF flash for video recording
					setFlashMode(AVCaptureFlashMode.Off, videoDeviceInput == null ? null : videoDeviceInput.device());
					
					String outputFilePath = Foundation.NSTemporaryDirectory() + "movie.mov";
					movieFileOutput.startRecordingToOutputFileURLRecordingDelegate(NSURL.fileURLWithPath(outputFilePath), AVCamViewController.this);
				} else {
					movieFileOutput.stopRecording();
				}
			}
		});
	}
	
	@Selector("changeCamera:")
	public void changeCamera(UIButton sender) {
		cameraButton().setEnabled(false);
		recordButton().setEnabled(false);
		stillButton().setEnabled(false);
		
		dispatch_async(sessionQueue, new Block_dispatch_async() {
			
			@Override
			public void call_dispatch_async() {
				AVCaptureDevice currentVideoDevice = videoDeviceInput == null ? null : videoDeviceInput.device();
				// TODO: Use true enums
				long preferredPosition = AVCaptureDevicePosition.Unspecified;
				long currentPosition = currentVideoDevice.position();
				if (currentPosition == AVCaptureDevicePosition.Unspecified) {
					preferredPosition = AVCaptureDevicePosition.Back;
				} else if (currentPosition == AVCaptureDevicePosition.Back) {
					preferredPosition = AVCaptureDevicePosition.Front;
				} else if (currentPosition == AVCaptureDevicePosition.Front) {
					preferredPosition = AVCaptureDevicePosition.Back;
				}
				AVCaptureDevice videoDevice = deviceWithMediaType(AVFoundation.AVMediaTypeVideo(), preferredPosition);
				AVCaptureDeviceInput videoDeviceInput = AVCaptureDeviceInput.deviceInputWithDeviceError(videoDevice, null);
				session().beginConfiguration();
				session().removeInput(AVCamViewController.this.videoDeviceInput);
				if (session().canAddInput(videoDeviceInput)) {
					NSNotificationCenter.defaultCenter().removeObserverNameObject(AVCamViewController.this, AVFoundation.AVCaptureDeviceSubjectAreaDidChangeNotification(), currentVideoDevice);
					setFlashMode(AVCaptureFlashMode.Auto, videoDevice);
					NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(AVCamViewController.this, 
							new SEL("subjectAreaDidChange:"), 
							AVFoundation.AVCaptureDeviceSubjectAreaDidChangeNotification(), 
							videoDevice);
					session().addInput(videoDeviceInput);
					AVCamViewController.this.videoDeviceInput = videoDeviceInput;
				} else {
					session().addInput(AVCamViewController.this.videoDeviceInput);
				}
				session().commitConfiguration();
				dispatch_async(dispatch_get_main_queue(), new Block_dispatch_async() {
					
					@Override
					public void call_dispatch_async() {
						cameraButton().setEnabled(true);
						recordButton().setEnabled(true);
						stillButton().setEnabled(true);
					}
				});
			}
		});
	}

	@Selector("snapStillImage:")
	public void snapStillImage(UIButton sender) {
		dispatch_async(sessionQueue, new Block_dispatch_async() {
			
			@Override
			public void call_dispatch_async() {
				long videoOrientation = ((AVCaptureVideoPreviewLayer) previewView().layer()).connection().videoOrientation();
				stillImageOutput.connectionWithMediaType(AVFoundation.AVMediaTypeVideo()).setVideoOrientation(videoOrientation);
				setFlashMode(AVCaptureFlashMode.Auto, videoDeviceInput == null ? null : videoDeviceInput.device());
				stillImageOutput.captureStillImageAsynchronouslyFromConnectionCompletionHandler(
						stillImageOutput.connectionWithMediaType(AVFoundation.AVMediaTypeVideo()), new Block_captureStillImageAsynchronouslyFromConnectionCompletionHandler() {
							
							@Override
							public void call_captureStillImageAsynchronouslyFromConnectionCompletionHandler(
									CMSampleBufferRef imageDataSampleBuffer, NSError arg1) {
								if (imageDataSampleBuffer != null) {
									NSData imageData = AVCaptureStillImageOutput.jpegStillImageNSDataRepresentation(imageDataSampleBuffer);
									UIImage image = UIImage.alloc().initWithData(imageData);
									ALAssetsLibrary.alloc().init().writeImageToSavedPhotosAlbumOrientationCompletionBlock(image.CGImage(), image.imageOrientation(), null);
								}
								
							}
						});
			}
		});
	}

	@Selector("focusAndExposeTap:")
	public void focusAndExposeTap(UIGestureRecognizer gestureRecognizer) {
		CGPoint devicePoint = ((AVCaptureVideoPreviewLayer)previewView().layer()).captureDevicePointOfInterestForPoint(gestureRecognizer.locationInView(gestureRecognizer.view()));
		changeFocus(AVCaptureFocusMode.AutoFocus, AVCaptureExposureMode.AutoExpose, devicePoint, true);
	}
	
	@Selector("subjectAreaDidChange:")
	public void subjectAreaDidChange(NSNotification notification) {
		CGPoint devicePoint = new CGPoint(.5f, .5f);
		changeFocus(AVCaptureFocusMode.ContinuousAutoFocus, AVCaptureExposureMode.ContinuousAutoExposure, devicePoint, false);
	}

	@Selector("focusWithMode:exposeWithMode:atDevicePoint:monitorSubjectAreaChange:")
	public void changeFocus(final /* TODO: Should be AVCaptureFocusMode */ long focusMode, final /* TODO: Should be AVCaptureExposureMode */ long exposureMode,
			final CGPoint point, final boolean monitorSubjectAreaChange) {		
		// TODO: Use getter
		dispatch_async(sessionQueue, 
			new Block_dispatch_async() {					
				@Override
				public void call_dispatch_async() {
					// TODO: Use getter
					AVCaptureDevice device = videoDeviceInput == null ? null : videoDeviceInput.device();
					Ptr<NSError> error = PtrFactory.newObjectReference(NSError.class);
					if (device.lockForConfiguration(error)) {
						if (device.isFocusPointOfInterestSupported() && device.isFocusModeSupported(focusMode)) {
							device.setFocusMode(focusMode);
							device.setFocusPointOfInterest(point);
						}
						if (device.isExposurePointOfInterestSupported() && device.isExposureModeSupported(exposureMode)) {
							device.setExposureMode(focusMode);
							device.setExposurePointOfInterest(point);
						}
						device.setSubjectAreaChangeMonitoringEnabled(monitorSubjectAreaChange);
						device.unlockForConfiguration();
					} else {
						// TODO: NSLog
						System.out.println(error.get().localizedDescription());
					}					
				}
			});
	}

	private static void setFlashMode(long flashMode, AVCaptureDevice device) {
		if (device.hasFlash() && device.isFlashModeSupported(flashMode)) {
			Ptr<NSError> error = PtrFactory.newObjectReference(NSError.class);
			if (device.lockForConfiguration(error)) {
				device.setFlashMode(flashMode);
				device.unlockForConfiguration();
			} else {
				// TODO: NSLog
				System.out.println(error.get().localizedDescription());
			}
		}
	}
	
	private void runStillImageCaptureAnimation() {
		dispatch_async(dispatch_get_main_queue(), new Block_dispatch_async() {
			
			@Override
			public void call_dispatch_async() {
				previewView().layer().setOpacity(0.0f);
				UIView.animateWithDurationAnimations(0.25f, new Block_animateWithDurationAnimations() {
					
					@Override
					public void call_animateWithDurationAnimations() {
						previewView().layer().setOpacity(1.0f);
					}
				});
			}
		});
	}
	
	@NotImplemented
	@Override
	@Selector("captureOutput:didStartRecordingToOutputFileAtURL:fromConnections:")
	@IsOptional
	public native void captureOutputDidStartRecordingToOutputFileAtURLFromConnections(
			AVCaptureFileOutput arg0, NSURL arg1, NSArray arg2);

	@Override
	@Selector("captureOutput:didFinishRecordingToOutputFileAtURL:fromConnections:error:")
	public void captureOutputDidFinishRecordingToOutputFileAtURLFromConnectionsError(
			AVCaptureFileOutput arg0, NSURL arg1, NSArray arg2, NSError error) {
		if (error != null) {
			// TODO: NSLog
			System.err.println(error.localizedDescription());
		}
		
		// Note the backgroundRecordingID for use in the ALAssetsLibrary completion handler to end the background task 
		// associated with this recording. This allows a new recording to be started, associated with a new UIBackgroundTaskIdentifier, 
		// once the movie file output's -isRecording is back to NO — which happens sometime after this method returns.
		
		final long backgroundRecordingId = this.backgroundRecordingID;
		this.backgroundRecordingID = UIKit.UIBackgroundTaskInvalid();
		
		ALAssetsLibrary.alloc().init().writeVideoAtPathToSavedPhotosAlbumCompletionBlock(arg1, new Block_writeVideoAtPathToSavedPhotosAlbumCompletionBlock() {
			
			@Override
			public void call_writeVideoAtPathToSavedPhotosAlbumCompletionBlock(NSURL arg0, NSError error) {
				if (error != null) {
					// TODO: NSLog
					System.err.println(error.localizedDescription());
				}
				NSFileManager.defaultManager().removeItemAtURLError(arg0, null);
				if (backgroundRecordingId != UIKit.UIBackgroundTaskInvalid()) {
					UIApplication.sharedApplication().endBackgroundTask(backgroundRecordingId);
				}
			}
		});
	}
}