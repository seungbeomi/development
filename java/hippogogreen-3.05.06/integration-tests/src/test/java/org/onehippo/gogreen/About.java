package org.onehippo.gogreen;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class About extends GoGreenTest {

	@Test
    public void About() throws Exception {
        selenium.setTimeout("60000");
		selenium.open("/site/about");
		assertTrue(selenium.isTextPresent(""));
		assertEquals("Hippo Go Green - About", selenium.getTitle());
		assertTrue(selenium.isElementPresent("link=Home"));
		assertTrue(selenium.isElementPresent("link=News & Events"));
		assertTrue(selenium.isElementPresent("link=Jobs"));
		assertTrue(selenium.isElementPresent("link=Products"));
		assertTrue(selenium.isElementPresent("link=About"));
		assertTrue(selenium.isElementPresent("left"));
		assertTrue(selenium.isElementPresent("//div[@id='article']/p"));
		assertTrue(selenium.isElementPresent("//img[@alt='onehippo.com']"));
		assertTrue(selenium.isElementPresent("link=Terms & Conditions"));
		assertTrue(selenium.isTextPresent("Hippo © 2010-2012"));
		assertEquals("SERVICE", selenium.getText("//div[@id='ft-nav']/div[1]/h3"));
		assertTrue(selenium.isElementPresent("link=Contact"));
		assertTrue(selenium.isElementPresent("link=FAQ"));
		assertTrue(selenium.isElementPresent("link=RSS"));
		assertTrue(selenium.isElementPresent("link=Sitemap"));
		assertTrue(selenium.isElementPresent("link=API"));
		assertTrue(selenium.isElementPresent("link=Contact"));
		assertEquals("SECTIONS", selenium.getText("//div[@id='ft-nav']/div[2]/h3"));
		assertTrue(selenium.isElementPresent("link=News & Events"));
		assertTrue(selenium.isElementPresent("link=Jobs"));
		assertTrue(selenium.isElementPresent("link=Products"));
		assertTrue(selenium.isElementPresent("link=About"));
		assertTrue(selenium.isElementPresent("link=Mobile"));
		assertTrue(selenium.isElementPresent("ft-disclaimer"));
	}
}
