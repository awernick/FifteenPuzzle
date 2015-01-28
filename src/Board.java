/**
 * Created by awernick on 1/27/15.
 */
public class Board
{
    private Tile[][] tiles;

    public Tile[][] getTiles()
    {
        return tiles;
    }

    public Tile getTile(int x, int y)
    {
        return tiles[x][y];
    }

    public void setTiles(Tile[][] tiles)
    {
        this.tiles = tiles;
    }
}
