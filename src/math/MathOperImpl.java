package math;

public abstract class MathOperImpl<T extends Number> implements MathOper<T> {
	@Override
	public abstract T f(T a, T b);

	public MathFunc<T> curry(T value) {
		return new CurryingFunc<T>(this, value);
	}

	public MathOper<T> apply(MathFunc<T> func) {
		return new AppliedOper<T>(this, func);
	}
}
