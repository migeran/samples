package com.migeran.demo.helper;

import ios.foundation.NSArray;
import ios.foundation.NSIndexPath;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;
import ios.uikit.enums.UITableViewRowAnimation;

import java.util.ArrayList;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.objc.ann.Selector;

public class SimpleTableController extends UITableViewController {

	private static final String CELL_IDENTIFIER = "Cell";

	static {
		NatJ.register();
	}

	private final ArrayList<String> options = new ArrayList<String>();

	public static interface EventListener {
		public void tableViewDidSelectRow(UITableView tableView, String row);
	}

	private EventListener listener;

	@Generated("NatJ")
	@Selector("alloc")
	public static native SimpleTableController alloc();

	@Selector("init")
	public native SimpleTableController init();

	@Generated("NatJ")
	protected SimpleTableController(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		tableView().registerClassForCellReuseIdentifier(
				new com.migeran.natj.objc.Class("UITableViewCell"),
				CELL_IDENTIFIER);
	}

	public void add(String elem) {
		options.add(elem);
		if (tableView() != null) {
			NSIndexPath path = NSIndexPath.indexPathForRowInSection(
					options.size() - 1, 0);
			NSArray paths = NSArray.arrayWithObject(path);
			tableView().insertRowsAtIndexPathsWithRowAnimation(paths,
					UITableViewRowAnimation.Automatic);
		}
	}

	@Selector("numberOfSectionsInTableView:")
	@Override
	@NInt
	public long numberOfSectionsInTableView(UITableView tableView) {
		return 1;
	}

	@Override
	@Selector("tableView:numberOfRowsInSection:")
	@NInt
	public long tableViewNumberOfRowsInSection(UITableView tableView, @NInt long section) {
		return options.size();
	}

	@Override
	@Selector("tableView:cellForRowAtIndexPath:")
	public UITableViewCell tableViewCellForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath) {
		UITableViewCell cell = (UITableViewCell) tableView
				.dequeueReusableCellWithIdentifierForIndexPath(CELL_IDENTIFIER,
						indexPath);

		cell.textLabel().setText(options.get((int)indexPath.row()));

		return cell;
	}

	@Override
	@Selector("tableView:didSelectRowAtIndexPath:")
	public void tableViewDidSelectRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath) {
		if (listener != null) {
			listener.tableViewDidSelectRow(tableView,
					options.get((int)indexPath.row()));
		}
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public EventListener getListener() {
		return listener;
	}

	public void setListener(EventListener listener) {
		this.listener = listener;
	}

}
