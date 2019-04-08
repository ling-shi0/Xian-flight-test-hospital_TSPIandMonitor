package app;

import com.usbxyz.USB2GPIO;
import com.usbxyz.USB_Device;

public class USB2XXXUSBDeviceTest {
	/**
	 * Launch the application.
	 */
	public USB2XXXUSBDeviceTest() {
		
	}
	public void  light(int i)
	{
		int ret;
		int DevHandle = 0;
		int[] DevHandleArry = new int[20];
		boolean state;
		// 扫描设备
		ret = USB_Device.INSTANCE.USB_ScanDevice(DevHandleArry);
		if (ret > 0) {
			System.out.println("DeviceNum = " + ret);
			DevHandle = DevHandleArry[0];
			System.out.println("DevHandle = " + DevHandle);
		} else {
			System.out.println("No device");
			return;
		}
		// 打开设备
		state = USB_Device.INSTANCE.USB_OpenDevice(DevHandle);
		if (!state) {
			System.out.println("open device error");
			return;
		}
		// 获取设备信息
		USB_Device.DEVICE_INFO DevInfo = new USB_Device.DEVICE_INFO();
		byte[] funcStr = new byte[128];
		state = USB_Device.INSTANCE.DEV_GetDeviceInfo(DevHandle, DevInfo, funcStr);
		if (!state) {
			System.out.println("get device infomation error");
			return;
		} else {
			try {
				System.out.println("Firmware Info:");
				System.out.println("--Name:" + new String(DevInfo.FirmwareName, "UTF-8"));
				System.out.println("--Build Date:" + new String(DevInfo.BuildDate, "UTF-8"));
				System.out.println(String.format("--Firmware Version:v%d.%d.%d", (DevInfo.FirmwareVersion >> 24) & 0xFF,
						(DevInfo.FirmwareVersion >> 16) & 0xFF, DevInfo.FirmwareVersion & 0xFFFF));
				System.out.println(String.format("--Hardware Version:v%d.%d.%d", (DevInfo.HardwareVersion >> 24) & 0xFF,
						(DevInfo.HardwareVersion >> 16) & 0xFF, DevInfo.HardwareVersion & 0xFFFF));
				System.out.println("--Functions:" + new String(funcStr, "UTF-8"));
				int s = 0;
				/**
				 * 0x0001--1 0x0010--16 0x0100--256 0x1000--4096 0x1111--4369
				 *
				 */
				int w = 4369;

				USB2GPIO.INSTANCE.GPIO_SetOutput(DevHandle, 4369, (byte) 0);
				//s = USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, w);

				int[] ss = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				s = USB2GPIO.INSTANCE.GPIO_Read(DevHandle, 0x1111, ss);
				int ledstate = (int)(ss[0]);
				int r = Integer.decode(Integer.toHexString(ss[0]));
				System.out.println(r);
				switch(i) {
				case 1:
				{

					if (r % 10 == 0)//p0亮
					{
						ledstate = ledstate - 1;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p0灭
					}
					else
					{
						ledstate = ledstate + 1;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p0灭
					}
					if (r/10 % 10 == 0)//p4亮
					{
						ledstate = ledstate - 16;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p4灭
					}
					else
					{
						ledstate = ledstate + 16;
					}
					if (r/10 /10 % 10 == 0)//p8亮
					{
						ledstate = ledstate - 256;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p8灭
					}
					else
					{
						ledstate = ledstate + 256;
					}
					if (r/10 /10/10 % 10 == 0)//p12亮
					{
						ledstate = ledstate - 4096;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p12灭
					}
					else
					{
						ledstate = ledstate + 4096;
					}
					//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);
					break;			
				}
				case 2:{
					if (r % 10 == 1)//p0亮
					{
						ledstate = ledstate - 1;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p0灭
					}
					else
					{
						ledstate = ledstate + 1;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p0灭
					}
					USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);
					break;
				}
				case 3:{
					if (r % 10 == 0)//p0亮
					{
						ledstate = ledstate - 1;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p0灭
					}
					else
					{
						ledstate = ledstate + 1;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p0灭
					}
					USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);
					break;
				}
				case 4:{
					if (r/10 % 10 == 1)//p4亮
					{
						ledstate = ledstate - 16;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p4灭
					}
					else
					{
						ledstate = ledstate + 16;
					}
					USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);
				break;	
				}
				case 5:{
					if (r/10 % 10 == 0)//p4亮
					{
						ledstate = ledstate - 16;
						//USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);//p4灭
					}
					else
					{
						ledstate = ledstate + 16;
					}
					USB2GPIO.INSTANCE.GPIO_Write(DevHandle, 4369, ledstate);
					break;
				}
				}
				
			} catch (Exception ep) {
				ep.printStackTrace();
			}
		}
		// 关闭设备
		//USB_Device.INSTANCE.USB_CloseDevice(DevHandle);
	}
}
