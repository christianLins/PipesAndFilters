package image.beans;

import image.beans.MedianBean;

import java.beans.PropertyDescriptor;

public class MedianBeanBeanInfo extends MyBeanInfo {

	public MedianBeanBeanInfo() {
		super(MedianBean.class);
	}
	
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor propDesc1;
		PropertyDescriptor propDesc2;
        PropertyDescriptor[] propDescriptors = null;
        
        try {
			propDesc1 = new PropertyDescriptor("mask", myClass);
			propDesc2 = new PropertyDescriptor("intensity", myClass);
			propDescriptors = new PropertyDescriptor[]{propDesc1, propDesc2};
		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
		}       

        return propDescriptors;
	}
}
