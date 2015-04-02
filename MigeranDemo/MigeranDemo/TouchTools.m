#import "TouchTools.h"

@implementation TouchTools

+ (NSArray *)convertArrayOfUIColorToCGColor:(NSArray *)colors {
    NSMutableArray *out_colors = [[NSMutableArray alloc] initWithCapacity:colors.count];
    for (UIColor *color in colors) {
        [out_colors addObject:(id)color.CGColor];
    }
    return out_colors;
}

@end
