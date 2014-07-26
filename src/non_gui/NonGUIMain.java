package non_gui;

import java.io.IOException;

import performance_calc.FileCalcContainer;

public class NonGUIMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final FileCalcContainer calc = new FileCalcContainer();

		try {
			calc.loadFile1("in1.txt"); //$NON-NLS-1$
			calc.loadFile2("in2.txt"); //$NON-NLS-1$
			calc.calcFile(0.5f, 0.5f, 0.5f, new PrintProgress());
			calc.saveFile("out1.txt", "out2.txt"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
