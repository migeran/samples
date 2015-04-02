#import <UIKit/UIKit.h>

#ifdef __cplusplus
#define NST_EXTERN		extern "C" __attribute__((visibility ("default")))
#else
#define NST_EXTERN	        extern __attribute__((visibility ("default")))
#endif

NST_EXTERN void NST_Init();

NST_EXTERN void      NST_VoidReturn();
NST_EXTERN int32_t   NST_IntReturn();
NST_EXTERN int64_t   NST_LongReturn();
NST_EXTERN float     NST_FloatReturn();
NST_EXTERN double    NST_DoubleReturn();
NST_EXTERN id        NST_NilReturn();
NST_EXTERN id        NST_BindingReturn();
NST_EXTERN id        NST_BindingWithHiddenImplClassReturn();
NST_EXTERN id        NST_InheritedReturn();
NST_EXTERN id        NST_ProxyReturn();
NST_EXTERN id        NST_HybridReturn();
NST_EXTERN NSString* NST_StringReturn();

NST_EXTERN void NST_setInheritedReturn(id obj);
NST_EXTERN void NST_setProxyReturn(id obj);
NST_EXTERN void NST_setHybridReturn(id obj);

@interface NatJStressTests : NSObject

@end
