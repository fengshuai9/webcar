/** 
 * <pre>项目名称:ssm-book-wjj 
 * 文件名称:EasyuiPage.java 
 * 包名:com.jk.wjj.utils 
 * 创建日期:2019年2月17日下午7:52:15 
 * Copyright (c) 2019, 1207727105@qq.com All Rights Reserved.</pre> 
 */  
package com.cn.utils;

/** 
 * <pre>项目名称：ssm-book-wjj    
 * 类名称：EasyuiPage    
 * 类描述：    
 * 创建人：王京京  
 * 创建时间：2019年2月17日 下午7:52:15    
 * 修改人：王京京
 * 修改时间：2019年2月17日 下午7:52:15    
 * 修改备注：       
 * @version </pre>    
 */
public class EasyuiPage {
	private Integer total;
	private Object rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	/* (non-Javadoc)    
	 * @see java.lang.Object#toString()    
	 */
	@Override
	public String toString() {
		return "EasyuiPage [total=" + total + ", rows=" + rows + "]";
	}

}
