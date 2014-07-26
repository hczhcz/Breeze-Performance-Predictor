package performance_calc;

public abstract class Progress {
	private final int _step;
	private int _progress;
	private int _pNow;
	private int _pTotal;

	protected abstract void onProgress(int value);

	public Progress(int step) {
		_step = step;
		_progress = 0;
		_pNow = 0;
		_pTotal = 0;
	}

	public float getReal() {
		return ((float) _pNow) / ((float) _pTotal);
	}

	public int getProgress() {
		return Math.round(getReal() * _step);
	}

	public void put(int value) {
		_pTotal += value;
	}

	public void did(int value) {
		_pNow += value;

		final int now = getProgress();
		if (now != _progress) {
			_progress = now;
			onProgress(now);
		}
	}
}
