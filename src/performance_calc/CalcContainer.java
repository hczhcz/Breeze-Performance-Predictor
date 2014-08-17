package performance_calc;

import matrix_math.AbstractMatrix;
import matrix_math.MatrixScanner;
import matrix_math.X2YMatrix;
import matrix_math.Y2XMatrix;

public class CalcContainer extends MatrixScanner<Float> {
	protected Progress pr;

	protected class Data extends FloatDataMatrix {
		protected Data(int x, int y) {
			super(x, y, 0.0f);
			pr.did(x * y);
		}
	}

	protected class DataBlock {
		final protected AbstractMatrix<Float> input;
		final protected int x;
		final protected int y;

		protected AbstractMatrix<Float> mainData;

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

		protected AbstractMatrix<Float> resultData;

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

			// Reset
			pr.did(0);
		}

		protected void PhraseAverage(boolean sqrtFiltering) {
			if (sqrtFiltering) {
				mainData = new Data(x, y);
				map(input, mainData, MathToolSet.sqrt);
			} else {
				mainData = input;
			}

			xSum = new Data(x, 1);
			ySum = new Data(1, y);
			reduceY2X(mainData, xSum, MathToolSet.add);
			reduceX2Y(mainData, ySum, MathToolSet.add);

			xCount = new Data(x, 1);
			yCount = new Data(1, y);
			reduceY2X(mainData, xCount, MathToolSet.add.apply(MathToolSet.bool));
			reduceX2Y(mainData, yCount, MathToolSet.add.apply(MathToolSet.bool));

			xAve = new Data(x, 1);
			yAve = new Data(1, y);
			each(xSum, xCount, xAve, MathToolSet.div);
			each(ySum, yCount, yAve, MathToolSet.div);

			xAbove = new Data(x, y);
			yAbove = new Data(x, y);
			mapY2X(mainData, xAve, xAbove, MathToolSet.sub.boolCond());
			mapX2Y(mainData, yAve, yAbove, MathToolSet.sub.boolCond());
		}

		protected void PhraseSimilarity(boolean intersection) {
			AbstractMatrix<Float> xAboveRef;
			AbstractMatrix<Float> yAboveRef;

			if (intersection) {
				xAboveRef = yAbove;
				yAboveRef = xAbove;
			} else {
				xAboveRef = xAbove;
				yAboveRef = yAbove;
			}

			xSqrSum = new Data(x, 1);
			ySqrSum = new Data(1, y);

			reduceY2X(xAboveRef, xSqrSum,
					MathToolSet.add.apply(MathToolSet.sqr));
			reduceX2Y(yAboveRef, ySqrSum,
					MathToolSet.add.apply(MathToolSet.sqr));

			xSimBase = new Data(x, 1);
			ySimBase = new Data(1, y);
			map(xSqrSum, xSimBase, MathToolSet.rSqrt);
			map(ySqrSum, ySimBase, MathToolSet.rSqrt);

			// size x * x
			xSimWeight = new Data(x, x);
			for (int i = 0; i < x; ++i) {
				final AbstractMatrix<Float> mulMapX = new Data(x, y);
				mapX2Y(xAboveRef, new X2YMatrix<Float>(yAbove, i), mulMapX,
						MathToolSet.mul);

				reduceY2X(mulMapX, new Y2XMatrix<Float>(xSimWeight, i),
						MathToolSet.add);
			}

			// size y * y
			ySimWeight = new Data(y, y);
			for (int i = 0; i < y; ++i) {
				final AbstractMatrix<Float> mulMapY = new Data(x, y);
				mapY2X(yAboveRef, new Y2XMatrix<Float>(xAbove, i), mulMapY,
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
				Float lambda, boolean autoLambda, boolean sqrtFiltering) {
			xTotalSim = new Data(x, 1);
			yTotalSim = new Data(1, y);
			reduceY2X(xMixedSim, xTotalSim, MathToolSet.add);
			reduceX2Y(yMixedSim, yTotalSim, MathToolSet.add);

			xPSum = new Data(x, y);
			for (int i = 0; i < y; ++i) {
				final AbstractMatrix<Float> mulMapX = new Data(x, x);
				mapX2Y(xMixedSim, new Y2XMatrix<Float>(xAbove, i), mulMapX,
						MathToolSet.mul);

				reduceY2X(mulMapX, new Y2XMatrix<Float>(xPSum, i),
						MathToolSet.add);
			}

			yPSum = new Data(x, y);
			for (int i = 0; i < x; ++i) {
				final AbstractMatrix<Float> mulMapY = new Data(y, y);
				mapY2X(yMixedSim, new X2YMatrix<Float>(yAbove, i), mulMapY,
						MathToolSet.mul);

				reduceX2Y(mulMapY, new X2YMatrix<Float>(yPSum, i),
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

			if (sqrtFiltering) {
				resultData = new Data(x, y);
			} else {
				resultData = dest;
			}

			if (autoLambda) {
				final AbstractMatrix<Float> lambdaMapX = new Data(x, y);
				final AbstractMatrix<Float> lambdaMapY = new Data(x, y);
				mapY2X(xPValue, xAve, lambdaMapX, MathToolSet.mul);
				mapX2Y(yPValue, yAve, lambdaMapY, MathToolSet.mul);

				final AbstractMatrix<Float> lambdaMapSum = new Data(x, y);
				each(lambdaMapX, lambdaMapY, lambdaMapSum, MathToolSet.add);

				final AbstractMatrix<Float> lambdaMapBase = new Data(x, y);
				// notice: reuse
				mapY2X(lambdaMapBase, xAve, lambdaMapBase, MathToolSet.add);
				mapX2Y(lambdaMapBase, yAve, lambdaMapBase, MathToolSet.add);

				each(lambdaMapSum, lambdaMapBase, resultData, MathToolSet.div);
			} else {
				each(xPValue, yPValue, resultData,
						MathToolSet.mix(lambda, false));
			}

			if (sqrtFiltering) {
				map(resultData, dest, MathToolSet.sqr);
			}
		}
	}

	public void calc(Float lambda1, Float lambda2, Float lambdaXY,
			AbstractMatrix<Float> source1, AbstractMatrix<Float> source2,
			AbstractMatrix<Float> dest1, AbstractMatrix<Float> dest2,
			Progress progress, int options) {
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

		data1.PhraseAverage((options & CalcInfo.sqrtFiltering) != 0);
		data2.PhraseAverage((options & CalcInfo.sqrtFiltering) != 0);
		data1.PhraseSimilarity((options & CalcInfo.intersection) != 0);
		data2.PhraseSimilarity((options & CalcInfo.intersection) != 0);
		data1.PhraseRemixing(data2, lambda1);
		data2.PhraseRemixing(data1, lambda2);
		data1.PhrasePredictedValue(dest1, lambdaXY,
				(options & CalcInfo.autoLambda) != 0,
				(options & CalcInfo.sqrtFiltering) != 0);
		data2.PhrasePredictedValue(dest2, lambdaXY,
				(options & CalcInfo.autoLambda) != 0,
				(options & CalcInfo.sqrtFiltering) != 0);
	}
}
