package matrix_math;

public abstract class MathConditionalOper<T extends Number> implements
		MathOper<T> {
	private final MathOper<T> _oper;

	protected abstract boolean Cond(T a);

	public MathConditionalOper(MathOper<T> oper) {
		_oper = oper;
	}

	@Override
	public final T f(T a, T b) {
		return Cond(a) ? _oper.f(a, b) : b;
	}
}
