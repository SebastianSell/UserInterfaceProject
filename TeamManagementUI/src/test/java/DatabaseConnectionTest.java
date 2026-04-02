import com.example.finalUI.database.DatabaseConnection;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @Test
    void testDatabaseConnection() throws Exception {

        Connection conn = DatabaseConnection.getConnection();

        assertNotNull(conn);
        assertFalse(conn.isClosed());
    }
}