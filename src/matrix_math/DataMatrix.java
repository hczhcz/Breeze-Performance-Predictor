package matrix_math;

import java.util.ArrayList;

public class DataMatrix<T extends Number> implements AbstractMatrix<T> {
	protected int _xSize;
	protected int _ySize;
	protected ArrayList<ArrayList<T>> data;

	public DataMatrix(int x, int y) {
		_xSize = x;
		_ySize = y;
		data = new ArrayList<ArrayList<T>>(y);
		for (int i = 0; i < y; ++i) {
			data.set(i, new ArrayList<T>(x));
		}
	}

	@Override
	public T get(int x, int y) {
		return data.get(y).get(x);
	}

	@Override
	public void set(int x, int y, T value) {
		data.get(y).set(x, value);
	}

	@Override
	public int xSize() {
		return _xSize;
	}

	@Override
	public int ySize() {
		return _ySize;
	}
}
