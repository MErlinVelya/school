package com.veresklia.entity;

import java.util.Objects;

public class Group {
    private String groupName;
    private int studentsquantity;

    public Group(String group, int studentsquantity) {
        this.groupName = group;
        this.studentsquantity = studentsquantity;
    }

    public Group(String group) {
        this.groupName = group;
    }
    public String getGroupName() {
        return groupName;
    }

    public int getStudentsquantity() {
        return studentsquantity;
    }

    public void setStudentsquantity(int quantity) {
        studentsquantity = quantity;
    }

    public void reduceStudentsquantity() {
        studentsquantity--;
    }

    @Override
    public String toString() {
        return groupName + " " + studentsquantity + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Group)){
            return false;
        }
        Group group = (Group) o;
        return getStudentsquantity() == group.getStudentsquantity() &&
            getGroupName().equals(group.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName(), getStudentsquantity());
    }
}
