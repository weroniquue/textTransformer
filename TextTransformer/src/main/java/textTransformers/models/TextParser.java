package textTransformers.models;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 
 * String container
 * 	capable of applying transformations
 *	
 */

@Repository
public class TextParser {

    private String content;

    public TextParser() {
       
    }
    
    
    /**
     * function for applying chosen transformations
     *  ! not implemented yet !
     *  + mysle, ze do Upper/lowercase/capitalize wystarczą nam ify w tej funkcji, bez dodatkowych f() but dunno
     */
    public void transform(){ 
        String listed[] = this.content.split(" ");
        for (int i=0; i<listed.length; i++){
            //System.out.println(listed[i]);
            try {
                int num = Integer.parseInt(listed[i]);
                listed[i] = num2str(num);
            }catch (NumberFormatException ex){
                continue;
            }
            //System.out.println(listed[i]);
        }
        this.content = String.join(" ",listed);
    }
    
    /**
     * transforms given number into words
     * @param numberIn - int in range: 1 - 999,999,999
     */
    private String num2str(int numberIn) {
        int digitsHundreds = numberIn%1000;  // "......***"
        numberIn /= 1000;
        int digitsThousands = numberIn%1000; // "...***..."
        numberIn /= 1000;
        int digitsMillions = numberIn%1000;  // "***......"

        String resultHundreds = "";
        String resultThousands = "";
        String resultMillions = "";
        String hundreds[] ={
                "",
                "sto",
                "dwieście",
                "trzysta",
                "czterysta",
                "pięćset",
                "sześćset",
                "siedemset",
                "osiemset",
                "dziewięćset"};
        String tensUnder20[] = {
                "dziesięć",
                "jedenaście",
                "dwanaście",
                "trzynaście",
                "czternaście",
                "piętnaście",
                "szesnaście",
                "siedemnaście",
                "osiemnaście",
                "dziewiętnaście"
        };
        String tens[] = {
                "",
                "",
                "dwadzieścia",
                "trzydzieści",
                "czterdzieści",
                "pięćdziesiąt",
                "sześćdziesiąt",
                "siedemdziesiąt",
                "osiemdziesiąt",
                "dziewięćdziesiąt"
        };
        String ones[] = {
                "",
                "jeden",
                "dwa",
                "trzy",
                "cztery",
                "pięć",
                "sześć",
                "siedem",
                "osiem",
                "dziewięć"
        };


        // Hundreds ......***
        resultHundreds = hundreds[digitsHundreds/100];
        if ((digitsHundreds/10)%10==1) {
            resultHundreds = resultHundreds + " " + tensUnder20[digitsHundreds%10];
        }
        else {
            resultHundreds = resultHundreds + " " + tens[(digitsHundreds%100)/10] + " " + ones[digitsHundreds%10];
        }

        // Thousands ...***...
        resultThousands = hundreds[digitsThousands/100];
        if (digitsThousands==0){

        }
        else if ((digitsThousands/10)%10==1) {
            resultThousands = resultThousands + " " + tensUnder20[digitsThousands%10] + " tysięcy";
        }
        else{
            resultThousands = resultThousands + " " + tens[(digitsThousands%100)/10] + " " + ones[digitsThousands%10];
            if (digitsThousands==1) resultThousands = "tysiąc";
            else if (digitsThousands%10 >=2 && digitsThousands%10 <=4){
                resultThousands = resultThousands + " tysiące";
            }
            else resultThousands = resultThousands + " tysięcy";
        }

        // Millions ***......
        resultMillions = hundreds[digitsMillions/100];
        if (digitsMillions==0){

        }
        else if ((digitsMillions/10)%10==1){
            resultMillions= resultMillions + " " + tensUnder20[digitsMillions%10] + " milionów";
        }
        else {
            resultMillions = resultMillions + " " + tens[(digitsMillions%100)/10] + " " + ones[digitsMillions%10];
            if (digitsMillions==1) resultMillions = "milion";
            else if(digitsMillions%10 >=2 && digitsMillions%10 <=4){
                resultMillions = resultMillions + " miliony";
            }
            else resultMillions = resultMillions + " milionów";
        }
        return (resultMillions + " " + resultThousands + " " + resultHundreds).trim();
    }

    /**
     * Reverses letters and order of Upper/Lowercase in text
     *  
     */
    public String reverse(String text) {
    	//todo
    }
    
    /**
     * applies predefined abbreviations into text  (wtf help me here)
     * 
     */
    public String abbreviate(String text) {
    	//todo
    }
    
    /**
     * 
     * 
     * 
     */
    public String unAbbreviate(String text) {
    	//todo
    }
    
    
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
