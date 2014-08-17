package gui;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Window extends GUIBuilder {
	protected Display display;
	protected Shell shell;
	protected BuilderAll content;

	public Window() {
		super(null);

		init();
	}

	@Override
	protected void initWidgets() {
		display = new Display();
		shell = new Shell(display);

		content = new BuilderAll(shell, display, shell);
		content.initWidgets();
	}

	@Override
	protected void initLayouts() {
		shell.setMinimumSize(LayoutInfo.windowWidth, LayoutInfo.windowHeight);
		shell.setLayout(new FormLayout());

		content.initLayouts();
	}

	@Override
	protected void initStyle() {
		content.initStyle();
	}

	@Override
	protected void initEvents() {
		content.initEvents();
	}

	public void show() {
		shell.setSize(LayoutInfo.windowWidth, LayoutInfo.windowHeight);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}
}
