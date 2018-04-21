package com.timelessapps.javafxtemplate.helpers.services;

import static org.junit.Assert.*;

import org.junit.Test;

public class SceneHelperTest 
{
	SceneHelper sceneHelper = new SceneHelper();
	
	@Test
	public void getSourceID_button_expects_buttonID() 
	{
		Object source = "Button[id=homeButton, styleClass=button leftPaneButton]'Home'";
		String result = sceneHelper.getSourceID(source);
		assertEquals("homeButton", result);
	}
	
	

}
