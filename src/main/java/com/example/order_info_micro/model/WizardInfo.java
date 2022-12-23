package com.example.order_info_micro.model;

public class WizardInfo {
    private String id;
    private String name;
    private int age;
    private String joinedDate;
    private boolean active;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "WizardInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", joinedDate='" + joinedDate + '\'' +
                ", active=" + active +
                '}';
    }
}
