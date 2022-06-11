package com.monsters.util;

import java.time.LocalDate;

public class Entry {
    private LocalDate date;
    private String project;
    private String taskName;
    private Double duration;
    private String User;

    public Entry(LocalDate date, String project, String taskName, Double duration, String user) {
        this.date = date;
        this.project = project;
        this.taskName = taskName;
        this.duration = duration;
        User = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "date=" + date +
                ", project='" + project + '\'' +
                ", taskName='" + taskName + '\'' +
                ", duration=" + duration +
                ", User='" + User + '\'' +
                '}';
    }
}
