package gui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ProgressBar;

import performance_calc.FileCalcContainer;

public class BuilderExec extends GUIBuilder {
	protected FileCalcContainer calc;

	protected BuilderFile contentFile;
	protected BuilderLambda contentLambda;

	protected Button buttonExec;
	protected ProgressBar progress;

	public BuilderExec(Composite target, FileCalcContainer targetCalc,
			BuilderFile file, BuilderLambda lambda) {
		super(target);

		calc = targetCalc;

		contentFile = file;
		contentLambda = lambda;
	}

	@Override
	protected void initWidgets() {
		buttonExec = new Button(parent, SWT.PUSH);
		progress = new ProgressBar(parent, SWT.BORDER);
	}

	protected void initExec(ProgressBar progressBar, Button exec) {
		final FormData lmPos = new FormData();
		lmPos.left = new FormAttachment(0);
		lmPos.top = new FormAttachment(0);
		lmPos.right = new FormAttachment(100, -LayoutInfo.stdWidth
				- LayoutInfo.border);
		lmPos.bottom = new FormAttachment(100);
		progressBar.setLayoutData(lmPos);

		final FormData rPos = new FormData();
		rPos.top = new FormAttachment(0);
		rPos.right = new FormAttachment(100);
		rPos.bottom = new FormAttachment(100);
		rPos.width = LayoutInfo.stdWidth;
		exec.setLayoutData(rPos);
	}

	@Override
	protected void initLayouts() {
		initExec(progress, buttonExec);
	}

	@Override
	protected void initStyle() {
		buttonExec.setText(Messages.getString("Window.go")); //$NON-NLS-1$
		progress.setMinimum(0);
		progress.setMaximum(LayoutInfo.scaleStep2);
		progress.setSelection(0);
	}

	@Override
	protected void initEvents() {
		final SelectionAdapter execEvent = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				assert e.widget == buttonExec;

				try {
					if (contentFile.get1a().isEmpty()
							|| contentFile.get1b().isEmpty()
							|| contentFile.get2a().isEmpty()
							|| contentFile.get2b().isEmpty()) {
						// TODO error msg
					} else {
						calc.calcFile(contentLambda.get3(),
								contentLambda.get4(), contentLambda.get5(),
								new SWTProgress(progress),
								contentLambda.getOptions());
						try {
							calc.saveFile(contentFile.get1b(),
									contentFile.get2b());
						} catch (final IOException e1) {
							e1.printStackTrace();
						}
					}
				} finally {
					// Nothing
				}
			}
		};
		buttonExec.addSelectionListener(execEvent);
	}
}
