package non_gui;

import performance_calc.Progress;

public class PrintProgress extends Progress {
	public PrintProgress() {
		super(100);
	}

	@Override
	protected void onProgress(int value) {
		System.out.printf("Progress: %d%%" + System.lineSeparator(), value); //$NON-NLS-1$
	}
}
