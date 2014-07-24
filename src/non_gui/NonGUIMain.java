package non_gui;

import java.io.IOException;

import performance_calc.CalcContainer;

public class NonGUIMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final CalcContainer calc = new CalcContainer();

		try {
			calc.calcFile(0.5f, 0.5f, 0.5f, "in1.txt", "in2.txt", "out1.txt", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					"out2.txt", new PrintProgress()); //$NON-NLS-1$
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
