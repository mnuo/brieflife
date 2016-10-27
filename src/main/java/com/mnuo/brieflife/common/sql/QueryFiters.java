/**
 * QueryFiters.java created at 2016年10月26日 下午6:06:38
 */
package com.mnuo.brieflife.common.sql;

import java.util.ArrayList;

import org.hibernate.criterion.Order;

import com.mnuo.brieflife.common.utils.StringUtil;

/**
 * @author saxon
 */
public class QueryFiters {
	private ArrayList<QueryFiter> param = new ArrayList<QueryFiter>();
	
	private String orderName;
	private String orderType;
	
	public Order getOrder(){
		if(StringUtil.isNotEmpty(orderName)){
			if("asc".equals(orderType)){
				return Order.desc(orderName.trim());
			}else{
				return Order.asc(orderName.trim());
			}
		}
		return null;
	}

	public ArrayList<QueryFiter> getParam() {
		return param;
	}
	public void setParam(ArrayList<QueryFiter> param) {
		this.param = param;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
