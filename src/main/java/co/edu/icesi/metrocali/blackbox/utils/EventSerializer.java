package co.edu.icesi.metrocali.blackbox.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Event;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventTrack;

public class EventSerializer extends StdSerializer<Event>{

	private static final long serialVersionUID = -2769463476374903180L;

	public EventSerializer() {
		this(null);
	}
	
	protected EventSerializer(Class<Event> t) {
		super(t);
	}

	@Override
	public void serialize(Event value, JsonGenerator gen, 
			SerializerProvider provider) throws IOException {
		
		gen.writeStartObject(); 
		gen.writeStringField("context", "black-box");
		gen.writeNumberField("id", value.getId());
		gen.writeObjectField("creation", value.getCreation());
		
		
		gen.writeFieldName("event_tracks");
		gen.writeStartArray();
		for (EventTrack eventTrack : value.getEventsTracks()) {
			
			gen.writeStartObject();
			
			gen.writeNumberField("id", eventTrack.getId());
			gen.writeObjectField("startTime", eventTrack.getStartTime());
			gen.writeObjectField("endTime", eventTrack.getEndTime());
			gen.writeNumberField("priority", eventTrack.getPriority());
			
			gen.writeObjectFieldStart("user");
			gen.writeNumberField("id", eventTrack.getUser().getId());
			gen.writeStringField("accountName", eventTrack.getUser().getAccountName());
			gen.writeEndObject();
			
			gen.writeObjectFieldStart("state");
			gen.writeNumberField("id", eventTrack.getState().getId());
			gen.writeStringField("name", eventTrack.getState().getName());
			gen.writeEndObject();
			
			gen.writeEndObject();
			
		}
		gen.writeEndArray();
		gen.writeEndObject();
	}
	
	
	

}
