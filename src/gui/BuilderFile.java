package gui;

import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import performance_calc.FileCalcContainer;

public class BuilderFile extends GUIBuilder {
	protected Shell shell;
	protected FileCalcContainer calc;

	protected Composite groupFile1a;
	protected Composite groupFile1b;
	protected Composite groupFile2a;
	protected Composite groupFile2b;
	protected Label label1a;
	protected Label label1b;
	protected Label label2a;
	protected Label label2b;
	protected Text text1a;
	protected Text text1b;
	protected Text text2a;
	protected Text text2b;
	protected Button button1a;
	protected Button button1b;
	protected Button button2a;
	protected Button button2b;

	public BuilderFile(Composite target, Shell targetShell,
			FileCalcContainer targetCalc) {
		super(target);

		shell = targetShell;
		calc = targetCalc;
	}

	@Override
	protected void initWidgets() {
		groupFile1a = new Composite(parent, SWT.NONE);
		groupFile1b = new Composite(parent, SWT.NONE);
		groupFile2a = new Composite(parent, SWT.NONE);
		groupFile2b = new Composite(parent, SWT.NONE);
		label1a = new Label(groupFile1a, SWT.NONE);
		label1b = new Label(groupFile1b, SWT.NONE);
		label2a = new Label(groupFile2a, SWT.NONE);
		label2b = new Label(groupFile2b, SWT.NONE);
		text1a = new Text(groupFile1a, SWT.BORDER);
		text1b = new Text(groupFile1b, SWT.BORDER);
		text2a = new Text(groupFile2a, SWT.BORDER);
		text2b = new Text(groupFile2b, SWT.BORDER);
		button1a = new Button(groupFile1a, SWT.PUSH);
		button1b = new Button(groupFile1b, SWT.PUSH);
		button2a = new Button(groupFile2a, SWT.PUSH);
		button2b = new Button(groupFile2b, SWT.PUSH);
	}

	protected void initOpenFile(Label label, Text text, Button button) {
		final FormData lPos = new FormData();
		lPos.left = new FormAttachment(0);
		lPos.top = new FormAttachment(0, LayoutInfo.borderLabel);
		lPos.bottom = new FormAttachment(100, -LayoutInfo.borderLabel);
		lPos.width = LayoutInfo.stdWidth;
		label.setLayoutData(lPos);

		final FormData mPos = new FormData();
		mPos.left = new FormAttachment(0, LayoutInfo.stdWidth
				+ LayoutInfo.border);
		mPos.top = new FormAttachment(0);
		mPos.right = new FormAttachment(100, -LayoutInfo.stdWidth
				- LayoutInfo.border);
		mPos.bottom = new FormAttachment(100);
		text.setLayoutData(mPos);

		final FormData rPos = new FormData();
		rPos.top = new FormAttachment(0);
		rPos.right = new FormAttachment(100);
		rPos.bottom = new FormAttachment(100);
		rPos.width = LayoutInfo.stdWidth;
		button.setLayoutData(rPos);

		label.setAlignment(SWT.RIGHT);
	}

	@Override
	protected void initLayouts() {
		groupFile1a.setLayout(new FormLayout());
		groupFile1b.setLayout(new FormLayout());
		groupFile2a.setLayout(new FormLayout());
		groupFile2b.setLayout(new FormLayout());

		initOpenFile(label1a, text1a, button1a);
		initOpenFile(label1b, text1b, button1b);
		initOpenFile(label2a, text2a, button2a);
		initOpenFile(label2b, text2b, button2b);
	}

	@Override
	protected void initStyle() {
		// groupFile.setText("Files");
		label1a.setText(Messages.getString("Window.1a")); //$NON-NLS-1$
		label1b.setText(Messages.getString("Window.1b")); //$NON-NLS-1$
		label2a.setText(Messages.getString("Window.2a")); //$NON-NLS-1$
		label2b.setText(Messages.getString("Window.2b")); //$NON-NLS-1$
		button1a.setText(Messages.getString("Window.b")); //$NON-NLS-1$
		button1b.setText(Messages.getString("Window.b")); //$NON-NLS-1$
		button2a.setText(Messages.getString("Window.b")); //$NON-NLS-1$
		button2b.setText(Messages.getString("Window.b")); //$NON-NLS-1$
	}

	@Override
	protected void initEvents() {
		final SelectionAdapter browseEvent = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					final Text widget;
					final int mode;

					if (e.widget == button1a) {
						widget = text1a;
						mode = SWT.OPEN;
					} else if (e.widget == button1b) {
						widget = text1b;
						mode = SWT.SAVE;
					} else if (e.widget == button2a) {
						widget = text2a;
						mode = SWT.OPEN;
					} else if (e.widget == button2b) {
						widget = text2b;
						mode = SWT.SAVE;
					} else {
						// Never reach
						widget = null;
						mode = 0;

						assert false;
					}

					final FileDialog dialog = new FileDialog(shell, mode);

					final String fileName = dialog.open();
					if (fileName != null && widget != null) {
						widget.setText(fileName);
						widget.setSelection(fileName.length());

						if (e.widget == button1a) {
							if (text1b.getText().isEmpty()) {
								text1b.setText(fileName + MiscInfo.outputExt);
								text1b.setSelection(fileName.length()
										+ MiscInfo.outputExt.length());
							}

							try {
								calc.loadFile1(fileName);
							} catch (final FileNotFoundException e1) {
								// e1.printStackTrace();
							}
						} else if (e.widget == button2a) {
							if (text2b.getText().isEmpty()) {
								text2b.setText(fileName + MiscInfo.outputExt);
								text2b.setSelection(fileName.length()
										+ MiscInfo.outputExt.length());
							}

							try {
								calc.loadFile2(fileName);
							} catch (final FileNotFoundException e1) {
								// e1.printStackTrace();
							}
						}
					}
				} finally {
					// Nothing
				}
			}
		};
		button1a.addSelectionListener(browseEvent);
		button1b.addSelectionListener(browseEvent);
		button2a.addSelectionListener(browseEvent);
		button2b.addSelectionListener(browseEvent);
	}

	public String get1a() {
		return text1a.getText();
	}

	public String get1b() {
		return text1b.getText();
	}

	public String get2a() {
		return text2a.getText();
	}

	public String get2b() {
		return text2b.getText();
	}
}
