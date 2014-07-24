package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
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
	protected Group groupFile1a;
	protected Group groupFile1b;
	protected Group groupFile2a;
	protected Group groupFile2b;
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
	protected Group groupLambda3;
	protected Group groupLambda4;
	protected Group groupLambda5;
	protected Label label3l;
	protected Label label3r;
	protected Label label4l;
	protected Label label4r;
	protected Label label5l;
	protected Label label5r;
	protected Scale scale3;
	protected Scale scale4;
	protected Scale scale5;
	protected ProgressBar progress;

	public Window() {
		initWidgets();
		initLayouts();
		initSizes();
		initStyle();
	}

	protected void initWidgets() {
		display = new Display();

		shell = new Shell(display);

		groupFile = new Group(shell, 0);
		groupFile1a = new Group(groupFile, 0);
		groupFile1b = new Group(groupFile, 0);
		groupFile2a = new Group(groupFile, 0);
		groupFile2b = new Group(groupFile, 0);
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
		groupLambda3 = new Group(groupLambda, 0);
		groupLambda4 = new Group(groupLambda, 0);
		groupLambda5 = new Group(groupLambda, 0);
		label3l = new Label(groupLambda3, 0);
		label4l = new Label(groupLambda4, 0);
		label5l = new Label(groupLambda5, 0);
		scale3 = new Scale(groupLambda3, 0);
		scale4 = new Scale(groupLambda4, 0);
		scale5 = new Scale(groupLambda5, 0);
		label3r = new Label(groupLambda3, 0);
		label4r = new Label(groupLambda4, 0);
		label5r = new Label(groupLambda5, 0);

		progress = new ProgressBar(shell, 0);
	}

	protected void initLayouts() {
		shell.setMinimumSize(LayoutInfo.windowWidth, LayoutInfo.windowHeight);
		shell.setSize(LayoutInfo.windowWidth, LayoutInfo.windowHeight);

		shell.setLayout(new FormLayout());

		final FormData groupFilePos = new FormData();
		groupFilePos.left = new FormAttachment(0, LayoutInfo.border);
		groupFilePos.top = new FormAttachment(0, LayoutInfo.border);
		groupFilePos.right = new FormAttachment(50, -LayoutInfo.borderHalf);
		groupFilePos.height = 4 * LayoutInfo.stdHeight + 5 * LayoutInfo.border;
		groupFile.setLayoutData(groupFilePos);
		// groupFile.setBounds(0, 0, 100, 100);

		final FormData groupLambdaPos = new FormData();
		groupLambdaPos.left = new FormAttachment(50, LayoutInfo.borderHalf);
		groupLambdaPos.top = new FormAttachment(0, LayoutInfo.border);
		groupLambdaPos.right = new FormAttachment(100, -LayoutInfo.borderHalf);
		groupLambdaPos.height = 3 * LayoutInfo.stdHeight + 4
				* LayoutInfo.border;
		groupLambda.setLayoutData(groupLambdaPos);

		final FillLayout groupLayout = new FillLayout(SWT.VERTICAL);
		groupLayout.marginWidth = LayoutInfo.border;
		groupLayout.marginHeight = LayoutInfo.border;
		groupLayout.spacing = LayoutInfo.border;
		groupFile.setLayout(groupLayout);
		groupLambda.setLayout(groupLayout);

		final FillLayout groupLineLayout = new FillLayout(SWT.HORIZONTAL);
		groupLineLayout.marginWidth = 0;
		groupLineLayout.marginHeight = 0;
		groupLineLayout.spacing = LayoutInfo.border;
		groupFile1a.setLayout(groupLineLayout);
		groupFile1b.setLayout(groupLineLayout);
		groupFile2a.setLayout(groupLineLayout);
		groupFile2b.setLayout(groupLineLayout);
		groupLambda3.setLayout(groupLineLayout);
		groupLambda4.setLayout(groupLineLayout);
		groupLambda5.setLayout(groupLineLayout);
	}

	protected void initSizes() {

		initOpenFile(label1a, text1a, button1a);
		initOpenFile(label1b, text1b, button1b);
		initOpenFile(label2a, text2a, button2a);
		initOpenFile(label2b, text2b, button2b);

		initSetLambda(label3l, scale3, label3r);
		initSetLambda(label4l, scale4, label4r);
		initSetLambda(label5l, scale5, label5r);
	}

	protected void initStyle() {
	}

	protected void initOpenFile(Label label, Text text, Button button) {
		label.setSize(LayoutInfo.stdWidth, LayoutInfo.stdHeight);
		button.setSize(LayoutInfo.stdWidth, LayoutInfo.stdHeight);
	}

	protected void initSetLambda(Label label1, Scale scale, Label label2) {
		label1.setSize(LayoutInfo.stdWidth, LayoutInfo.stdHeight);
		label2.setSize(LayoutInfo.stdWidth, LayoutInfo.stdHeight);
	}

	public void exec() {
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}
}
