

import com.example.finalUI.util.Session;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

    @Test
    void testSetUserId() {

        Session.currentUserId = 5;

        assertEquals(5, Session.currentUserId);
    }

    @Test
    void testUpdateUserId() {

        Session.currentUserId = 3;

        Session.currentUserId = 7;

        assertEquals(7, Session.currentUserId);
    }

    @Test
    void testResetUserId() {

        Session.currentUserId = 10;

        Session.currentUserId = 0;

        assertEquals(0, Session.currentUserId);
    }
}