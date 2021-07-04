package com.gide.assessment.providers;

import java.io.File;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import com.gide.assessment.util.AppUtil;

public class TreeViewSorter extends ViewerComparator {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof File && e2 instanceof File) {
			return AppUtil.compareFilesizes(e1, e2);
		} else if (e1 instanceof String) {
			return ((String) e1).compareTo((String) e2);
		}
		return super.compare(viewer, e1, e2);
	}
}
