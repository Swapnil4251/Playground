package com.gide.assessment.logic;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.ui.part.ViewPart;

import com.gide.assessment.view.FileBrowserView;

public class ScanModelDataBinding {
	
	public static void bind(ViewPart view, ModelObject model) {
		FileBrowserView fileView = (FileBrowserView) view;
		DataBindingContext dbc = new DataBindingContext();

		IObservableValue modelObservable = BeansObservables.observeValue(model, "searchText");
		dbc.bindValue(SWTObservables.observeText(fileView.getSearchText(), SWT.Modify), modelObservable, null, null);
	}
}
