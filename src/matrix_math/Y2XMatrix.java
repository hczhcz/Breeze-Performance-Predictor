package matrix_math;

public class Y2XMatrix<T extends Number> implements AbstractMatrix<T> {
	private final AbstractMatrix<T> _data;
	private final int _y;

	public Y2XMatrix(AbstractMatrix<T> data, int y) {
		_data = data;
		_y = y;
	}

	@Override
	public T get(int x, int y) {
		assert y == 0;

		return _data.get(x, _y);
	}

	@Override
	public void set(int x, int y, T value) {
		assert y == 0;

		_data.set(x, _y, value);
	}

	@Override
	public int xSize() {
		return _data.xSize();
	}

	@Override
	public int ySize() {
		return 1;
	}
}
