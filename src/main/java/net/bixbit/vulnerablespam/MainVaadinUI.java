/*
 * Copyright (c) 2017 ICM Uniwersytet Warszawski All rights reserved.
 * See LICENCE.txt file for licensing information.
 */
package net.bixbit.vulnerablespam;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SpringUI
public class MainVaadinUI extends UI
{
	@Override
	protected void init(VaadinRequest vaadinRequest)
	{
		setContent(new Label("Hello! I'm the root UI!"));
	}
}
