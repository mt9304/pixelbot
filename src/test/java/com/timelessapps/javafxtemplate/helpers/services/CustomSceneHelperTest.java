package test.java.com.timelessapps.javafxtemplate.helpers.services;

import static org.junit.Assert.*;
import org.junit.Test;

import main.java.com.timelessapps.javafxtemplate.helpers.services.CustomSceneHelper;

public class CustomSceneHelperTest 
{
	CustomSceneHelper sceneHelper = new CustomSceneHelper();
	
	/** This section tests buttons found in the LeftMenuPane for changing pages. **/
	@Test
	public void getSourceID_homeButton() 
	{
		Object source = "Button[id=homeButton, styleClass=button leftPaneButton]'Home'";
		String result = sceneHelper.getSourceID(source);
		assertEquals("homeButton", result);
	}
	
	@Test
	public void getSourceID_applicationButton() 
	{
		Object source = "Button[id=applicationButton, styleClass=button leftPaneButton]'Application'";
		String result = sceneHelper.getSourceID(source);
		assertEquals("applicationButton", result);
	}
	
	@Test
	public void getSourceID_apiDatabaseButton() 
	{
		Object source = "Button[id=apiDatabaseButton, styleClass=button leftPaneButton]'Api/Database'";
		String result = sceneHelper.getSourceID(source);
		assertEquals("apiDatabaseButton", result);
	}
	
	@Test
	public void getSourceID_generalSettingsButton() 
	{
		Object source = "Button[id=generalSettingsButton, styleClass=button leftPaneButton]'General Settings";
		String result = sceneHelper.getSourceID(source);
		assertEquals("generalSettingsButton", result);
	}
	
	@Test
	public void getSourceID_logsButton() 
	{
		Object source = "Button[id=logsButton, styleClass=button leftPaneButton]'Logs'";
		String result = sceneHelper.getSourceID(source);
		assertEquals("logsButton", result);
	}
	
	/** **/

	/** This section for testing source name of default menu items. **/
	@Test
	public void getSourceName_homeButton()
	{
		Object source = "Button[id=homeButton, styleClass=button leftPaneButton]'Home'";
		String results = sceneHelper.getSourceName(source);
		assertEquals("Home", results);
	}
	
	@Test
	public void getSourceName_applicationButton()
	{
		Object source = "Button[id=applicationButton, styleClass=button leftPaneButton]'Application'";
		String results = sceneHelper.getSourceName(source);
		assertEquals("Application", results);
	}
	
	@Test
	public void getSourceName_apiDatabaseButton()
	{
		Object source = "Button[id=apiDatabaseButton, styleClass=button leftPaneButton]'Api/Database'";
		String results = sceneHelper.getSourceName(source);
		assertEquals("Api/Database", results);
	}
	
	@Test
	public void getSourceName_generalSettingsButton()
	{
		Object source = "Button[id=generalSettingsButton, styleClass=button leftPaneButton]'General Settings";
		String results = sceneHelper.getSourceName(source);
		assertEquals("General Settings", results);
	}
	
	@Test
	public void getSourceName_logsButton()
	{
		Object source = "Button[id=logsButton, styleClass=button leftPaneButton]'Logs'";
		String results = sceneHelper.getSourceName(source);
		assertEquals("Logs", results);
	}
	/** **/
	
	@Test
	public void convertNameToID_home()
	{
		String nodeName = "Home";
		String suffix = "Page";
		String results = sceneHelper.convertNameToID(nodeName, suffix);
		assertEquals("homePage", results);
	}
	
	@Test
	public void convertNameToID_apiDatabase_symbols()
	{
		String nodeName = "Api/Database";
		String suffix = "Page";
		String results = sceneHelper.convertNameToID(nodeName, suffix);
		assertEquals("apiDatabasePage", results);
	}
	
	@Test
	public void convertNameToID_generalSettings_spaces()
	{
		String nodeName = "General Settings";
		String suffix = "Button";
		String results = sceneHelper.convertNameToID(nodeName, suffix);
		assertEquals("generalSettingsButton", results);
	}
}
