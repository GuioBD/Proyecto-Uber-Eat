package Clases;
import java.util.Random;

public class Generator {
    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*_=+-/";
    private static final int PASSWORD_LENGTH = 8;
    
    public static String generatePassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        
        // Pick random characters from each category
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            switch (i % 4) {
                case 0:
                    password.append(LOWERCASE_CHARACTERS.charAt(random.nextInt(LOWERCASE_CHARACTERS.length())));
                    break;
                case 1:
                    password.append(UPPERCASE_CHARACTERS.charAt(random.nextInt(UPPERCASE_CHARACTERS.length())));
                    break;
                case 2:
                    password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
                    break;
                default:
                    password.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
                    break;
            }
        }
        
        return password.toString();
    }
    
    public static String generateId(String tipoEmpresa,String codigoPostal,String codigoPais,long n){
        
        return tipoEmpresa.substring(0,3) + codigoPostal.substring(codigoPostal.length()-2,codigoPostal.length()) + 
                codigoPais.substring(codigoPais.length()-2,codigoPais.length())+ n;
        
    }
}
