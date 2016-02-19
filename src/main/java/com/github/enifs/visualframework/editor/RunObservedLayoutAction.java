
//
//  Armands Vitols
//  Didzis Romanovskis
//
//  VisualFramework 2016 under GPLv3
//

package com.github.enifs.visualframework.editor;


import com.github.enifs.visualframework.elements.Element;
import com.github.enifs.visualframework.elements.button.ButtonAction;
import com.github.enifs.visualframework.layout.Layout;


public class RunObservedLayoutAction extends ButtonAction
{
	public RunObservedLayoutAction(Element foreignRoot)
	{
		this.foreignRoot = foreignRoot;
	}

	@Override
	public void action()
	{
		Layout layout = new Layout(this.foreignRoot, null);
		layout.run();
	}

	Element foreignRoot;
}
