//
//  ZhatConstant.h
//  Zhat
//
//  Created by Zhi Li on 3/16/15.
//  Copyright (c) 2015 com.zhi.li. All rights reserved.
//

#import <Foundation/Foundation.h>

#pragma mark - info.plist key

extern NSString *const PLIST_DOMAIN_URL_KEY;











#pragma mark - notification name -

extern NSString *const MineNotificationLoginDidSucceed;
extern NSString *const MineNotificationSignUpDidSucceed;
extern NSString *const MineNotificationAddTransactionDidSucceed;
extern NSString *const MineNotificationGetAllTransactionsDidSucceed;
extern NSString *const MineNotificationDeleteTransactionDidSucceed;
extern NSString *const MineNotificationDownViewDidHide;
extern NSString *const MineNotificationShowLoginView;

#pragma mark - request parameter -

extern NSString *const MineRequestParameterUsername;
extern NSString *const MineRequestParameterPasscode;
extern NSString *const MineRequestParameterFirstname;
extern NSString *const MineRequestParameterLastname;
extern NSString *const MineRequestParameterGender;
extern NSString *const MineRequestParameterToken;
extern NSString *const MineRequestParameterTimestamp;
extern NSString *const MineRequestParameterPrice;
extern NSString *const MineRequestParameterTransactionId;

#pragma mark - response key -

extern NSString *const MineResponseKeyErrorJson;
extern NSString *const MineResponseKeyErrorCode;
extern NSString *const MineResponseKeyErrorMsg;

extern NSString *const MineResponseKeyResponseJson;
extern NSString *const MineResponseKeyResponseToken;
extern NSString *const MineResponseKeyResponseTransactions;
extern NSString *const MineResponseKeyResponseDeletedTransactionId;

#pragma mark - alert view title and body msg -

extern NSString *const MineAlertViewTitleGeneralError;
extern NSString *const MineAlertViewMsgGeneralError;

extern NSString *const MineAlertViewTitleInternetNotAvailable;
extern NSString *const MineAlertViewMsgInternetNotAvailable;

extern NSString *const MineAlertViewTitleLoginRequestTimeout;
extern NSString *const MineAlertViewMsgLoginRequestTimeout;

extern NSString *const MineAlertViewTitleSignupRequestTimeout;
extern NSString *const MineAlertViewMsgSignupRequestTimeout;

extern NSString *const MineAlertViewTitleAddTransactionRequestTimeout;
extern NSString *const MineAlertViewMsgAddTransactionRequestTimeout;

extern NSString *const MineAlertViewTitleUserNotExist;
extern NSString *const MineAlertViewMsgUserNotExist;

extern NSString *const MineAlertViewTitleWrongPasscode;
extern NSString *const MineAlertViewMsgWrongPasscode;

