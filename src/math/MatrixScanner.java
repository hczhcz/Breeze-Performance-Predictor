package math;

public class MatrixScanner<T extends Number> {
	public void reduceX2Y(AbstractMatrix<T> source, AbstractMatrix<T> dest,
			MathOper<T> oper) {
		assert dest.xSize() == 1;
		assert dest.ySize() == source.ySize();

		for (int y = 0; y < source.ySize(); ++y) {
			for (int x = 0; x < source.xSize(); ++x) {
				dest.set(0, y, oper.f(dest.get(0, y), source.get(x, y)));
			}
		}
	}

	public void reduceY2X(AbstractMatrix<T> source, AbstractMatrix<T> dest,
			MathOper<T> oper) {
		assert dest.xSize() == source.xSize();
		assert dest.ySize() == 1;

		for (int x = 0; x < source.xSize(); ++x) {
			for (int y = 0; y < source.ySize(); ++y) {
				dest.set(x, 0, oper.f(dest.get(x, 0), source.get(x, y)));
			}
		}
	}

	public void mapX2Y(AbstractMatrix<T> source, AbstractMatrix<T> line,
			AbstractMatrix<T> dest, MathOper<T> oper) {
		assert line.xSize() == 1;
		assert line.ySize() == source.ySize();
		assert dest.xSize() == source.xSize();
		assert dest.ySize() == source.ySize();

		for (int y = 0; y < source.ySize(); ++y) {
			for (int x = 0; x < source.xSize(); ++x) {
				dest.set(x, y, oper.f(source.get(x, y), line.get(0, y)));
			}
		}
	}

	public void mapY2X(AbstractMatrix<T> source, AbstractMatrix<T> line,
			AbstractMatrix<T> dest, MathOper<T> oper) {
		assert line.xSize() == source.xSize();
		assert line.ySize() == 1;
		assert dest.xSize() == source.xSize();
		assert dest.ySize() == source.ySize();

		for (int x = 0; x < source.xSize(); ++x) {
			for (int y = 0; y < source.ySize(); ++y) {
				dest.set(x, y, oper.f(source.get(x, y), line.get(x, 0)));
			}
		}
	}

	public void map(AbstractMatrix<T> source, AbstractMatrix<T> dest,
			MathFunc<T> func) {
		assert dest.xSize() == source.xSize();
		assert dest.ySize() == source.ySize();

		for (int y = 0; y < source.ySize(); ++y) {
			for (int x = 0; x < source.xSize(); ++x) {
				dest.set(x, y, func.f(source.get(x, y)));
			}
		}
	}
}
