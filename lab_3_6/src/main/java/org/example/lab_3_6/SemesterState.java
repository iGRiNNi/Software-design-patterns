package org.example.lab_3_6;

public class SemesterState implements StudentState {

    @Override
    public void apply(StudentCharacter student) {
        student.setSleepView();
    }
}