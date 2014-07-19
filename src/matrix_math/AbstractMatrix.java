package matrix_math;

public interface AbstractMatrix<T extends Number> {
	public T get(int x, int y);

	public void set(int x, int y, T value);

	public int xSize();

	public int ySize();
}
