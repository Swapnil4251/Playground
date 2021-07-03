package com.gide.assessment.view;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.gide.assessment.logic.ScanResultModel;

public class ScanFilesDialog extends Dialog {

	private Text text;
	private String searchText;
	private ScanResultModel model;

	public ScanFilesDialog(Shell parentShell, String scanDirectory) {
		super(parentShell);
		this.model = new ScanResultModel(scanDirectory);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		createSearchTextField(container);

		return area;
	}

	private void createSearchTextField(Composite container) {
		Label lbtFirstName = new Label(container, SWT.NONE);
		lbtFirstName.setText("Search text in files");

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;

		text = new Text(container, SWT.BORDER);
		text.setLayoutData(dataFirstName);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		this.searchText = text.getText();
		executeScanFilesOperationAsJob();
		super.okPressed();
	}

	private void executeScanFilesOperationAsJob() {
		Job job = new Job("Scanning files...") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				model.scanFiles(monitor, searchText);
				Display.getDefault().asyncExec(() -> {
					ScanResultDialog dialog = new ScanResultDialog(getParentShell(), model);
					dialog.open();
				});
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();
	}
}
