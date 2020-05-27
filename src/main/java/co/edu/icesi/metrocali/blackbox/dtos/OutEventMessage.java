package co.edu.icesi.metrocali.blackbox.dtos;

import java.sql.Timestamp;
import java.util.List;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Category;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventTrack;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.ProtocolTrack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OutEventMessage {

	private Long id;
	
	private Timestamp creation;
	
	private String description;
	
	private Long source;
	
	private String sourceType;
	
	private String title;
	
	private Category category;
	
	private List<EventTrack> eventsTracks;
	
	private List<ProtocolTrack> protocolTracks;
	
}
