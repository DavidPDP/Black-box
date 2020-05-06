package co.edu.icesi.metrocali.blackbox.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Category;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.Protocol;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.Step;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.StepType;

public class CategorySerializer extends StdSerializer<Category> {

	private static final long serialVersionUID = -3786261380693215041L;

	public CategorySerializer() {
		this(null);
	}
	
	protected CategorySerializer(Class<Category> t) {
		super(t);
	}

	@Override
	public void serialize(Category value, JsonGenerator gen, 
			SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("name", value.getName());
		
		if(value.getCategory() != null)
			gen.writeStringField("parent", value.getCategory().getName());
		
		if(value.getBasePriority() != null)
			gen.writeNumberField("base_priority", value.getBasePriority());
		
		gen.writeFieldName("protocols");
		gen.writeStartArray();
		
		for (Protocol protocol : value.getProtocols()) {
			gen.writeStartObject();
			gen.writeNumberField("id", protocol.getId());
			gen.writeNumberField("step_order", protocol.getStepOrder());
			
			Step step = protocol.getStep();
			gen.writeObjectFieldStart("step");
			gen.writeNumberField("id", step.getId());
			gen.writeStringField("description", step.getDescription());
			
			StepType stepType = step.getStepType();
			gen.writeObjectFieldStart("stateType");
			gen.writeNumberField("id", stepType.getId());
			gen.writeStringField("name", stepType.getName());
			gen.writeEndObject();
			
			gen.writeEndObject();
			
			gen.writeEndObject();
		}

		gen.writeEndArray();
		
		gen.writeEndObject();
		
	}

}
