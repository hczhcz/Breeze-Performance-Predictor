package math;

public class Matrix<T> {
	protected int _xSize;
	protected int _ySize;
	public T[][] data;
	
	public Matrix(int x, int y) {
		_xSize = x;
		_ySize = y;
	}
	
	public int xSize() {
		return _xSize;
	}
	
	public int ySize() {
		return _ySize;
	}
}
