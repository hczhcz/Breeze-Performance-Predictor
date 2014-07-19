package matrix_math;

import java.util.Scanner;

public abstract class FileMatrix<T extends Number> implements AbstractMatrix<T> {
	private DataMatrix<T> _data;

	protected abstract T readData(Scanner reader);

	public FileMatrix(int x, int y) {
		_data = new DataMatrix<T>(x, y);
	}

	public FileMatrix(String fileName) {
		loadFromFile(fileName);
	}

	@Override
	public T get(int x, int y) {
		return _data.get(x, y);
	}

	@Override
	public void set(int x, int y, T value) {
		_data.set(x, y, value);
	}

	@Override
	public int xSize() {
		return _data.xSize();
	}

	@Override
	public int ySize() {
		return _data.ySize();
	}

	public void loadFromFile(String fileName) {
		final Scanner reader = new Scanner(System.in);

		final int x = reader.nextInt();
		final int y = reader.nextInt();

		for (int y1 = 0; y1 < y; ++y1) {
			for (int x1 = 0; x1 < x; ++x1) {
				set(x, y, readData(reader));
			}
		}
	}

	public void saveToFile(String fileName) {
		//
	}
}
