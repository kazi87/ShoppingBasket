package com.xkzielinski.basket.registry;

import com.xkzielinski.basket.model.Item;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * TODO: Add description...
 */
public class ItemRegistryInMemoryTest {

    private ItemRegistryInMemory testObj;

    @Before
    public void setUp() throws Exception {
        testObj = new ItemRegistryInMemory();
    }

    @Test
    public void shouldReturnItem() throws Exception {
        // given
        // when
        Item item = testObj.getItemByName("apple");
        // then
        assertNotNull(item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectExceptionIfItemDoesNotExistInRegistry() throws Exception {
        // given
        // when
        testObj.getItemByName("unknown");
        // then
    }


}
