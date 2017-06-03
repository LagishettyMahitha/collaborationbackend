package com.mahi.dao;

import java.util.List;

import com.mahi.model.Friend;

public interface FriendDao
{

	public void addFriend(Friend friend);
	public void updateFriend(Friend friend);
	public void deleteFriend(long id);
	public Friend getFriend(long userid, long friendId);
	public List<Friend> listMyFriends(long userid);
	public List<Friend> listNewFriendRequests(long userid);
	public void setOnline(long userid);
	public void setOffline(long userid);
}
