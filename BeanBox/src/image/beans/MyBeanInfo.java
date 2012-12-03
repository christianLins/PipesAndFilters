package image.beans;

import image.events.PlanarImageEvent;
import image.events.PlanarImageEventListener;

import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

/**
 * Specifies Events, Methods and Properties
 * @author Lucia
 *
 */
public abstract class MyBeanInfo extends SimpleBeanInfo {
	
	protected Class myClass;
	
	public MyBeanInfo(Class myClass) {
		super();
		this.myClass = myClass;		
	}
	
	@Override
	public EventSetDescriptor[] getEventSetDescriptors() {
		
		Class c = myClass;
		String s = "PlanarImage";
		Class listenerC = PlanarImageEventListener.class;
		String methodeName = "handlePlanarImageEvent";
		
		EventSetDescriptor evtDesc = null;
		try {
			evtDesc = new EventSetDescriptor(c, s, listenerC, methodeName);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		EventSetDescriptor[] evtDescriptor = {evtDesc};
		
		return evtDescriptor;
	}
	
	@Override
	public MethodDescriptor[] getMethodDescriptors() {
		
		Class c = myClass;
		Class paramTypes[] = new Class[1];
		paramTypes[0] = PlanarImageEvent.class;
		String methodName = "handlePlanarImageEvent";
		
		Method method = null;
		try {
			method = c.getMethod(methodName, paramTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		ParameterDescriptor paramDesc[] = new ParameterDescriptor[1];
		paramDesc[0] = new ParameterDescriptor();
		MethodDescriptor methodeDesc = new MethodDescriptor(method,paramDesc);
		MethodDescriptor methodeDescriptor[] = {methodeDesc};
		
		return methodeDescriptor;
	}
	
	/**
	 * can be changed in each beanInfo and therefore enables a 
	 * specification of the properties
	 */
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		return new PropertyDescriptor[] {};
	}

}
