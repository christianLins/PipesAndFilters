package image.beans;

import image.beans.AndOrBean;

import java.beans.PropertyDescriptor;

public class AndOrBeanBeanInfo extends MyBeanInfo {

	public AndOrBeanBeanInfo() {
		super(AndOrBean.class);
	}
	
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor propDesc1;
        PropertyDescriptor[] propDescriptors = null;
        
        try {
        	propDesc1 = new PropertyDescriptor("function", myClass);
        	propDescriptors = new PropertyDescriptor[]{propDesc1};
		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
		}       

        return propDescriptors;
	}
}
