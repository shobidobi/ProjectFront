package Logic;

import java.util.ArrayList;

public class Law {
    public boolean move_Check(){
        boolean legal_move=false;


        return true;
    }

    /**
     * @param move The tiles the player took down for the first time.
     * @return True if the amount is greater than 30 and false otherwise.
     */
    private boolean up30(ArrayList<Tile> move){
        int sum=0;
        for (Tile tile:move) {
            sum+= tile.TileToNum(tile.getNumber(),GameStatus.playing);
        }
        return (sum>=30);
    }


}
