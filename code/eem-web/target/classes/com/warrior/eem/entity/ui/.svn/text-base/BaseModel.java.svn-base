package com.warrior.eem.entity.ui;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

public class BaseModel {
	public String toString() {
		XStream xStream = new XStream();
		xStream.processAnnotations(this.getClass());
		return xStream.toXML(this);
	}

	public String toJson() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.enableComplexMapKeySerialization().setPrettyPrinting()
				.create();
		return gson.toJson(this);
	}

	public String addAliasNameToJson(String aliasName) {
		if (aliasName != null && !aliasName.equals("")) {
			return "{\"" + aliasName + "\":" + toJson() + "}";
		} else {
			return toJson();
		}
	}
}
