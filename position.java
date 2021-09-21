import javax.swing.text.Position;

public class position{
		
	int row;
	int col;
	
	public position(int row,int col){
		this.row = row;
		this.col = col;
		
		}
	
	public int getRow() {
		return this.row;
	}
	public int getCol() {
		return this.col;
	}
	public String toString() {
		return "" + row + ", "+col;
	}
	
}