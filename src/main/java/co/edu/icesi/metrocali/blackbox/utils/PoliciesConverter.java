package co.edu.icesi.metrocali.blackbox.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import co.edu.icesi.metrocali.blackbox.vos.atc.AccessMessage;
import co.edu.icesi.metrocali.blackbox.vos.atc.UserData;

@Component
public class PoliciesConverter {

	private ModelMapper modelMapper;
	private ObjectMapper jsonMapper;

	@Autowired
	public PoliciesConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.jsonMapper = new ObjectMapper();
	}

	@PostConstruct
	private void initUserEntityConverter() {
		Converter<User, UserData> converter = new Converter<User, UserData>() {
			@Override
			public UserData convert(MappingContext<User, UserData> context) {
				List<String> userRoles = context.getSource().getRoles().stream()
						.map(role -> role.getName())
						.collect(Collectors.toList());
				return new UserData(
						context.getSource().getId(), 
						context.getSource().getAccountName(), 
						context.getSource().getIsActive(), 
						context.getSource().getPassword(), 
						userRoles
				);
			}
		};
		modelMapper.addConverter(converter);
	}
	
	@PostConstruct
	private void initInputJsonMessage() throws IOException {
		Converter<JsonNode, AccessMessage> converter = new Converter<JsonNode, AccessMessage>() {
			@Override
			public AccessMessage convert(MappingContext<JsonNode, AccessMessage> context) {
				return new AccessMessage(
						context.getSource().get("system").asText(),
						context.getSource().get("accountNamae").asText()
				);
			}
		};
		modelMapper.addConverter(converter);
	}

	public Optional<UserData> convertUserEntity(Optional<User> user) {
		if (user.isPresent()) {
			return Optional.of(modelMapper.map(user.get(), UserData.class));
		} else {
			return Optional.empty();
		}
	}
	
	public AccessMessage convertInputJsonToAccessMessage(String jsonMessage) 
			throws IOException {
		JsonNode jsonObject = jsonMapper.readTree(
				new StringReader(jsonMessage)
		);
		return modelMapper.map(jsonObject, AccessMessage.class);
	}
}
