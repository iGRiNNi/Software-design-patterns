package org.example.lab_2_3;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RoadFacade {
    private final Pane root;
    private final TrafficLight trafficLight;
    private final List<RoadVehicle> vehicles = new ArrayList<>();

    private final double sceneWidth;

    private final double roadY = 120;
    private final double roadHeight = 260;
    private final double laneHeight = roadHeight / 4.0;

    private final double shoulderHeight = 24;
    private final double dividerTopY = roadY + 2 * laneHeight - 8;
    private final double dividerBottomY = roadY + 2 * laneHeight + 8;

    private final double stopLineX;
    private final double oppositeStopLineX;

    private final double crossingX;
    private final double crossingWidth = 90;

    private final Timeline trafficLightTimeline;
    private final AnimationTimer animationTimer;

    public RoadFacade(double width, double height) {
        this.sceneWidth = width;

        this.stopLineX = width - 320;
        this.crossingX = stopLineX + 22;
        this.oppositeStopLineX = crossingX + crossingWidth + 22;

        root = new Pane();
        root.setPrefSize(width, height);

        buildBackground(width, height);
        drawRoad(width);
        drawMarkings(width);
        drawPedestrianCrossing();

        createVehicles();

        trafficLight = new TrafficLight(
                oppositeStopLineX + 26,
                roadY + roadHeight + shoulderHeight + 26
        );

        Line stopLineForward = new Line(stopLineX, dividerBottomY, stopLineX, roadY + roadHeight);
        stopLineForward.setStroke(Color.WHITE);
        stopLineForward.setStrokeWidth(7);

        Line stopLineOpposite = new Line(oppositeStopLineX, roadY, oppositeStopLineX, dividerTopY);
        stopLineOpposite.setStroke(Color.WHITE);
        stopLineOpposite.setStrokeWidth(7);

        root.getChildren().addAll(stopLineForward, stopLineOpposite);

        for (RoadVehicle vehicle : vehicles) {
            root.getChildren().add(vehicle.car.getView());
        }

        root.getChildren().add(trafficLight.getView());

        trafficLightTimeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> trafficLight.nextState())
        );
        trafficLightTimeline.setCycleCount(Timeline.INDEFINITE);

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateVehicles();
            }
        };
    }

    public Pane getView() {
        return root;
    }

    public void start() {
        trafficLightTimeline.play();
        animationTimer.start();
    }

    private void createVehicles() {
        vehicles.add(new RoadVehicle(
                new Car("/car_red.png", -180, getLaneCenterY(3), true, 150),
                1.8,
                true
        ));

        vehicles.add(new RoadVehicle(
                new Car("/car2.png", -520, getLaneCenterY(4), true, 145),
                2.05,
                true
        ));

        vehicles.add(new RoadVehicle(
                new Car("/car13.png", sceneWidth + 120, getLaneCenterY(1), false, 142),
                1.6,
                false
        ));

        vehicles.add(new RoadVehicle(
                new Car("/car11.png", sceneWidth + 420, getLaneCenterY(2) - 12, false, 142),
                1.95,
                false
        ));
    }

    private void buildBackground(double width, double height) {
        Rectangle topSidewalk = new Rectangle(0, 0, width, roadY - shoulderHeight);
        topSidewalk.setFill(Color.web("#CFCFCF"));

        Rectangle topShoulder = new Rectangle(0, roadY - shoulderHeight, width, shoulderHeight);
        topShoulder.setFill(Color.web("#8D857B"));

        Rectangle road = new Rectangle(0, roadY, width, roadHeight);
        road.setFill(Color.web("#4B4B4B"));

        Rectangle bottomShoulder = new Rectangle(0, roadY + roadHeight, width, shoulderHeight);
        bottomShoulder.setFill(Color.web("#8D857B"));

        Rectangle bottomSidewalk = new Rectangle(
                0,
                roadY + roadHeight + shoulderHeight,
                width,
                height - (roadY + roadHeight + shoulderHeight)
        );
        bottomSidewalk.setFill(Color.web("#CFCFCF"));

        root.getChildren().addAll(topSidewalk, topShoulder, road, bottomShoulder, bottomSidewalk);

        drawTiles(0, 0, width, roadY - shoulderHeight);
        drawTiles(0, roadY + roadHeight + shoulderHeight, width, height - (roadY + roadHeight + shoulderHeight));
    }

    private void drawTiles(double startX, double startY, double width, double height) {
        double tileW = 56;
        double tileH = 36;

        for (double y = startY; y < startY + height; y += tileH) {
            double offset = ((int) ((y - startY) / tileH) % 2 == 0) ? 0 : tileW / 2.0;

            for (double x = startX - offset; x < startX + width; x += tileW) {
                Rectangle tile = new Rectangle(x, y, tileW - 2, tileH - 2);
                tile.setFill(Color.web("#D9D9D9"));
                tile.setStroke(Color.web("#B8B8B8"));
                tile.setStrokeWidth(1);
                root.getChildren().add(tile);
            }
        }
    }

    private void drawRoad(double width) {
        Rectangle topBorder = new Rectangle(0, roadY, width, 4);
        topBorder.setFill(Color.WHITE);

        Rectangle bottomBorder = new Rectangle(0, roadY + roadHeight - 4, width, 4);
        bottomBorder.setFill(Color.WHITE);

        Rectangle divider1 = new Rectangle(0, dividerTopY, width, 4);
        divider1.setFill(Color.GOLD);

        Rectangle divider2 = new Rectangle(0, dividerBottomY - 4, width, 4);
        divider2.setFill(Color.GOLD);

        root.getChildren().addAll(topBorder, bottomBorder, divider1, divider2);
    }

    private void drawMarkings(double width) {
        double upperDashedY = roadY + laneHeight;
        double lowerDashedY = roadY + 3 * laneHeight;

        for (int x = 40; x < width; x += 120) {
            Rectangle dash1 = new Rectangle(x, upperDashedY - 4, 78, 8);
            dash1.setFill(Color.WHITE);

            Rectangle dash2 = new Rectangle(x, lowerDashedY - 4, 78, 8);
            dash2.setFill(Color.WHITE);

            root.getChildren().addAll(dash1, dash2);
        }
    }

    private void drawPedestrianCrossing() {
        double stripeHeight = 12;
        double stripeGap = 10;

        double startY = roadY + 8;
        double endYTop = dividerTopY - 10;
        double startYBottom = dividerBottomY + 6;
        double endYBottom = roadY + roadHeight - 8;

        for (double y = startY; y < endYTop; y += stripeHeight + stripeGap) {
            Rectangle stripe = new Rectangle(crossingX, y, crossingWidth, stripeHeight);
            stripe.setFill(Color.WHITE);
            root.getChildren().add(stripe);
        }

        for (double y = startYBottom; y < endYBottom; y += stripeHeight + stripeGap) {
            Rectangle stripe = new Rectangle(crossingX, y, crossingWidth, stripeHeight);
            stripe.setFill(Color.WHITE);
            root.getChildren().add(stripe);
        }
    }

    private double getLaneCenterY(int laneNumberFromTop) {
        return roadY + (laneNumberFromTop - 1) * laneHeight + laneHeight / 2.0;
    }

    private void updateVehicles() {
        boolean stopSignal = trafficLight.isRed() || trafficLight.isYellow();

        for (RoadVehicle vehicle : vehicles) {
            if (vehicle.moveRight) {
                updateRightMovingVehicle(vehicle, stopSignal);
            } else {
                updateLeftMovingVehicle(vehicle, stopSignal);
            }
        }
    }

    private void updateRightMovingVehicle(RoadVehicle vehicle, boolean stopSignal) {
        double frontX = vehicle.car.getX() + vehicle.car.getWidth();
        boolean beforeStopLine = frontX < stopLineX;
        boolean crossesStopLineNow = frontX + vehicle.speed >= stopLineX;

        if (stopSignal && beforeStopLine && crossesStopLineNow) {
            return;
        }

        vehicle.car.move(vehicle.speed);

        if (vehicle.car.getX() > sceneWidth + 60) {
            double offset = ThreadLocalRandom.current().nextDouble(120, 420);
            vehicle.car.setX(-vehicle.car.getWidth() - offset);
        }
    }

    private void updateLeftMovingVehicle(RoadVehicle vehicle, boolean stopSignal) {
        double leftEdgeX = vehicle.car.getX();
        boolean beforeStopLine = leftEdgeX > oppositeStopLineX;
        boolean crossesStopLineNow = leftEdgeX - vehicle.speed <= oppositeStopLineX;

        if (stopSignal && beforeStopLine && crossesStopLineNow) {
            return;
        }

        vehicle.car.move(-vehicle.speed);

        if (vehicle.car.getX() + vehicle.car.getWidth() < -60) {
            double offset = ThreadLocalRandom.current().nextDouble(120, 420);
            vehicle.car.setX(sceneWidth + offset);
        }
    }

    private static class RoadVehicle {
        private final Car car;
        private final double speed;
        private final boolean moveRight;

        private RoadVehicle(Car car, double speed, boolean moveRight) {
            this.car = car;
            this.speed = speed;
            this.moveRight = moveRight;
        }
    }
}