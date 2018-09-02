package tam.example.SpringDataPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@EnableCaching 
@SpringBootApplication
public class SpringDataProApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataProApplication.class, args);
	}
}
