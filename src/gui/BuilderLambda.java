package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;

public class BuilderLambda extends GUIBuilder {
	protected Composite groupLambda3;
	protected Composite groupLambda4;
	protected Composite groupLambda5;
	protected Composite groupLambda6;
	protected Label label3l;
	protected Label label3r;
	protected Label label4l;
	protected Label label4r;
	protected Label label5l;
	protected Label label5r;
	protected Scale scale3;
	protected Scale scale4;
	protected Scale scale5;
	protected Button checkbox1;
	protected Button checkbox2;
	protected Button checkbox3;

	public BuilderLambda(Composite target) {
		super(target);
	}

	@Override
	protected void initWidgets() {
		groupLambda3 = new Composite(parent, SWT.NONE);
		groupLambda4 = new Composite(parent, SWT.NONE);
		groupLambda5 = new Composite(parent, SWT.NONE);
		groupLambda6 = new Composite(parent, SWT.NONE);
		label3l = new Label(groupLambda3, SWT.NONE);
		label4l = new Label(groupLambda4, SWT.NONE);
		label5l = new Label(groupLambda5, SWT.NONE);
		scale3 = new Scale(groupLambda3, SWT.NONE);
		scale4 = new Scale(groupLambda4, SWT.NONE);
		scale5 = new Scale(groupLambda5, SWT.NONE);
		label3r = new Label(groupLambda3, SWT.NONE);
		label4r = new Label(groupLambda4, SWT.NONE);
		label5r = new Label(groupLambda5, SWT.NONE);
		checkbox1 = new Button(groupLambda6, SWT.CHECK);
		checkbox2 = new Button(groupLambda6, SWT.CHECK);
		checkbox3 = new Button(groupLambda6, SWT.CHECK);
	}

	protected void initSetLambda(Label label1, Scale scale, Label label2) {
		final FormData lPos = new FormData();
		lPos.left = new FormAttachment(0);
		lPos.top = new FormAttachment(0, LayoutInfo.borderLabel);
		lPos.bottom = new FormAttachment(100, -LayoutInfo.borderLabel);
		lPos.width = LayoutInfo.stdWidth;
		label1.setLayoutData(lPos);

		final FormData mPos = new FormData();
		mPos.left = new FormAttachment(0, LayoutInfo.stdWidth
				+ LayoutInfo.border);
		mPos.top = new FormAttachment(0);
		mPos.right = new FormAttachment(100, -LayoutInfo.stdWidth
				- LayoutInfo.border);
		mPos.bottom = new FormAttachment(100);
		scale.setLayoutData(mPos);

		final FormData rPos = new FormData();
		rPos.top = new FormAttachment(0, LayoutInfo.borderLabel);
		rPos.right = new FormAttachment(100);
		rPos.bottom = new FormAttachment(100, -LayoutInfo.borderLabel);
		rPos.width = LayoutInfo.stdWidth;
		label2.setLayoutData(rPos);

		label1.setAlignment(SWT.RIGHT);
		label2.setAlignment(SWT.LEFT);
	}

	@Override
	protected void initLayouts() {
		final FillLayout horizontalLayout = new FillLayout(SWT.HORIZONTAL);
		horizontalLayout.marginWidth = 0;
		horizontalLayout.marginHeight = 0;
		horizontalLayout.spacing = LayoutInfo.border;

		groupLambda3.setLayout(new FormLayout());
		groupLambda4.setLayout(new FormLayout());
		groupLambda5.setLayout(new FormLayout());
		groupLambda6.setLayout(horizontalLayout);

		initSetLambda(label3l, scale3, label3r);
		initSetLambda(label4l, scale4, label4r);
		initSetLambda(label5l, scale5, label5r);
	}

	@Override
	protected void initStyle() {
		// groupLambda.setText("Prediction");
		label3l.setText(Messages.getString("Window.3l")); //$NON-NLS-1$
		label4l.setText(Messages.getString("Window.4l")); //$NON-NLS-1$
		label5l.setText(Messages.getString("Window.5l")); //$NON-NLS-1$
		scale3.setMinimum(0);
		scale3.setMaximum(LayoutInfo.scaleStep);
		scale3.setSelection(LayoutInfo.scaleStep * 3 / 4); // See initEvents()
															// if changed
		scale4.setMinimum(0);
		scale4.setMaximum(LayoutInfo.scaleStep);
		scale4.setSelection(LayoutInfo.scaleStep * 3 / 4); // See initEvents()
															// if changed
		scale5.setMinimum(0);
		scale5.setMaximum(LayoutInfo.scaleStep);
		scale5.setSelection(LayoutInfo.scaleStep / 2); // See initEvents()
														// if changed
		label3r.setText(Messages.getString("Window.3r")); //$NON-NLS-1$
		label4r.setText(Messages.getString("Window.4r")); //$NON-NLS-1$
		label5r.setText(Messages.getString("Window.5r")); //$NON-NLS-1$
		checkbox1.setText(Messages.getString("Window.o0")); //$NON-NLS-1$
		checkbox2.setText(Messages.getString("Window.o1")); //$NON-NLS-1$
		checkbox2.setSelection(true);
		checkbox3.setText(Messages.getString("Window.o2")); //$NON-NLS-1$
		checkbox3.setSelection(true);
	}

	@Override
	protected void initEvents() {
		final SelectionAdapter scaleEvent = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final Scale widget = (Scale) e.widget;

				widget.setToolTipText(String.format("%.2f / 1.00", //$NON-NLS-1$
						widget.getSelection() * LayoutInfo.scaleUnit));
			}
		};
		scale3.addSelectionListener(scaleEvent);
		scale4.addSelectionListener(scaleEvent);
		scale5.addSelectionListener(scaleEvent);

		scale3.setToolTipText("0.75 / 1.00"); //$NON-NLS-1$
		scale4.setToolTipText("0.75 / 1.00"); //$NON-NLS-1$
		scale5.setToolTipText("0.50 / 1.00"); //$NON-NLS-1$

		final SelectionAdapter autoLambdaEvent = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				assert e.widget == checkbox1;

				scale5.setEnabled(!checkbox1.getSelection());
			}
		};
		checkbox1.addSelectionListener(autoLambdaEvent);
	}

	public float get3() {
		return scale3.getSelection() * LayoutInfo.scaleUnit;
	}

	public float get4() {
		return scale4.getSelection() * LayoutInfo.scaleUnit;
	}

	public float get5() {
		return scale5.getSelection() * LayoutInfo.scaleUnit;
	}

	public int getOptions() {
		return (checkbox1.getSelection() ? 1 : 0)
				| (checkbox2.getSelection() ? 2 : 0)
				| (checkbox3.getSelection() ? 4 : 0);
	}
}
