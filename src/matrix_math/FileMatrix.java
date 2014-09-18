package matrix_math;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public abstract class FileMatrix<T extends Number> implements AbstractMatrix<T> {
	private AbstractMatrix<T> _data;

	protected abstract T readData(Scanner reader);

	protected abstract void writeData(Formatter writer, T value);

	protected abstract T fillData();

	public FileMatrix(int x, int y) {
		_data = new DataMatrix<T>(x, y, fillData());
	}

	public FileMatrix(String fileName) throws FileNotFoundException {
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

	public void loadFromFile(String fileName) throws FileNotFoundException {
		try (final Scanner reader = new Scanner(new FileReader(fileName))) {
			final int x = reader.nextInt();
			final int y = reader.nextInt();

			_data = new DataMatrix<T>(x, y, fillData());

			for (int y1 = 0; y1 < y; ++y1) {
				for (int x1 = 0; x1 < x; ++x1) {
					_data.set(x1, y1, readData(reader));
				}
			}
		} catch (final Exception e) {
			// Ignore error
		}
	}

	public void saveToFile(String fileName) throws IOException {
		try (final Formatter writer = new Formatter(new FileWriter(fileName))) {
			final int x = _data.xSize();
			final int y = _data.ySize();

			writer.format("%d\t%d" + System.lineSeparator(), x, y); //$NON-NLS-1$

			for (int y1 = 0; y1 < y; ++y1) {
				for (int x1 = 0; x1 < x; ++x1) {
					writeData(writer, _data.get(x1, y1));
				}
				writer.format(System.lineSeparator()); //$NON-NLS-1$
			}
		} catch (final Exception e) {
			// Ignore error
		}
	}
}
