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
	protected Composite groupQuery;

	protected BuilderFile contentFile;
	protected BuilderLambda contentLambda;
	protected BuilderExec contentExec;
	protected BuilderCanvas contentCanvas;
	protected BuilderQuery contentQuery;

	public BuilderAll(Composite target, Display targetDisplay, Shell targetShell) {
		super(target);

		display = targetDisplay;
		shell = targetShell;
		calc = new FileCalcContainer() {
			@Override
			public void onChanged1() {
				contentCanvas.update1();
				contentQuery.update();
			}

			@Override
			public void onChanged2() {
				contentCanvas.update2();
				contentQuery.update();
			}
		};
	}

	@Override
	protected void initWidgets() {
		groupFile = new Group(parent, 0);
		groupLambda = new Group(parent, 0);
		groupExec = new Composite(parent, SWT.NONE);
		groupCanvas = new Composite(parent, SWT.NONE);
		groupQuery = new Group(parent, 0);

		tip = new ToolTip(shell, 0);

		contentFile = new BuilderFile(groupFile, shell, calc);
		contentLambda = new BuilderLambda(groupLambda);
		contentExec = new BuilderExec(groupExec, calc, contentFile,
				contentLambda);
		contentCanvas = new BuilderCanvas(groupCanvas, display, tip, calc);
		contentQuery = new BuilderQuery(groupQuery, calc);

		contentFile.initWidgets();
		contentLambda.initWidgets();
		contentExec.initWidgets();
		contentCanvas.initWidgets();
		contentQuery.initWidgets();
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
		groupCanvasPos.right = new FormAttachment(100, -LayoutInfo.stdWidth - 4
				* LayoutInfo.border);
		groupCanvasPos.bottom = new FormAttachment(100, -LayoutInfo.border);
		groupCanvas.setLayoutData(groupCanvasPos);

		final FormData groupQueryPos = new FormData();
		groupQueryPos.top = new FormAttachment(groupExec, LayoutInfo.border,
				SWT.BOTTOM);
		groupQueryPos.right = new FormAttachment(100, -LayoutInfo.border);
		groupQueryPos.width = LayoutInfo.stdWidth + 2 * LayoutInfo.border;
		groupQueryPos.height = 9 * LayoutInfo.stdHeight + 10
				* LayoutInfo.border - 18 * LayoutInfo.borderLabel;
		groupQuery.setLayoutData(groupQueryPos);

		final FillLayout groupLayout = new FillLayout(SWT.VERTICAL);
		groupLayout.marginWidth = LayoutInfo.border;
		groupLayout.marginHeight = LayoutInfo.border;
		groupLayout.spacing = LayoutInfo.border;
		groupFile.setLayout(groupLayout);
		groupLambda.setLayout(groupLayout);
		groupQuery.setLayout(groupLayout);

		groupExec.setLayout(new FormLayout());

		contentFile.initLayouts();
		contentLambda.initLayouts();
		contentExec.initLayouts();
		contentCanvas.initLayouts();
		contentQuery.initLayouts();
	}

	@Override
	protected void initStyle() {
		contentFile.initStyle();
		contentLambda.initStyle();
		contentExec.initStyle();
		contentCanvas.initStyle();
		contentQuery.initStyle();
	}

	@Override
	protected void initEvents() {
		contentFile.initEvents();
		contentLambda.initEvents();
		contentExec.initEvents();
		contentCanvas.initEvents();
		contentQuery.initEvents();
	}
}
