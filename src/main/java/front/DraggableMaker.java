package front;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class DraggableMaker {

    public double getMouseAnchorX() {
        return mouseAnchorX;
    }

    public void setMouseAnchorX(double mouseAnchorX) {
        this.mouseAnchorX = mouseAnchorX;
    }

    public double getMouseAnchorY() {
        return mouseAnchorY;
    }

    public void setMouseAnchorY(double mouseAnchorY) {
        this.mouseAnchorY = mouseAnchorY;
    }

    private double mouseAnchorX;
    private double mouseAnchorY;

    public void makeDraggable(Node node){

        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
//            System.out.println("Node X: " + node.getLayoutX());
//            System.out.println("Node Y: " + node.getLayoutY());
        });
    }
    public void makeDraggable2(Node node) {
        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            double offsetX = mouseEvent.getSceneX() - mouseAnchorX;
            double offsetY = mouseEvent.getSceneY() - mouseAnchorY;

            // עדכון מיקום התמונה על פי הגרירה ולא על פי המיקום המקורי
            node.setLayoutX(node.getLayoutX() + offsetX);
            node.setLayoutY(node.getLayoutY() + offsetY);

            // עדכון מיקום העכבר
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
        });
}
    public void makeDraggableWithAnimation(Node node, double x, double y) {
        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            double offsetX = mouseEvent.getSceneX() - mouseAnchorX;
            double offsetY = mouseEvent.getSceneY() - mouseAnchorY;

            // עדכון מיקום התמונה על פי הגרירה ולא על פי המיקום המקורי
            node.setLayoutX(node.getLayoutX() + offsetX);
            node.setLayoutY(node.getLayoutY() + offsetY);

            // עדכון מיקום העכבר
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
        });

        // הוספת האנימציה
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), node);
        transition.setToX(x);
        transition.setToY(y);

        // אירוע שיתרחש בסיום האנימציה
        transition.setOnFinished(event -> {
            System.out.println("Animation finished");
        });

        // הפעלת הגרירה והאנימציה במקביל
        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
            transition.playFromStart();  // הפעלת האנימציה מההתחלה
        });
    }
    public void makeDraggableWithAnimation(Node node) {
        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            double offsetX = mouseEvent.getSceneX() - mouseAnchorX;
            double offsetY = mouseEvent.getSceneY() - mouseAnchorY;

            // עדכון מיקום התמונה על פי הגרירה ולא על פי המיקום המקורי
            node.setLayoutX(node.getLayoutX() + offsetX);
            node.setLayoutY(node.getLayoutY() + offsetY);

            // עדכון מיקום העכבר
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
        });

        // הוספת האנימציה
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), node);
        transition.setToX(81);
        transition.setToY(219);

        // אירוע שיתרחש בסיום האנימציה
        transition.setOnFinished(event -> {
            System.out.println("Animation finished");
        });

        // הפעלת הגרירה והאנימציה במקביל
        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
            transition.playFromStart();  // הפעלת האנימציה מההתחלה
        });
    }
    public void makeDraggableWithAnimation1(Node node, double x, double y) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), node);
        transition.setToX(x);
        transition.setToY(y);

        // אירוע שיתרחש בסיום האנימציה
        transition.setOnFinished(event -> {
            System.out.println("Animation finished");
        });

        // הפעלת האנימציה
        transition.playFromStart();
    }


}