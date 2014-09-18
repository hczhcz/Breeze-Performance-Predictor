package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import performance_calc.FileCalcContainer;

public class BuilderQuery extends GUIBuilder {
	protected FileCalcContainer calc;

	protected Label label7;
	protected Label label7y;
	protected Label label8t;
	protected Label label8a;
	protected Label label8b;
	protected Label label9t;
	protected Label label9a;
	protected Label label9b;
	protected Text text7x;
	protected Text text7y;

	public BuilderQuery(Composite target, FileCalcContainer targetCalc) {
		super(target);

		calc = targetCalc;
	}

	@Override
	protected void initWidgets() {
		label7 = new Label(parent, SWT.NONE);
		text7x = new Text(parent, SWT.NONE);
		text7y = new Text(parent, SWT.NONE);
		label8t = new Label(parent, SWT.NONE);
		label8a = new Label(parent, SWT.NONE);
		label8b = new Label(parent, SWT.NONE);
		label9t = new Label(parent, SWT.NONE);
		label9a = new Label(parent, SWT.NONE);
		label9b = new Label(parent, SWT.NONE);
	}

	@Override
	protected void initLayouts() {
		// Nothing
	}

	@Override
	protected void initStyle() {
		label7.setText(Messages.getString("Window.xy")); //$NON-NLS-1$
		label8t.setText(Messages.getString("Window.8")); //$NON-NLS-1$
		label8a.setText("1:"); //$NON-NLS-1$
		label8b.setText("2:"); //$NON-NLS-1$
		label9t.setText(Messages.getString("Window.9")); //$NON-NLS-1$
		label9a.setText("1:"); //$NON-NLS-1$
		label9b.setText("2:"); //$NON-NLS-1$
	}

	@Override
	protected void initEvents() {
		final ModifyListener queryEvent = new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				update();
			}
		};
		text7x.addModifyListener(queryEvent);
		text7y.addModifyListener(queryEvent);
	}

	public void update() {
		try {
			if (!text7x.getText().isEmpty() && !text7y.getText().isEmpty()) {
				final int x = Integer.parseInt(text7x.getText());
				final int y = Integer.parseInt(text7y.getText());

				if (calc.source1 != null && x < calc.source1.xSize()
						&& y < calc.source1.ySize()) {
					if (calc.source1.get(x, y) > 0) {
						label8a.setText(String.format(
								"1: %.6f", calc.source1.get(x, y))); //$NON-NLS-1$
					} else {
						label8a.setText("1:"); //$NON-NLS-1$
					}

					if (calc.dest1 != null) {
						assert calc.dest1.xSize() == calc.source1.xSize();
						assert calc.dest1.ySize() == calc.source1.ySize();

						if (calc.dest1.get(x, y) > 0) {
							label9a.setText(String.format(
									"1: %.6f", calc.dest1.get(x, y))); //$NON-NLS-1$
						} else {
							label9a.setText("1:"); //$NON-NLS-1$
						}
					}
				}

				if (calc.source2 != null && x < calc.source2.xSize()
						&& y < calc.source2.ySize()) {
					if (calc.source2.get(x, y) > 0) {
						label8b.setText(String.format(
								"2: %.6f", calc.source2.get(x, y))); //$NON-NLS-1$
					} else {
						label8b.setText("2:"); //$NON-NLS-1$
					}

					if (calc.dest2 != null) {
						assert calc.dest2.xSize() == calc.source2.xSize();
						assert calc.dest2.ySize() == calc.source2.ySize();

						if (calc.dest2.get(x, y) > 0) {
							label9b.setText(String.format(
									"2: %.6f", calc.dest2.get(x, y))); //$NON-NLS-1$
						} else {
							label9b.setText("2:"); //$NON-NLS-1$
						}
					}
				}
			}
		} catch (final Exception e) {
			// Ignore error
		}
	}
}
