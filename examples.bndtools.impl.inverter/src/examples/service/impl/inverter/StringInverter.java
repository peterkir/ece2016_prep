package examples.service.impl.inverter;

import org.osgi.service.component.annotations.Component;

import examples.service.api.StringModifier;

@Component
public class StringInverter implements StringModifier {

	@Override
	public String modify(String message) {
		return new StringBuilder(message).reverse().toString();
	}

}
