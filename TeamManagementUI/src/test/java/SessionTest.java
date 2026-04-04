

import com.example.finalUI.util.Session;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * course code: cst8412
 *
 *
 * Test file for the Session class.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class SessionTest {
    /**Tests if the currentUserId property gets assigned properly */
    @Test
    void testSetUserId() {

        Session.currentUserId = 5;

        assertEquals(5, Session.currentUserId);
    }
    /**Tests if the currentUserId property can update properly */
    @Test
    void testUpdateUserId() {

        Session.currentUserId = 3;

        Session.currentUserId = 7;

        assertEquals(7, Session.currentUserId);
    }
    /**Tests if the currentUserId property get can turn back to 0 with no issue */
    @Test
    void testResetUserId() {

        Session.currentUserId = 10;

        Session.currentUserId = 0;

        assertEquals(0, Session.currentUserId);
    }
}