package front;

import Logic.Deck;
import Logic.Tile;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML
    private ImageView board_left;

    @FXML
    private ImageView board_right;

    @FXML
    private ImageView board_up;

    @FXML
    private ImageView image;

    @FXML
    private ImageView oneY;

    @FXML
    private ImageView tryc;
    @FXML
    private ImageView deckI;
    //FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

    @FXML
    private AnchorPane the_game=new AnchorPane();
    @FXML
    private ImageView mainBoard;
    @FXML
    private Button welcomeText;
    Deck deck=new Deck();
    Stage stage;
    Scene_Manager sceneManager;

    DraggableMaker draggableMaker = new DraggableMaker();
    Map<Tile,ImageView> imageViewMap=new HashMap<>();

    public HelloController(Scene_Manager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public HelloController() {
    }
    Point[] points = new Point[22];

    @FXML
    public void initialize() throws IOException {
        String path = "file:///C:\\Users\\ariel\\IdeaProjects\\javafx-project\\src\\main\\resources\\front\\images\\9-black.png";
//        // Load the image from your project's resources
//        Image image1 = new Image(path);
//        //image.setImage(image1);
        setPoints();
        draggableMaker.makeDraggable(image);
        draggableMaker.makeDraggable(oneY);
        TranslateTransition transition = new TranslateTransition();
        //showcard(transition);
        PauseTransition initialPause = new PauseTransition(Duration.seconds(2));
        initialPause.setOnFinished(event -> {
            //showcard();
            showCardAnimation();
        });

        // הפעלת השהייה
        initialPause.play();

    }
    public void showCard(int index) {
        if (index < 22) {
            ImageView imageView = getNextCard();
            draggableMaker.makeDraggableWithAnimation1(imageView, points[index].getX(), points[index].getY());
            PauseTransition cardDelay = new PauseTransition(Duration.seconds(0.1));
            cardDelay.setOnFinished(event -> showCard(index + 1));
            cardDelay.play();
        }
    }

    // ואז בפונקציה המפעילה את האנימציה:
    public void showCardAnimation() {
        showCard(0);
    }

//    public void showcard(){
//        ImageView imageView;
//        for (int i = 0; i < 22; i++) {
//            imageView=getNextCard();
//            draggableMaker.makeDraggableWithAnimation(imageView,points[i].getX(),points[i].getY());
//        }
//    }

    @FXML
    public void clickDeck(MouseEvent event) throws InterruptedException {
        putCard();
        System.out.println("You clicked me!");
    }
    public void putCard() throws InterruptedException {
        Tile tile = deck.pull_out_tile(1, null);
        tryc.setImage(tile.getImages().getImage());

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(tryc);
        draggableMaker.makeDraggableWithAnimation(tryc);
        tryc.setVisible(true);
        transition.setToX(81);
        transition.setToY(219);

        System.out.println("--------------------");
        transition.setOnFinished(event -> {
            tryc.setLayoutX(tryc.getLayoutX() + 0);
            tryc.setLayoutY(tryc.getLayoutY() + 0);
        });

        PauseTransition pause = new PauseTransition(Duration.seconds(2)); // 2 seconds delay
        pause.setOnFinished(event -> {
            transition.stop();
            draggableMaker.makeDraggableWithAnimation(tryc);            System.out.println("done");
        });

        transition.play();
        pause.play();
        System.out.println("x: " + tryc.getLayoutX());
        System.out.println("y: " + tryc.getLayoutY());
    }
    private void setPoints(){
        double l1=478.0,l2=543;
        double deltax=258.0;
        points[0]=new Point(436.0+deltax,l1);
        points[1]=new Point(384.79998779296875+deltax,l1);
        points[2]=new Point(332.79998779296875+deltax,l1);
        points[3]=new Point(283.20001220703125+deltax,l1);
        points[4]=new Point(230.39999389648438+deltax,l1);
        points[5]=new Point(179.1999969482422+deltax,l1);
        points[6]=new Point(129.60000610351562+deltax,l1);
        points[7]=new Point(77.5999984741211+deltax,l1);
        points[8]=new Point(28.0+deltax,l1);
        points[9]=new Point(-24+deltax,l1);
        points[10]=new Point(-73.5999984741211+deltax,l1);
        points[11]=new Point(440.0+deltax,l2);
        points[12]=new Point(389.6000061035156+deltax,l2);
        points[13]=new Point(340.0+deltax,l2);
        points[14]=new Point(286.3999938964844+deltax,l2);
        points[15]=new Point(233.60000610351562+deltax,l2);
        points[16]=new Point(182.39999389648438+deltax,l2);
        points[17]=new Point(129.60000610351562+deltax,l2);
        points[18]=new Point(78.4000015258789+deltax,l2);
        points[19]=new Point(26.399999618530273+deltax,l2);
        points[20]=new Point(-23.200000762939453+deltax,l2);
        points[21]=new Point(-76.0+deltax,l2);

    }

    public ImageView getNextCard(){
        Tile tile=deck.pull_out_tile(1,null);
        ImageView imageView = new ImageView(tile.getImages().getImage());
        ObservableList<Node> children = the_game.getChildren();
//        imageView.setX(255);
//        imageView.setY(480);
        imageView.setFitHeight(62);
        imageView.setFitWidth(52);
        System.out.println(imageView.getId());
        the_game.getChildren().add(imageView);
        draggableMaker.makeDraggable2(imageView);
        System.out.println("jgk");
        imageViewMap.put(tile,imageView);
        return imageView;
    }

    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        sceneManager.getStage().setScene(scene);
        sceneManager.getStage().show();
    }

    public void setSceneManager(Scene_Manager sceneManager) {
        this.sceneManager = sceneManager;
    }
    @FXML
    void gps(MouseEvent event) {
        for (Map.Entry<Tile, ImageView> entry : imageViewMap.entrySet()) {
            System.out.println("Key: " + entry.getKey().getColor()+entry.getKey().getNumber());
            System.out.println("the index are:"+entry.getValue().localToScene(0, 0).getX()+" , "+entry.getValue().localToScene(0, 0).getY());
        }
//        for (int i = 0; i < 22; i++) {
//            System.out.print(imageViewMap.);
//            System.out.println("the index are:"+imageViews.get(i).getX()+" , "+imageViews.get(i).getY());
//        }
        System.out.println("-------------------------------------");
    }
}
////package front;
////
////import Logic.Deck;
////import Logic.Tile;
////import javafx.animation.PauseTransition;
////import javafx.animation.TranslateTransition;
////import javafx.collections.ObservableList;
////import javafx.fxml.FXML;
////import javafx.fxml.FXMLLoader;
////import javafx.scene.Node;
////import javafx.scene.Scene;
////import javafx.scene.control.Button;
////import javafx.scene.image.ImageView;
////import javafx.scene.input.MouseEvent;
////import javafx.scene.layout.AnchorPane;
////import javafx.stage.Stage;
////import javafx.util.Duration;
////
////import java.io.IOException;
////
////public class HelloController {
////
////    @FXML
////    private ImageView board_left;
////
////    @FXML
////    private ImageView board_right;
////
////    @FXML
////    private ImageView board_up;
////
////    @FXML
////    private ImageView image;
////
////    @FXML
////    private ImageView oneY;
////
////    @FXML
////    private ImageView tryc;
////
////    @FXML
////    private ImageView deckI;
////
////    @FXML
////    private AnchorPane the_game;
////
////    @FXML
////    private ImageView mainBoard;
////
////    @FXML
////    private Button welcomeText;
////
////    Deck deck = new Deck();
////    Stage stage;
////    Scene_Manager sceneManager;
////
////    DraggableMaker draggableMaker = new DraggableMaker();
////
////    public HelloController(Scene_Manager sceneManager) {
////        this.sceneManager = sceneManager;
////    }
////
////    public HelloController() {
////    }
////
////    @FXML
////    public void initialize() throws IOException {
////        draggableMaker.makeDraggable(image);
////        draggableMaker.makeDraggable(oneY);
////        showcard();
////    }
////
////    public void showcard() {
////        for (int i = 0; i < 20; i++) {
////            getNextCard();
////        }
////    }
////
////    @FXML
////    public void clickDeck(MouseEvent event) throws InterruptedException {
////        putCard();
////        System.out.println("You clicked me!");
////    }
////
////    public void putCard() throws InterruptedException {
////        Tile tile = deck.pull_out_tile(1, null);
////        tryc.setImage(tile.getImages().getImage());
////
////        TranslateTransition transition = new TranslateTransition();
////        transition.setNode(tryc);
////        draggableMaker.makeDraggableWithAnimation(tryc);
////        tryc.setVisible(true);
////        transition.setToX(81);
////        transition.setToY(219);
////
////        System.out.println("--------------------");
////        transition.setOnFinished(event -> {
////            tryc.setLayoutX(tryc.getLayoutX() + 0);
////            tryc.setLayoutY(tryc.getLayoutY() + 0);
////        });
////
////        PauseTransition pause = new PauseTransition(Duration.seconds(2)); // 2 seconds delay
////        pause.setOnFinished(event -> {
////            transition.stop();
////            draggableMaker.makeDraggableWithAnimation(tryc);
////            System.out.println("done");
////        });
////
////        transition.play();
////        pause.play();
////        System.out.println("x: " + tryc.getLayoutX());
////        System.out.println("y: " + tryc.getLayoutY());
////    }
////
////    public ImageView getNextCard() {
////        Tile tile = deck.pull_out_tile(1, null);
////        ImageView imageView = new ImageView(tile.getImages().getImage());
////        ObservableList<Node> children = the_game.getChildren();
////        imageView.setX(255);
////        imageView.setY(480);
////        imageView.setFitHeight(62);
////        imageView.setFitWidth(52);
////        System.out.println(imageView.getId());
////        the_game.getChildren().add(imageView);
////        draggableMaker.makeDraggable(imageView);
////        System.out.println("jgk");
////        return imageView;
////    }
////
////    public void show() throws IOException {
////        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
////        Scene scene = new Scene(fxmlLoader.load());
////        sceneManager.getStage().setScene(scene);
////        sceneManager.getStage().show();
////    }
////
////    public void setSceneManager(Scene_Manager sceneManager) {
////        this.sceneManager = sceneManager;
////    }
////}
//package front;
//
//import Logic.Deck;
//import Logic.Tile;
//import javafx.animation.PauseTransition;
//import javafx.animation.TranslateTransition;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.io.IOException;
//
//public class HelloController {
//
//    @FXML
//    private ImageView board_left;
//
//    @FXML
//    private ImageView board_right;
//
//    @FXML
//    private ImageView board_up;
//
//    @FXML
//    private ImageView image;
//
//    @FXML
//    private ImageView oneY;
//
//    @FXML
//    private ImageView tryc;
//
//    @FXML
//    private ImageView deckI;
//
//    @FXML
//    private AnchorPane the_game = new AnchorPane();
//
//    @FXML
//    private ImageView mainBoard;
//
//    @FXML
//    private Button welcomeText;
//
//    @FXML
//    private StackPane cardStackPane;  // נוסיף את ה־StackPane ל־FXML
//
//    Deck deck = new Deck();
//    Stage stage;
//    Scene_Manager sceneManager;
//
//    DraggableMaker draggableMaker = new DraggableMaker();
//
//    public HelloController(Scene_Manager sceneManager) {
//        this.sceneManager = sceneManager;
//    }
//
//    public HelloController() {
//    }
//
//    @FXML
//    public void initialize() throws IOException {
//        String path = "file:///C:\\Users\\ariel\\IdeaProjects\\javafx-project\\src\\main\\resources\\front\\images\\9-black.png";
//        draggableMaker.makeDraggable(image);
//        draggableMaker.makeDraggable(oneY);
//        showcard();
//    }
//
//    public void showcard() {
//        for (int i = 0; i < 20; i++) {
//            getNextCard();
//        }
//    }
//
//    @FXML
//    public void clickDeck(MouseEvent event) throws InterruptedException {
//        putCard();
//        System.out.println("You clicked me!");
//    }
//
//    public void putCard() throws InterruptedException {
//        Tile tile = deck.pull_out_tile(1, null);
//        // tryc.setImage(tile.getImages().getImage());
//
//        addCardToStack(tile.getImages().getImage());
//
//        TranslateTransition transition = new TranslateTransition();
//        transition.setNode(tryc);
//        draggableMaker.makeDraggableWithAnimation(tryc);
//        tryc.setVisible(true);
//        transition.setToX(81);
//        transition.setToY(219);
//
//        System.out.println("--------------------");
//        transition.setOnFinished(event -> {
//            tryc.setLayoutX(tryc.getLayoutX() + 0);
//            tryc.setLayoutY(tryc.getLayoutY() + 0);
//        });
//
//        PauseTransition pause = new PauseTransition(Duration.seconds(2)); // 2 seconds delay
//        pause.setOnFinished(event -> {
//            transition.stop();
//            draggableMaker.makeDraggableWithAnimation(tryc);
//            System.out.println("done");
//        });
//
//        transition.play();
//        pause.play();
//        System.out.println("x: " + tryc.getLayoutX());
//        System.out.println("y: " + tryc.getLayoutY());
//    }
//
//    public void addCardToStack(Image image) {
//        ImageView imageView = new ImageView(image);
//        StackPane.setAlignment(imageView, javafx.geometry.Pos.CENTER);
//        cardStackPane.getChildren().add(imageView);
//    }
//
//    public ImageView getNextCard() {
//        Tile tile = deck.pull_out_tile(1, null);
//        ImageView imageView = new ImageView(tile.getImages().getImage());
//        ObservableList<Node> children = the_game.getChildren();
//        imageView.setX(255);
//        imageView.setY(480);
//        imageView.setFitHeight(62);
//        imageView.setFitWidth(52);
//        System.out.println(imageView.getId());
//        the_game.getChildren().add(imageView);
//        draggableMaker.makeDraggable(imageView);
//        System.out.println("jgk");
//        return imageView;
//    }
//
//    public void show() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        sceneManager.getStage().setScene(scene);
//        sceneManager.getStage().show();
//    }
//
//    public void setSceneManager(Scene_Manager sceneManager) {
//        this.sceneManager = sceneManager;
//    }
//}
