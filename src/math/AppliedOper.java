package math;

public class AppliedOper<T extends Number> extends MathOperImpl<T> {
	private final MathOper<T> _oper;
	private final MathFunc<T> _func;

	public AppliedOper(MathOper<T> oper, MathFunc<T> func) {
		_oper = oper;
		_func = func;
	}

	@Override
	public final T f(T a, T b) {
		return _oper.f(a, _func.f(b));
	}
}
