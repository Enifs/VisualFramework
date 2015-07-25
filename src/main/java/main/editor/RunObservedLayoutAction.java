
package main.editor;


import main.elements.button.ButtonAction;
import main.elements.Element;
import main.layout.Layout;


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
