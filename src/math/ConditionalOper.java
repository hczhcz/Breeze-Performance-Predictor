package math;

public abstract class ConditionalOper<T extends Number> extends MathOperImpl<T> {
	private final MathOper<T> _oper;

	protected abstract boolean Cond(T a);

	public ConditionalOper(MathOper<T> oper) {
		_oper = oper;
	}

	@Override
	public final T f(T a, T b) {
		return Cond(a) ? _oper.f(a, b) : b;
	}
}
