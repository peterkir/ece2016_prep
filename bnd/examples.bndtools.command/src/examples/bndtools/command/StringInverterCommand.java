package examples.bndtools.command;

import org.osgi.service.component.annotations.*;
import examples.service.api.StringModifier;

@Component(property = { 
		"osgi.command.scope" + "=zExampleInv",
		"osgi.command.function" + "=modify" }, 
	service = StringInverterCommand.class
)
public class StringInverterCommand {

	@Reference
	StringModifier eci;

	public void modify(String message) {
		System.out.println(eci.modify(message));
	}

}
