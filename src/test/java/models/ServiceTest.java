package models;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceTest {
    @Test
    void objectCorrectlyInstantiates() {
        Service service = new Service(500,7,1);
        assertEquals(true, service instanceof Service);
    }
}