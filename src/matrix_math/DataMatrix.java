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

	@Override
	public AbstractMatrix<T> getLineX2Y(int x) {
		final DataMatrix<T> result = new DataMatrix<T>(1, _ySize);

		for (int i = 0; i < _ySize; ++i) {
			result.set(0, i, _data.get(i).get(x));
		}

		return result;
	}

	@Override
	public AbstractMatrix<T> getLineY2X(int y) {
		final DataMatrix<T> result = new DataMatrix<T>(_xSize, 1);

		for (int i = 0; i < _xSize; ++i) {
			result.set(i, 0, _data.get(y).get(i));
		}

		return result;
	}

	@Override
	public void setLineX2Y(int x, AbstractMatrix<T> line) {
		assert line.xSize() == 1;
		assert line.ySize() == _ySize;

		for (int i = 0; i < _ySize; ++i) {
			_data.get(i).set(x, line.get(0, i));
		}
	}

	@Override
	public void setLineY2X(int y, AbstractMatrix<T> line) {
		assert line.xSize() == _xSize;
		assert line.ySize() == 1;

		for (int i = 0; i < _xSize; ++i) {
			_data.get(y).set(i, line.get(i, 0));
		}
	}
}
