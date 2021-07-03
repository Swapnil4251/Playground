package com.gide.assessment.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.gide.assessment.view.FileBrowserView;
import com.gide.assessment.view.ScanFilesDialog;

public class ScanFilesHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		ScanFilesDialog dialog = new ScanFilesDialog(window.getShell(), getBrowseDirectory(window));
		dialog.open();
		return null;
	}
	
	@SuppressWarnings("deprecation")
	private String getBrowseDirectory(IWorkbenchWindow window) {
		return ((FileBrowserView) window.getPages()[0].getViews()[0]).getRootDirectory();
	}
}
