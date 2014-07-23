package performance_calc;

import java.io.IOException;

import matrix_math.AbstractMatrix;
import matrix_math.DataMatrix;
import matrix_math.FileMatrix;
import matrix_math.MatrixScanner;
import matrix_math.X2YMatrix;
import matrix_math.Y2XMatrix;

public class PerformanceCalcContainer extends MatrixScanner<Float> {
	protected Progress pr;

	protected class Data extends DataMatrix<Float> {
		protected Data(int x, int y) {
			super(x, y, 0.0f);
			pr.did(x * y);
		}
	}

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
		protected AbstractMatrix<Float> xPAbove;
		protected AbstractMatrix<Float> yPAbove;
		protected AbstractMatrix<Float> xPValue;
		protected AbstractMatrix<Float> yPValue;

		protected DataBlock(AbstractMatrix<Float> source) {
			input = source;
			x = source.xSize();
			y = source.ySize();
		}

		protected void AddProgress() {
			// Average
			pr.put(x * 1);
			pr.put(1 * y);
			pr.put(x * 1);
			pr.put(1 * y);
			pr.put(x * 1);
			pr.put(1 * y);
			pr.put(x * y);
			pr.put(x * y);

			// Similarity
			pr.put(x * 1);
			pr.put(1 * y);
			pr.put(x * 1);
			pr.put(1 * y);
			pr.put(x * x);
			pr.put(x * y * x);
			pr.put(y * y);
			pr.put(x * y * y);
			pr.put(x * x);
			pr.put(y * y);

			// Remixing
			pr.put(x * x);
			pr.put(y * y);

			// PredictedValue
			pr.put(x * 1);
			pr.put(1 * y);
			pr.put(x * y);
			pr.put(x * y * x);
			pr.put(x * y);
			pr.put(x * y * y);
			pr.put(x * y);
			pr.put(x * y);
			pr.put(x * y);
			pr.put(x * y);
		}

		protected void PhraseAverage() {
			xSum = new Data(x, 1);
			ySum = new Data(1, y);
			reduceY2X(input, xSum, MathToolSet.add);
			reduceX2Y(input, ySum, MathToolSet.add);

			xCount = new Data(x, 1);
			yCount = new Data(1, y);
			reduceY2X(input, xCount, MathToolSet.add.apply(MathToolSet.bool));
			reduceX2Y(input, yCount, MathToolSet.add.apply(MathToolSet.bool));

			xAve = new Data(x, 1);
			yAve = new Data(1, y);
			each(xSum, xCount, xAve, MathToolSet.div);
			each(ySum, yCount, yAve, MathToolSet.div);

			xAbove = new Data(x, y);
			yAbove = new Data(x, y);
			mapY2X(input, xAve, xAbove, MathToolSet.sub.boolCond());
			mapX2Y(input, yAve, yAbove, MathToolSet.sub.boolCond());
		}

		protected void PhraseSimilarity() {
			xSqrSum = new Data(x, 1);
			ySqrSum = new Data(1, y);
			// notice: swap xAbove and yAbove
			reduceY2X(yAbove, xSqrSum, MathToolSet.add.apply(MathToolSet.sqr));
			reduceX2Y(xAbove, ySqrSum, MathToolSet.add.apply(MathToolSet.sqr));

			xSimBase = new Data(x, 1);
			ySimBase = new Data(1, y);
			map(xSqrSum, xSimBase, MathToolSet.rSqrt);
			map(ySqrSum, ySimBase, MathToolSet.rSqrt);

			// size x * x
			xSimWeight = new Data(x, x);
			for (int i = 0; i < x; ++i) {
				final AbstractMatrix<Float> mulMapX = new Data(x, y);
				// notice: swap xAbove and yAbove
				mapX2Y(yAbove, new X2YMatrix<Float>(yAbove, i), mulMapX,
						MathToolSet.mul);

				reduceY2X(mulMapX, new Y2XMatrix<Float>(xSimWeight, i),
						MathToolSet.add);
			}

			// size y * y
			ySimWeight = new Data(y, y);
			for (int i = 0; i < y; ++i) {
				final AbstractMatrix<Float> mulMapY = new Data(x, y);
				// notice: swap xAbove and yAbove
				mapY2X(xAbove, new Y2XMatrix<Float>(xAbove, i), mulMapY,
						MathToolSet.mul);

				reduceX2Y(mulMapY, new X2YMatrix<Float>(ySimWeight, i),
						MathToolSet.add);
			}

			xSim = new Data(x, x);
			ySim = new Data(y, y);
			mapY2X(xSimWeight, xSimBase, xSim, MathToolSet.div);
			mapX2Y(ySimWeight, ySimBase, ySim, MathToolSet.div);
		}

		protected void PhraseRemixing(DataBlock another, Float lambda) {
			xMixedSim = new Data(x, x);
			yMixedSim = new Data(y, y);
			each(xSim, another.xSim, xMixedSim, MathToolSet.mix(lambda, true));
			each(ySim, another.ySim, yMixedSim, MathToolSet.mix(lambda, true));
		}

		protected void PhrasePredictedValue(AbstractMatrix<Float> dest,
				Float lambda) {
			xTotalSim = new Data(x, 1);
			yTotalSim = new Data(1, y);
			reduceY2X(xMixedSim, xTotalSim, MathToolSet.add);
			reduceX2Y(yMixedSim, yTotalSim, MathToolSet.add);

			xPSum = new Data(x, y);
			for (int i = 0; i < x; ++i) {
				final AbstractMatrix<Float> mulMapX = new Data(x, y);
				mapY2X(xAbove, new Y2XMatrix<Float>(xSim, i), mulMapX,
						MathToolSet.mul);

				reduceX2Y(mulMapX, new X2YMatrix<Float>(xPSum, i),
						MathToolSet.add);
			}

			yPSum = new Data(x, y);
			for (int i = 0; i < y; ++i) {
				final AbstractMatrix<Float> mulMapY = new Data(x, y);
				mapX2Y(yAbove, new X2YMatrix<Float>(ySim, i), mulMapY,
						MathToolSet.mul);

				reduceY2X(mulMapY, new Y2XMatrix<Float>(yPSum, i),
						MathToolSet.add);
			}

			xPAbove = new Data(x, y);
			yPAbove = new Data(x, y);
			mapY2X(xPSum, xTotalSim, xPAbove, MathToolSet.div);
			mapX2Y(yPSum, yTotalSim, yPAbove, MathToolSet.div);

			xPValue = new Data(x, y);
			yPValue = new Data(x, y);
			mapY2X(xPAbove, xAve, xPValue, MathToolSet.add.boolCond());
			mapX2Y(yPAbove, yAve, yPValue, MathToolSet.add.boolCond());

			each(xPValue, yPValue, dest, MathToolSet.mix(lambda, false));
		}
	}

	public void calc(Float lambda1, Float lambda2, Float lambdaXY,
			AbstractMatrix<Float> source1, AbstractMatrix<Float> source2,
			AbstractMatrix<Float> dest1, AbstractMatrix<Float> dest2,
			Progress progress) {
		assert source2.xSize() == source1.xSize();
		assert source2.ySize() == source1.ySize();
		assert dest1.xSize() == source1.xSize();
		assert dest1.ySize() == source1.ySize();
		assert dest2.xSize() == source1.xSize();
		assert dest2.ySize() == source1.ySize();

		pr = progress;

		final DataBlock data1 = new DataBlock(source1);
		final DataBlock data2 = new DataBlock(source2);

		data1.AddProgress();
		data2.AddProgress();

		data1.PhraseAverage();
		data2.PhraseAverage();
		data1.PhraseSimilarity();
		data2.PhraseSimilarity();
		data1.PhraseRemixing(data2, lambda1);
		data2.PhraseRemixing(data1, lambda2);
		data1.PhrasePredictedValue(dest1, lambdaXY);
		data2.PhrasePredictedValue(dest2, lambdaXY);
	}

	public void calcFile(Float lambda1, Float lambda2, Float lambdaXY,
			String sourceFile1, String sourceFile2, String destFile1,
			String destFile2, Progress progress) throws IOException {
		final FileMatrix<Float> source1 = new FloatFileMatrix(sourceFile1);
		final FileMatrix<Float> source2 = new FloatFileMatrix(sourceFile2);
		final FileMatrix<Float> dest1 = new FloatFileMatrix(source1.xSize(),
				source1.ySize());
		final FileMatrix<Float> dest2 = new FloatFileMatrix(source2.xSize(),
				source2.ySize());

		calc(lambda1, lambda2, lambdaXY, source1, source2, dest1, dest2,
				progress);

		dest1.saveToFile(destFile1);
		dest2.saveToFile(destFile2);
	}
}
