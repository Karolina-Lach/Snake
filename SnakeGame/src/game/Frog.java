package game;
public class Frog {
    private Cell head;

    public Frog(Cell initPos) {
        this.head = initPos;
    }

    public Frog() {
        this.head = new Cell(5,40, CellType.FROG);
    }

    public void update() {
        System.out.println("update");
        if( head.getRow() == 5 && head.getCol() <= 45 && head.getCol() > 5)
            head = new Cell(head.getRow(), head.getCol()-1, CellType.FROG);
        if(head.getRow() >= 5 && head.getRow() <45 && head.getCol() == 5)
            head = new Cell(head.getRow()+1, head.getCol(), CellType.FROG);
        if(head.getRow() == 45 && head.getCol() >= 5 && head.getCol() < 45)
            head = new Cell(head.getRow(), head.getCol()+1, CellType.FROG);
        if(head.getCol() == 45 && head.getRow() <=45 && head.getRow() > 5)
            head = new Cell(head.getRow()-1, head.getCol(), CellType.FROG);
        System.out.println(head.getRow() + "    " + head.getCol());
    }

    public Cell getHead() {
        return head;
    }

    public void setHead(Cell head) {
        this.head = head;
    }
}
