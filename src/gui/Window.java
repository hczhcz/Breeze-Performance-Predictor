package gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import matrix_math.AbstractMatrix;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolTip;

import performance_calc.FileCalcContainer;

public class Window {
	protected Display display;
	protected Shell shell;

	protected Group groupFile;
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

	protected Group groupLambda;
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
	protected Button buttonExec;
	protected ProgressBar progress;

	protected Composite groupCanvas;
	protected Canvas canvas1;
	protected Canvas canvas2;
	protected ToolTip tip;

	protected FileCalcContainer calc;

	public Window() {
		calc = new FileCalcContainer();

		initWidgets();
		initLayouts();
		initInnerLayouts();
		initStyle();
		initEvents();
	}

	protected void initWidgets() {
		display = new Display();

		shell = new Shell(display);

		groupFile = new Group(shell, 0);
		groupFile1a = new Composite(groupFile, SWT.NONE);
		groupFile1b = new Composite(groupFile, SWT.NONE);
		groupFile2a = new Composite(groupFile, SWT.NONE);
		groupFile2b = new Composite(groupFile, SWT.NONE);
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

		groupLambda = new Group(shell, 0);
		groupLambda3 = new Composite(groupLambda, SWT.NONE);
		groupLambda4 = new Composite(groupLambda, SWT.NONE);
		groupLambda5 = new Composite(groupLambda, SWT.NONE);
		groupLambda6 = new Composite(groupLambda, SWT.NONE);
		label3l = new Label(groupLambda3, SWT.NONE);
		label4l = new Label(groupLambda4, SWT.NONE);
		label5l = new Label(groupLambda5, SWT.NONE);
		scale3 = new Scale(groupLambda3, SWT.NONE);
		scale4 = new Scale(groupLambda4, SWT.NONE);
		scale5 = new Scale(groupLambda5, SWT.NONE);
		label3r = new Label(groupLambda3, SWT.NONE);
		label4r = new Label(groupLambda4, SWT.NONE);
		label5r = new Label(groupLambda5, SWT.NONE);
		buttonExec = new Button(groupLambda6, SWT.PUSH);
		progress = new ProgressBar(groupLambda6, SWT.BORDER);

		groupCanvas = new Composite(shell, SWT.NONE);
		canvas1 = new Canvas(groupCanvas, SWT.BORDER);
		canvas2 = new Canvas(groupCanvas, SWT.BORDER);
		tip = new ToolTip(shell, 0);
	}

	protected void initLayouts() {
		shell.setMinimumSize(LayoutInfo.windowWidth, LayoutInfo.windowHeight);

		shell.setLayout(new FormLayout());

		final FormData groupFilePos = new FormData();
		groupFilePos.left = new FormAttachment(0, LayoutInfo.border);
		groupFilePos.top = new FormAttachment(0, LayoutInfo.border);
		groupFilePos.right = new FormAttachment(50, -LayoutInfo.borderHalf);
		groupFilePos.height = 4 * LayoutInfo.stdHeight + 5 * LayoutInfo.border;
		groupFile.setLayoutData(groupFilePos);

		final FormData groupLambdaPos = new FormData();
		groupLambdaPos.left = new FormAttachment(50, LayoutInfo.borderHalf);
		groupLambdaPos.top = new FormAttachment(0, LayoutInfo.border);
		groupLambdaPos.right = new FormAttachment(100, -LayoutInfo.border);
		groupLambdaPos.height = 4 * LayoutInfo.stdHeight + 5
				* LayoutInfo.border;
		groupLambda.setLayoutData(groupLambdaPos);

		final FormData groupCanvasPos = new FormData();
		groupCanvasPos.left = new FormAttachment(0, LayoutInfo.border);
		assert groupFilePos.height == groupLambdaPos.height;
		groupCanvasPos.top = new FormAttachment(groupFile, LayoutInfo.border,
				SWT.BOTTOM);
		groupCanvasPos.right = new FormAttachment(100, -LayoutInfo.border);
		groupCanvasPos.bottom = new FormAttachment(100, -LayoutInfo.border);
		groupCanvas.setLayoutData(groupCanvasPos);

		final FillLayout groupLayout = new FillLayout(SWT.VERTICAL);
		groupLayout.marginWidth = LayoutInfo.border;
		groupLayout.marginHeight = LayoutInfo.border;
		groupLayout.spacing = LayoutInfo.border;
		groupFile.setLayout(groupLayout);
		groupLambda.setLayout(groupLayout);

		final FillLayout groupLayout2 = new FillLayout(SWT.VERTICAL);
		groupLayout2.marginWidth = 0;
		groupLayout2.marginHeight = 0;
		groupLayout2.spacing = LayoutInfo.border;
		groupCanvas.setLayout(groupLayout2);

		groupFile1a.setLayout(new FormLayout());
		groupFile1b.setLayout(new FormLayout());
		groupFile2a.setLayout(new FormLayout());
		groupFile2b.setLayout(new FormLayout());
		groupLambda3.setLayout(new FormLayout());
		groupLambda4.setLayout(new FormLayout());
		groupLambda5.setLayout(new FormLayout());
		groupLambda6.setLayout(new FormLayout());
	}

	protected void initInnerLayouts() {
		initOpenFile(label1a, text1a, button1a);
		initOpenFile(label1b, text1b, button1b);
		initOpenFile(label2a, text2a, button2a);
		initOpenFile(label2b, text2b, button2b);

		initSetLambda(label3l, scale3, label3r);
		initSetLambda(label4l, scale4, label4r);
		initSetLambda(label5l, scale5, label5r);
		initExec(progress, buttonExec);
	}

	protected void initStyle() {
		// groupFile.setText("Files");
		label1a.setText("Input 1");
		label1b.setText("Output 1");
		label2a.setText("Input 2");
		label2b.setText("Output 2");
		button1a.setText("Browse ...");
		button1b.setText("Browse ...");
		button2a.setText("Browse ...");
		button2b.setText("Browse ...");

		// groupLambda.setText("Prediction");
		label3l.setText("Mix SIM-2");
		label4l.setText("Mix SIM-1");
		label5l.setText("Y-based");
		scale3.setMinimum(0);
		scale3.setMaximum(LayoutInfo.scaleStep);
		scale3.setSelection(LayoutInfo.scaleStep * 3 / 4);
		scale4.setMinimum(0);
		scale4.setMaximum(LayoutInfo.scaleStep);
		scale4.setSelection(LayoutInfo.scaleStep * 3 / 4);
		scale5.setMinimum(0);
		scale5.setMaximum(LayoutInfo.scaleStep);
		scale5.setSelection(LayoutInfo.scaleStep / 2);
		label3r.setText("Keep SIM-1");
		label4r.setText("Keep SIM-2");
		label5r.setText("X-based");
		buttonExec.setText("Go");
		progress.setMinimum(0);
		progress.setMaximum(LayoutInfo.scaleStep2);
		progress.setSelection(0);

		canvas1.setBackgroundMode(SWT.INHERIT_FORCE);
		canvas2.setBackgroundMode(SWT.INHERIT_FORCE);
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

	protected void initEvents() {
		final SelectionAdapter browseEvent = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
							e1.printStackTrace();
						}
						canvas1.redraw();
					} else if (e.widget == button2a) {
						if (text2b.getText().isEmpty()) {
							text2b.setText(fileName + MiscInfo.outputExt);
							text2b.setSelection(fileName.length()
									+ MiscInfo.outputExt.length());
						}

						try {
							calc.loadFile2(fileName);
						} catch (final FileNotFoundException e1) {
							e1.printStackTrace();
						}
						canvas2.redraw();
					}
				}
			}
		};
		button1a.addSelectionListener(browseEvent);
		button1b.addSelectionListener(browseEvent);
		button2a.addSelectionListener(browseEvent);
		button2b.addSelectionListener(browseEvent);

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

		final SelectionAdapter execEvent = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text1a.getText().isEmpty() || text1b.getText().isEmpty()
						|| text2a.getText().isEmpty()
						|| text2b.getText().isEmpty()) {
					// TODO error msg
				} else {
					calc.calcFile(scale3.getSelection() * LayoutInfo.scaleUnit,
							scale4.getSelection() * LayoutInfo.scaleUnit,
							scale5.getSelection() * LayoutInfo.scaleUnit,
							new SWTProgress(progress));
					try {
						calc.saveFile(text1b.getText(), text2b.getText());
					} catch (final IOException e1) {
						e1.printStackTrace();
					}
					canvas1.redraw();
					canvas2.redraw();
				}
			}
		};
		buttonExec.addSelectionListener(execEvent);

		final PaintListener drawEvent = new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				AbstractMatrix<Float> source;
				AbstractMatrix<Float> dest;
				Float max = 1.0f;

				if (e.widget == canvas1) {
					source = calc.source1;
					dest = calc.dest1;
				} else if (e.widget == canvas2) {
					source = calc.source2;
					dest = calc.dest2;
				} else {
					// Never reach
					source = null;
					dest = null;

					assert false;
				}

				// TODO change window size

				if (source != null && dest != null) {
					assert dest.xSize() == source.xSize();
					assert dest.ySize() == source.ySize();

					for (int y = 0; y < source.ySize(); ++y) {
						for (int x = 0; x < source.xSize(); ++x) {
							if (source.get(x, y) > max) {
								max = source.get(x, y);
							}

							if (dest.get(x, y) > max) {
								max = dest.get(x, y);
							}
						}
					}

					max = (float) Math.sqrt(max);
					final int depth = MiscInfo.colorStep - 1;
					final Float scale = depth / max;

					for (int y = 0; y < source.ySize(); ++y) {
						for (int x = 0; x < source.xSize(); ++x) {
							final Color color;
							Float value = source.get(x, y);

							if (value > 0) {
								value = (float) (Math.sqrt(value) * scale);
								color = new Color(display, depth
										- Math.round(value), depth, depth
										- Math.round(value));
							} else {
								value = dest.get(x, y);
								value = (float) (Math.sqrt(value) * scale);
								color = new Color(display, depth
										- Math.round(value), depth
										- Math.round(value), depth);
							}

							e.gc.setForeground(color);
							e.gc.drawPoint(x, y);
						}
					}
				}
			}
		};
		canvas1.addPaintListener(drawEvent);
		canvas2.addPaintListener(drawEvent);

		final MouseMoveListener queryEvent = new MouseMoveListener() {
			@Override
			public void mouseMove(MouseEvent e) {
				AbstractMatrix<Float> source;
				AbstractMatrix<Float> dest;

				if (e.widget == canvas1) {
					source = calc.source1;
					dest = calc.dest1;
				} else if (e.widget == canvas2) {
					source = calc.source2;
					dest = calc.dest2;
				} else {
					// Never reach
					source = null;
					dest = null;

					assert false;
				}

				if (source != null && dest != null && e.x < source.xSize()
						&& e.y < source.ySize()) {
					assert dest.xSize() == source.xSize();
					assert dest.ySize() == source.ySize();

					String data = String.format("Pos: (%d, %d)", e.x, e.y); //$NON-NLS-1$
					if (source.get(e.x, e.y) > 0) {
						data += String.format("\nValue: %.6f", //$NON-NLS-1$
								source.get(e.x, e.y));
					}
					if (dest.get(e.x, e.y) > 0) {
						data += String.format("\nPredicted: %.6f", //$NON-NLS-1$
								dest.get(e.x, e.y));
					}

					tip.setText(data);
					tip.setAutoHide(true);
					tip.setVisible(true);
				}
			}
		};
		canvas1.addMouseMoveListener(queryEvent);
		canvas2.addMouseMoveListener(queryEvent);

		final MouseTrackListener queryExitListener = new MouseTrackListener() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				// Nothing

			}

			@Override
			public void mouseExit(MouseEvent arg0) {
				tip.setVisible(false);
			}

			@Override
			public void mouseHover(MouseEvent arg0) {
				// Nothing

			}
		};
		canvas1.addMouseTrackListener(queryExitListener);
		canvas2.addMouseTrackListener(queryExitListener);

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

	public void exec() {
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
