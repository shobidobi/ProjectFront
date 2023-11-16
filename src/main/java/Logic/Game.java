package Logic;


import java.util.ArrayList;

public class Game {
    GameStatus gameStatus;
    public Game(GameStatus gameStatus) {
        this.gameStatus=GameStatus.playing;
    }
//    EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("default");
//    EntityManager entityManager=entityManagerFactory.createEntityManager();
//    EntityTransaction transaction=entityManager.getTransaction();
    //EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("default");
    public static void main(String[] args) {

        ArrayList tilesDeck=new ArrayList<Tile>();
        //createList(tilesDeck);
//        printdeck(tilesDeck);
//        Deck deck=new Deck();
//        ImageIcon icon=deck.tilesDeck.get(0).getImages();
//        System.out.println(icon);
    }
}
