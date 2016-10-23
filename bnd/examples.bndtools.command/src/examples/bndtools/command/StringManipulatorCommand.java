package examples.bndtools.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.felix.service.command.Descriptor;
import org.apache.felix.service.command.Parameter;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import examples.service.api.StringModifier;

@Component(
		property = { 
				"osgi.command.scope"    + "=zExampleModifier",
				"osgi.command.function" + "=modify" 
		}, 
		service = StringManipulatorCommand.class
)
public class StringManipulatorCommand {

	private List<StringModifier> modifier = new ArrayList<>();

	@Activate
	void activate() {
		System.out.println(this.getClass() + " activated");
	}

	@Deactivate
	void deactivate() {
		System.out.println(this.getClass() + " deactivated");
	}
	
	@Reference(
			cardinality=ReferenceCardinality.AT_LEAST_ONE,
			policy=ReferencePolicy.DYNAMIC
	)
	void bindStringModifier(StringModifier modifier) {
		this.modifier.add(modifier);
	}
	
	void unbindStringModifier(StringModifier modifier) {
		this.modifier.remove(modifier);
	}
	
	void updatedStringModifier(StringModifier modifier, Map<String, Object> properties) {
		System.out.println(modifier.getClass() + " configuration updated");
		properties.forEach((key, value) -> System.out.println(key + " = " + value));
	}

	public void modify(
			@Parameter(
					names = { "-i", "--input" },
					absentValue = "no message given")
					@Descriptor ("input text") String input
			) {
		modifier.forEach(m -> System.out.println(m.modify(input)));
	}
}