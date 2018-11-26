package textTransformers.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import textTransformers.models.TextParser;
import textTransformers.payload.ApiResponse;
import textTransformers.payload.TextResponse;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TextTransformerController {

	@Autowired
	private TextParser textParser;

	private String message;

	private List<String> availableFunctions = Arrays.asList("upper", "lower", "capitalize", "reverse", "num2String",
			"abbreviate", "unAbbreviate", "encode", "decode");

	static Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

	@RequestMapping("/transform")
	public ResponseEntity<?> transform(@RequestParam(value = "text", required = false) String text,
			@RequestParam(value = "function", required = false) String function) {
		if (text == "" || text == null) {
			logger.info("Empty text");
			return new ResponseEntity<>(new ApiResponse(false, "Empty string!"), HttpStatus.BAD_REQUEST);
		} else {
			textParser.setContent(text);
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
			case "abbreviate":
				textParser.abbreviate();
				break;
			case "unAbbreviate":
				textParser.unAbbreviate();
				break;
			case "encode":
				textParser.encode();
				break;
			case "decode":
				textParser.decode();
				break;
			default:
				break;
			}
			return new ResponseEntity<>(new TextResponse(textParser.getContent()), HttpStatus.OK);
		}
	}

	@PostMapping("/latexFormat")
	public ResponseEntity<?> transformToLatexFormat(@RequestBody Map<String, String> payload) {
		if (payload.get("text") == null) {
			return new ResponseEntity<>(new ApiResponse(false, "Bad request!"), HttpStatus.BAD_REQUEST);
		} else if (payload.get("text") == "" || payload.get("text") == null) {
			logger.info("Empty text");
			return new ResponseEntity<>(new ApiResponse(false, "Empty string!"), HttpStatus.BAD_REQUEST);
		} else {
			textParser.setContent(payload.get("text"));
			textParser.latexFormat();
			return new ResponseEntity<>(new TextResponse(textParser.getContent()), HttpStatus.OK);
		}
	}

}
