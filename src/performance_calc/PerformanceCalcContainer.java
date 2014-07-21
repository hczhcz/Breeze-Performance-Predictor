package performance_calc;

import matrix_math.AbstractMatrix;
import matrix_math.DataMatrix;
import matrix_math.MatrixScanner;

public class PerformanceCalcContainer extends MatrixScanner<Float> {
	protected void calc(AbstractMatrix<Float> source, AbstractMatrix<Float> dest) {
		assert dest.xSize() == source.xSize();
		assert dest.ySize() == source.ySize();

		final int x = source.xSize();
		final int y = source.ySize();

		final DataMatrix<Float> xSum = new DataMatrix<Float>(x, 1);
		final DataMatrix<Float> ySum = new DataMatrix<Float>(1, y);

		reduceY2X(source, xSum, MathFuncOperSet.add);
		reduceX2Y(source, ySum, MathFuncOperSet.add);

		final DataMatrix<Float> xAve = new DataMatrix<Float>(x, 1);
		final DataMatrix<Float> yAve = new DataMatrix<Float>(1, y);

		map(xSum, xAve, MathFuncOperSet.mul.curry(1.0f / y));
		map(ySum, yAve, MathFuncOperSet.mul.curry(1.0f / x));

		final DataMatrix<Float> xAbove = new DataMatrix<Float>(x, y);
		final DataMatrix<Float> yAbove = new DataMatrix<Float>(x, y);

		mapY2X(source, xAve, xAbove, MathFuncOperSet.sub);
		mapX2Y(source, yAve, yAbove, MathFuncOperSet.sub);

		final DataMatrix<Float> xSqrSum = new DataMatrix<Float>(x, 1);
		final DataMatrix<Float> ySqrSum = new DataMatrix<Float>(1, y);

		reduceY2X(source, xSqrSum, MathFuncOperSet.addSqr);
		reduceX2Y(source, ySqrSum, MathFuncOperSet.addSqr);

		final DataMatrix<Float> xSimBase = new DataMatrix<Float>(x, 1);
		final DataMatrix<Float> ySimBase = new DataMatrix<Float>(1, y);

		map(xSqrSum, xSimBase, MathFuncOperSet.rSqrt);
		map(ySqrSum, ySimBase, MathFuncOperSet.rSqrt);
	}
}
