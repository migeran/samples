#import <UIKit/UIKit.h>

@interface RSSCell : UITableViewCell

@property (retain, nonatomic) IBOutlet UILabel *rssTitle;
@property (retain, nonatomic) IBOutlet UITextView *rssDescription;
@property (retain, nonatomic) IBOutlet UILabel *rssDate;

@end
