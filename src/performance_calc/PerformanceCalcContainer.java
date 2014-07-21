package performance_calc;

import matrix_math.AbstractMatrix;
import matrix_math.DataMatrix;
import matrix_math.MatrixScanner;

public class PerformanceCalcContainer extends MatrixScanner<Float> {
	protected class BaseCalc {
		final protected DataMatrix<Float> input;
		final int x;
		final int y;

		protected DataMatrix<Float> exist;

		protected DataMatrix<Float> xSum;
		protected DataMatrix<Float> ySum;
		protected DataMatrix<Float> xCount;
		protected DataMatrix<Float> yCount;
		protected DataMatrix<Float> xAve;
		protected DataMatrix<Float> yAve;
		protected DataMatrix<Float> xAbove;
		protected DataMatrix<Float> yAbove;
		protected DataMatrix<Float> xSqrSum;
		protected DataMatrix<Float> ySqrSum;
		protected DataMatrix<Float> xSimBase;
		protected DataMatrix<Float> ySimBase;
		protected DataMatrix<Float> xSimSum;
		protected DataMatrix<Float> ySimSum;
		protected DataMatrix<Float> xSim;
		protected DataMatrix<Float> ySim;

		protected BaseCalc(DataMatrix<Float> source) {
			input = source;
			x = source.xSize();
			y = source.ySize();
		}

		protected void DoCalc() {
			xSum = new DataMatrix<Float>(x, 1);
			ySum = new DataMatrix<Float>(1, y);
			reduceY2X(input, xSum, MathToolSet.add);
			reduceX2Y(input, ySum, MathToolSet.add);

			xCount = new DataMatrix<Float>(x, 1);
			yCount = new DataMatrix<Float>(1, y);
			reduceY2X(input, xCount, MathToolSet.add.apply(MathToolSet.bool));
			reduceX2Y(input, yCount, MathToolSet.add.apply(MathToolSet.bool));

			xAve = new DataMatrix<Float>(x, 1);
			yAve = new DataMatrix<Float>(1, y);
			each(xSum, xCount, xAve, MathToolSet.div.boolCond());
			each(ySum, yCount, yAve, MathToolSet.div.boolCond());

			xAbove = new DataMatrix<Float>(x, y);
			yAbove = new DataMatrix<Float>(x, y);
			mapY2X(input, xAve, xAbove, MathToolSet.sub.boolCond());
			mapX2Y(input, yAve, yAbove, MathToolSet.sub.boolCond());

			xSqrSum = new DataMatrix<Float>(x, 1);
			ySqrSum = new DataMatrix<Float>(1, y);
			// notice: swap xAbove and yAbove
			reduceY2X(yAbove, xSqrSum, MathToolSet.add.apply(MathToolSet.sqr));
			reduceX2Y(xAbove, ySqrSum, MathToolSet.add.apply(MathToolSet.sqr));

			xSimBase = new DataMatrix<Float>(x, 1);
			ySimBase = new DataMatrix<Float>(1, y);
			map(xSqrSum, xSimBase, MathToolSet.rSqrt);
			map(ySqrSum, ySimBase, MathToolSet.rSqrt);

			// size x * x
			xSimSum = new DataMatrix<Float>(x, x);
			for (int i = 0; i < x; ++i) {
				final DataMatrix<Float> mulMapX = new DataMatrix<Float>(x, y);
				// notice: swap xAbove and yAbove
				mapX2Y(yAbove, yAbove.getLineX2Y(x), mulMapX, MathToolSet.mul);

				final DataMatrix<Float> mulSumX = new DataMatrix<Float>(x, 1);
				reduceY2X(mulMapX, mulSumX, MathToolSet.add);
			}

			// size y * y
			ySimSum = new DataMatrix<Float>(y, y);
			for (int i = 0; i < y; ++i) {
				final DataMatrix<Float> mulMapY = new DataMatrix<Float>(x, y);
				// notice: swap xAbove and yAbove
				mapY2X(xAbove, xAbove.getLineY2X(y), mulMapY, MathToolSet.mul);

				final DataMatrix<Float> mulSumY = new DataMatrix<Float>(1, y);
				reduceX2Y(mulMapY, mulSumY, MathToolSet.add);
			}

			for (int i = 0; i < x; ++i) {

			}

			for (int i = 0; i < y; ++i) {

			}
		}
	}

	protected void calc(AbstractMatrix<Float> source, AbstractMatrix<Float> dest) {
		assert dest.xSize() == source.xSize();
		assert dest.ySize() == source.ySize();

		final int x = source.xSize();
		final int y = source.ySize();

	}
}
