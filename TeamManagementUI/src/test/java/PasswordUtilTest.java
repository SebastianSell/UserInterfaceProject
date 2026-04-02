import com.example.finalUI.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilTest {

    @Test
    void testHashPasswordNotNull() {

        String result = PasswordUtil.hashPassword("mypassword");

        assertNotNull(result);
    }

    @Test
    void testHashPasswordDifferentFromOriginal() {

        String password = "mypassword";
        String hash = PasswordUtil.hashPassword(password);

        assertNotEquals(password, hash);
    }

    @Test
    void testSamePasswordSameHash() {

        String hash1 = PasswordUtil.hashPassword("mypassword");
        String hash2 = PasswordUtil.hashPassword("mypassword");

        assertEquals(hash1, hash2);
    }
}