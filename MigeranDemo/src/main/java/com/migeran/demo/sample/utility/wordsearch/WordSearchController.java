package com.migeran.demo.sample.utility.wordsearch;

import ios.foundation.NSArray;
import ios.foundation.NSBundle;
import ios.foundation.NSError;
import ios.foundation.NSIndexPath;
import ios.foundation.NSMutableArray;
import ios.foundation.NSString;
import ios.foundation.enums.Enums;
import ios.foundation.struct.NSRange;
import ios.uikit.UIAlertView;
import ios.uikit.UIColor;
import ios.uikit.UIDevice;
import ios.uikit.UISegmentedControl;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITextField;
import ios.uikit.UIViewController;
import ios.uikit.enums.NSLayoutAttribute;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UILayoutConstraintAxis;
import ios.uikit.enums.UIRectEdge;
import ios.uikit.enums.UIReturnKeyType;
import ios.uikit.enums.UITextAutocapitalizationType;
import ios.uikit.enums.UITextAutocorrectionType;
import ios.uikit.enums.UITextBorderStyle;
import ios.uikit.enums.UITextFieldViewMode;
import ios.uikit.enums.UIUserInterfaceIdiom;
import ios.uikit.protocol.UITableViewDataSource;
import ios.uikit.protocol.UITextFieldDelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.migeran.demo.util.UIBuilder;
import com.migeran.demo.util.UILayout;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.ByValue;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ptr.Ptr;
import com.migeran.natj.general.ptr.impl.PtrFactory;
import com.migeran.natj.objc.SEL;
import com.migeran.natj.objc.ann.IsOptional;
import com.migeran.natj.objc.ann.NotImplemented;
import com.migeran.natj.objc.ann.Selector;

public class WordSearchController extends UIViewController implements
		UITableViewDataSource, UITextFieldDelegate {

	private static final String CELL_IDENTIFIER = "Cell";

	static {
		NatJ.register();
	}

	static final class PrefixedWords {
		public static final int STARTS_WITH_MODE = 0;
		public static final int CONTAINS_MODE = 1;
		public static final int ENDS_WITH = 2;

		public final String prefix;
		private final ArrayList<String> words = new ArrayList<String>();

		public PrefixedWords(String prefix, List<String> allwords) {
			this.prefix = prefix;
			for (String word : allwords) {
				if (word.startsWith(prefix)) {
					words.add(word);
				}
			}
			Collections.sort(words);
		}

		private PrefixedWords(String prefix, List<String> allwords,
				String filter, int mode) {
			this.prefix = prefix;
			if (mode == STARTS_WITH_MODE) {
				for (String word : allwords) {
					if (word.startsWith(filter)) {
						words.add(word);
					}
				}
			} else if (mode == CONTAINS_MODE) {
				for (String word : allwords) {
					if (word.indexOf(filter) != -1) {
						words.add(word);
					}
				}
			} else if (mode == ENDS_WITH) {
				for (String word : allwords) {
					if (word.endsWith(filter)) {
						words.add(word);
					}
				}
			} else {
				throw new IllegalArgumentException();
			}
		}

		public int size() {
			return words.size();
		}

		public String get(int index) {
			return words.get(index);
		}

		public PrefixedWords getFiltered(String filter, int mode) {
			if (filter.length() == 0) {
				return this;
			} else {
				return new PrefixedWords(prefix, words, filter, mode);
			}
		}
	}

	private static final boolean OPTIMIZED_FILTERING = true;

	private final ArrayList<PrefixedWords> data = new ArrayList<WordSearchController.PrefixedWords>();
	private final ArrayList<PrefixedWords> filtered = new ArrayList<WordSearchController.PrefixedWords>();

	private UITableView tableView;

	private UILayout layout;
	private String filterString = "";
	private int filterMode = PrefixedWords.STARTS_WITH_MODE;

	@Generated("NatJ")
	@Owned
	@Selector("alloc")
	public static native WordSearchController alloc();

	@Selector("init")
	public native WordSearchController init();

	@Generated("NatJ")
	protected WordSearchController(Pointer peer) {
		super(peer);
	}

	@Selector("viewDidLoad")
	@Override
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Word Search");

		setEdgesForExtendedLayout(UIRectEdge.None);
		view().setBackgroundColor(UIColor.whiteColor());
		this.view().setUserInteractionEnabled(false);

		UIBuilder builder = new UIBuilder(this);

		NSMutableArray modes = NSMutableArray.alloc().init();
		modes.add("Starts With");
		modes.add("Contains");
		modes.add("Ends With");

		// Create views
		tableView = builder.add(UITableView.alloc().init(), "table");
		final UITextField filterField = builder.addTextField("filter");
		final UISegmentedControl modeControl = builder.add(UISegmentedControl
				.alloc().initWithItems(modes), "modes");

		// Set content hugging
		modeControl.setContentHuggingPriorityForAxis(500, UILayoutConstraintAxis.Horizontal);

		// Prepare layout
		layout = builder.newLayout();
		layout.add("H:|-0-[table]-0-|");
		if (UIDevice.currentDevice().userInterfaceIdiom() == UIUserInterfaceIdiom.Pad) {
			layout.add("H:|-[modes]-[filter]-|");
			layout.add("V:[topGuide]-[filter]-[table]-0-[bottomGuide]");
			layout.setEqual(NSLayoutAttribute.CenterY, "modes", "filter");
		} else {
			layout.add("H:|-[filter]-|");
			layout.add("V:[topGuide]-[filter]-[modes]-[table]-0-[bottomGuide]");
			layout.setEqual(NSLayoutAttribute.CenterX, modeControl, view());
		}
		layout.activate(null);

		// Setup tableview
		tableView.registerClassForCellReuseIdentifier(
				new com.migeran.natj.objc.Class("UITableViewCell"),
				CELL_IDENTIFIER);
		tableView.setDataSource_unsafe(this);

		// Setup filter field
		filterField.setBorderStyle(UITextBorderStyle.RoundedRect);
		filterField.setClearButtonMode(UITextFieldViewMode.Always);
		filterField.setReturnKeyType(UIReturnKeyType.Done);
		filterField
				.setAutocapitalizationType(UITextAutocapitalizationType.None);
		filterField.setAutocorrectionType(UITextAutocorrectionType.No);
		filterField.setDelegate_unsafe(this);

		// Setup mode control
		modeControl.setSelectedSegmentIndex(1);
		modeChanged(modeControl);
		modeControl.addTargetActionForControlEvents(this, new SEL(
				"modeChanged:"), UIControlEvents.ValueChanged);

		this.performSelectorInBackgroundWithObject(new SEL("loadDictionary"),
				null);
	}

	@Selector("modeChanged:")
	private void modeChanged(UISegmentedControl sender) {
		if (sender.selectedSegmentIndex() == 0) {
			filterMode = PrefixedWords.STARTS_WITH_MODE;
		} else if (sender.selectedSegmentIndex() == 1) {
			filterMode = PrefixedWords.CONTAINS_MODE;
		} else {
			filterMode = PrefixedWords.ENDS_WITH;
		}
		filter(true);
	}

	@Selector("loadDictionary")
	public void loadDictionary() {
		data.clear();
		String path = NSBundle.mainBundle().pathForResourceOfType("US", "dic");
		Ptr<NSError> err = PtrFactory.newObjectPtr(NSError.class, 1, true,
				false);

		NSString source = NSString.stringWithContentsOfFileEncodingError(path,
				Enums.NSASCIIStringEncoding, err);
		if (err.get() != null || source == null || source.length() == 0) {
			this.performSelectorOnMainThreadWithObjectWaitUntilDone(new SEL(
					"loadDictionaryFailed"), null, false);
			return;
		}

		String[] sourceWords = source.toString().split("\n");

		for (char c = 'a'; c <= 'z'; ++c) {
			data.add(new PrefixedWords("" + c, Arrays.asList(sourceWords)));
		}

		this.view().setUserInteractionEnabled(true);
		filter(true);
	}

	public void filter(boolean root) {
		if (root || !OPTIMIZED_FILTERING) {
			filtered.clear();
			filtered.addAll(data);
		}

		for (int i = 0; i < filtered.size(); ++i) {
			filtered.set(i,
					filtered.get(i).getFiltered(filterString, filterMode));
		}

		this.performSelectorOnMainThreadWithObjectWaitUntilDone(new SEL(
				"reloadTableViewData"), null, false);
	}

	@Selector("loadDictionaryFailed")
	public void loadDictionaryFailed() {
		UIAlertView alert = UIAlertView.alloc().init();
		alert.setTitle("An error occurred");
		alert.setMessage("Couldn't load dictionary file");
		alert.addButtonWithTitle("Close");
		alert.show();
	}

	@Selector("reloadTableViewData")
	public void reloadTableViewData() {
		tableView.reloadData();
	}

	@Override
	@Selector("numberOfSectionsInTableView:")
	@IsOptional
	public @NInt long numberOfSectionsInTableView(UITableView tableView) {
		int count = 0;
		for (PrefixedWords pw : filtered) {
			if (pw.size() > 0) {
				++count;
			}
		}
		return count;
	}

	private PrefixedWords getFilteredForSection(long section) {
		for (PrefixedWords pw : filtered) {
			if (pw.size() > 0) {
				if (section == 0) {
					return pw;
				}
				--section;
			}
		}
		return null;
	}

	@Override
	@Selector("tableView:cellForRowAtIndexPath:")
	public UITableViewCell tableViewCellForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath) {
		UITableViewCell cell = (UITableViewCell) tableView
				.dequeueReusableCellWithIdentifierForIndexPath(CELL_IDENTIFIER,
						indexPath);

		String text = getFilteredForSection(indexPath.section()).get(
				(int)indexPath.row());
		cell.textLabel().setText(text);

		return cell;
	}

	@Override
	@Selector("tableView:numberOfRowsInSection:")
	public @NInt long tableViewNumberOfRowsInSection(UITableView tableView, @NInt long section) {
		return getFilteredForSection(section).size();
	}

	@Override
	@Selector("tableView:titleForHeaderInSection:")
	public String tableViewTitleForHeaderInSection(UITableView tableView,
			@NInt long section) {
		return getFilteredForSection(section).prefix.toUpperCase();
	}

	@Override
	@Selector("textField:shouldChangeCharactersInRange:replacementString:")
	public boolean textFieldShouldChangeCharactersInRangeReplacementString(
			UITextField textField, @ByValue NSRange range, String string) {
		String newfilter = filterString.substring(0, (int)range.location())
				+ string.toLowerCase()
				+ filterString.substring((int)range.location() + (int)range.length(),
						filterString.length());
		boolean doRoot = !newfilter.startsWith(filterString);
		filterString = newfilter;
		filter(doRoot);
		return true;
	}

	@Override
	@Selector("textFieldShouldClear:")
	public boolean textFieldShouldClear(UITextField textField) {
		filterString = "";
		filter(true);
		return true;
	}

	@Override
	@Selector("textFieldShouldReturn:")
	public boolean textFieldShouldReturn(UITextField textField) {
		textField.resignFirstResponder();
		return false;
	}

	@NotImplemented
	@Override
	@Selector("sectionIndexTitlesForTableView:")
	@IsOptional
	public native NSArray sectionIndexTitlesForTableView(UITableView tableView);

	@NotImplemented
	@Override
	@Selector("tableView:canEditRowAtIndexPath:")
	@IsOptional
	public native boolean tableViewCanEditRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath);

	@NotImplemented
	@Override
	@Selector("tableView:canMoveRowAtIndexPath:")
	@IsOptional
	public native boolean tableViewCanMoveRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath);

	@NotImplemented
	@Override
	@Selector("tableView:commitEditingStyle:forRowAtIndexPath:")
	@IsOptional
	public native void tableViewCommitEditingStyleForRowAtIndexPath(
			UITableView tableView, @NInt long editingStyle, NSIndexPath indexPath);

	@NotImplemented
	@Override
	@Selector("tableView:moveRowAtIndexPath:toIndexPath:")
	@IsOptional
	public native void tableViewMoveRowAtIndexPathToIndexPath(
			UITableView tableView, NSIndexPath sourceIndexPath,
			NSIndexPath destinationIndexPath);

	@NotImplemented
	@Override
	@Selector("tableView:sectionForSectionIndexTitle:atIndex:")
	@IsOptional
	public native @NInt long tableViewSectionForSectionIndexTitleAtIndex(
			UITableView tableView, String title, @NInt long index);

	@NotImplemented
	@Override
	@Selector("tableView:titleForFooterInSection:")
	@IsOptional
	public native String tableViewTitleForFooterInSection(
			UITableView tableView, @NInt long section);

	@NotImplemented
	@Override
	@Selector("textFieldDidBeginEditing:")
	@IsOptional
	public native void textFieldDidBeginEditing(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldDidEndEditing:")
	@IsOptional
	public native void textFieldDidEndEditing(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldShouldBeginEditing:")
	@IsOptional
	public native boolean textFieldShouldBeginEditing(UITextField textField);

	@NotImplemented
	@Override
	@Selector("textFieldShouldEndEditing:")
	@IsOptional
	public native boolean textFieldShouldEndEditing(UITextField textField);
}
