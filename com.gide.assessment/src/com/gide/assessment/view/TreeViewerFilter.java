package com.gide.assessment.view;

import java.io.File;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.gide.assessment.util.AppUtil;

public class TreeViewerFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parent, Object element) {
		if (element instanceof File) {
			File file = (File) element;
			return file.isDirectory() || "txt".equalsIgnoreCase(AppUtil.getFileExtension((File) element));
		}
		return false;
	}

}
