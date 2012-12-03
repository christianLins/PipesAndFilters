package image.beans;

import image.beans.LoadBean;
import java.beans.*;

public class LoadBeanBeanInfo extends MyBeanInfo {

	public LoadBeanBeanInfo() {
		super(LoadBean.class);
	}
	
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor propDesc1;
        PropertyDescriptor[] propDescriptors = null;
        
        try {
			propDesc1 = new PropertyDescriptor("imageSourcePath", myClass);
			propDescriptors = new PropertyDescriptor[]{propDesc1};
		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
		}       

        return propDescriptors;
	}
}
