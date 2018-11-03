package textTransformers.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import textTransformers.models.TextParser;
import textTransformers.payload.ApiResponse;
import textTransformers.payload.TextResponse;

@RestController
public class TextTransformerController {
	
	@Autowired
	private TextParser textParser;
	
	private List<String> availableFunctions = Arrays.asList("upper", "lower", "capitalize", "reverse", "num2String");
	
	

	@RequestMapping("/transform")
	public ResponseEntity<?> transform(@RequestParam(value = "text", required=false) String text,
			@RequestParam(value = "function", required=false) String function) {
		if(text == "" || text == null) {
			return new ResponseEntity<>(new ApiResponse(false, "Empty string!"), HttpStatus.BAD_REQUEST);
		}else {
			textParser.setContent(text);
			
			if(function == "" || function == null) {
				return new ResponseEntity<>(new ApiResponse(false, "You have to choose transformation function!"), HttpStatus.BAD_REQUEST);
			}
			
			if(!availableFunctions.contains(function)) {
				return new ResponseEntity<>(new ApiResponse(false, "Such function isn't available"), HttpStatus.BAD_REQUEST);
			}
			
			switch (function) {
			case "upper":
				textParser.upper();
				break;
			case "lower":
				textParser.lower();
				break;
			case "capitalize":
				textParser.capitalize();
				break;
			case "reverse":
				textParser.reverse();
				break;
			case "num2String":
				textParser.num2StringTransform();
				break;

			default:
				break;
			}
			return new ResponseEntity<>(new TextResponse(textParser.getContent()), HttpStatus.OK);
		}	
	}
	

}
