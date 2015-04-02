package com.migeran.demo.sample.map.simplemap;

import ios.foundation.NSArray;
import ios.foundation.NSError;
import ios.foundation.struct.NSRange;
import ios.mapkit.MKAnnotationView;
import ios.mapkit.MKMapView;
import ios.mapkit.MKOverlayRenderer;
import ios.mapkit.MKOverlayView;
import ios.mapkit.MKPinAnnotationView;
import ios.mapkit.MKPointAnnotation;
import ios.mapkit.MKUserLocation;
import ios.mapkit.enums.MKPinAnnotationColor;
import ios.mapkit.protocol.MKMapViewDelegate;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UIControl;
import ios.uikit.UITextField;
import ios.uikit.UIViewController;
import ios.uikit.enums.NSLayoutAttribute;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UIRectEdge;
import ios.uikit.enums.UIReturnKeyType;
import ios.uikit.enums.UITextBorderStyle;
import ios.uikit.protocol.UITextFieldDelegate;

import java.lang.ref.WeakReference;

import com.migeran.demo.util.UIBuilder;
import com.migeran.demo.util.UILayout;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.ByValue;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.general.ann.NUInt;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.Selector;

public class SimpleMapController extends UIViewController {

	private static final String PointAnnotationViewID = "PointAnnotation";

	static {
		NatJ.register();
	}

	private WeakReference<UITextField> fieldRef;
	private WeakReference<MKMapView> mapRef;

	private final UITextFieldDelegate tfdelegate = new UITextFieldDelegate() {

		@NotImplemented
		@Generated
		@Selector("textField:shouldChangeCharactersInRange:replacementString:")
		public native boolean textFieldShouldChangeCharactersInRangeReplacementString(
				UITextField textField, @ByValue NSRange range, String string);

		@NotImplemented
		@Generated
		@Selector("textFieldDidBeginEditing:")
		public native void textFieldDidBeginEditing(UITextField textField);

		@NotImplemented
		@Generated
		@Selector("textFieldDidEndEditing:")
		public native void textFieldDidEndEditing(UITextField textField);

		@NotImplemented
		@Generated
		@Selector("textFieldShouldBeginEditing:")
		public native boolean textFieldShouldBeginEditing(
				UITextField textField);

		@NotImplemented
		@Generated
		@Selector("textFieldShouldClear:")
		public native boolean textFieldShouldClear(UITextField textField);

		@NotImplemented
		@Generated
		@Selector("textFieldShouldEndEditing:")
		public native boolean textFieldShouldEndEditing(
				UITextField textField);

		@Selector("textFieldShouldReturn:")
		public boolean textFieldShouldReturn(UITextField textField) {
			textField.resignFirstResponder();
			return false;
		}
	};
	
	private UILayout vertical_layout;

	@Generated("NatJ")
	@Selector("alloc")
	public static native SimpleMapController alloc();

	@Generated("NatJ")
	@Selector("init")
	public native SimpleMapController init();

	@Generated("NatJ")
	protected SimpleMapController(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Simple Map");

		setEdgesForExtendedLayout(UIRectEdge.None);
		view().setBackgroundColor(UIColor.whiteColor());

		UIBuilder builder = new UIBuilder(this);

		final MKMapView mapView = builder.add(MKMapView.alloc().init(), "map");
		final UITextField pinLabelField = builder.addTextField("pinLabel");
		mapRef = new WeakReference<MKMapView>(mapView);
		fieldRef = new WeakReference<UITextField>(pinLabelField);

		mapView.setDelegate(new MKMapViewDelegate() {
			
			@Selector("mapView:didSelectAnnotationView:")
			public void mapViewDidSelectAnnotationView(MKMapView mapView,
					MKAnnotationView view) {
				MKPointAnnotation ann = (MKPointAnnotation) view.annotation();
				pinLabelField.setText(ann.title());
				MKPinAnnotationView pa = (MKPinAnnotationView) view;
				pa.setPinColor(MKPinAnnotationColor.Green);
			}

			@Selector("mapView:didDeselectAnnotationView:")
			public void mapViewDidDeselectAnnotationView(MKMapView mapView,
					MKAnnotationView view) {
				MKPinAnnotationView pa = (MKPinAnnotationView) view;
				pa.setPinColor(MKPinAnnotationColor.Red);
			}

			@Selector("mapView:viewForAnnotation:")
			public MKAnnotationView mapViewViewForAnnotation(MKMapView mapView,
					Object annotation) {
				MKPinAnnotationView view = (MKPinAnnotationView) mapView
						.dequeueReusableAnnotationViewWithIdentifier(PointAnnotationViewID);
				if (view == null) {
					view = MKPinAnnotationView.alloc()
							.initWithAnnotationReuseIdentifier(annotation,
									PointAnnotationViewID);
				} else {
					view.setAnnotation(annotation);
				}
				view.setAnimatesDrop(true);
				return view;
			}

			@NotImplemented
			@Generated
			@Selector("mapView:annotationView:calloutAccessoryControlTapped:")
			public native void mapViewAnnotationViewCalloutAccessoryControlTapped(
					MKMapView mapView, MKAnnotationView view, UIControl control);

			@NotImplemented
			@Generated
			@Selector("mapView:annotationView:didChangeDragState:fromOldState:")
			public native void mapViewAnnotationViewDidChangeDragStateFromOldState(
					MKMapView mapView, MKAnnotationView view, @NUInt long newState,
					@NUInt long oldState);

			@NotImplemented
			@Generated
			@Selector("mapView:didAddAnnotationViews:")
			public native void mapViewDidAddAnnotationViews(MKMapView mapView,
					NSArray views);

			@NotImplemented
			@Generated
			@Selector("mapView:didAddOverlayRenderers:")
			public native void mapViewDidAddOverlayRenderers(MKMapView mapView,
					NSArray renderers);

			@NotImplemented
			@Generated
			@Deprecated
			@Selector("mapView:didAddOverlayViews:")
			public native void mapViewDidAddOverlayViews(MKMapView mapView,
					NSArray overlayViews);

			@NotImplemented
			@Generated
			@Selector("mapView:didChangeUserTrackingMode:animated:")
			public native void mapViewDidChangeUserTrackingModeAnimated(
					MKMapView mapView, @NInt long mode, boolean animated);

			@NotImplemented
			@Generated
			@Selector("mapView:didFailToLocateUserWithError:")
			public native void mapViewDidFailToLocateUserWithError(
					MKMapView mapView, NSError error);

			@NotImplemented
			@Generated
			@Selector("mapView:didUpdateUserLocation:")
			public native void mapViewDidUpdateUserLocation(MKMapView mapView,
					MKUserLocation userLocation);

			@NotImplemented
			@Generated
			@Selector("mapView:regionDidChangeAnimated:")
			public native void mapViewRegionDidChangeAnimated(
					MKMapView mapView, boolean animated);

			@NotImplemented
			@Generated
			@Selector("mapView:regionWillChangeAnimated:")
			public native void mapViewRegionWillChangeAnimated(
					MKMapView mapView, boolean animated);

			@NotImplemented
			@Generated
			@Selector("mapView:rendererForOverlay:")
			public native MKOverlayRenderer mapViewRendererForOverlay(
					MKMapView mapView, Object overlay);

			@NotImplemented
			@Generated
			@Deprecated
			@Selector("mapView:viewForOverlay:")
			public native MKOverlayView mapViewViewForOverlay(
					MKMapView mapView, Object overlay);

			@NotImplemented
			@Generated
			@Selector("mapViewDidFailLoadingMap:withError:")
			public native void mapViewDidFailLoadingMapWithError(
					MKMapView mapView, NSError error);

			@NotImplemented
			@Generated
			@Selector("mapViewDidFinishLoadingMap:")
			public native void mapViewDidFinishLoadingMap(MKMapView mapView);

			@NotImplemented
			@Generated
			@Selector("mapViewDidFinishRenderingMap:fullyRendered:")
			public native void mapViewDidFinishRenderingMapFullyRendered(
					MKMapView mapView, boolean fullyRendered);

			@NotImplemented
			@Generated
			@Selector("mapViewDidStopLocatingUser:")
			public native void mapViewDidStopLocatingUser(MKMapView mapView);

			@NotImplemented
			@Generated
			@Selector("mapViewWillStartLoadingMap:")
			public native void mapViewWillStartLoadingMap(MKMapView mapView);

			@NotImplemented
			@Generated
			@Selector("mapViewWillStartLocatingUser:")
			public native void mapViewWillStartLocatingUser(MKMapView mapView);

			@NotImplemented
			@Generated
			@Selector("mapViewWillStartRenderingMap:")
			public native void mapViewWillStartRenderingMap(MKMapView mapView);

		});
		view().addSubview(mapView);

		pinLabelField.setReturnKeyType(UIReturnKeyType.Done);
		pinLabelField.setBorderStyle(UITextBorderStyle.RoundedRect);
		pinLabelField.setDelegate(tfdelegate);
		view().addSubview(pinLabelField);

		UIButton addPinBtn = builder.addButton("+", "addPinBtn");
		addPinBtn.addTargetActionForControlEvents(this, new SEL(
				"annotationAction:"), UIControlEvents.TouchUpInside);
		view().addSubview(addPinBtn);

		UIButton remPinBtn = builder.addButton("-", "remPinBtn");
		remPinBtn.addTargetActionForControlEvents(this, new SEL(
				"annotationAction:"), UIControlEvents.TouchUpInside);
		view().addSubview(remPinBtn);

		vertical_layout = builder.newLayout();
		_setupVerticalLayout(vertical_layout);
		vertical_layout.activate(null);
	}

	private void _setupVerticalLayout(UILayout layout) {
		layout.add("H:|-[pinLabel]-[addPinBtn]-[remPinBtn]-|");
		layout.add("H:|-0-[map]-0-|");
		layout.add("V:[topGuide]-[pinLabel]-[map]-0-[bottomGuide]");
		layout.setEqual(NSLayoutAttribute.Baseline, "pinLabel", "addPinBtn",
				"remPinBtn");
	}

	@Selector("annotationAction:")
	public void annotationAction(UIButton sender) {
		if (sender.titleLabel().text().equals("+")) {
			MKPointAnnotation pa = MKPointAnnotation.alloc().init();
			pa.setTitle(fieldRef.get().text());
			pa.setCoordinate(mapRef.get().centerCoordinate());
			mapRef.get().addAnnotation(pa);
		} else {
			mapRef.get().removeAnnotations(mapRef.get().selectedAnnotations());
		}
	}

}
