/**
 * Created by awernick on 1/27/15.
 */
public class Board
{
    private Tile[][] tiles;

    public Board()
    {
        tiles = new Tile[4][4];

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

//
//  public void setTiles(Tile[][] tiles)
//  {
//      this.tiles = tiles;
//  }
}
