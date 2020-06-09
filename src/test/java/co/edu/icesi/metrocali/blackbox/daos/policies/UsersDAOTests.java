package co.edu.icesi.metrocali.blackbox.daos.policies;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.metrocali.blackbox.repositories.policies.UsersRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UsersDAOTests {

	@Autowired
	public UsersRepository users;
	
	@Test
	public void userExistenceTest() {
		long c = users.count();
		System.out.println(c);
		assertTrue(c == 1);
	}
	
	
}
