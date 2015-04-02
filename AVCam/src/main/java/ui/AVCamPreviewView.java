package ui;

import ios.avfoundation.AVCaptureSession;
import ios.avfoundation.AVCaptureVideoPreviewLayer;
import ios.coregraphics.struct.CGRect;
import ios.uikit.UIView;

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

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("AVCamPreviewView")
@RegisterOnStartup
public class AVCamPreviewView extends UIView {
	static {
		NatJ.register();
	}

	@Generated
	protected AVCamPreviewView(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native AVCamPreviewView alloc();

	@Generated
	@Selector("init")
	public native AVCamPreviewView init();

	@Generated
	@Selector("initWithFrame:")
	public native AVCamPreviewView initWithFrame(@ByValue CGRect frame);
	

	@Selector("layerClass")
	public static com.migeran.natj.objc.Class layerClass() {
		com.migeran.natj.objc.Class cls = new com.migeran.natj.objc.Class(AVCaptureVideoPreviewLayer.class);
		System.out.format("Returning AVCaptureVideoPreviewLayer class: 0x%x\n", cls.getPeerPointer());
		return cls;
	}
	
	@Selector("session")
	public AVCaptureSession session() {
		return ((AVCaptureVideoPreviewLayer)this.layer()).session();
	}
	
	@Selector("setSession:")
	public void setSession(AVCaptureSession session) {
		((AVCaptureVideoPreviewLayer)this.layer()).setSession(session);
	}
	
}