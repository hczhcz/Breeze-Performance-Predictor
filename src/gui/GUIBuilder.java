package gui;

import org.eclipse.swt.widgets.Composite;

public abstract class GUIBuilder {
	protected Composite parent;

	public GUIBuilder(Composite target) {
		parent = target;
	}

	public void init() {
		initWidgets();
		initLayouts();
		initStyle();
		initEvents();
	}

	protected abstract void initWidgets();

	protected abstract void initLayouts();

	protected abstract void initStyle();

	protected abstract void initEvents();
}
