package test;

import junit.framework.TestCase;
import model.Board;
import model.Tile;
import org.junit.Assert;

public class BoardTest extends TestCase {

    private int boardLength;
    private int boardWidth;
    private Board board;

    public void setUp() throws Exception {
        super.setUp();
        boardLength = 4;
        boardWidth  = 4;
        board = new Board(boardLength, boardWidth);
    }

    public void testGetTiles() throws Exception
    {
        Tile[][] tiles = board.getTiles();

        // Ensure that the returned tile 2D array have the same length
        // and height as the values that the board was initialized with.
        Assert.assertEquals(boardLength, tiles.length);
        Assert.assertEquals(boardWidth, tiles[0].length);


        // Assert tile array is of type model.Tile and are not null
        for(int i = 0; i < boardLength; i++)
        {
            for (int j = 0; j < boardWidth; j++)
            {
                Assert.assertTrue("model.Tile at x: " + i + ", y: " + j + " is valid", tiles[i][j] instanceof Tile);
                Assert.assertTrue("model.Tile at x: " + i + ", y: " + j + " is not null", tiles[i][j] != null);
            }
        }
    }

    public void testGetTile() throws Exception
    {
        Tile[][] tiles = board.getTiles();


        for(int i = 0; i < boardLength; i++)
        {
            for(int j = 0; j < boardWidth; j++)
            {
                //Test objects for equality
                assertEquals("model.Tile at x: " + i + ", y: " + j + " and board.getTile("+ i +", "+j +") are equal",
                        tiles[i][j], board.getTile(i,j));

                //Check that values are the same
                assertEquals("model.Tile at x: " + i + ", y: " + j + " and board.getTile("+ i +", "+j +") values are equal",
                        tiles[i][j].getValue(), board.getTile(i,j).getValue());
            }
        }
    }

    public void testGetLength() throws Exception
    {
        assertEquals("model.Board length is correct", board.getLength(), boardLength);
    }

    public void testGetWidth() throws Exception
    {
        assertEquals("model.Board width is correct", board.getWidth(), boardWidth);
    }
}