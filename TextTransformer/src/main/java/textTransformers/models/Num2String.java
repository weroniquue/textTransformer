package textTransformers.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * transforms given numbers into words
 * 
 * @param numberIn - int in range: 1 - 999,999,999
 */
public class Num2String extends Decorator{
	
	static Logger logger = LoggerFactory.getLogger(Num2String.class);

	public Num2String(ITextParser input) {
		super(input);
		// TODO Auto-generated constructor stub
	}


	public String num2StringTransform(String text) {
		String listed[] = text.split(" ");
		for (int i = 0; i < listed.length; i++) {

			try {
				logger.info("Function which change numbers to string.");
				int num = Integer.parseInt(listed[i]);
				listed[i] = num2str(num);
			} catch (NumberFormatException ex) {
				logger.error("Catched NumberFormatException!");
				;
				continue;
			}

		}
		text = String.join(" ", listed);
		return text;
	}
	
	
	private String num2str(int numberIn) {
		if (numberIn == 0) return "zero";
		int digitsHundreds = numberIn % 1000; // "......***"
		numberIn /= 1000;
		int digitsThousands = numberIn % 1000; // "...***..."
		numberIn /= 1000;
		int digitsMillions = numberIn % 1000; // "***......"

		String resultHundreds = "";
		String resultThousands = "";
		String resultMillions = "";
		String hundreds[] = { "", "sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset",
				"osiemset", "dziewięćset" };
		String tensUnder20[] = { "dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście",
				"szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście" };
		String tens[] = { "", "", "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt",
				"siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt" };
		String ones[] = { "", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć" };

		// Hundreds ......***
		resultHundreds = hundreds[digitsHundreds / 100];
		if ((digitsHundreds / 10) % 10 == 1) {
			resultHundreds = resultHundreds + " " + tensUnder20[digitsHundreds % 10];
		} else {
			resultHundreds = resultHundreds + " " + tens[(digitsHundreds % 100) / 10] + " " + ones[digitsHundreds % 10];
		}

		// Thousands ...***...
		resultThousands = hundreds[digitsThousands / 100];
		if (digitsThousands == 0) {

		} else if ((digitsThousands / 10) % 10 == 1) {
			resultThousands = resultThousands + " " + tensUnder20[digitsThousands % 10] + " tysięcy";
		} else {
			resultThousands = resultThousands + " " + tens[(digitsThousands % 100) / 10] + " "
					+ ones[digitsThousands % 10];
			if (digitsThousands == 1)
				resultThousands = "tysiąc";
			else if (digitsThousands % 10 >= 2 && digitsThousands % 10 <= 4) {
				resultThousands = resultThousands + " tysiące";
			} else
				resultThousands = resultThousands + " tysięcy";
		}

		// Millions ***......
		resultMillions = hundreds[digitsMillions / 100];
		if (digitsMillions == 0) {

		} else if ((digitsMillions / 10) % 10 == 1) {
			resultMillions = resultMillions + " " + tensUnder20[digitsMillions % 10] + " milionów";
		} else {
			resultMillions = resultMillions + " " + tens[(digitsMillions % 100) / 10] + " " + ones[digitsMillions % 10];
			if (digitsMillions == 1)
				resultMillions = "milion";
			else if (digitsMillions % 10 >= 2 && digitsMillions % 10 <= 4) {
				resultMillions = resultMillions + " miliony";
			} else
				resultMillions = resultMillions + " milionów";
		}
		String result = (resultMillions + " " + resultThousands + " " + resultHundreds).trim();
		int index = result.indexOf("  ");
		while (index >= 0)
		{
			
			result = result.substring(0,index) + result.substring(index+1);
			index = result.indexOf("  ");
		}
		return result;
	}


	@Override
	public String transform() {
		return num2StringTransform(super.transform());
	}
	
	

}
