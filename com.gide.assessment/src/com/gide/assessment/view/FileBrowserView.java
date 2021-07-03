package com.gide.assessment.view;

import java.io.File;
import java.net.URL;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.gide.assessment.Application;
import com.gide.assessment.logic.FileBrowsingModel;
import com.gide.assessment.logic.ScanModelDataBinding;
import com.gide.assessment.messages.Messages;
import com.gide.assessment.providers.NumericValueLabelProvider;
import com.gide.assessment.providers.ViewContentProvider;
import com.gide.assessment.providers.ViewLabelProvider;

public class FileBrowserView extends ViewPart {

	public static final String ID = "com.gide.assessment.view.filebrowser";
	private static final Logger logger = Logger.getLogger(FileBrowserView.class);

	@Inject
	IWorkbench workbench;

	private String rootDirectory;
	private TreeViewer viewer;
	private Text searchText;
	private Label selectedDirectoryLabel;
	private FileBrowsingModel model;

	@Override
	public void createPartControl(Composite parent) {

		this.setBrowseDirectory(openDialogAndGetRootFolderToBrowse(parent, false));

		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);

		searchText = new Text(parent, SWT.SEARCH);
		searchText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		searchText.setToolTipText("Search files");

		searchText.addModifyListener(event -> {
			String str = ((Text) event.getSource()).getText();
			if (str != null && !str.isBlank()) {
				viewer.setInput(getModel().filterFiles(str));
			}
		});

		Button button = new Button(parent, SWT.PUSH);
		button.setText(Messages.getProperty("BrowseButton"));

		button.addListener(SWT.Selection, event -> {
			setBrowseDirectory(openDialogAndGetRootFolderToBrowse(parent, true));
			updateWidgets();
		});

		selectedDirectoryLabel = new Label(parent, SWT.HORIZONTAL);
		selectedDirectoryLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		viewer = new TreeViewer(parent);
		viewer.setContentProvider(new ViewContentProvider(getModel()));

		viewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		viewer.getTree().setHeaderVisible(true);

		TreeViewerColumn mainColumn = new TreeViewerColumn(viewer, SWT.NONE);
		mainColumn.getColumn().setText(Messages.getProperty("NameColumnLabel"));
		mainColumn.getColumn().setWidth(200);
		mainColumn.setLabelProvider(
				new DelegatingStyledCellLabelProvider(new ViewLabelProvider(createImageDescriptor())));

		TreeViewerColumn fileSizeColumn = new TreeViewerColumn(viewer, SWT.NONE);
		fileSizeColumn.getColumn().setText(Messages.getProperty("FilesizeLabel"));
		fileSizeColumn.getColumn().setWidth(100);
		fileSizeColumn.getColumn().setAlignment(SWT.RIGHT);
		fileSizeColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new NumericValueLabelProvider()));

		viewer.getTree().setSortColumn(fileSizeColumn.getColumn());
		viewer.getTree().setSortDirection(SWT.UP);
		viewer.addFilter(new TreeViewerFilter());
		viewer.addDoubleClickListener(this::openFile);
		
		ScanModelDataBinding.bind(this, this.getModel());
		updateWidgets();

	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private void openFile(DoubleClickEvent event) {
		File file = null;
		Object selection = ((TreeSelection) event.getSelection()).getFirstElement();
		if (selection instanceof File) {
			file = (File) selection;
		}
		if (file != null) {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				IFileStore fileStore = EFS.getStore(file.toURI());
				IEditorInput input = new FileStoreEditorInput(fileStore);
				page.openEditor(input, EditorsUI.DEFAULT_TEXT_EDITOR_ID);
			} catch (CoreException e) {
				logger.error(e);
			}
		}
	}

	private Object[] browseFiles(String folderPath) {
		return getModel().browseFiles(folderPath, "");
	}

	private String openDialogAndGetRootFolderToBrowse(Composite parent, boolean showAtStart) {
		String rootDir = Application.DEFAULT_ROOT_DIRECTORY;
		if (showAtStart) {
			try {
				boolean isYes = MessageDialog.openQuestion(parent.getShell(), Messages.getProperty("Browse.title"),
						Messages.getProperty("Browse.message"));
				if (isYes) {
					DirectoryDialog dialog = new DirectoryDialog(parent.getShell());
					dialog.setFilterPath(rootDir);
					rootDir = dialog.open();
					logger.info("Selected folder : " + rootDir);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rootDir;
	}

	public void setBrowseDirectory(String directory) {
		this.rootDirectory = directory;
		this.getModel().setRootDirectory(directory);
	}

	public String getBrowseDirectory() {
		return this.rootDirectory;
	}

	private void updateWidgets() {
		this.viewer.setInput(browseFiles(getBrowseDirectory()));
		this.selectedDirectoryLabel.setText("Selected folder: " + getBrowseDirectory());
	}

	public FileBrowsingModel getModel() {
		if (model == null) {
			model = new FileBrowsingModel(this.rootDirectory);
		}
		return model;
	}

	private ImageDescriptor createImageDescriptor() {
		Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path("icons/folder.png"), null);
		return ImageDescriptor.createFromURL(url);
	}

	public String getRootDirectory() {
		return rootDirectory;
	}

	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	public void setViewer(TreeViewer viewer) {
		this.viewer = viewer;
	}

	public Text getSearchText() {
		return searchText;
	}

	public void setSearchText(Text searchText) {
		this.searchText = searchText;
	}

	public Label getSelectedDirectoryLabel() {
		return selectedDirectoryLabel;
	}

	public void setSelectedDirectoryLabel(Label selectedDirectoryLabel) {
		this.selectedDirectoryLabel = selectedDirectoryLabel;
	}

	public void setModel(FileBrowsingModel model) {
		this.model = model;
	}
}