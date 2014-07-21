package matrix_math;

public class MathCurryingOper<T extends Number> implements MathFunc<T> {
	private final MathOper<T> _oper;
	private final T _value;

	public MathCurryingOper(MathOper<T> oper, T value) {
		_oper = oper;
		_value = value;
	}

	@Override
	public T f(T a) {
		return _oper.f(a, _value);
	}
}
