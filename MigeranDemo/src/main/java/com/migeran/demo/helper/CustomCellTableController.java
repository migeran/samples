package com.migeran.demo.helper;

import ios.foundation.NSIndexPath;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;

import java.util.ArrayList;

import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.objc.ann.Selector;

public abstract class CustomCellTableController extends UITableViewController {

	public static final String CELL_IDENTIFIER = "Cell";

	static {
		NatJ.register();
	}

	private final ArrayList<Object> data = new ArrayList<Object>();

	public static interface EventListener {
		public void tableViewDidSelectRow(UITableView tableView, Object row);
	}

	private EventListener listener;

	@Generated("NatJ")
	@Selector("alloc")
	public static native CustomCellTableController alloc();

	@Selector("init")
	public native CustomCellTableController init();

	@Generated("NatJ")
	protected CustomCellTableController(Pointer peer) {
		super(peer);
	}

	@Override
	@Selector("viewDidLoad")
	public void viewDidLoad() {
		super.viewDidLoad();

		prepareController();
	}

	protected abstract void prepareController();

	protected void add(Object elem) {
		data.add(elem);
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
		return data.size();
	}

	@Override
	@Selector("tableView:cellForRowAtIndexPath:")
	public UITableViewCell tableViewCellForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath) {
		UITableViewCell cell = (UITableViewCell) tableView
				.dequeueReusableCellWithIdentifierForIndexPath(CELL_IDENTIFIER,
						indexPath);

		setupCellAtIndex(cell, data.get((int)indexPath.row()));

		return cell;
	}

	protected abstract void setupCellAtIndex(UITableViewCell cell,
			Object rowData);

	@Override
	@Selector("tableView:didSelectRowAtIndexPath:")
	public void tableViewDidSelectRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath) {
		if (listener != null) {
			listener.tableViewDidSelectRow(tableView, data.get((int)indexPath.row()));
		}
	}

	public ArrayList<Object> getOptions() {
		return data;
	}

	public EventListener getListener() {
		return listener;
	}

	public void setListener(EventListener listener) {
		this.listener = listener;
	}

}
