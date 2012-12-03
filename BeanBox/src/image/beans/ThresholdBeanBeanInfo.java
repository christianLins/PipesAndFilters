package image.beans;

import image.beans.ThresholdBean;

import java.beans.PropertyDescriptor;

public class ThresholdBeanBeanInfo extends MyBeanInfo {

	public ThresholdBeanBeanInfo() {
		super(ThresholdBean.class);
	}
	
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor propDesc1;
		PropertyDescriptor propDesc2;
		PropertyDescriptor propDesc3;
        PropertyDescriptor[] propDescriptors = null;
        
        try {
        	propDesc1 = new PropertyDescriptor("low", myClass);
        	propDesc2 = new PropertyDescriptor("high", myClass);
        	propDesc3 = new PropertyDescriptor("map", myClass);
			propDescriptors = new PropertyDescriptor[]{propDesc1, propDesc2, propDesc3};
		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
		}       

        return propDescriptors;
	}

}
