//
//  MineService.h
//  Mine
//
//  Created by Zhi Li on 7/26/14.
//  Copyright (c) 2014 com.zhi.li. All rights reserved.
//

#import <Foundation/Foundation.h>

#pragma mark - Http Request Method enum

typedef enum HttpRequestMethod : NSUInteger {
    GET,
    POST,
    NONE
} HttpRequestMethod;

#pragma mark - interface

@interface ZhatService : NSObject

@property (strong, nonatomic) NSMutableDictionary *requestParameters;
@property (nonatomic, assign) BOOL finished;
@property (nonatomic, assign) BOOL ignoreCache;
@property (assign, nonatomic) NSInteger expireTimeInterval;
@property (strong, nonatomic) NSString *token;
@property (assign, nonatomic) NSInteger timeout;
@property (weak, nonatomic) id <NSURLConnectionDelegate> connectionDelegate;

- (NSString *)hostUrl;
- (NSString *)apiPath;
- (HttpRequestMethod)HttpMethod;
- (void)start;

@end
