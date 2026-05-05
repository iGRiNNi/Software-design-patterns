package org.example.lab_3_6;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StudentCharacter extends Pane {
    private StudentState state;

    private final Circle head;

    private final Circle leftEye;
    private final Circle rightEye;

    private final Line leftClosedEye;
    private final Line rightClosedEye;

    private final Line leftEyebrow;
    private final Line rightEyebrow;

    private final Arc smileMouth;
    private final Arc sadMouth;
    private final Line neutralMouth;

    private final Line body;
    private final Line leftArm;
    private final Line rightArm;
    private final Line leftLeg;
    private final Line rightLeg;

    private final Text stateText;
    private final Text sleepText;

    public StudentCharacter() {
        setPrefSize(600, 420);
        setStyle("-fx-background-color: #F4F4F4;");

        // Голова
        head = new Circle(300, 120, 55);
        head.setFill(Color.web("#FFE0B2"));
        head.setStroke(Color.BLACK);
        head.setStrokeWidth(3);

        // Открытые глаза
        leftEye = new Circle(280, 110, 6);
        leftEye.setFill(Color.BLACK);

        rightEye = new Circle(320, 110, 6);
        rightEye.setFill(Color.BLACK);

        // Закрытые глаза
        leftClosedEye = new Line(270, 110, 290, 110);
        leftClosedEye.setStroke(Color.BLACK);
        leftClosedEye.setStrokeWidth(4);

        rightClosedEye = new Line(310, 110, 330, 110);
        rightClosedEye.setStroke(Color.BLACK);
        rightClosedEye.setStrokeWidth(4);

        // Брови для тревожного состояния
        leftEyebrow = new Line(268, 92, 292, 100);
        leftEyebrow.setStroke(Color.BLACK);
        leftEyebrow.setStrokeWidth(3);

        rightEyebrow = new Line(308, 100, 332, 92);
        rightEyebrow.setStroke(Color.BLACK);
        rightEyebrow.setStrokeWidth(3);

        // Весёлый рот
        smileMouth = new Arc(300, 130, 30, 25, 200, 140);
        smileMouth.setType(ArcType.OPEN);
        smileMouth.setFill(Color.TRANSPARENT);
        smileMouth.setStroke(Color.BLACK);
        smileMouth.setStrokeWidth(4);

        // Грустный рот
        sadMouth = new Arc(300, 155, 30, 25, 20, 140);
        sadMouth.setType(ArcType.OPEN);
        sadMouth.setFill(Color.TRANSPARENT);
        sadMouth.setStroke(Color.BLACK);
        sadMouth.setStrokeWidth(4);

        // Нейтральный рот
        neutralMouth = new Line(285, 140, 315, 140);
        neutralMouth.setStroke(Color.BLACK);
        neutralMouth.setStrokeWidth(4);

        // Тело
        body = new Line(300, 175, 300, 295);
        body.setStroke(Color.BLACK);
        body.setStrokeWidth(5);

        // Руки
        leftArm = new Line(300, 210, 245, 255);
        leftArm.setStroke(Color.BLACK);
        leftArm.setStrokeWidth(5);

        rightArm = new Line(300, 210, 355, 255);
        rightArm.setStroke(Color.BLACK);
        rightArm.setStrokeWidth(5);

        // Ноги
        leftLeg = new Line(300, 295, 255, 360);
        leftLeg.setStroke(Color.BLACK);
        leftLeg.setStrokeWidth(5);

        rightLeg = new Line(300, 295, 345, 360);
        rightLeg.setStroke(Color.BLACK);
        rightLeg.setStrokeWidth(5);

        // Текст состояния
        stateText = new Text(145, 400, "");
        stateText.setFont(Font.font(24));
        stateText.setFill(Color.web("#333333"));

        // Надпись сна
        sleepText = new Text(120, 210, "Zzz...");
        sleepText.setFont(Font.font(28));
        sleepText.setFill(Color.web("#4444AA"));

        getChildren().addAll(
                body,
                leftArm,
                rightArm,
                leftLeg,
                rightLeg,
                head,
                leftEye,
                rightEye,
                leftClosedEye,
                rightClosedEye,
                leftEyebrow,
                rightEyebrow,
                smileMouth,
                sadMouth,
                neutralMouth,
                stateText,
                sleepText
        );
    }

    public void setState(StudentState newState) {
        if (newState == null) {
            throw new IllegalArgumentException("State cannot be null");
        }

        if (state != null && state.getClass() == newState.getClass()) {
            return;
        }

        this.state = newState;
        this.state.apply(this);
    }

    public void setNormalView() {
        setStandingPose();

        showOpenEyes();
        showNeutralMouth();

        leftEyebrow.setVisible(false);
        rightEyebrow.setVisible(false);
        sleepText.setVisible(false);

        head.setFill(Color.web("#FFE0B2"));
    }

    public void setHappyView() {
        setStandingPose();

        showOpenEyes();
        showSmile();

        leftEyebrow.setVisible(false);
        rightEyebrow.setVisible(false);
        sleepText.setVisible(false);

        // Руки подняты вверх
        leftArm.setStartX(300);
        leftArm.setStartY(210);
        leftArm.setEndX(235);
        leftArm.setEndY(150);

        rightArm.setStartX(300);
        rightArm.setStartY(210);
        rightArm.setEndX(365);
        rightArm.setEndY(150);

        head.setFill(Color.web("#FFE0B2"));
        stateText.setText("Каникулы: студент радуется!");
    }

    public void setSleepView() {
        setLyingPose();

        showClosedEyes();
        showNeutralMouth();

        leftEyebrow.setVisible(false);
        rightEyebrow.setVisible(false);
        sleepText.setVisible(true);

        head.setFill(Color.web("#FFE0B2"));
        stateText.setText("Семестр: студент лёг и заснул...");
    }

    public void setSadView() {
        setStandingPose();

        showOpenEyes();
        showSadMouth();

        leftEyebrow.setVisible(true);
        rightEyebrow.setVisible(true);
        sleepText.setVisible(false);

        leftArm.setStartX(300);
        leftArm.setStartY(210);
        leftArm.setEndX(250);
        leftArm.setEndY(285);

        rightArm.setStartX(300);
        rightArm.setStartY(210);
        rightArm.setEndX(350);
        rightArm.setEndY(285);

        head.setFill(Color.web("#FFCCBC"));
        stateText.setText("Сессия: студент переживает...");
    }

    private void setStandingPose() {
        // Голова
        head.setCenterX(300);
        head.setCenterY(120);

        // Открытые глаза
        leftEye.setCenterX(280);
        leftEye.setCenterY(110);

        rightEye.setCenterX(320);
        rightEye.setCenterY(110);

        // Закрытые глаза
        setLine(leftClosedEye, 270, 110, 290, 110);
        setLine(rightClosedEye, 310, 110, 330, 110);

        // Брови
        setLine(leftEyebrow, 268, 92, 292, 100);
        setLine(rightEyebrow, 308, 100, 332, 92);

        // Рты
        smileMouth.setCenterX(300);
        smileMouth.setCenterY(130);
        smileMouth.setRotate(0);

        sadMouth.setCenterX(300);
        sadMouth.setCenterY(155);
        sadMouth.setRotate(0);

        setLine(neutralMouth, 285, 140, 315, 140);

        // Тело
        setLine(body, 300, 175, 300, 295);

        // Руки
        setLine(leftArm, 300, 210, 245, 255);
        setLine(rightArm, 300, 210, 355, 255);

        // Ноги
        setLine(leftLeg, 300, 295, 255, 360);
        setLine(rightLeg, 300, 295, 345, 360);

        sleepText.setX(120);
        sleepText.setY(210);
    }

    private void setLyingPose() {

        // Голова справа
        head.setCenterX(415);
        head.setCenterY(270);

        leftEye.setCenterX(405);
        leftEye.setCenterY(258);

        rightEye.setCenterX(405);
        rightEye.setCenterY(282);

        setLine(leftClosedEye, 438, 248, 438, 266);
        setLine(rightClosedEye, 438, 274, 438, 292);

        setLine(leftEyebrow, 392, 248, 399, 268);
        setLine(rightEyebrow, 411, 272, 418, 292);

        setLine(neutralMouth, 405, 258, 405, 282);

        smileMouth.setCenterX(432);
        smileMouth.setCenterY(270);
        smileMouth.setRotate(90);

        sadMouth.setCenterX(448);
        sadMouth.setCenterY(270);
        sadMouth.setRotate(90);

        setLine(body, 360, 270, 210, 270);

        setLine(leftArm, 320, 270, 275, 235);
        setLine(rightArm, 320, 270, 275, 305);

        setLine(leftLeg, 210, 270, 130, 235);
        setLine(rightLeg, 210, 270, 130, 305);

        sleepText.setX(455);
        sleepText.setY(220);
    }

    private void showOpenEyes() {
        leftEye.setVisible(true);
        rightEye.setVisible(true);

        leftClosedEye.setVisible(false);
        rightClosedEye.setVisible(false);
    }

    private void showClosedEyes() {
        leftEye.setVisible(false);
        rightEye.setVisible(false);

        leftClosedEye.setVisible(true);
        rightClosedEye.setVisible(true);
    }

    private void showSmile() {
        smileMouth.setVisible(true);
        sadMouth.setVisible(false);
        neutralMouth.setVisible(false);
    }

    private void showSadMouth() {
        smileMouth.setVisible(false);
        sadMouth.setVisible(true);
        neutralMouth.setVisible(false);
    }

    private void showNeutralMouth() {
        smileMouth.setVisible(false);
        sadMouth.setVisible(false);
        neutralMouth.setVisible(true);
    }

    private void setLine(Line line, double startX, double startY, double endX, double endY) {
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
    }
}