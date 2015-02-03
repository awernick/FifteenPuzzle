package test;

import controller.BoardController;
import model.Tile;
import org.junit.Assert;

/**
 * Created by awernick on 1/27/15.
 */
public class BoardControllerTest
{
    BoardController boardController;

    @org.junit.Before
    public void setUp() throws Exception
    {
        boardController = new BoardController(4,4);
        boardController.init();
    }

    @org.junit.Test
    public void testTileClicked() throws Exception
    {

    }


    @org.junit.Test
    public void testSolveBoard() throws Exception
    {
        Tile[][] tiles = boardController.getBoard();

        int counter = 1;
        for(int i = 0; i < tiles.length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                if(counter == tiles.length * tiles[0].length)
                    tiles[j][i].setValue(-1);
                else
                    tiles[j][i].setValue(counter);

                counter++;
            }
        }

        boardController.solveBoard();
        Tile[][] boardSolved = boardController.getBoard();

        for(int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[0].length; j++)
            {
                System.out.println("Expected: " + tiles[j][i].getValue() + ", Actual: " + boardSolved[j][i].getValue());
                Assert.assertEquals(tiles[j][i].getValue(), boardSolved[j][i].getValue());
            }
        }
    }

    @org.junit.Test
    public void testScrambleBoard() throws Exception
    {

        Tile[][] tiles = boardController.getBoard();

        boardController.scrambleBoard();

        Tile[][]  scrambled = boardController.getBoard();

        for(int i = 0; i < tiles.length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                System.out.println("Original: " + tiles[i][j].getValue() +  ", Scrambled: " + scrambled[i][j].getValue());
                Assert.assertNotEquals(tiles[i][j], scrambled[i][j]);
            }
        }
    }
}

