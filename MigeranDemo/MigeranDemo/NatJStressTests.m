#import "NatJStressTests.h"

static NSData*  NST_TestBindingObject = nil;
static UIColor* NST_TestBindingHICObject = nil;
static UIColor* NST_TestInheritedObject = nil;
static UIColor* NST_TestProxyObject = nil;
static UIColor* NST_TestHybridObject = nil;

NST_EXTERN void NST_Init() {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        NST_TestBindingObject = [[NSData alloc] init];
        NST_TestBindingHICObject = [UIColor redColor];
    });
}

void      NST_VoidReturn()                       {}
int32_t   NST_IntReturn()                        { return 0x7FFFFFFF; }
int64_t   NST_LongReturn()                       { return 0x7FFFFFFFFFFFFFFFL; }
float     NST_FloatReturn()                      { return M_PI; }
double    NST_DoubleReturn()                     { return M_PI; }
id        NST_NilReturn()                        { return nil; }
id        NST_BindingReturn()                    { return NST_TestBindingObject; }
id        NST_BindingWithHiddenImplClassReturn() { return NST_TestBindingHICObject; }
id        NST_InheritedReturn()                  { return NST_TestInheritedObject; }
id        NST_ProxyReturn()                      { return NST_TestProxyObject; }
id        NST_HybridReturn()                     { return NST_TestHybridObject; }
NSString* NST_StringReturn()                     { return @"test"; }

void NST_setInheritedReturn(id obj) {
    NST_TestInheritedObject = obj;
}

void NST_setProxyReturn(id obj) {
    NST_TestProxyObject = obj;
}

void NST_setHybridReturn(id obj) {
    NST_TestHybridObject = obj;
}

@implementation NatJStressTests

@end
