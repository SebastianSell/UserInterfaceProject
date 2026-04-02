package com.example.assigment2;

import com.example.finalUI.util.Session;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

    @Test
    void testSetUser() {

        Session.setUser("admin");

        assertEquals("admin", Session.getUser());
    }

    @Test
    void testClearSession() {

        Session.setUser("admin");

        Session.clear();

        assertNull(Session.getUser());
    }
}