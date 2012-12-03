package image.beans;

import image.beans.SaveBean;

import java.beans.PropertyDescriptor;

public class SaveBeanBeanInfo extends MyBeanInfo {

	public SaveBeanBeanInfo() {
		super(SaveBean.class);
	}
	
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor pd1;
        PropertyDescriptor[] ps = null;
        
        try {
			pd1 = new PropertyDescriptor("path", myClass);
			ps = new PropertyDescriptor[]{pd1};
		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
		}       

        return ps;
	}

}
