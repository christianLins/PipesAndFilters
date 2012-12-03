package image.beans;

import image.beans.ErodeBean;

import java.beans.PropertyDescriptor;

public class ErodeBeanBeanInfo extends MyBeanInfo {

	public ErodeBeanBeanInfo() {
		super(ErodeBean.class);
	}
	
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor propDesc1;
        PropertyDescriptor[] propDescriptors = null;
        
        try {
			propDesc1 = new PropertyDescriptor("repetitions", myClass);
			propDescriptors = new PropertyDescriptor[]{propDesc1};
		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
		}       

        return propDescriptors;
	}

}
