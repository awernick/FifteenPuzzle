package model;

/**
 * Created by awernick on 1/27/15.
 */
public class Tile
{
    private int value;

    public Tile(int value)
    {
        this.value = value;
    }

    /**
     * Fetch tile's value
     *
     * @return tile's value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Set tile's value
     *
     * @param value tile's desired value
     */
    public void setValue(int value)
    {
        this.value = value;
    }
}
