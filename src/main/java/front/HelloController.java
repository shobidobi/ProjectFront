package front;

import Logic.Deck;
import Logic.Tile;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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

    DraggableMaker draggableMaker = new DraggableMaker();



    @FXML
    public void initialize() throws IOException {
        String path = "file:///C:\\Users\\ariel\\IdeaProjects\\javafx-project\\src\\main\\resources\\front\\images\\9-black.png";
//        // Load the image from your project's resources
//        Image image1 = new Image(path);
//        //image.setImage(image1);
        draggableMaker.makeDraggable(image);
        draggableMaker.makeDraggable(oneY);
        showcard();
    }
    public void showcard(){
        for (int i = 0; i < 20; i++) {
            getNextCard();
        }
    }
    @FXML
    public void clickDeck(MouseEvent event) {
        putCard();
        System.out.println("You clicked me!");
    }
    public void putCard() {
        Tile tile=deck.pull_out_tile(1,null);
        tryc.setImage(tile.getImages().getImage());
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(tryc);
        draggableMaker.makeDraggable(tryc);
        tryc.setVisible(true);
        transition.setToX(188-tryc.getLayoutX());
        transition.setToY(479-tryc.getLayoutY());
        transition.play();

    }
    public ImageView getNextCard(){
        Tile tile=deck.pull_out_tile(1,null);
        ImageView imageView = new ImageView(tile.getImages().getImage());
        ObservableList<Node> children = the_game.getChildren();
        imageView.setX(255);
        imageView.setY(480);
        imageView.setFitHeight(62);
        imageView.setFitWidth(52);
        the_game.getChildren().add(imageView);
        draggableMaker.makeDraggable(imageView);
        System.out.println("jgk");
        return imageView;
    }
}