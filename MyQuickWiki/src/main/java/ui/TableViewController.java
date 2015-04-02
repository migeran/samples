package ui;

import com.migeran.DataSource;
import com.migeran.DataSource.Entry;
import com.migeran.natj.general.NatJ;
import com.migeran.natj.general.Pointer;
import com.migeran.natj.general.ann.Generated;
import com.migeran.natj.general.ann.NInt;
import com.migeran.natj.general.ann.Owned;
import com.migeran.natj.general.ann.RegisterOnStartup;
import com.migeran.natj.general.ann.Runtime;
import com.migeran.natj.objc.ObjCRuntime;
import com.migeran.natj.objc.ann.ObjCClassName;
import com.migeran.natj.objc.ann.Selector;

import ios.foundation.NSBundle;
import ios.foundation.NSIndexPath;
import ios.uikit.UIStoryboardSegue;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;

import java.lang.String;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("TableViewController")
@RegisterOnStartup
public class TableViewController extends UITableViewController {
	static {
		NatJ.register();
	}

	@Generated
	protected TableViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native TableViewController alloc();

	@Generated
	@Selector("init")
	public native TableViewController init();

	@Generated
	@Selector("initWithNibName:bundle:")
	public native TableViewController initWithNibNameBundle(
			String nibNameOrNil, NSBundle nibBundleOrNil);

	@Generated
	@Selector("initWithStyle:")
	public native TableViewController initWithStyle(@NInt long style);
	
	private static final String CELL_ID = "ItemCell";
	
	@NInt
	@Selector("numberOfSectionsInTableView:")
	@Override
	public long numberOfSectionsInTableView(UITableView tableView) {
		return 1;
	}
	
	@NInt
	@Selector("tableView:numberOfRowsInSection:")
	@Override
	public long tableViewNumberOfRowsInSection(UITableView tableView,
			@NInt long section) {
		return DataSource.size();
	}
	
	@Selector("tableView:cellForRowAtIndexPath:")
	@Override
	public UITableViewCell tableViewCellForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath) {
		TableViewCell cell = (TableViewCell) tableView.dequeueReusableCellWithIdentifier(CELL_ID);
		
		Entry entry = DataSource.get(indexPath.row());
		cell.titleLabel().setText(entry.getTitle());
		cell.categoryLabel().setText(entry.getCategory());
		
		return cell;
	}
	
	@Selector("prepareForSegue:sender:")
	@Override
	public void prepareForSegueSender(UIStoryboardSegue segue, Object sender) {
		if (segue.identifier().equals("showDetails")) {
			TableViewCell cell = (TableViewCell) sender;
			long row = tableView().indexPathForCell(cell).row();
			
			Entry entry = DataSource.get(row);
			DetailViewController dest = (DetailViewController) segue.destinationViewController();
			dest.setTitle(entry.getTitle());
			dest.setLoadURL(entry.getTitle());
		}
	}
}