package com.simbirsoft.backing;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.simbirsoft.entities.User;
import com.simbirsoft.service.UserService;

import java.io.Serializable;
import java.util.ResourceBundle;

@ManagedBean
@RequestScoped
public class RegBacking implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserService userService;
	
	@ManagedProperty("#{i18n}")
	private ResourceBundle i18n;
	
	private User user = new User();
	
	public ResourceBundle getI18n() {
		return i18n;
	}
	public void setI18n(ResourceBundle i18n) {
		this.i18n = i18n;;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String submit() {
		if (userService.isExistByLogin(user)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, i18n.getString("loginExists"), ""));
			return "registration";
		} else {
			userService.insertUser(user);
		}
		return "/login?faces-redirect=true";
	}
}