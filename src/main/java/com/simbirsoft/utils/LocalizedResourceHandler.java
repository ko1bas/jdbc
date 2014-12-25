package com.simbirsoft.utils;

import java.util.Locale;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.faces.context.FacesContext;

/**
 * Adds ability to place localized properties directly to the component folder
 */
public class LocalizedResourceHandler extends ResourceHandlerWrapper {
	private ResourceHandler wrapped;

	public LocalizedResourceHandler(ResourceHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public Resource createResource(String resourceName) {
		return createResource(resourceName, null, null);
	}

	@Override
	public Resource createResource(String resourceName, String libraryName) {
		return createResource(resourceName, libraryName, null);
	}

	@Override
	public Resource createResource(String resourceName, String libraryName,
			String contentType) {
		Resource resource = null;
		if (resourceName.endsWith(".properties")) {
			FacesContext context = FacesContext.getCurrentInstance();
			Locale locale;
			if (context.getViewRoot() != null) {
				locale = context.getViewRoot().getLocale();
			} else {
				locale = context.getApplication().getViewHandler()
						.calculateLocale(context);
			}
			String localizedResourceName = resourceName.replace(".properties",
					"_" + locale.getLanguage() + ".properties");
			resource = super.createResource(localizedResourceName, libraryName,
					contentType);
		}
		if (resource == null) {
			resource = super.createResource(resourceName, libraryName,
					contentType);
		}
		return resource;
	}

	@Override
	public ResourceHandler getWrapped() {
		return wrapped;
	}
}