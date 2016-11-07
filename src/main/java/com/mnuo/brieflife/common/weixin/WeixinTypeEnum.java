/**
 * WeixinTypeEnum.java created at 2016年11月4日 下午4:55:59
 */
package com.mnuo.brieflife.common.weixin;

/**
 * @author saxon
 */
public enum WeixinTypeEnum {
	//枚举常量必须要写在类开始的位置,不然会报错  
    INT_EXP_STATUS_UNSUBMIT(1), //报销状态-未提交状态  
    INT_EXP_STATUS_SUBMITTED(2),//报销状态-已提交状态  
    INT_EXP_STATUS_VERIFYUNPASS(3),//报销状态-未审核状态  
    INT_EXP_STATUS_VERIFYPASS(4),//报销状态-已审核状态  
    
	STRING_FULLTIEM_EMP("正式员工"),//员工类型-正式员工  
	STRING_TRAINEE_EMP("实习生"),//员工类型-实习生  
	STRING_PARTTIME_EMP("兼职/劳务");//员工类型-兼职/劳务  
	  
	private String stringValue;  
	private int intVlue;  
   
	//构造函数必须为private的,防止意外调用  
	private WeixinTypeEnum(String stringValue){  
		this.stringValue = stringValue;  
	}  
   
	//构造函数必须为private的,防止意外调用  
	private WeixinTypeEnum(int intVlue){  
		this.intVlue = intVlue;  
    }  
  
    public String getStringValue() {  
    	return stringValue;  
    }  
  
    public int getIntVlue() {  
    	return intVlue;  
    }  
}
