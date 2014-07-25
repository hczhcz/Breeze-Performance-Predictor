package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

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

	public Window() {
		initWidgets();
		initLayouts();
		initInnerLayouts();
		initStyle();
	}

	protected void initWidgets() {
		display = new Display();

		shell = new Shell(display);

		groupFile = new Group(shell, 0);
		groupFile1a = new Composite(groupFile, SWT.NONE);
		groupFile1b = new Composite(groupFile, SWT.NONE);
		groupFile2a = new Composite(groupFile, SWT.NONE);
		groupFile2b = new Composite(groupFile, SWT.NONE);
		label1a = new Label(groupFile1a, 0);
		label1b = new Label(groupFile1b, 0);
		label2a = new Label(groupFile2a, 0);
		label2b = new Label(groupFile2b, 0);
		text1a = new Text(groupFile1a, 0);
		text1b = new Text(groupFile1b, 0);
		text2a = new Text(groupFile2a, 0);
		text2b = new Text(groupFile2b, 0);
		button1a = new Button(groupFile1a, 0);
		button1b = new Button(groupFile1b, 0);
		button2a = new Button(groupFile2a, 0);
		button2b = new Button(groupFile2b, 0);

		groupLambda = new Group(shell, 0);
		groupLambda3 = new Composite(groupLambda, SWT.NONE);
		groupLambda4 = new Composite(groupLambda, SWT.NONE);
		groupLambda5 = new Composite(groupLambda, SWT.NONE);
		label3l = new Label(groupLambda3, 0);
		label4l = new Label(groupLambda4, 0);
		label5l = new Label(groupLambda5, 0);
		scale3 = new Scale(groupLambda3, 0);
		scale4 = new Scale(groupLambda4, 0);
		scale5 = new Scale(groupLambda5, 0);
		label3r = new Label(groupLambda3, 0);
		label4r = new Label(groupLambda4, 0);
		label5r = new Label(groupLambda5, 0);
		buttonExec = new Button(groupLambda, 0);

		progress = new ProgressBar(shell, 0);
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
		groupLambdaPos.right = new FormAttachment(100, -LayoutInfo.borderHalf);
		groupLambdaPos.height = 4 * LayoutInfo.stdHeight + 5
				* LayoutInfo.border;
		groupLambda.setLayoutData(groupLambdaPos);

		final FormData progressPos = new FormData();
		progressPos.left = new FormAttachment(0, LayoutInfo.border);
		progressPos.right = new FormAttachment(100, -LayoutInfo.border);
		progressPos.bottom = new FormAttachment(100, -LayoutInfo.border);
		progressPos.height = LayoutInfo.stdHeight;
		progress.setLayoutData(progressPos);

		final FillLayout groupLayout = new FillLayout(SWT.VERTICAL);
		groupLayout.marginWidth = LayoutInfo.border;
		groupLayout.marginHeight = LayoutInfo.border;
		groupLayout.spacing = LayoutInfo.border;
		groupFile.setLayout(groupLayout);
		groupLambda.setLayout(groupLayout);

		groupFile1a.setLayout(new FormLayout());
		groupFile1b.setLayout(new FormLayout());
		groupFile2a.setLayout(new FormLayout());
		groupFile2b.setLayout(new FormLayout());
		groupLambda3.setLayout(new FormLayout());
		groupLambda4.setLayout(new FormLayout());
		groupLambda5.setLayout(new FormLayout());
	}

	protected void initInnerLayouts() {
		initOpenFile(label1a, text1a, button1a);
		initOpenFile(label1b, text1b, button1b);
		initOpenFile(label2a, text2a, button2a);
		initOpenFile(label2b, text2b, button2b);

		initSetLambda(label3l, scale3, label3r);
		initSetLambda(label4l, scale4, label4r);
		initSetLambda(label5l, scale5, label5r);
	}

	protected void initStyle() {
		label1a.setText("Input 1");
		label1b.setText("Output 1");
		label2a.setText("Input 2");
		label2b.setText("Output 2");
		button1a.setText("Browse ...");
		button1b.setText("Browse ...");
		button2a.setText("Browse ...");
		button2b.setText("Browse ...");
		label3l.setText("Mix SIM-2");
		label4l.setText("Keep SIM-1");
		label5l.setText("Y-based");
		label3r.setText("Mix SIM-1");
		label4r.setText("Keep SIM-2");
		label5r.setText("X-based");
		buttonExec.setText("Go");
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
		rPos.right = new FormAttachment(100, -LayoutInfo.borderLabel);
		rPos.bottom = new FormAttachment(100);
		rPos.width = LayoutInfo.stdWidth;
		label2.setLayoutData(rPos);

		label1.setAlignment(SWT.RIGHT);
		label2.setAlignment(SWT.LEFT);
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
