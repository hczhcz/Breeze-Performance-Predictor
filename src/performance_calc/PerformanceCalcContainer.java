package performance_calc;

import matrix_math.AbstractMatrix;
import matrix_math.DataMatrix;
import matrix_math.MatrixScanner;
import matrix_math.X2YMatrix;
import matrix_math.Y2XMatrix;

public class PerformanceCalcContainer extends MatrixScanner<Float> {
	protected class DataBlock {
		final protected AbstractMatrix<Float> input;
		final protected int x;
		final protected int y;

		protected AbstractMatrix<Float> exist;

		protected AbstractMatrix<Float> xSum;
		protected AbstractMatrix<Float> ySum;
		protected AbstractMatrix<Float> xCount;
		protected AbstractMatrix<Float> yCount;
		protected AbstractMatrix<Float> xAve;
		protected AbstractMatrix<Float> yAve;
		protected AbstractMatrix<Float> xAbove;
		protected AbstractMatrix<Float> yAbove;
		protected AbstractMatrix<Float> xSqrSum;
		protected AbstractMatrix<Float> ySqrSum;
		protected AbstractMatrix<Float> xSimBase;
		protected AbstractMatrix<Float> ySimBase;
		protected AbstractMatrix<Float> xSimWeight;
		protected AbstractMatrix<Float> ySimWeight;
		protected AbstractMatrix<Float> xSim;
		protected AbstractMatrix<Float> ySim;
		protected AbstractMatrix<Float> xMixedSim;
		protected AbstractMatrix<Float> yMixedSim;
		protected AbstractMatrix<Float> xTotalSim;
		protected AbstractMatrix<Float> yTotalSim;
		protected AbstractMatrix<Float> xPSum;
		protected AbstractMatrix<Float> yPSum;
		protected AbstractMatrix<Float> xPValue;
		protected AbstractMatrix<Float> yPValue;

		protected DataBlock(AbstractMatrix<Float> source) {
			input = source;
			x = source.xSize();
			y = source.ySize();
		}

		protected void PhraseAverage() {
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
		}

		protected void PhraseSimilarity() {
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
			xSimWeight = new DataMatrix<Float>(x, x);
			for (int i = 0; i < x; ++i) {
				final AbstractMatrix<Float> mulMapX = new DataMatrix<Float>(x,
						y);
				// notice: swap xAbove and yAbove
				mapX2Y(yAbove, new X2YMatrix<Float>(yAbove, i), mulMapX,
						MathToolSet.mul);

				reduceY2X(mulMapX, new Y2XMatrix<Float>(xSimWeight, i),
						MathToolSet.add);
			}

			// size y * y
			ySimWeight = new DataMatrix<Float>(y, y);
			for (int i = 0; i < y; ++i) {
				final AbstractMatrix<Float> mulMapY = new DataMatrix<Float>(x,
						y);
				// notice: swap xAbove and yAbove
				mapY2X(xAbove, new Y2XMatrix<Float>(xAbove, i), mulMapY,
						MathToolSet.mul);

				reduceX2Y(mulMapY, new X2YMatrix<Float>(ySimWeight, i),
						MathToolSet.add);
			}

			xSim = new DataMatrix<Float>(x, y);
			ySim = new DataMatrix<Float>(x, y);
			mapY2X(xSimWeight, xSimBase, xSim, MathToolSet.div.boolCond());
			mapX2Y(ySimWeight, ySimBase, ySim, MathToolSet.div.boolCond());
		}

		protected void PhraseRemixing(DataBlock another, Float lambda) {
			each(xSim, another.xSim, xMixedSim, MathToolSet.mix(lambda, true));
			each(ySim, another.ySim, yMixedSim, MathToolSet.mix(lambda, true));
		}

		protected void PhrasePredictedValue(AbstractMatrix<Float> dest) {
			// TODO: TotalSim PSum PValue
		}
	}

	protected void calc(Float lambda1, Float lambda2,
			AbstractMatrix<Float> source1, AbstractMatrix<Float> source2,
			AbstractMatrix<Float> dest1, AbstractMatrix<Float> dest2) {
		assert source2.xSize() == source1.xSize();
		assert source2.ySize() == source1.ySize();
		assert dest1.xSize() == source1.xSize();
		assert dest1.ySize() == source1.ySize();
		assert dest2.xSize() == source1.xSize();
		assert dest2.ySize() == source1.ySize();

		final DataBlock data1 = new DataBlock(source1);
		final DataBlock data2 = new DataBlock(source2);

		data1.PhraseAverage();
		data2.PhraseAverage();
		data1.PhraseSimilarity();
		data2.PhraseSimilarity();
		data1.PhraseRemixing(data2, lambda1);
		data2.PhraseRemixing(data1, lambda2);
		data1.PhrasePredictedValue(dest1);
		data2.PhrasePredictedValue(dest2);
	}
}
