package com.gide.assessment.providers;

import java.io.File;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;

import com.gide.assessment.logic.SearchResult;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;

public class NumericValueLabelProvider extends LabelProvider implements IStyledLabelProvider {

	@Override
	public StyledString getStyledText(Object element) {
		if (element instanceof File) {
			File file = (File) element;
			if (file.isDirectory()) {
				return new StyledString("0");
			}
			return new StyledString(String.valueOf(file.length()));
		} else if (element instanceof SearchResult) {
			SearchResult result = (SearchResult) element;
			return new StyledString(String.valueOf(result.getLines().size()));
		}
		return new StyledString();
	}
}
