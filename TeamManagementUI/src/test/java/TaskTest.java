
import com.example.finalUI.model.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Task class.
 *
 * These tests verify that task properties are correctly set
 * and updated through the constructor and setter methods.
 */
public class TaskTest {

    /**
     * Tests that the Task constructor correctly initializes all fields.
     */
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

    /**
     * Tests that the task name can be updated.
     */
    @Test
    void testSetTaskName() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setTaskName("Updated Task");

        assertEquals("Updated Task", task.getTaskName());
    }
    /**
     * Tests that the task difficulty can be updated.
     */
    @Test
    void testSetDifficulty() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setTaskDifficulty("Hard");

        assertEquals("Hard", task.getTaskDifficulty());
    }
    /**
     * Tests that the member assigned field can be updated.
     */
    @Test
    void testSetMemberAssigned() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setMemberAssigned("Charlie");

        assertEquals("Charlie", task.getMemberAssigned());
    }
    /**
     * Tests that the task status can be updated.
     */
    @Test
    void testSetStatus() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setStatus("Completed");

        assertEquals("Completed", task.getStatus());
    }
    /**
     * Tests that the task due date can be updated.
     */
    @Test
    void testSetDueDate() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setDueDate("2026-05-01");

        assertEquals("2026-05-01", task.getDueDate());
    }
    /**
     * Tests that the task notes can be updated.
     */
    @Test
    void testSetNotes() {

        Task task = new Task("Task1","Easy","Bob","Open","2026-04-10","2026-04-01","Notes");

        task.setTaskNotes("Updated notes");

        assertEquals("Updated notes", task.getTaskNotes());
    }
}