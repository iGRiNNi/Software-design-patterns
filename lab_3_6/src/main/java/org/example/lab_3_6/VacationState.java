package org.example.lab_3_6;

public class VacationState implements StudentState {

    @Override
    public void apply(StudentCharacter student) {
        student.setHappyView();
    }
}
