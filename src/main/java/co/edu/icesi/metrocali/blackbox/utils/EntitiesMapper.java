package co.edu.icesi.metrocali.blackbox.utils;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.metrocali.blackbox.dtos.OutCategoryMessage;
import co.edu.icesi.metrocali.blackbox.dtos.OutEventMessage;
import co.edu.icesi.metrocali.blackbox.dtos.OutProtocolMessage;
import co.edu.icesi.metrocali.blackbox.dtos.OutStateMessage;
import co.edu.icesi.metrocali.blackbox.dtos.OutUserMessage;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.Category;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.Event;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.State;
import co.edu.icesi.metrocali.blackbox.entities.policies.User;

@Component
public class EntitiesMapper {

	private ModelMapper modelMapper;

	@Autowired
	public EntitiesMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@PostConstruct
	private void initUserConverter() {
		Converter<User, OutUserMessage> newConverter = 
				new Converter<User, OutUserMessage>() {
			@Override
			public OutUserMessage convert(
					MappingContext<User, OutUserMessage> context) {
				return new OutUserMessage(
					context.getSource().getId(), 
					context.getSource().getAccountName(), 
					context.getSource().getEmail(), 
					context.getSource().getName(), 
					context.getSource().getLastName(), 
					context.getSource().getPassword(), 
					context.getSource().getRoles()
						.stream()
						.map(role -> {
							//role.setServices(null);
							role.setUsers(null);
							return role;
						})
						.collect(Collectors.toList())
				);
			};
		};
		modelMapper.addConverter(newConverter);
	}
	
	@PostConstruct
	private void initStateConverter() {
		Converter<State, OutStateMessage> newConverter = 
				new Converter<State, OutStateMessage>() {
			@Override
			public OutStateMessage convert(
					MappingContext<State, OutStateMessage> context) {
				return new OutStateMessage(
					context.getSource().getId(), 
					context.getSource().getName(), 
					context.getSource().getStateType().getName()
				);
			};
		};
		modelMapper.addConverter(newConverter);
	}
	
	@PostConstruct
	private void initCategoryConverter() {
		Converter<Category, OutCategoryMessage> newConverter = 
				new Converter<Category, OutCategoryMessage>() {
			@Override
			public OutCategoryMessage convert(
					MappingContext<Category, OutCategoryMessage> context) {
				if(context.getSource().getBasePriority() != null) {
					return new OutCategoryMessage(
							context.getSource().getId(), 
							context.getSource().getBasePriority(), 
							context.getSource().getName(), 
							context.getSource().getProtocols().stream()
								.map(protocol -> {
									return new OutProtocolMessage(
											protocol.getId(), 
											protocol.getStepOrder(), 
											protocol.getStep().getDescription(),
											protocol.getStep().getStepType().getName());
								})
								.collect(Collectors.toList())
					);
				}else {
					return null;
				}
				
			};
		};
		modelMapper.addConverter(newConverter);
	}
	
	@PostConstruct
	private void initEventConverter() {
		Converter<Event, OutEventMessage> newConverter = 
				new Converter<Event, OutEventMessage>() {
			@Override
			public OutEventMessage convert(
					MappingContext<Event, OutEventMessage> context) {
				System.out.println(context.getSource().getCategory());
				return new OutEventMessage(
					context.getSource().getId(), 
					context.getSource().getCreation(), 
					context.getSource().getDescription(), 
					context.getSource().getSource(), 
					context.getSource().getSourceType(), 
					context.getSource().getTitle(), 
					context.getSource().getCategory(), 
					context.getSource().getEventsTracks(), 
					context.getSource().getProtocolTracks()
				);
			};
		};
		modelMapper.addConverter(newConverter);
	}
	
	public OutUserMessage convertUserEntity(User user) {
		return modelMapper.map(user, OutUserMessage.class);
	}
	
	public OutStateMessage convertStateEntity(State state) {
		return modelMapper.map(state, OutStateMessage.class);
	}
	
	public OutCategoryMessage convertCategoryEntity(Category category) {
		return modelMapper.map(category, OutCategoryMessage.class);
	}
	
	public OutEventMessage convertEventEntity(Event event) {
		return modelMapper.map(event, OutEventMessage.class);
	}

}
