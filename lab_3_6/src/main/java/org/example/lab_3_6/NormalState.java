package org.example.lab_3_6;

public class NormalState implements StudentState {

    @Override
    public void apply(StudentCharacter student) {
        student.setNormalView();
    }
}
