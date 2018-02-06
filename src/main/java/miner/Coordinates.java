package miner;

import java.util.Objects;

public class Coordinates
{
    public int x;
    public int y;

    public Coordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) // auto generated, may be error here
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates coordinates = (Coordinates) o;
        return x == coordinates.x &&
                y == coordinates.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
