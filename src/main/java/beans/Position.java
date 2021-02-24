package beans;

public class Position
{
 private int row;
 private int col;

	Position(int row,int col)
	{
		this.col=col;
		this.row=row;
	}

	public int getRow() {

		return row;
	}

	public void setRow(int row) {

		this.row = row;
	}

	public int getCol() {

		return col;
	}

	public void setCol(int col) {

		this.col = col;
	}
}
