package model;

/**
 * Board is a model class that represents the FifteenPuzzle
 * board. A Board object encapsulate a set of 16 Tile objects
 * with unique values ranging from -1 to 15.
 *
 * @author Alan Wernick
 */
public class Board
{
    private Tile[][] tiles; // Tile objects for the puzzle
    private int length; // Board length
    private int width; // Board width

    /** Constructs a Board with a default grid of 16 Tile objects */
    public Board()
    {
        this.length = 4;
        this.width = 4;

        tiles = new Tile[width][length];

        for(int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++)
                tiles[i][j] = new Tile(0);
    }

    /**
     * Returns the Tile object at the specified x and y coordinates.
     *
     * @param x desired Tile's x coordinate
     * @param y desired Tile's y coordinate
     *
     * @return desired tile
     * @see model.Tile
     */
    public Tile getTile(int x, int y)
    {
        return tiles[x][y];
    }

    /**
     * Returns a 4x4 Tile array
     *
     * @return tile-based board
     */
    public Tile[][] getTiles()
    {
        return tiles;
    }


    /**
     * Returns board's length
     *
     * @return length of board
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Returns board's width
     *
     * @return width of board
     */
    public int getWidth()
    {
        return width;
    }
}
