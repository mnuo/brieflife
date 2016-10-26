/**
 * QueryFiter.java created at 2016年10月26日 下午6:07:54
 */
package com.mnuo.brieflife.common.sql;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 * @author saxon
 */
public class QueryFiter {
	public static final String EQ="eq";//eq是等于，gt是大于，lt是小于,or是或
	public static final String GT="gt";
	public static final String LT="lt";
	public static final String OR="or";
	
	
	private String name;
	private Object value;
	private String type;
	
	public SimpleExpression getSimpleException(){
		if(type.equals(EQ)){
			return Restrictions.eq(name, value);
		}
		if(type.equals(GT)){
			return Restrictions.gt(name, value);
		}
		if(type.equals(LT)){
			return Restrictions.lt(name, value);
		}
		return null;
	}
	
	public QueryFiter() {
	}
	public QueryFiter(String name, Object value, String type){
		this.name= name;
		this.value = value;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
