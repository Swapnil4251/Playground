package com.gide.assessment.providers;

import java.io.File;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.gide.assessment.logic.FileBrowsingModel;

public class ViewContentProvider implements ITreeContentProvider {

	@Inject
	FileBrowsingModel model;
	
	public ViewContentProvider(FileBrowsingModel model) {
		this.model = model;
	}
	
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return (Object[]) inputElement;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		File file = (File) parentElement;
		return model.browseFiles(file.getAbsolutePath());
	}

	@Override
	public Object getParent(Object element) {
		File file = (File) element;
		return file.getParentFile();
	}

	@Override
	public boolean hasChildren(Object element) {
		File file = (File) element;
		if (file.isDirectory()) {
			return true;
		}
		return false;
	}
}
