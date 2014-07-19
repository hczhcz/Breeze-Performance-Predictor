package performance_calc;

import matrix_math.AbstractMatrix;
import matrix_math.DataMatrix;
import matrix_math.MathFunc;
import matrix_math.MathOper;
import matrix_math.MatrixScanner;

public class PerformanceCalcContainer {
	protected void calc(AbstractMatrix<Float> source, AbstractMatrix<Float> dest) {
		assert dest.xSize() == source.xSize();
		assert dest.ySize() == source.ySize();

		final int x = source.xSize();
		final int y = source.ySize();
		final float rx = 1 / x;
		final float ry = 1 / y;

		final MathOper<Float> add = new MathOper<Float>() {
			@Override
			public Float f(Float a, Float b) {
				return a + b;
			}
		};

		final MathOper<Float> sub = new MathOper<Float>() {
			@Override
			public Float f(Float a, Float b) {
				return a - b;
			}
		};

		final MathOper<Float> addSqr = new MathOper<Float>() {
			@Override
			public Float f(Float a, Float b) {
				return a + b * b;
			}
		};

		final MathFunc<Float> divX = new MathFunc<Float>() {
			@Override
			public Float f(Float a) {
				return a * rx;
			}
		};

		final MathFunc<Float> divY = new MathFunc<Float>() {
			@Override
			public Float f(Float a) {
				return a * ry;
			}
		};

		final MatrixScanner<Float> scanner = new MatrixScanner<Float>();

		final DataMatrix<Float> xSum = new DataMatrix<Float>(x, 1);
		final DataMatrix<Float> ySum = new DataMatrix<Float>(1, y);

		scanner.reduceY2X(source, xSum, add);
		scanner.reduceX2Y(source, ySum, add);

		final DataMatrix<Float> xAve = new DataMatrix<Float>(x, 1);
		final DataMatrix<Float> yAve = new DataMatrix<Float>(1, y);

		scanner.map(xSum, xAve, divY);
		scanner.map(ySum, yAve, divX);

		final DataMatrix<Float> xAbove = new DataMatrix<Float>(x, y);
		final DataMatrix<Float> yAbove = new DataMatrix<Float>(x, y);

		scanner.mapY2X(source, xAve, xAbove, sub);
		scanner.mapX2Y(source, yAve, yAbove, sub);

		final DataMatrix<Float> xSqrSum = new DataMatrix<Float>(x, 1);
		final DataMatrix<Float> ySqrSum = new DataMatrix<Float>(1, y);

		scanner.reduceY2X(source, xSqrSum, addSqr);
		scanner.reduceX2Y(source, ySqrSum, addSqr);
	}
}
