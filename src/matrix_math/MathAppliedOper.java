package matrix_math;

public class MathAppliedOper<T extends Number> implements MathOper<T> {
	private final MathOper<T> _oper;
	private final MathFunc<T> _func;

	public MathAppliedOper(MathOper<T> oper, MathFunc<T> func) {
		_oper = oper;
		_func = func;
	}

	@Override
	public final T f(T a, T b) {
		return _oper.f(a, _func.f(b));
	}
}
