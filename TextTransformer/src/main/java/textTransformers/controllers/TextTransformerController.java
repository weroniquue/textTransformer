package textTransformers.controllers;

import java.util.Optional;

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
	

	@RequestMapping("/transform")
	public ResponseEntity<?> transform(@RequestParam(value = "text", required=false) String text) {
		if(text == "" || text == null) {
			return new ResponseEntity<>(new ApiResponse(false, "Empty string!"), HttpStatus.BAD_REQUEST);
		}else {
			textParser.setContent(text);
			//in this place will be switch to choose transform function
			textParser.transform();
			return new ResponseEntity<>(new TextResponse(textParser.getContent()), HttpStatus.OK);
		}	
	}
	

}
