package game;

public class Cell {

    private final int row, col;
    private CellType cellType;

    public Cell(int row, int col)
    {
        this.row = row;
        this.col = col;
        setCellType(CellType.EMPTY);
    }

    public Cell(int row, int col, CellType type)
    {
        this.row = row;
        this.col = col;
        setCellType(type);
    }
    public CellType getCellType()
    {
        return cellType;
    }

    public void setCellType(CellType cellType)
    {
        this.cellType = cellType;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }
}
