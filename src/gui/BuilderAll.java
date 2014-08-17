package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;

import performance_calc.FileCalcContainer;

public class BuilderAll extends GUIBuilder {
	protected Display display;
	protected Shell shell;

	protected ToolTip tip;
	protected FileCalcContainer calc;

	protected Group groupFile;
	protected Group groupLambda;
	protected Composite groupExec;
	protected Composite groupCanvas;

	protected BuilderFile contentFile;
	protected BuilderLambda contentLambda;
	protected BuilderExec contentExec;
	protected BuilderCanvas contentCanvas;

	public BuilderAll(Composite target, Display targetDisplay, Shell targetShell) {
		super(target);

		display = targetDisplay;
		shell = targetShell;
		calc = new FileCalcContainer() {
			@Override
			public void onChanged1() {
				contentCanvas.update1();
			}

			@Override
			public void onChanged2() {
				contentCanvas.update2();
			}
		};
	}

	@Override
	protected void initWidgets() {
		groupFile = new Group(parent, 0);
		groupLambda = new Group(parent, 0);
		groupExec = new Composite(parent, SWT.NONE);
		groupCanvas = new Composite(parent, SWT.NONE);

		tip = new ToolTip(shell, 0);

		contentFile = new BuilderFile(groupFile, shell, calc);
		contentLambda = new BuilderLambda(groupLambda);
		contentExec = new BuilderExec(groupExec, calc, contentFile,
				contentLambda);
		contentCanvas = new BuilderCanvas(groupCanvas, display, tip, calc);

		contentFile.initWidgets();
		contentLambda.initWidgets();
		contentExec.initWidgets();
		contentCanvas.initWidgets();
	}

	@Override
	protected void initLayouts() {
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

		final FormData groupExecPos = new FormData();
		groupExecPos.left = new FormAttachment(0, LayoutInfo.border);
		groupExecPos.top = new FormAttachment(groupFile, LayoutInfo.border,
				SWT.BOTTOM);
		groupExecPos.right = new FormAttachment(100, -LayoutInfo.border);
		groupExecPos.height = LayoutInfo.stdHeight;
		groupExec.setLayoutData(groupExecPos);

		final FormData groupCanvasPos = new FormData();
		groupCanvasPos.left = new FormAttachment(0, LayoutInfo.border);
		assert groupFilePos.height == groupLambdaPos.height;
		groupCanvasPos.top = new FormAttachment(groupExec, LayoutInfo.border,
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

		groupExec.setLayout(new FormLayout());

		contentFile.initLayouts();
		contentLambda.initLayouts();
		contentExec.initLayouts();
		contentCanvas.initLayouts();
	}

	@Override
	protected void initStyle() {
		contentFile.initStyle();
		contentLambda.initStyle();
		contentExec.initStyle();
		contentCanvas.initStyle();
	}

	@Override
	protected void initEvents() {
		contentFile.initEvents();
		contentLambda.initEvents();
		contentExec.initEvents();
		contentCanvas.initEvents();
	}
}
