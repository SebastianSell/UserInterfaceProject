package com.example.assigment2;

import javafx.beans.property.StringProperty;


/**
 * Name: Sebastian Sell, Luca Beumer, Bennet Ireland
 * Student number: 041147547,
 * course code: cst8412
 * assignment name: Assignment2
 * */

/** Task class for a record template for the CRUD application*/
public class Task {

    private StringProperty taskName;
    private StringProperty taskDifficulty;
    private StringProperty memberAssigned;
    private StringProperty status;
    private StringProperty dueDate;
    private StringProperty createdDate;
    private StringProperty taskNotes;

    public Task(StringProperty taskName, StringProperty taskDifficulty, StringProperty memberAssigned, StringProperty status, StringProperty dueDate, StringProperty createdDate, StringProperty taskNotes) {
        this.taskName = taskName;
        this.taskDifficulty = taskDifficulty;
        this.memberAssigned = memberAssigned;
        this.status = status;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.taskNotes = taskNotes;
    }


    public String getTaskName() {
        return taskName.get();
    }

    public StringProperty taskNameProperty() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public String getTaskDifficulty() {
        return taskDifficulty.get();
    }

    public StringProperty taskDifficultyProperty() {
        return taskDifficulty;
    }

    public void setTaskDifficulty(String taskDifficulty) {
        this.taskDifficulty.set(taskDifficulty);
    }

    public String getMemberAssigned() {
        return memberAssigned.get();
    }

    public StringProperty memberAssignedProperty() {
        return memberAssigned;
    }

    public void setMemberAssigned(String memberAssigned) {
        this.memberAssigned.set(memberAssigned);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public StringProperty dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    public String getCreatedDate() {
        return createdDate.get();
    }

    public StringProperty createdDateProperty() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate.set(createdDate);
    }

    public String getTaskNotes() {
        return taskNotes.get();
    }

    public StringProperty taskNotesProperty() {
        return taskNotes;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes.set(taskNotes);
    }
}
