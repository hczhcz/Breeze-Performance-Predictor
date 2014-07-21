package matrix_math;

public class X2YMatrix<T extends Number> implements AbstractMatrix<T> {
	private final AbstractMatrix<T> _data;
	private final int _x;

	public X2YMatrix(AbstractMatrix<T> data, int x) {
		_data = data;
		_x = x;
	}

	@Override
	public T get(int x, int y) {
		assert x == 0;

		return _data.get(_x, y);
	}

	@Override
	public void set(int x, int y, T value) {
		assert x == 0;

		_data.set(_x, y, value);
	}

	@Override
	public int xSize() {
		return 1;
	}

	@Override
	public int ySize() {
		return _data.ySize();
	}
}
