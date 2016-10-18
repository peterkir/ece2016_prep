package examples.pde.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import examples.service.api.StringModifier;

@Component(
		property = { 
				"osgi.command.scope:String=examples",
				"osgi.command.function:String=modify" 
		}, 
		service = StringModifierCommand.class)
public class StringModifierCommand {

	private StringModifier modifier;

	@Reference
	void bindStringModifier(StringModifier modifier) {
		this.modifier = modifier;
	}

	public void modify(String input) {
		System.out.println(modifier.modify(input));
	}
}
