package com.migeran.demo.sample.graphics.opengl1;

import ios.coregraphics.struct.CGRect;
import ios.coregraphics.struct.CGSize;
import ios.foundation.NSBundle;
import ios.foundation.NSMutableDictionary;
import ios.foundation.NSNumber;
import ios.foundation.NSSet;
import ios.glkit.GLKBaseEffect;
import ios.glkit.GLKTextureInfo;
import ios.glkit.GLKTextureLoader;
import ios.glkit.GLKView;
import ios.glkit.GLKViewController;
import ios.glkit.c.GLKit;
import ios.glkit.enums.GLKVertexAttrib;
import ios.glkit.protocol.GLKViewDelegate;
import ios.glkit.struct.GLKMatrix4;
import ios.opengles.EAGLContext;
import ios.opengles.c.OpenGLES;
import ios.opengles.enums.ES2;
import ios.uikit.UIEvent;
import ios.uikit.enums.UIRectEdge;

import com.migeran.natj.c.CRuntime;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ptr.BytePtr;
import com.migeran.natj.general.ptr.FloatPtr;
import com.migeran.natj.general.ptr.IntPtr;
import com.migeran.natj.general.ptr.impl.PtrFactory;
import com.migeran.natj.objc.ann.Selector;

public class OpenGLBoxController extends GLKViewController implements
		GLKViewDelegate {

	@Generated
	protected OpenGLBoxController(Pointer peer) {
		super(peer);
	}

	final float[] VERTICES = new float[] {
			// Front
			+1, -1, +1, 1, 1, //
			+1, +1, +1, 1, 0, //
			-1, +1, +1, 0, 0, //
			-1, -1, +1, 0, 1, //
			// Back
			+1, +1, -1, 0, 0, //
			-1, -1, -1, 1, 1, //
			+1, -1, -1, 0, 1, //
			-1, +1, -1, 1, 0, //
			// Left
			-1, -1, +1, 1, 1, //
			-1, +1, +1, 1, 0, //
			-1, +1, -1, 0, 0, //
			-1, -1, -1, 0, 1, //
			// Right
			+1, -1, -1, 1, 1, //
			+1, +1, -1, 1, 0, //
			+1, +1, +1, 0, 0, //
			+1, -1, +1, 0, 1, //
			// Top
			+1, +1, +1, 0, 0, //
			+1, +1, -1, 0, 1, //
			-1, +1, -1, 1, 1, //
			-1, +1, +1, 1, 0, //
			// Bottom
			+1, -1, -1, 1, 0, //
			+1, -1, +1, 1, 1, //
			-1, -1, +1, 0, 1, //
			-1, -1, -1, 0, 0 //
	};

	final byte[] INDICES = {
			// Front
			0, 1, 2, //
			2, 3, 0, //
			// Back
			4, 6, 5, //
			4, 5, 7, //
			// Left
			8, 9, 10, //
			10, 11, 8, //
			// Right
			12, 13, 14, //
			14, 15, 12, //
			// Top
			16, 17, 18, //
			18, 19, 16, //
			// Bottom
			20, 21, 22, //
			22, 23, 20 //
	};

	final BytePtr indices = PtrFactory.newByteArray(INDICES);

	static {
		NatJ.register();
	}

	@Generated("NatJ")
	@Owned
	@Selector("alloc")
	public static native OpenGLBoxController alloc();

	@Generated("NatJ")
	@Selector("init")
	public native OpenGLBoxController init();

	static final int GLKViewDrawableMultisample4X = 1;

	EAGLContext context;
	GLKBaseEffect effect;

	IntPtr vertexBuffer = PtrFactory.newIntReference();
	IntPtr indexBuffer = PtrFactory.newIntReference();
	IntPtr vertexArray = PtrFactory.newIntReference();
	float rotation;

	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("OpenGL Box");

		setEdgesForExtendedLayout(UIRectEdge.None);

		context = EAGLContext.alloc().initWithAPI(
				ios.opengles.enums.EAGLRenderingAPI.OpenGLES2);
		if (context == null) {
			System.out.println("Failed to create ES context");
		}
		GLKView view = (GLKView) view();
		view.setContext(context);
		view.setDrawableMultisample(GLKViewDrawableMultisample4X);
		setupGL();
	}

	@Override
	@Selector("viewDidDisappear:")
	public void viewDidDisappear(boolean animated) {
		super.viewDidDisappear(animated);
		tearDownGL();
		if (EAGLContext.currentContext() == context) {
			EAGLContext.setCurrentContext(null);
		}
		context = null;
	}

	@Selector("didReceiveMemoryWarning")
	public void didReceiveMemoryWarning() {
		super.didReceiveMemoryWarning();
		if (isViewLoaded() && (view().window() == null)) {
			setView(null);
			tearDownGL();
			if (EAGLContext.currentContext() == context) {
				EAGLContext.setCurrentContext(null);
			}
			context = null;
		}
		// Dispose of any resources that can be recreated.
	}

	private void setupGL() {
		EAGLContext.setCurrentContext(context);
		OpenGLES.glEnable(ES2.GL_CULL_FACE);
		effect = GLKBaseEffect.alloc().init();
		// Texture
		NSMutableDictionary options = NSMutableDictionary.alloc().init();
		options.put(NSNumber.numberWithBool(true),
				GLKit.GLKTextureLoaderOriginBottomLeft());
		String path = NSBundle.mainBundle()
				.pathForResourceOfType("migeran_logo_square", "png").toString();
		GLKTextureInfo info = GLKTextureLoader
				.textureWithContentsOfFileOptionsError(path, options, null);
		if (info == null) {
			System.out.println("Error loading file: migeran_logo_square.png");
		}
		effect.texture2d0().setName(info.name());
		effect.texture2d0().setEnabled((byte) 1);
		// New lines
		OpenGLES.glGenVertexArraysOES(1, vertexArray);
		OpenGLES.glBindVertexArrayOES(vertexArray.getValue());
		// Old stuff
		OpenGLES.glGenBuffers(1, vertexBuffer);
		OpenGLES.glBindBuffer(ES2.GL_ARRAY_BUFFER, vertexBuffer.getValue());
		FloatPtr vertices = PtrFactory.newFloatArray(VERTICES);
		int sizeOfVertices = VERTICES.length * CRuntime.FLOAT_SIZE;
		OpenGLES.glBufferData(ES2.GL_ARRAY_BUFFER, sizeOfVertices, vertices,
				ES2.GL_STATIC_DRAW);
		OpenGLES.glGenBuffers(1, indexBuffer);
		OpenGLES.glBindBuffer(ES2.GL_ELEMENT_ARRAY_BUFFER,
				indexBuffer.getValue());
		int sizeOfIndices = INDICES.length * 1;
		OpenGLES.glBufferData(ES2.GL_ELEMENT_ARRAY_BUFFER, sizeOfIndices,
				indices, ES2.GL_STATIC_DRAW);
		// New lines (were previously in draw)
		OpenGLES.glEnableVertexAttribArray(GLKVertexAttrib.Position);
		int sizeOfVertex = (3 + 2) * CRuntime.FLOAT_SIZE;
		OpenGLES.glVertexAttribPointer(GLKVertexAttrib.Position, 3,
				ES2.GL_FLOAT, (byte) ES2.GL_FALSE, sizeOfVertex,
				PtrFactory.newWeakVoidPtr(0));
		OpenGLES.glEnableVertexAttribArray(GLKVertexAttrib.TexCoord0);
		OpenGLES.glVertexAttribPointer(GLKVertexAttrib.TexCoord0, 2,
				ES2.GL_FLOAT, (byte) ES2.GL_FALSE, sizeOfVertex,
				PtrFactory.newWeakVoidPtr(3 * CRuntime.FLOAT_SIZE));
		// New line
		OpenGLES.glBindVertexArrayOES(0);
	}

	private void tearDownGL() {
		EAGLContext.setCurrentContext(context);
		OpenGLES.glDeleteBuffers(1, vertexBuffer);
		OpenGLES.glDeleteBuffers(1, indexBuffer);
		effect = null;
	}

	@Override
	@Selector("glkView:drawInRect:")
	public void glkViewDrawInRect(GLKView view, CGRect rect) {
		OpenGLES.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		OpenGLES.glClear(ES2.GL_COLOR_BUFFER_BIT);
		effect.prepareToDraw();
		OpenGLES.glBindVertexArrayOES(vertexArray.getValue());
		OpenGLES.glDrawElements(ES2.GL_TRIANGLES, INDICES.length,
				ES2.GL_UNSIGNED_BYTE, null);
	}

	@Selector("update")
	public void update() {
		CGSize size = view().bounds().size();
		float aspect = (float)Math.abs(size.width() / size.height());
		GLKMatrix4 projectionMatrix = GLKit.GLKMatrix4MakePerspective(
				GLKit.GLKMathDegreesToRadians(65.0f), aspect, 4.0f, 10.0f);
		effect.transform().setProjectionMatrix(projectionMatrix);
		GLKMatrix4 modelViewMatrix = GLKit.GLKMatrix4MakeTranslation(0.0f,
				0.0f, -6.0f);
		rotation += 90 * timeSinceLastUpdate();
		modelViewMatrix = GLKit.GLKMatrix4Rotate(modelViewMatrix,
				GLKit.GLKMathDegreesToRadians(25), 1, 0, 0);
		modelViewMatrix = GLKit.GLKMatrix4Rotate(modelViewMatrix,
				GLKit.GLKMathDegreesToRadians(rotation), 0, 1, 0);
		effect.transform().setModelviewMatrix(modelViewMatrix);
	}

	@Selector("touchesBegan:withEvent:")
	public void touchesBeganWithEvent(NSSet touches, UIEvent event) {
		setPaused(!isPaused());
	}

}
