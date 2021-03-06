//
//  MainViewController.m
//  Zhat
//
//  Created by Zhi Li on 3/16/15.
//  Copyright (c) 2015 Zhi Li. All rights reserved.
//

#import "MainViewController.h"
#import "FriendsListViewController.h"
#import "ChatsListViewController.h"

@interface MainViewController ()

@property (strong, nonatomic) FriendsListViewController *friendsListController;
@property (strong, nonatomic) ChatsListViewController *chatsListController;

@end

@implementation MainViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.

    [self initTabControllers];
    [self initTabImages];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)initTabControllers {
    self.friendsListController = [[FriendsListViewController alloc] init];
    self.chatsListController = [[ChatsListViewController alloc] init];
    
    NSArray *controllers = [NSArray arrayWithObjects:self.friendsListController, self.chatsListController, nil];
    [self setViewControllers:controllers];
}

- (void)initTabImages {
    UIImage *friendsListImage = [UIImage imageNamed:@"friends-Small.png"];
    UIImage *chatsListImage = [UIImage imageNamed:@"chat-Small.png"];
    
    self.friendsListController.tabBarItem = [[UITabBarItem alloc] initWithTitle:@"Friends" image:friendsListImage tag:0];
    self.chatsListController.tabBarItem = [[UITabBarItem alloc] initWithTitle:@"Chats" image:chatsListImage tag:1];
}

@end
