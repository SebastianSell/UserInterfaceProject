package com.example.finalUI.model;

import javafx.beans.property.StringProperty;


/**
 * course code: cst8412
 *
 *
 * Represents a task in the Task Manager application.
 * Each task contains information about the task name,
 * difficulty, assigned member, status, due date, creation
 * date, and optional notes.
 *
 * This class acts as the model for the Task TableView.
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */

public class Task {

    private StringProperty taskName;
    private StringProperty taskDifficulty;
    private StringProperty memberAssigned;
    private StringProperty status;
    private StringProperty dueDate;
    private StringProperty createdDate;
    private StringProperty taskNotes;
    /**
     * Creates a new Task object.
     *
     * @param taskName name of the task
     * @param taskDifficulty difficulty level of the task
     * @param memberAssigned person responsible for the task
     * @param status current status of the task
     * @param dueDate due date of the task
     * @param createdDate date the task was created
     * @param taskNotes additional notes about the task
     */
    public Task(StringProperty taskName, StringProperty taskDifficulty, StringProperty memberAssigned, StringProperty status, StringProperty dueDate, StringProperty createdDate, StringProperty taskNotes) {
        this.taskName = taskName;
        this.taskDifficulty = taskDifficulty;
        this.memberAssigned = memberAssigned;
        this.status = status;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.taskNotes = taskNotes;
    }

    /** Getter for task name property */
    public String getTaskName() {
        return taskName.get();
    }
    /** Returns task name property*/
    public StringProperty taskNameProperty() {
        return taskName;
    }
    /** Setter for task name property
     @param taskName */
    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }
    /** Getter for task difficulty property */
    public String getTaskDifficulty() {
        return taskDifficulty.get();
    }
    /** Returns task difficulty property*/
    public StringProperty taskDifficultyProperty() {
        return taskDifficulty;
    }
    /** Setter for task name property
     @param taskDifficulty */
    public void setTaskDifficulty(String taskDifficulty) {
        this.taskDifficulty.set(taskDifficulty);
    }
    /** getter for member assigned property*/
    public String getMemberAssigned() {
        return memberAssigned.get();
    }
    /** Returns member assigned property*/
    public StringProperty memberAssignedProperty() {
        return memberAssigned;
    }
    /** Setter for member Assigned property
     @param memberAssigned */
    public void setMemberAssigned(String memberAssigned) {
        this.memberAssigned.set(memberAssigned);
    }
    /** getter for status property*/
    public String getStatus() {
        return status.get();
    }
    /** Returns status property*/
    public StringProperty statusProperty() {
        return status;
    }
    /** Setter for status property
     @param status */
    public void setStatus(String status) {
        this.status.set(status);
    }
    /** getter for due date property*/
    public String getDueDate() {
        return dueDate.get();
    }
    /** Returns due date property*/
    public StringProperty dueDateProperty() {
        return dueDate;
    }
    /** Setter for due date property
     @param dueDate */
    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }
    /** getter for created date property*/
    public String getCreatedDate() {
        return createdDate.get();
    }
    /** Returns created date property*/
    public StringProperty createdDateProperty() {
        return createdDate;
    }
    /** Setter for created date property
     @param createdDate */
    public void setCreatedDate(String createdDate) {
        this.createdDate.set(createdDate);
    }
    /** getter for task notes property*/
    public String getTaskNotes() {
        return taskNotes.get();
    }
    /** Returns task notes property*/
    public StringProperty taskNotesProperty() {
        return taskNotes;
    }
    /** Setter for task notes property
     @param taskNotes */
    public void setTaskNotes(String taskNotes) {
        this.taskNotes.set(taskNotes);
    }
}
