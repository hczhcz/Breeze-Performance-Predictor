package non_gui;

import performance_calc.Progress;

public class PrintProgress extends Progress {
	public PrintProgress() {
		super(100);
	}

	@Override
	protected void OnProgress(int value) {
		System.out.printf("Progress: %d%%\n", value);
	}
}
