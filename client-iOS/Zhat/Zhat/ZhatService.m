//
//  MineService.m
//  Mine
//
//  Created by Zhi Li on 7/26/14.
//  Copyright (c) 2014 com.zhi.li. All rights reserved.
//

#import "ZhatService.h"
//#import "MineTimeUtil.h"
//#import "MinePersistDataUtil.h"
//#import "MinePreferenceService.h"
//#import "MineConstant.h"
//#import "MineServiceManager.h"
//#import "MineAlertViewUtil.h"
//#import "Reachability.h"

#import "ZhatConstant.h"
#import "AFHTTPRequestOperationManager.h"

@interface ZhatService ()

@property (strong, nonatomic) NSString *fullUrl;

@end

@implementation ZhatService

- (id)init {
    self = [super init];
    if (self) {
        _requestParameters = [[NSMutableDictionary alloc] init];
        _fullUrl = [[NSString alloc] init];
        _finished = NO;
        _ignoreCache = NO;
        _expireTimeInterval = -1;
        _timeout = 10;
    }
    return self;
}

- (NSString *)hostUrl
{
    return [[NSBundle mainBundle] objectForInfoDictionaryKey:PLIST_DOMAIN_URL_KEY];
}

- (NSString *)apiPath
{
    return nil;
}

- (HttpRequestMethod)HttpMethod
{
    return NONE;
}

- (NSString *)fullUrl
{
    // lazy initialization
    if (!_fullUrl || [_fullUrl length] == 0) {
        _fullUrl = [NSString stringWithFormat:@"%@%@", [self hostUrl], [self apiPath]];
    }
    
    return _fullUrl;
}

- (NSDictionary *)parameters {
    return nil;
}

- (void)start
{
    HttpRequestMethod method = [self HttpMethod];
    
    if (method == NONE)
        return;
    
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    if (method == GET) {
        [manager GET:[self fullUrl] parameters:[self parameters] success:^(AFHTTPRequestOperation *operation, id responseObject) {
            NSLog(@"JSON: %@", responseObject);
        } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
            NSLog(@"Error: %@", error);
        }];
    }
    else if (method == POST) {
        manager.requestSerializer = [AFJSONRequestSerializer serializer];
        [manager
            POST:[self fullUrl]
            parameters:[self parameters]
            success: ^(AFHTTPRequestOperation *operation, id responseObject) {
                NSLog(@"JSON: %@", responseObject);
                [self completionBlockForSuccess](operation, responseObject);
            }
            failure: ^(AFHTTPRequestOperation *operation, NSError *error) {
                NSLog(@"Error: %@", error);
                [self completionBlockForFailure](operation, error);
            }
        ];
    }
}

- (void (^)(AFHTTPRequestOperation *operation, id responseObject))completionBlockForSuccess
{
    return ^(AFHTTPRequestOperation *operation, id responseObject) {
    };
}

- (void (^)(AFHTTPRequestOperation *operation, NSError *NSError))completionBlockForFailure
{
    return ^(AFHTTPRequestOperation *operation, NSError *NSError) {
    };
}

@end
