package com.gide.assessment.providers;

import org.eclipse.jface.viewers.ITreeContentProvider;

import com.gide.assessment.logic.ScanResultModel;
import com.gide.assessment.logic.SearchResult;

public class ScanResultContentProvider implements ITreeContentProvider {
	
	ScanResultModel model;
	
	public ScanResultContentProvider(ScanResultModel model) {
		this.model = model;
	}
	
	public Object[] getElements(Object element) {
		return model.getScanResult().values().toArray();
	}

	@Override
	public Object[] getChildren(Object element) {
		return ((SearchResult) element).getLines().toArray();
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof SearchResult) {
			return ((SearchResult) element).getLines().size() > 0;
		}
		return false;
	}
}
