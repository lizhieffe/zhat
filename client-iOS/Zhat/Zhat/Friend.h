//
//  Friend.h
//  Zhat
//
//  Created by Zhi Li on 3/16/15.
//  Copyright (c) 2015 Zhi Li. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Email;

@interface Friend : NSObject

@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) Email *email;
@property (strong, nonatomic) UIImage *image;

@end

