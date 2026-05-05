package org.example.lab_3_6;

public class SessionState implements StudentState {

    @Override
    public void apply(StudentCharacter student) {
        student.setSadView();
    }
}
