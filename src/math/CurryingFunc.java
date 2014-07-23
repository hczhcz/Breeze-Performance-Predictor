package math;

public final class CurryingFunc<T extends Number> extends MathFuncImpl<T> {
	private final MathOper<T> _oper;
	private final T _value;

	public CurryingFunc(MathOper<T> oper, T value) {
		_oper = oper;
		_value = value;
	}

	@Override
	public final T f(T a) {
		return _oper.f(a, _value);
	}
}
