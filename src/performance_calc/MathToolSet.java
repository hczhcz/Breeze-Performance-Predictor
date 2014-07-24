package performance_calc;

import math.ConditionalOper;
import math.MathFuncImpl;
import math.MathOper;
import math.MathOperImpl;

public final class MathToolSet {
	private MathToolSet() {
	}

	protected static abstract class OperBase extends MathOperImpl<Float> {
		@Override
		public abstract Float f(Float a, Float b);

		public OperBase() {
		}

		public MathOper<Float> boolCond() {
			return new ConditionalOper<Float>(this) {
				@Override
				protected final boolean Cond(Float a) {
					return a > 0;
				}
			};
		}
	}

	private static abstract class FuncBase extends MathFuncImpl<Float> {
		@Override
		public abstract Float f(Float a);

		public FuncBase() {
		}
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
			assert a == 0 || b != 0; // if x/0, error

			return (b != 0) ? (a / b) : 0;
		}
	};

	private static class _MixClass extends OperBase {
		private final Float _lambda;
		private final boolean _force;

		public _MixClass(Float lambda, boolean force) {
			_lambda = lambda;
			_force = force;
		}

		@Override
		public Float f(Float a, Float b) {
			assert a >= 0;
			assert b >= 0;

			if (!_force) {
				if (a == 0) {
					return b;
				}
				if (b == 0) {
					return a;
				}
			}

			return a * (_lambda) + b * (1 - _lambda);
		}
	}

	public final static OperBase mix(Float lambda, boolean force) {
		assert lambda >= 0;
		assert lambda <= 1;

		return new _MixClass(lambda, force);
	}

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
