package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_protocols", schema="event_managment")
@Getter
@Setter
public class Protocol {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="step_order")
	private Integer stepOrder;

	@OneToMany(mappedBy="protocol", fetch=FetchType.LAZY)
	@JsonManagedReference("ptr1")
	@JsonIgnore
	private List<ProtocolTrack> protocolTracks;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category")
	@JsonBackReference("pr1")
	private Category category;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="step")
	@JsonBackReference("sr1")
	private Step step;

}