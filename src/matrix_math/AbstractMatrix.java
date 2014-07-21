package matrix_math;

public interface AbstractMatrix<T extends Number> {
	public T get(int x, int y);

	public void set(int x, int y, T value);

	public int xSize();

	public int ySize();

	public AbstractMatrix<T> getLineX2Y(int x);

	public AbstractMatrix<T> getLineY2X(int y);

	public void setLineX2Y(int x, AbstractMatrix<T> line);

	public void setLineY2X(int y, AbstractMatrix<T> line);
}
