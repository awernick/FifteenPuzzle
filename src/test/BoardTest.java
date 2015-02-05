package test;

import junit.framework.TestCase;
import model.Board;
import model.Tile;
import org.junit.Assert;

public class BoardTest extends TestCase
{
    private Board board;

    public void setUp() throws Exception {
        super.setUp();
        board = new Board();
    }

    public void testGetTiles() throws Exception
    {
        Tile[][] tiles = board.getTiles();

        // Tiles should not be null
        assertNotNull(tiles);

        // Ensure that the returned tile 2D array have the same length
        // and height as the values that the board was initialized with.
        int length = tiles.length;
        int width = tiles[0].length;

        assertEquals(4, length);
        assertEquals(4, width);

        //Make sure length and width are the same
        assertEquals(length, width);


        // Assert tile array is of type model.Tile and are not null
        for(int i = 0; i < length; i++)
        {
            for (int j = 0; j < width; j++)
            {
                Assert.assertNotNull("model.Tile at x: " + i + ", y: " + j + " is not null", tiles[i][j]);
                Assert.assertTrue("model.Tile at x: " + i + ", y: " + j + " is valid", tiles[i][j] instanceof Tile);
            }
        }
    }

    public void testGetTile() throws Exception
    {
        Tile[][] tiles = board.getTiles();

        // Ensure that tiles where fetched correctly
        assertNotNull(tiles);

        for(int i = 0; i < tiles.length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                //Test objects for equality (Object memory addresses should be the same)
                assertEquals("model.Tile at x: " + i + ", y: " + j + " and board.getTile("+ i +", "+j +") are equal ",
                        tiles[i][j], board.getTile(i,j));

                //Check that values are the same
                assertEquals("model.Tile at x: " + i + ", y: " + j + " and board.getTile("+ i +", "+j +") values are equal",
                        tiles[i][j].getValue(), board.getTile(i,j).getValue());
            }
        }
    }

    public void testGetLength() throws Exception
    {
        assertEquals("model.Board length is correct", 4, board.getLength());
    }

    public void testGetWidth() throws Exception
    {
        assertEquals("model.Board width is correct", 4, board.getWidth());
    }
}