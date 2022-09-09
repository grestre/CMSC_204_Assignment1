import java.util.*; 
import java.io.*; 

public class PasswordCheckerUtility {
	boolean valid; 
	boolean length; 
	ArrayList<String> password = new ArrayList<>(); 
	public PasswordCheckerUtility() {
		this.valid = valid; 
		this.length = length; 
		this.password = password; 
	}
	/**
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException if the passwords are different
	 * 
	 */
	//Compare passwords to check if they are the same
	static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if(!password.equals(passwordConfirm)) {
			throw new UnmatchedException("Passwords do not match"); 
		}
	}
	
	/**
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @return true if passwords are equals, false if they are not equal
	 */
	//Compare passwords. If they are the same return true, else return false
	static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		boolean status; 
		if(password.equals(passwordConfirm))
			status = true; 
		else
			status = false; 
		return status; 
	}
	
	/**
	 * 
	 * @param passwords
	 * @return a list of all invalid passwords
	 */
	//Create an invalid password list, run a for loop that iterates each password, if there is an exception add that invalid password to the 
	//invalid password list
	static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		ArrayList<String>invalidPassList = new ArrayList<String>();  
		for(String pass : passwords) {
			try {
				isValidPassword(pass); 
			} catch(Exception e) {
				invalidPassList.add(pass + " -> " + e.getMessage()); 
			}
		}
		return invalidPassList; 
	}
	
	/**
	 * 
	 * @param password
	 * @return true if the password is longer than 6 characters and less then 9 characters
	 */
	//check if the length of the password is between 6 and 9 characters
	static boolean hasBetweenSixAndNineChars(String password) {
		boolean status; 
		if(password.length() >= 6 || password.length() <=9)
			status = true; 
		else
			status = false; 
		return status; 
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoDigitException if the password does not contain a digit
	 */
	//Iterate every char in the password and check for any digit
	static boolean hasDigit(String password) throws NoDigitException{
		boolean status;
		char[] passChar = password.toCharArray();
		for(char a : passChar) {
			if(Character.isDigit(a)) {
				status = true; 
				return status; 
			}
		}
		throw new NoDigitException("The password must contain at least one digit"); 
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoLowerAlphaException if there is no lower case character
	 */
	//Iterate every char in the password and check for a lower case letter
	static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
		boolean status; 
		char[] passChar = password.toCharArray(); 
		for(char a : passChar) {
			if(Character.isLowerCase(a)) {
				status = true; 
				return status; 
			}
		}
		throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character"); 
	}

	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoSpecialCharacterException if there is no special character
	 */
	//Convert the password in charArray, iterate a for loop for each char, if the character is not a letter nor a digit return true
	//else throw NoSpecialCharacterException
	static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		boolean status; 
		char[] passChar = password.toCharArray(); 
		for(char a : passChar) {
			if(!Character.isLetter(a) && !Character.isDigit(a)) {
				status = true; 
				return status; 
			}
		}
		throw new NoSpecialCharacterException("The password must contain at least one special character");
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoUpperAlphaException if there is no upper case character
	 */
	//Iterate through every char in the password and check for an upper case
	static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
		boolean status; 
		char[] passChar = password.toCharArray(); 
		for(char a : passChar) {
			//Character class
			if(Character.isUpperCase(a)) {
				status = true; 
				return status; 
			}
		}
		throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character"); 
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws LengthException if the password is less than 6 characters
	 */
	//Check if the password is less than the minimum length, 6
	static boolean isValidLength(String password) throws LengthException{
		boolean status;
		if(password.length() >= 6) {
			status = true;
			return status; 
		}
			throw new LengthException("The password must be at least 6 characters long"); 
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws LengthException if the password is less than 6 characters
	 * @throws NoUpperAlphaException if there is no upper case character
	 * @throws NoLowerAlphaException if there is no lower case character
	 * @throws NoDigitException if there is no digit
	 * @throws NoSpecialCharacterException if there is no special character
	 * @throws InvalidSequenceException if 2 or more of the same characters are side by side
	 */
	static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException{
		//Go through every exception with try catch and call each method
		boolean status = true; 
		try {
			isValidLength(password); 
		}catch(LengthException e) {
			throw new LengthException("The password must be at least 6 characters long");
		}
		try {
			hasUpperAlpha(password); 
		}catch(NoUpperAlphaException e) {
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character"); 
		}
		try {
			hasLowerAlpha(password); 
		}catch(NoLowerAlphaException e) {
			throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character"); 
		}
		try {
			hasDigit(password); 
		}catch(NoDigitException e) {
			throw new NoDigitException("The password must contain at least one digit"); 
		}
		try {
			hasSpecialChar(password); 
		}catch(NoSpecialCharacterException e) {
			throw new NoSpecialCharacterException("The password must contain at least one special character"); 
		}
		try {
			NoSameCharInSequence(password); 
		}catch(InvalidSequenceException e) {
			throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence"); 
		}
		return status;
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws WeakPasswordException if the password is less than 10 characters
	 */
	//Check if each password less than 10 characters
	static boolean isWeakPassword(String password) throws WeakPasswordException{
		boolean status;
		if(!hasBetweenSixAndNineChars(password)) {
			status = true; 
			return status; 
		}
		throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters."); 
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws InvalidSequenceException if 2 or more of the same characters are side by side
	 */
	//Convert the password to an array of chars, run a for loop that goes through the password, Create a variable with Character data class
	//Compare the variable with Character data class with array of chars with equals methods. 
	static boolean NoSameCharInSequence(String password) throws InvalidSequenceException{
		boolean status = true; 
		char[] passChar = password.toCharArray(); 
		for(int a = 0; a < password.length(); a++) {
			Character a2 = passChar[a];
			//a must be longer than 2 (characters)
			if(a>=2) {
				if(a2.equals(passChar[a-1]) && a2.equals(passChar[a-2])) {
					throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence"); 
				}
			}
		}
		return status; 
	}
}
