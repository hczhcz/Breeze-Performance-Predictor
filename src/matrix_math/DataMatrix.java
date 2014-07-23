package matrix_math;

import java.util.ArrayList;

public class DataMatrix<T extends Number> implements AbstractMatrix<T> {
	private final int _xSize;
	private final int _ySize;
	private final ArrayList<ArrayList<T>> _data;

	public DataMatrix(int x, int y, T fill) {
		assert x >= 0;
		assert y >= 0;

		_xSize = x;
		_ySize = y;
		_data = new ArrayList<ArrayList<T>>(y);
		for (int y1 = 0; y1 < y; ++y1) {
			_data.add(new ArrayList<T>(x));
			for (int x1 = 0; x1 < x; ++x1) {
				_data.get(y1).add(fill);
			}
		}
	}

	@Override
	public T get(int x, int y) {
		assert x >= 0;
		assert x < _xSize;
		assert y >= 0;
		assert y < _ySize;

		return _data.get(y).get(x);
	}

	@Override
	public void set(int x, int y, T value) {
		assert x >= 0;
		assert x < _xSize;
		assert y >= 0;
		assert y < _ySize;

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
