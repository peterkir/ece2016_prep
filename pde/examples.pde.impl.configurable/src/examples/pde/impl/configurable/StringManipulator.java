package examples.pde.impl.configurable;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import examples.service.api.StringModifier;

@Component(
		configurationPid="manipulator",
		configurationPolicy=ConfigurationPolicy.REQUIRE
)
public class StringManipulator implements StringModifier {

	Map<String, Object> properties;
	
	@Activate
	void activate(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	@Modified
	void modified(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	@Override
	public String modify(String input) {
		String prefix = (String) properties.getOrDefault(Constants.PREFIX, "");
		String suffix = (String) properties.getOrDefault(Constants.SUFFIX, "");
		Integer iteration = (Integer) properties.getOrDefault(Constants.ITERATION, Integer.valueOf(1));
		Boolean upperCase = (Boolean) properties.getOrDefault(Constants.UPPER_CASE, Boolean.FALSE);
		
		StringBuilder builder = new StringBuilder();
		builder.append(prefix);
		
		for (int i = 0; i < iteration; i++) {
			builder.append(upperCase ? input.toUpperCase() : input);
		}
		
		builder.append(suffix);
		
		return builder.toString();
	}

}
