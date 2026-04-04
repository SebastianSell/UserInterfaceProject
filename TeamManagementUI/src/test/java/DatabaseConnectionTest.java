import com.example.finalUI.database.DatabaseConnection;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
/**
 * course code: cst8412
 *
 *
 * Test file for the DatabaseConnection class.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class DatabaseConnectionTest {

    /**Tests out if the connection is created properly*/
    @Test
    void testDatabaseConnection() throws Exception {

        Connection conn = DatabaseConnection.getConnection();

        assertNotNull(conn);
        assertFalse(conn.isClosed());
    }
}