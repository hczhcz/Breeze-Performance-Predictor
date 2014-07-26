package performance_calc;

import java.io.FileNotFoundException;
import java.io.IOException;

import matrix_math.FileMatrix;

public class FileCalcContainer extends CalcContainer {
	public FileMatrix<Float> source1;
	public FileMatrix<Float> source2;
	public FileMatrix<Float> dest1;
	public FileMatrix<Float> dest2;

	public void loadFile1(String sourceFile1) throws FileNotFoundException {
		source1 = new FloatFileMatrix(sourceFile1);
		dest1 = new FloatFileMatrix(source1.xSize(), source1.ySize());
	}

	public void loadFile2(String sourceFile2) throws FileNotFoundException {
		source2 = new FloatFileMatrix(sourceFile2);
		dest2 = new FloatFileMatrix(source2.xSize(), source2.ySize());
	}

	public void calcFile(Float lambda1, Float lambda2, Float lambdaXY,
			Progress progress) {
		calc(lambda1, lambda2, lambdaXY, source1, source2, dest1, dest2,
				progress);
	}

	public void saveFile(String destFile1, String destFile2) throws IOException {
		dest1.saveToFile(destFile1);
		dest2.saveToFile(destFile2);
	}
}
