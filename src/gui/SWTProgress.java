package gui;

import org.eclipse.swt.widgets.ProgressBar;

import performance_calc.Progress;

public class SWTProgress extends Progress {
	private final ProgressBar _progress;

	public SWTProgress(ProgressBar progress) {
		super(progress.getMaximum() - progress.getMinimum());
		_progress = progress;
		progress.setSelection(progress.getMinimum());
	}

	@Override
	protected void onProgress(int value) {
		if (_progress != null) {
			_progress.setSelection(value + _progress.getMinimum());
		}
	}
}
