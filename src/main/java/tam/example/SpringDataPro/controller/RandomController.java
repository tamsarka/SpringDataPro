package tam.example.SpringDataPro.controller;

import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

//@Component
//@RequestScope
public class RandomController {
	int number ;
	public RandomController() {
		Random rand = new Random();
		number = rand.nextInt(100);
		System.out.println(" Its supposed to generate a new random number" + number);
	}

	public String getRandomNumber(){
		return "Value "+ number;
	}
}
