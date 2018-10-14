package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise18_19 extends Application {
    int newOrder = 0;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        //create trianglePane and initial setting
        SierpinskiTrianglePane trianglePane = new SierpinskiTrianglePane();

        //create + button
        Button btIncrease = new Button("+");
        btIncrease.setOnAction(e -> {
            increase();
            trianglePane.setOrder(newOrder);
        });

        //create - button
        Button btDecrease = new Button("-");
        btDecrease.setOnAction(e -> {
            decrease();
            trianglePane.setOrder(newOrder);
        });

        // Pane to hold buttons
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("Orders: "), btIncrease, btDecrease);
        hBox.setAlignment(Pos.CENTER);

        // create border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(trianglePane);
        borderPane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 400, 410);
        primaryStage.setTitle("SierpinskiTriangle"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // create listeners to paint scene
        trianglePane.setOrder(newOrder);  //initialize scene
        scene.widthProperty().addListener(ov -> trianglePane.paint());
        scene.heightProperty().addListener(ov -> trianglePane.paint());
    }

    //  method to increase newOrder

    public void increase() {
        newOrder++;
    }

    //  method to decrease newOrder
    public void decrease() {
        newOrder--;
        if (newOrder == 0) {
            System.out.println("0 is the minimum");
        } else if (newOrder < 0) {
            newOrder = 0;
            System.out.println("0 is the minimum");
        } else {
        }
    }

    //Pane for displaying triangles

    static class SierpinskiTrianglePane extends Pane {
        private int order = 0;

        // Set a new order
        public void setOrder(int order) {
            this.order = order;
            paint();
        }

        protected void paint() {
            // Select three points in proportion to the panel size
            Point2D p1 = new Point2D(getWidth() / 2, 10);
            Point2D p2 = new Point2D(10, getHeight() - 10);
            Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

            this.getChildren().clear(); // Clear the pane before redisplay

            displayTriangles(order, p1, p2, p3);
        }

        private void displayTriangles(int order, Point2D p1,
                                      Point2D p2, Point2D p3) {
            if (order == 0) {
                // Draw a triangle to connect three points
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(),
                        p2.getY(), p3.getX(), p3.getY());
                triangle.setStroke(Color.BLACK);
                triangle.setFill(Color.WHITE);

                this.getChildren().add(triangle);
            } else {
                // Get the midpoint on each edge in the triangle
                Point2D p12 = p1.midpoint(p2);
                Point2D p23 = p2.midpoint(p3);
                Point2D p31 = p3.midpoint(p1);

                // Recursively display three triangles
                displayTriangles(order - 1, p1, p12, p31);
                displayTriangles(order - 1, p12, p2, p23);
                displayTriangles(order - 1, p31, p23, p3);
            }
        }
    }


    /**  main method is only needed for the IDE with limited */
    public static void main(String[] args) {
        launch(args);
    }

}
