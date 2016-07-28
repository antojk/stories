package com.codependent.storyteller;

import static org.junit.Assert.*;


import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringListProperty;
import com.netflix.config.DynamicStringMapProperty;
import com.netflix.config.DynamicStringProperty;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigServerApplication.class)
public class ConfigServerApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Before
	public void setUp() throws Exception{
	    ConfigurationManager.loadCascadedPropertiesFromResources("newconfig");
	}
	
	@Test
	public void testBasicStringProps() {
	    DynamicStringProperty sampleProp = DynamicPropertyFactory.getInstance().getStringProperty("stringprop", "");
	    System.out.println(sampleProp.get());
	    assertEquals(sampleProp.get(), "propvalue");
	}
	/*@Test
	public void testBasicListProps() {
	    DynamicStringListProperty listProperty = new DynamicStringListProperty("listprop", Collections.emptyList());
	    assertThat(listProperty.get(), contains("value1", "value2", "value3"));
	}

	@Test
	public void testBasicMapProps() {
	    DynamicStringMapProperty mapProperty = new DynamicStringMapProperty("mapprop", Collections.emptyMap());
	    assertThat(mapProperty.getMap(), allOf(hasEntry("key1", "value1"), hasEntry("key2", "value2")));
	}*/

	@Test
	public void testBasicLongProperty() {
	    DynamicLongProperty longProp = DynamicPropertyFactory.getInstance().getLongProperty("longprop", 10);
	    System.out.println(longProp.get());
	    assertEquals(longProp.get(),100L);
	}
}
