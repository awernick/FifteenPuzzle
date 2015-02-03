package model;

/**
 * Created by awernick on 1/27/15.
 */
public class Board
{
    private Tile[][] tiles;
    private int length;
    private int width;

    public Board(int length, int width)
    {
        this.length = length;
        this.width = width;

        tiles = new Tile[width][length];

        for(int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++)
                tiles[i][j] = new Tile(0);
    }

    /**
     * Gets the tile at the specified x and y coordinates
     *
     * @param x desired tile's x coordinate
     * @param y desired tile's y coordinate
     *
     * @return desired tile
     */
    public Tile getTile(int x, int y)
    {
        return tiles[x][y];
    }

    /**
     * Fetch 4x4 tile-based game board
     *
     * @return tile-based board
     */
    public Tile[][] getTiles()
    {
        return tiles;
    }


    /**
     * Get board's length
     *
     * @return length of board
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Get board's width
     *
     * @return width of board
     */
    public int getWidth()
    {
        return width;
    }
//
//  public void setTiles(model.Tile[][] tiles)
//  {
//      this.tiles = tiles;
//  }
}
