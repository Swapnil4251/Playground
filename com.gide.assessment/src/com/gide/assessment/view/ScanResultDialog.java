package com.gide.assessment.view;

import java.net.URL;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.gide.assessment.logic.ScanResultModel;
import com.gide.assessment.logic.SearchResult;
import com.gide.assessment.messages.Messages;
import com.gide.assessment.providers.NumericValueLabelProvider;
import com.gide.assessment.providers.ScanResultContentProvider;
import com.gide.assessment.providers.ViewLabelProvider;

public class ScanResultDialog extends Dialog {

	Map<String, SearchResult> result;
	private ScanResultModel model;
	
	public ScanResultDialog(Shell parentShell, ScanResultModel model) {
		super(parentShell);
		this.model = model;
		this.result = model.getScanResult();
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Label label = new Label(area, SWT.NONE);
		label.setText(getSearchResultMessage());
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);
		
		TreeViewer viewer = new TreeViewer(container);

		viewer.setContentProvider(new ScanResultContentProvider(model));

		Tree tree = viewer.getTree();
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));

		TreeViewerColumn tc = new TreeViewerColumn(viewer, SWT.NONE);
		tc.getColumn().setText(Messages.getProperty("NameColumnLabel"));
		tc.getColumn().setWidth(200);
		tc.setLabelProvider(
				new DelegatingStyledCellLabelProvider(new ViewLabelProvider(createImageDescriptor())));
		
		tc = new TreeViewerColumn(viewer, SWT.NONE);
		tc.getColumn().setText(Messages.getProperty("SearchCount"));
		tc.getColumn().setWidth(200);
		tc.setLabelProvider(new DelegatingStyledCellLabelProvider(new NumericValueLabelProvider()));
		
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		
		viewer.setInput(result.values());
		
		return area;
	}

	private ImageDescriptor createImageDescriptor() {
		Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path("icons/file.png"), null);
		return ImageDescriptor.createFromURL(url);
	}
	
	private String getSearchResultMessage() {
		int totalSearchCount = model.getScanResult().values().stream().mapToInt(result -> result.getLines().size()).sum();
		return Messages.getProperty(totalSearchCount > 0 ? "SearchResult.message" : "SearchResult.NoTextFound", 
				model.getSearchText(), model.getScanResult().size(), totalSearchCount);
	}
	
	@Override
	protected boolean isResizable() {
		return true;
	}
}
