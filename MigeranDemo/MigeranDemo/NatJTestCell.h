#import <UIKit/UIKit.h>

@interface NatJTestCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
@property (weak, nonatomic) IBOutlet UILabel *timeLabel;
@property (weak, nonatomic) IBOutlet UILabel *mtTimeLabel;
@property (weak, nonatomic) IBOutlet UIActivityIndicatorView *indicatorView;
@end
