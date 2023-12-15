package Logic;

import javafx.scene.image.ImageView;


public class Tile {
    private Number number;
    private Color color;

    private ImageView image;

    public Tile(Number number, Color color) {
        this.number = number;
        this.color = color;
        this.image=new ImageView();
        this.image=getImages();
    }
    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public ImageView getImages() {
        String colorS="",numberS;
        String s="file:///C:\\Users\\ariel\\IdeaProjects\\javafx-project\\javafx\\src\\main\\resources\\front\\images\\";
        switch (color){
            case red:
                colorS=Color.red.name();
                break;
            case black:
                colorS=Color.black.name();
                break;
            case yellow:
                colorS=Color.yellow.name();
                break;
            case blue:
                colorS=Color.blue.name();
                break;
        }
        if (number!=Number.joker){numberS=Integer.toString(TileToNum(number,GameStatus.playing));}
        else {numberS="joker";}
        image.setImage(new ImageView(s+numberS+"-"+colorS+".png").getImage());
        return image;
    }

    /**
     * @param num The number to convert.
     * @param gameStatus Used to calculate the wildcard(joker) value.
     * @return The integer that represents the tile.
     */
    public int TileToNum(Number num,GameStatus gameStatus){
        switch (num){
            case One:
                return 1;
            case Two:
                return 2;
            case Three:
                return 3;
            case Four:
                return 4;
            case Five:
                return 5;
            case Six:
                return 6;
            case Seven:
                return 7;
            case Eight:
                return 8;
            case Nine:
                return 9;
            case Ten:
                return 10;
            case Eleven:
                return 11;
            case Twelve:
                return 12;
            case Thirteen:
                return 13;
            case joker:
                if (gameStatus==GameStatus.end){return 30;}
                if (gameStatus==GameStatus.playing){return -1;}
        }
        return 0;
    }
    @Override
    public String toString() {
        return "Tile{" +
                "number=" + number +
                ", color=" + color +
                '}';
    }

}
