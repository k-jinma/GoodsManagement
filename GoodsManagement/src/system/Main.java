package system;

import goods.License;

public class Main {

	public static void main(String[] args) {
		
		License zoom1 = new License();
		zoom1.setServiceName("Zoom");
		
		
		
		String serviceName = zoom1.getServiceName();
		System.out.println( serviceName );
	
		
	}

}
