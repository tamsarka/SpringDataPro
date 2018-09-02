package tam.example.SpringDataPro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import tam.example.SpringDataPro.controller.RandomController;

@Configuration
public class AppConfig {

	@Bean
	//proxyMode = ScopedProxyMode.TARGET_CLASS FOR  prototype bean accessed from a singlton bean
	@Scope(value="prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public RandomController getRandCtrl(){
		return new RandomController();
	}
}
