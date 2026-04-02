
import com.example.finalUI.model.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void testTaskCreation() {

        Task task = new Task(
                "Build UI",
                "Medium",
                "Alice",
                "Open",
                "2026-04-10",
                "2026-04-01",
                "Create dashboard layout"
        );

        assertEquals("Build UI", task.getTaskName());
        assertEquals("Medium", task.getTaskDifficulty());
        assertEquals("Alice", task.getMemberAssigned());
        assertEquals("Open", task.getStatus());
        assertEquals("2026-04-10", task.getDueDate());
        assertEquals("2026-04-01", task.getCreatedDate());
        assertEquals("Create dashboard layout", task.getTaskNotes());
    }

    @Test
    void testSetTaskName() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setTaskName("Updated Task");

        assertEquals("Updated Task", task.getTaskName());
    }

    @Test
    void testSetDifficulty() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setTaskDifficulty("Hard");

        assertEquals("Hard", task.getTaskDifficulty());
    }

    @Test
    void testSetMemberAssigned() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setMemberAssigned("Charlie");

        assertEquals("Charlie", task.getMemberAssigned());
    }

    @Test
    void testSetStatus() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setStatus("Completed");

        assertEquals("Completed", task.getStatus());
    }

    @Test
    void testSetDueDate() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setDueDate("2026-05-01");

        assertEquals("2026-05-01", task.getDueDate());
    }

    @Test
    void testSetNotes() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setTaskNotes("Updated notes");

        assertEquals("Updated notes", task.getTaskNotes());
    }
}