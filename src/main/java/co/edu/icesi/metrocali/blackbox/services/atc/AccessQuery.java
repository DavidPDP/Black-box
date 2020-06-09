package co.edu.icesi.metrocali.blackbox.services.atc;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import co.edu.icesi.metrocali.blackbox.exceptions.EntityNotFoundException;
import co.edu.icesi.metrocali.blackbox.repositories.policies.UsersRepository;
import co.edu.icesi.metrocali.blackbox.utils.PoliciesConverter;
import co.edu.icesi.metrocali.blackbox.vos.atc.AccessMessage;
import co.edu.icesi.metrocali.blackbox.vos.atc.UserData;

/**
 * Responsible for managing the queries regarding access to the system services.
 */
@Service
public class AccessQuery {
	
	/**
	 * Destination output queues name in the MOM.
	 */
	@Value("#{${mom.aviom.output.queues}}")
	private Map<String,String> destinationQueues;
	
	/**
	 * API to send messages to the MOM.
	 */
	private JmsTemplate jmsTemplate;
	
	/**
	 * Manages conversions between entities and DTOS, or vice versa.
	 */
	private PoliciesConverter policiesConverter;
	
	private UsersRepository usersRepository;
	
	@Autowired
	public AccessQuery(PoliciesConverter policiesConverter, UsersRepository usersDAO,
			JmsTemplate jmsTemplate) {
		this.policiesConverter = policiesConverter;
		this.usersRepository = usersDAO;
		this.jmsTemplate = jmsTemplate;
	}
	
	/**
	 * Query a user by name, receiving requests from 
	 * ${mom.aviom.input.queues.access} queue and sending 
	 * responses via ${mom.aviom.output.queues} queue.
	 * @param accountName
	 */
	@JmsListener(destination = "${mom.aviom.input.queues.access}")
	@Transactional
	public void queryRetrieveUserInfo(String message) {
		System.out.println("ENTRA");
		System.out.println(message);
		try {
			AccessMessage request = policiesConverter
					.convertInputJsonToAccessMessage(message);
			Optional<User> dbQueryResult = usersRepository
					.findByAccountName(request.getAccountName());
			Optional<UserData> bodyMessage = policiesConverter
					.convertUserEntity(dbQueryResult);
			String destinationQueue = destinationQueues
					.get(request.getSubSystem());
			System.out.println(destinationQueue);
			if(bodyMessage.isPresent()) {
				jmsTemplate.convertAndSend(destinationQueue, bodyMessage.get());
			}else {
				String exceptionMessage = "The query performed with the parameter: "
						+ request.getAccountName() + " ,did not find an entity.";
				jmsTemplate.convertAndSend(destinationQueue, 
						new EntityNotFoundException(exceptionMessage));
			}
		} catch (IOException e) {
			//generate alert
			//log.error("");
			e.printStackTrace();
		}
	}
	
	
	
}
