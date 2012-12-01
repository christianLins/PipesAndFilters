package image.beans.info;

import image.beans.ShowBean;
import image.events.PlanarImageEvent;

import java.beans.BeanDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

public class ShowBeanInfo extends SimpleBeanInfo {
	
	public ShowBeanInfo() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public BeanDescriptor getBeanDescriptor() {
		BeanDescriptor beanDescriptor = new BeanDescriptor(ShowBean.class);
		beanDescriptor.setDisplayName("Show a image");
		beanDescriptor.setHidden(false);
		beanDescriptor.setName("Show Bean image");
		beanDescriptor.setShortDescription("bean to show image from pipe");
		return beanDescriptor;
	}
	
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor[] props = {};
		return props;

	}
	
	@Override
	public EventSetDescriptor[] getEventSetDescriptors() {
		try {
			EventSetDescriptor desc = new EventSetDescriptor(ShowBean.class, "handlePlanarImageEvent", PlanarImageEvent.class , "handlePlanarImageEvent");
			EventSetDescriptor[] descs = new EventSetDescriptor[1];
			descs[0] = desc;
			return descs;
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 EventSetDescriptor esds[] = {  };
		    return esds;
		
	}
	
	@Override
	public MethodDescriptor[] getMethodDescriptors() {
		try {
			Method m = ShowBean.class.getMethod("handlePlanarImageEvent");
			
			MethodDescriptor desc = new MethodDescriptor(m);
			MethodDescriptor[] descs = new MethodDescriptor[1];
			descs[0] = desc;
			return descs;
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 MethodDescriptor mds[] = {  };
		    return mds;
	}
	
	
	
}
