#import <UIKit/UIKit.h>

@interface AddViewController : UIViewController <UITextFieldDelegate>

- (IBAction)doSave:(id)sender;

@property (weak, nonatomic) IBOutlet UITextField *titleField;
@property (weak, nonatomic) IBOutlet UITextField *categoryField;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *doneButton;
@property (weak, nonatomic) IBOutlet UIButton *previewButton;
@end
