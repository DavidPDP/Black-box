package co.edu.icesi.metrocali.blackbox.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventTrack;

public class EventTrackSerializer extends StdSerializer<EventTrack>{

	private static final long serialVersionUID = 2050706946339451170L;

	public EventTrackSerializer() {
		this(null);
	}
	
	protected EventTrackSerializer(Class<EventTrack> t) {
		super(t);
	}

	@Override
	public void serialize(EventTrack value, JsonGenerator gen, 
			SerializerProvider provider) throws IOException {
		
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeObjectField("startTime", value.getStartTime());
		gen.writeNumberField("priority", value.getPriority());
		gen.writeObjectField("endTime", value.getEndTime());
		
		gen.writeObjectFieldStart("user");
		gen.writeNumberField("id", value.getUser().getId());
		gen.writeEndObject();
		
		gen.writeObjectFieldStart("state");
		gen.writeNumberField("id", value.getState().getId());
		gen.writeEndObject();
		
		gen.writeEndObject();
	}
	
}
