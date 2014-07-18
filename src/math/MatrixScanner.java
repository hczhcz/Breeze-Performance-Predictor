package math;

public class MatrixScanner<T> {
	public void reduceX(Matrix<T> source, Matrix<T> dest, MathOper<T> oper) {
		assert dest._xSize == 1;
		assert dest._ySize == source._ySize;
		
		for (int y = 0; y < source._ySize; ++y) {
			for (int x = 0; x < source._xSize; ++x) {
				dest.data[y][0] = oper.f(dest.data[y][0], source.data[y][x]);
			}
		}
	}
	
	public void reduceY(Matrix<T> source, Matrix<T> dest, MathOper<T> oper) {
		assert dest._xSize == source._xSize;
		assert dest._ySize == 1;

		for (int x = 0; x < source._xSize; ++x) {
			for (int y = 0; y < source._ySize; ++y) {
				dest.data[0][x] = oper.f(dest.data[0][x], source.data[y][x]);
			}
		}
	}
	public void mapX(Matrix<T> source, Matrix<T> line, Matrix<T> dest, MathOper<T> oper) {
		assert line._xSize == 1;
		assert line._ySize == source._ySize;
		assert dest._xSize == source._xSize;
		assert dest._ySize == source._ySize;
		
		for (int y = 0; y < source._ySize; ++y) {
			for (int x = 0; x < source._xSize; ++x) {
				dest.data[y][x] = oper.f(source.data[y][x], line.data[y][0]);
			}
		}
	}
	
	public void mapY(Matrix<T> source, Matrix<T> line, Matrix<T> dest, MathOper<T> oper) {
		assert line._xSize == source._xSize;
		assert line._ySize == 1;
		assert dest._xSize == source._xSize;
		assert dest._ySize == source._ySize;

		for (int x = 0; x < source._xSize; ++x) {
			for (int y = 0; y < source._ySize; ++y) {
				dest.data[y][x] = oper.f(source.data[y][x], line.data[0][x]);
			}
		}
	}
	
	public void map(Matrix<T> source, Matrix<T> dest, MathFunc<T> func) {
		assert dest._xSize == source._xSize;
		assert dest._ySize == source._ySize;
		
		for (int y = 0; y < source._ySize; ++y) {
			for (int x = 0; x < source._xSize; ++x) {
				dest.data[y][x] = func.f(source.data[y][x]);
			}
		}
	}
}
