package gui;

import matrix_math.AbstractMatrix;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolTip;

import performance_calc.FileCalcContainer;

public class BuilderCanvas extends GUIBuilder {
	protected Display display;
	protected ToolTip tip;
	protected FileCalcContainer calc;

	protected Canvas canvas1;
	protected Canvas canvas2;

	public BuilderCanvas(Composite target, Display targetDisplay,
			ToolTip targetTip, FileCalcContainer targetCalc) {
		super(target);

		display = targetDisplay;
		tip = targetTip;
		calc = targetCalc;
	}

	@Override
	protected void initWidgets() {
		canvas1 = new Canvas(parent, SWT.BORDER);
		canvas2 = new Canvas(parent, SWT.BORDER);
	}

	@Override
	protected void initLayouts() {
		final FillLayout groupLayout = new FillLayout(SWT.VERTICAL);
		groupLayout.marginWidth = 0;
		groupLayout.marginHeight = 0;
		groupLayout.spacing = LayoutInfo.border;
		parent.setLayout(groupLayout);
	}

	@Override
	protected void initStyle() {
		canvas1.setBackgroundMode(SWT.INHERIT_FORCE);
		canvas2.setBackgroundMode(SWT.INHERIT_FORCE);
	}

	@Override
	protected void initEvents() {
		final PaintListener drawEvent = new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				try {
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

								/*
								 * if (dest.get(x, y) > max) { max = dest.get(x,
								 * y); }
								 */
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

									if (value > depth) {
										value = (float) depth;
									}

									color = new Color(display, depth
											- Math.round(value), depth, depth
											- Math.round(value));
								} else {
									value = dest.get(x, y);

									value = (float) (Math.sqrt(value) * scale);
									if (value > depth) {
										value = (float) depth;
									}

									color = new Color(display, depth
											- Math.round(value), depth
											- Math.round(value), depth);
								}

								e.gc.setForeground(color);
								e.gc.drawPoint(x, y);
							}
						}
					}
				} finally {
					// Nothing
				}
			}
		};
		canvas1.addPaintListener(drawEvent);
		canvas2.addPaintListener(drawEvent);

		final MouseMoveListener queryEvent = new MouseMoveListener() {
			@Override
			public void mouseMove(MouseEvent e) {
				try {
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
							data += String.format(System.lineSeparator()
									+ "Value: %.6f", //$NON-NLS-1$
									source.get(e.x, e.y));
						}
						if (dest.get(e.x, e.y) > 0) {
							data += String.format(System.lineSeparator()
									+ "Predicted: %.6f", //$NON-NLS-1$
									dest.get(e.x, e.y));
						}

						tip.setText(data);
						tip.setAutoHide(true);
						tip.setVisible(true);
					}
				} finally {
					// Nothing
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
				try {
					tip.setVisible(false);
				} finally {
					// Nothing
				}
			}

			@Override
			public void mouseHover(MouseEvent arg0) {
				// Nothing

			}
		};
		canvas1.addMouseTrackListener(queryExitListener);
		canvas2.addMouseTrackListener(queryExitListener);
	}

	public void update1() {
		canvas1.redraw();
	}

	public void update2() {
		canvas2.redraw();
	}
}
