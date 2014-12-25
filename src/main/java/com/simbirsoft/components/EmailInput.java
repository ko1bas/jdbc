package com.simbirsoft.components;

import javax.faces.component.FacesComponent;

import org.primefaces.component.inputtext.InputText;

import com.simbirsoft.utils.EmailValidator;

@FacesComponent(value = "com.simbirsoft.components.EmailInput")
public class EmailInput extends InputText {
	public EmailInput() {
		super();
		
		this.addValidator(new EmailValidator());
	}
}
