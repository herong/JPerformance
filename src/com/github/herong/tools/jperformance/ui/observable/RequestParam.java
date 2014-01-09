package com.github.herong.tools.jperformance.ui.observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RequestParam {
	private StringProperty id;
	private StringProperty name;
	private StringProperty value;

	public RequestParam(String id, String name, String value) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleStringProperty(value);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getValue() {
		return value.get();
	}

	public void setValue(String value) {
		this.value.set(value);
	}

}
