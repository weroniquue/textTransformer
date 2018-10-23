package textTransformers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextTransformerController {
	
	private static final String template = "Hello, %s!";
	

	@RequestMapping("/transform")
	public String transform(@RequestParam(value = "text", defaultValue = "World") String text) {
		return String.format(template, text);
	}
	

}
