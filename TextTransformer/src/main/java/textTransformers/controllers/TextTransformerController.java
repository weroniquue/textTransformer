package textTransformers.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import textTransformers.models.Abbreviate;
import textTransformers.models.Capitalize;
import textTransformers.models.DecodePassword;
import textTransformers.models.Decorator;
import textTransformers.models.EncodeBinary;
import textTransformers.models.EncodePassword;
import textTransformers.models.LatexFormat;
import textTransformers.models.Lowercase;
import textTransformers.models.Num2String;
import textTransformers.models.RemoveDuplicates;
import textTransformers.models.Reverse;
import textTransformers.models.TextParser;
import textTransformers.models.UnAbbreviate;
import textTransformers.models.Uppercase;
import textTransformers.payload.ApiResponse;
import textTransformers.payload.TextResponse;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TextTransformerController {

	private String message;

	private List<String> availableFunctions = Arrays.asList("upper", "lower", "capitalize", "reverse", "num2String",
			"abbreviate", "unAbbreviate", "encode", "decode", "encodeBinary", "removeDuplicates");

	static Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

	@RequestMapping("/transform")
	public ResponseEntity<?> transform(@RequestParam(value = "text", required = false) String text,
			@RequestParam(value = "function", required = false) String function) {

		if (text == "" || text == null) {
			logger.info("Empty text");
			return new ResponseEntity<>(new ApiResponse(false, "Empty string!"), HttpStatus.BAD_REQUEST);
		} else {

			Decorator decorator = new Decorator(new TextParser(text));

			logger.info("Text set to: {}.", text);
			if (function == "" || function == null) {
				return new ResponseEntity<>(new ApiResponse(false, "You have to choose transformation function!"),
						HttpStatus.BAD_REQUEST);
			}

			if (!availableFunctions.contains(function)) {
				logger.info("Incorrect name of function");
				message = "Such function isn't available. You have to chose one of: ";
				availableFunctions.forEach(x -> message = message + x + ", ");
				message = message.substring(0, message.length() - 2) + ".";
				return new ResponseEntity<>(new ApiResponse(false, message), HttpStatus.BAD_REQUEST);
			}

			switch (function) {
			case "upper":
				decorator = new Uppercase(decorator);
				break;
			case "lower":
				decorator = new Lowercase(decorator);
				break;
			case "capitalize":
				decorator = new Capitalize(decorator);
				break;
			case "reverse":
				decorator = new Reverse(decorator);
				break;
			case "num2String":
				decorator = new Num2String(decorator);
				break;
			case "abbreviate":
				decorator = new Abbreviate(decorator);
				break;
			case "unAbbreviate":
				decorator = new UnAbbreviate(decorator);
				break;
			case "removeDuplicates":
				decorator = new RemoveDuplicates(decorator);
				break;
			case "encodeBinary":
				decorator = new EncodeBinary(decorator);
				break;
			case "encode":
				decorator = new EncodePassword(decorator);
				break;
			case "decode":
				decorator = new DecodePassword(decorator);
				break;
			default:
				break;
			}
			return new ResponseEntity<>(new TextResponse(decorator.transform()), HttpStatus.OK);
		}
	}

	@PostMapping("/latexFormat")
	public ResponseEntity<?> transformToLatexFormat(@RequestBody TextParser textParser) {
		if (textParser.getContent() == null) {
			return new ResponseEntity<>(new ApiResponse(false, "Bad request!"), HttpStatus.BAD_REQUEST);
		} else if (textParser.getContent() == "" || textParser.getContent() == null) {
			logger.info("Empty text");
			return new ResponseEntity<>(new ApiResponse(false, "Empty string!"), HttpStatus.BAD_REQUEST);
		} else {
			
			Decorator decorator = new Decorator(new TextParser(textParser.getContent()));
			decorator = new LatexFormat(decorator);
			
			return new ResponseEntity<>(new TextResponse(decorator.transform()), HttpStatus.OK);
		}
	}

}
