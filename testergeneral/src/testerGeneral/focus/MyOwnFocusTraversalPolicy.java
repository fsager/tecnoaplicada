package testerGeneral.focus;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

import javax.swing.JLabel;

public class MyOwnFocusTraversalPolicy extends FocusTraversalPolicy
{
	Vector<Component> order;

	public MyOwnFocusTraversalPolicy(Vector<Component> order) {
		this.order = new Vector<Component>(order.size());
		this.order.addAll(order);
	}
	
	public Component getComponentAfter(Container focusCycleRoot,Component aComponent)
	{

		int idx = (order.indexOf(aComponent) + 1) % order.size();
		
		Component comp=order.get(idx);
		if(!comp.isEnabled() || !comp.isFocusable())
			comp=getComponentAfter(focusCycleRoot,order.get(idx));
		
		return comp;
	}

	public Component getComponentBefore(Container focusCycleRoot,Component aComponent)
	{
		int idx = order.indexOf(aComponent) - 1;
		if (idx < 0) {
			idx = order.size() - 1;			
		}
		
		Component comp=order.get(idx);
		
		if(!comp.isEnabled())
			comp=getComponentBefore(focusCycleRoot,order.get(idx));	
		
		return comp;
	}

	public Component getDefaultComponent(Container focusCycleRoot) {
		return order.get(0);
	}
	
	public Component getLastComponent(Container focusCycleRoot) {
		return order.lastElement();
	}
	
	public Component getFirstComponent(Container focusCycleRoot) {
		return order.get(0);
	}
}


