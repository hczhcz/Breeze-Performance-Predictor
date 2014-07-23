package performance_calc;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

import matrix_math.FileMatrix;

public class FloatFileMatrix extends FileMatrix<Float> {
	public FloatFileMatrix(int x, int y) {
		super(x, y);
	}

	public FloatFileMatrix(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	@Override
	protected Float readData(Scanner reader) {
		return reader.nextFloat();
	}

	@Override
	protected void writeData(Formatter writer, Float value) {
		writer.format("%f ", value);
	}
}
