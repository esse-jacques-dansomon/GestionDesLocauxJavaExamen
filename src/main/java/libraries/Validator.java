package libraries;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

public class Validator {

    public static boolean isValidMail(String email)
    {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    
    /**
     * Validate an not null string
     * @param email
     * @return bollen
     */
    public static boolean isValidNom(String nom) {
        if (nom == null ||  nom.equals(""))  {
            return false ;
        } else { 
            return true;
        }
    }

    
    /**
     * Validate a nci senegalese
     * @param email
     * @return bollen
     */
    public static boolean isValidNci(String nci)
    {
        String nciRegex = "(^[1|2])([0-9]{3})(19[4-9]\\d|20[0-1]\\d|2020|2021)([0-9]{5})$";

        Pattern pat = Pattern.compile(nciRegex);
        if (nci == null)
            return false;
        return pat.matcher(nci).matches();
    }
    
    /**
    * Validate a telephone number(senegalese)
    * @param email
    * @return bollen
    */
    public static boolean isValidTel(String tel)
    {
        String telRegex = "^(\\+|00)(221)(70|76|77|78|33)([0-9]{7})$";

        Pattern pat = Pattern.compile(telRegex);
        if (tel == null)
            return false;
        return pat.matcher(tel).matches();
    }
    

    /**
     * Validate a correct date
     * @param dateStr
     * @return bollen
    */
    //Java 8 - Use DateTimeFormatter (thread-safe)
    public static LocalDate isValideDate(String dateStr) 
    {

        String dateFormat = "uuuu-MM-dd";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
        return date;
    }


}


