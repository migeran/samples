#import "UIColor+MigeranDemo.h"

#import <dispatch/dispatch.h>

#import <Foundation/NSArray.h>

@implementation UIColor (MigeranDemo)

- (void)setAsCALayerBackgroundColor:(CALayer *)layer {
    layer.backgroundColor = self.CGColor;
}

- (void)setAsCALayerBorderColor:(CALayer *)layer {
    layer.borderColor = self.CGColor;
}

@end
