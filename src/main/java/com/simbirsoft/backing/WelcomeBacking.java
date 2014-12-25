package com.simbirsoft.backing;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class WelcomeBacking implements Serializable{

	private static final long serialVersionUID = -6420102636438518500L;
	
	@ManagedProperty("#{i18n}")
	private ResourceBundle i18n;
	
	public ResourceBundle getI18n() {
		return i18n;
	}
	
	public void setI18n(ResourceBundle i18n) {
		this.i18n = i18n;;
	}
	
	public String toProductList() {
		return "productlist";
	}
	



}
