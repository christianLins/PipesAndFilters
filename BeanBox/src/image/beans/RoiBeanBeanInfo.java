package image.beans;


import java.beans.PropertyDescriptor;

public class RoiBeanBeanInfo extends MyBeanInfo {

	public RoiBeanBeanInfo() {
		super(RoiBean.class);
	}
	
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor propDesc1;
		PropertyDescriptor propDesc2;
		PropertyDescriptor propDesc3;
		PropertyDescriptor propDesc4;
        PropertyDescriptor[] propDescriptors = null;
        
        try {
        	propDesc1 = new PropertyDescriptor("x", myClass);
        	propDesc2 = new PropertyDescriptor("y", myClass);
        	propDesc3 = new PropertyDescriptor("width", myClass);
        	propDesc4 = new PropertyDescriptor("hight", myClass);
			propDescriptors = new PropertyDescriptor[]{propDesc1, propDesc2, propDesc3, propDesc4};
		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
		}       

        return propDescriptors;
	}

}
