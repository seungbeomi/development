/**
 * Copyright (C) 2011 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onehippo.gogreen;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SimpleTest extends GoGreenTest {

	@Test
    public void simpleTest() throws Exception {
        selenium.setTimeout("60000");
		selenium.open("/site/");
		assertTrue(selenium.isTextPresent(""));
		selenium.click("link=News & Events");
		selenium.waitForPageToLoad("300000");
		assertEquals(selenium.getTitle(), "Hippo Go Green - News Overview");
		assertTrue(selenium.isTextPresent("SERVICE"));
		assertTrue(selenium.isTextPresent("Contact"));
		assertTrue(selenium.isTextPresent("FAQ"));
		assertTrue(selenium.isTextPresent("RSS"));
		assertTrue(selenium.isTextPresent("Sitemap"));
		assertTrue(selenium.isTextPresent("API"));
		assertEquals(selenium.getText("link=Sitemap"), "Sitemap");
		assertEquals(selenium.getText("link=Contact"), "Contact");
		assertEquals(selenium.getText("link=FAQ"), "FAQ");
		assertEquals(selenium.getText("link=RSS"), "RSS");
		assertEquals(selenium.getText("link=API"), "API");
	}
}
