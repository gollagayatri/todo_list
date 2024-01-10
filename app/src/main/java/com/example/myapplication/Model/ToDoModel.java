package com.example.myapplication.Model;

public class ToDoModel {
    private int id,status;
    private String task;
    //private String dueDate;
    private String priority;
    private String taskName;
    // private String dueDate;

    public int getId() {
        return id;
    }
    ///public String getDueDate(){
        //return dueDate;
    //}
   // public void setDueDate(String dueDate){
        //this.dueDate=dueDate;
    //}
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }



}
