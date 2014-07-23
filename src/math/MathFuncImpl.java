package math;

public abstract class MathFuncImpl<T extends Number> implements MathFunc<T> {
	@Override
	public abstract T f(T a);
}
