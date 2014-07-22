package performance_calc;

import java.util.Scanner;

import matrix_math.FileMatrix;

public class FloatFileMatrix extends FileMatrix<Float> {
	public FloatFileMatrix(int x, int y) {
		super(x, y);
	}

	public FloatFileMatrix(String fileName) {
		super(fileName);
	}

	@Override
	protected Float readData(Scanner reader) {
		return reader.nextFloat();
	}
}
