package co.edu.icesi.metrocali.blackbox.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import co.edu.icesi.metrocali.blackbox.entities.policies.Role;

/**
 * Allows to deserialize the Role class for many-to-many 
 * relationships that invoke it. For more information 
 * on how it works, see next link
 * {@link <a href="https://www.baeldung.com/jackson-deserialization">
 * JsonDeserializer example</a>}.
 * @author <a href="mailto:johan.ballesteros@outlook.com">Johan Ballesteros</a>
 */
public class RoleDeserializer extends StdDeserializer<Role>{

	private static final long serialVersionUID = 1602607962924508203L;

	public RoleDeserializer() {
		this(null);
	}
	
	protected RoleDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Role deserialize(JsonParser p, DeserializationContext ctxt) 
			throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);
		Role role = new Role();
		role.setId(node.get("id").asInt());
		role.setName(node.get("name").asText());
		return role;
	}

}
