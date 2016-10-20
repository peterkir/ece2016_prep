package examples.bndtools.command;

import org.apache.felix.service.command.Descriptor;
import org.apache.felix.service.command.Parameter;
import org.osgi.service.component.annotations.*;

import examples.service.api.StringModifier;

@Component(property = { "osgi.command.scope" + "=zample",
		"osgi.command.function" + "=manipulate" }, service = StringManipulator.class)
public class StringManipulator {

	@Reference
	StringModifier eci;

	public void manipulate(
			@Parameter(
					names = { "-m", "--message" },
					absentValue = "no message given" )
					@Descriptor ("text") String message
			) {
			System.out.println(eci.modify(message));
	}

}
