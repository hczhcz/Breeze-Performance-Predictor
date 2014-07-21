package performance_calc;

import matrix_math.MathAppliedOper;
import matrix_math.MathConditionalOper;
import matrix_math.MathCurryingFunc;
import matrix_math.MathFunc;
import matrix_math.MathOper;

public class MathToolSet {
	protected static abstract class OperBase implements MathOper<Float> {
		@Override
		public abstract Float f(Float a, Float b);

		public MathFunc<Float> curry(Float value) {
			return new MathCurryingFunc<Float>(this, value);
		}

		public MathOper<Float> apply(MathFunc<Float> func) {
			return new MathAppliedOper<Float>(this, func);
		}

		public MathOper<Float> boolCond() {
			return new MathConditionalOper<Float>(this) {
				@Override
				protected final boolean Cond(Float a) {
					return a > 0;
				}
			};
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

	public final static OperBase div = new OperBase() {
		@Override
		public Float f(Float a, Float b) {
			assert b != 0; // if x/0, error

			return a / b;
		}
	};

	public final static FuncBase bool = new FuncBase() {
		@Override
		public Float f(Float a) {
			return (a > 0) ? 1.0f : 0.0f;
		}
	};

	public final static FuncBase sqr = new FuncBase() {
		@Override
		public Float f(Float a) {
			return a * a;
		}
	};

	public final static FuncBase rSqrt = new FuncBase() {
		@Override
		public Float f(Float a) {
			return (a > 0) ? (float) (1.0f / Math.sqrt(a)) : 0.0f;
		}
	};
}
