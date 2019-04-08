package com.usbxyz;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface USB2GPIO extends Library {
	USB2GPIO INSTANCE  = (USB2GPIO)Native.loadLibrary("USB2XXX",USB2GPIO.class); 
	//定义函数返回错误代码
	public static int GPIO_SUCCESS             =(0);   //函数执行成功
	public static int GPIO_ERR_NOT_SUPPORT     =(-1);  //适配器不支持该函数
	public static int GPIO_ERR_USB_WRITE_FAIL  =(-2);  //USB写数据失败
	public static int GPIO_ERR_USB_READ_FAIL   =(-3);  //USB读数据失败
	public static int GPIO_ERR_CMD_FAIL        =(-4);  //命令执行失败
	
	/**
	  * @brief  将GPIO引脚设置为输入模式
	  * @param  DevHandle 设备索引号
	  * @param  PinMask 需要设置为输入模式的引脚，每个bit位代表一个引脚，对应bit位为1时改引脚对设置有效，最低位为P0
	  * @param  PuPd 0-浮空输入，无上拉或者下拉，1-上拉输入，2-下拉输入
	  * @retval 函数执行状态，小于0函数执行出错
	  */
	int  GPIO_SetInput(int DevHandle,int PinMask, byte PuPd);
	/**
	  * @brief  将GPIO引脚设置为输出模式
	  * @param  DevHandle 设备索引号
	  * @param  PinMask 需要设置为输出模式的引脚，每个bit位代表一个引脚，对应bit位为1时改引脚对设置有效，最低位对应P0
	  * @param  PuPd 0-推挽输出，无上拉或者下拉，1-上拉输出，2-下拉输出
	  * @retval 函数执行状态，小于0函数执行出错
	  */
	int  GPIO_SetOutput(int DevHandle,int PinMask, byte PuPd);
	/**
	  * @brief  将GPIO引脚设置为开漏模式，该模式下可作为双向引脚
	  * @param  DevHandle 设备索引号
	  * @param  PinMask 需要设置为开漏模式的引脚，每个bit位代表一个引脚，对应bit位为1时改引脚对设置有效，最低位对应P0
	  * @param  PuPd 0-内部无上拉或者下拉，1-使能上拉，2-使能下拉
	  * @retval 函数执行状态，小于0函数执行出错
	  */
	int  GPIO_SetOpenDrain(int DevHandle,int PinMask, byte PuPd);
	/**
	  * @brief  设置GPIO引脚的输出状态
	  * @param  DevHandle 设备索引号
	  * @param  PinMask 需要输出状态的引脚，每个bit位代表一个引脚，对应bit位为1时改引脚对设置有效，最低位对应P0
	  * @param  PinValue 对应引脚的状态，每个bit位代表一个引脚，对应bit位为1输出高电平，为0输出低电平，最低位对应P0
	  * @retval 函数执行状态，小于0函数执行出错
	  */
	int  GPIO_Write(int DevHandle,int PinMask, int PinValue);
	/**
	  * @brief  获取GPIO引脚的输入状态
	  * @param  DevHandle 设备索引号
	  * @param  PinMask 需要获取状态的引脚，每个bit位代表一个引脚，对应bit位为1时改引脚对设置有效，最低位对应P0
	  * @param  pPinValue 对应引脚的状态，每个bit位代表一个引脚，对应bit位为1引脚为高电平，为0引脚为低电平，最低位对应P0
	  * @retval 函数执行状态，小于0函数执行出错
	  */
	int  GPIO_Read(int DevHandle,int PinMask, int[] pPinValue);

}
