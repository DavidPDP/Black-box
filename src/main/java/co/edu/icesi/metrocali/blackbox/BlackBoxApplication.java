package co.edu.icesi.metrocali.blackbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BlackBoxApplication {

	//@Autowired
	//private UsersRepository users;
	
	//@Autowired
	//private AccessQuery query;
	
	public static void main(String[] args) {
		SpringApplication.run(BlackBoxApplication.class, args);
		//BlackBoxApplication t = ctx.getBean(BlackBoxApplication.class);
		//t.test();
		
	}

//	public void test() {
//		query.queryRetrieveUserInfo("Admin");
//	}
}
