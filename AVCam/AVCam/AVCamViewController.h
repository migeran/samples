#import <UIKit/UIKit.h>
#import "AVCamPreviewView.h"

@interface AVCamViewController : UIViewController

@property (weak, nonatomic) IBOutlet AVCamPreviewView *previewView;
@property (weak, nonatomic) IBOutlet UIButton *recordButton;
@property (weak, nonatomic) IBOutlet UIButton *stillButton;
@property (weak, nonatomic) IBOutlet UIButton *cameraButton;

- (IBAction)toggleMovieRecording:(UIButton *)sender;
- (IBAction)snapStillImage:(UIButton *)sender;
- (IBAction)changeCamera:(UIButton *)sender;
- (IBAction)focusAndExposeTap:(UIGestureRecognizer *)gestureRecognizer;

@end
