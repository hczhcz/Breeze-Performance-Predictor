package performance_calc;

import matrix_math.MathCurryingOper;
import matrix_math.MathFunc;
import matrix_math.MathOper;

public class MathFuncOperSet {
	protected static abstract class OperBase implements MathOper<Float> {
		@Override
		public abstract Float f(Float a, Float b);

		public MathFunc<Float> curry(Float value) {
			return new MathCurryingOper<Float>(this, value);
		}
	};

	protected static abstract class FuncBase implements MathFunc<Float> {
		@Override
		public abstract Float f(Float a);
	}

	public final static OperBase add = new OperBase() {
		@Override
		public Float f(Float a, Float b) {
			return a + b;
		}
	};

	public final static OperBase sub = new OperBase() {
		@Override
		public Float f(Float a, Float b) {
			return a - b;
		}
	};

	public final static OperBase mul = new OperBase() {
		@Override
		public Float f(Float a, Float b) {
			return a * b;
		}
	};

	public final static OperBase addSqr = new OperBase() {
		@Override
		public Float f(Float a, Float b) {
			return a + b * b;
		}
	};

	public final static FuncBase rSqrt = new FuncBase() {
		@Override
		public Float f(Float a) {
			return (float) (1 / Math.sqrt(a));
		}
	};
}
