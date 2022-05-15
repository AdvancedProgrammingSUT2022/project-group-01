package model.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ResourceListTest {

    private ResourceList resourceListUnderTest;

    @BeforeEach
    void setUp() {
        resourceListUnderTest = new ResourceList(ResourceType.BANANAS);
    }

//    @Test
//    void testIsResearched() {
//        assertTrue(resourceListUnderTest.isAvailable());
//    }
}
