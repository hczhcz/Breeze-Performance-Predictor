package non_gui;

import java.io.IOException;

import performance_calc.PerformanceCalcContainer;

public class NonGUIMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final PerformanceCalcContainer calc = new PerformanceCalcContainer();

		try {
			calc.calcFile(0.5f, 0.5f, 0.5f, "in1.txt", "in2.txt", "out1.txt",
					"out2.txt", new PrintProgress());
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
