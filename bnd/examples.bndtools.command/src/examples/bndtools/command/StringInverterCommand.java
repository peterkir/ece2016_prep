package examples.bndtools.command;

import org.osgi.service.component.annotations.*;

import examples.service.api.StringModifier;

@Component(property = { 
		"osgi.command.scope" + "=zample",
		"osgi.command.function" + "=modify" 
	}, service = StringInverterCommand.class
)
public class StringInverterCommand {

	@Reference
	StringModifier eci;

	
	@Deactivate
	void deactivate() {
		eci=null;
	}
	
	public void modify(String message) {
		System.out.println(eci.modify(message));
	}

}
