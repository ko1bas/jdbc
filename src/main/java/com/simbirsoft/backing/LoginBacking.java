package com.simbirsoft.backing;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.simbirsoft.security.AuthService;

@ManagedBean
@RequestScoped
public class LoginBacking implements Serializable {
	private static final long serialVersionUID = 3267095997550216944L;
	
	@Inject
	private AuthService authService;
	
	private String password;
	private String message;
	private String login;
	
	@ManagedProperty("#{i18n}")
	private ResourceBundle i18n;
	
	public ResourceBundle getI18n() {
		return i18n;
	}
	public void setI18n(ResourceBundle i18n) {
		this.i18n = i18n;;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String login() {
		boolean result = authService.login(login, password);
		if (result) {
			return "welcome";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, i18n.getString("invalidLogin"), i18n.getString("pleaseTryAgain")));
			return "login";
		}
	}

	public String logout() {
		authService.logout();
		return "login";
	}
}