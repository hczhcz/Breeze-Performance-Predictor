package matrix_math;

import java.util.ArrayList;

public class DataMatrix<T extends Number> implements AbstractMatrix<T> {
	private final int _xSize;
	private final int _ySize;
	private final ArrayList<ArrayList<T>> _data;

	public DataMatrix(int x, int y) {
		_xSize = x;
		_ySize = y;
		_data = new ArrayList<ArrayList<T>>(y);
		for (int i = 0; i < y; ++i) {
			_data.set(i, new ArrayList<T>(x));
		}
	}

	@Override
	public T get(int x, int y) {
		return _data.get(y).get(x);
	}

	@Override
	public void set(int x, int y, T value) {
		_data.get(y).set(x, value);
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
