/*
 * Copyright (c) 2017 ICM Uniwersytet Warszawski All rights reserved.
 * See LICENCE.txt file for licensing information.
 */
package net.bixbit.vulnerablespam;

public class User
{
	private String username;
	private String creditCard;
	private int bought;

	public User(String username, String creditCard, int bought)
	{
		this.username = username;
		this.creditCard = creditCard;
		this.bought = bought;
	}
	
	public String getUsername()
	{
		return username;
	}
	public String getCreditCard()
	{
		return creditCard;
	}
	public int getBought()
	{
		return bought;
	}
	public void incBought(int amount)
	{
		this.bought += amount;
	}
}
