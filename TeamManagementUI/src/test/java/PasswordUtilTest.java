import com.example.finalUI.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * course code: cst8412
 *
 *
 * Test file for the PasswordUtil class.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class PasswordUtilTest {
    /**
     * Verifies that hashing a password returns a non-null value.
     */
    @Test
    void testHashPasswordNotNull() {

        String result = PasswordUtil.hashPassword("mypassword");

        assertNotNull(result);
    }
    /**
     * Tests that the hashed password is different from the original password.
     */
    @Test
    void testHashPasswordDifferentFromOriginal() {

        String password = "mypassword";
        String hash = PasswordUtil.hashPassword(password);

        assertNotEquals(password, hash);
    }
    /**
     * Tests that hashing the same password produces the same hash value.
     */
    @Test
    void testSamePasswordSameHash() {

        String hash1 = PasswordUtil.hashPassword("mypassword");
        String hash2 = PasswordUtil.hashPassword("mypassword");

        assertEquals(hash1, hash2);
    }
}