/*
 * Copyright (c) 2017 ICM Uniwersytet Warszawski All rights reserved.
 * See LICENCE.txt file for licensing information.
 */
package net.bixbit.vulnerablespam;

public class UserComment
{
	private String user;
	private String comment;

	public UserComment(String user, String comment)
	{
		this.user = user;
		this.comment = comment;
	}

	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
