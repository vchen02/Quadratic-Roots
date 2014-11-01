//ROOTS OF AN QUADRATIC EQUATION
//usage: ax^2 + bx + c
//result:
//root 1: [-b + sqrt(b^2 - 4ac)] / 2a 
//root 2: [-b - sqrt(b^2 - 4ac)] / 2a 

import java.util.Scanner;

// Custom Negative Number Exception
class NegNumber extends Exception {
	private int e;
	public NegNumber (int e){
		this.e = e;
	}
	
	public String toString() {
		return "(b^2) - 4*a*c yields negative discriminant " + e;
	}
}

class Quadratic_Equation {
	private int a, b, c;
	
	//This Constructor should never be called
	public Quadratic_Equation() {
		System.err.println("usage: ax^2 + bx + c");
	}

	public Quadratic_Equation(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	//calculate the discriminate value
	public int discriminant() {
		return ((b*b) - 4*a*c);	
	}
	
	//returns the positive root of the quadratic equation
	public double pos_root() {
		//calculates root 1: [-b + sqrt(b^2 - 4ac)] / 2a 
		return ( (-b + Math.sqrt(discriminant()))/(2*a) );
	}
	
	//returns the negative root of the quadratic equation
	public double neg_root() {
		//calculates root 2: [-b - sqrt(b^2 - 4ac)] / 2a 
		return ( (-b - Math.sqrt(discriminant()))/(2*a) ) ;
	}
	
	//print both positive and negative roots of the quadratic equation
	public void print_roots() {
		System.out.printf("Root 1 of Quad Eq:\t%.2f\n", this.pos_root());
		System.out.printf("Root 2 of Quad Eq:\t%.2f\n", this.neg_root());
	}
}

public class Quadratic_Roots {
	
	//Valid User Input include: 
	//	Case 1: a b c are all positive single digit integer values => Minimum Length = 5
	//  Case 2: '-' negative character must be followed by an integer digit
	//	Case 3: ' ' whitespace character must be followed by either an '-' or integer digit
	//	Case 4: integer digit  must be followed by another integer digit or white space unless it is the last character
	//	All other forms of user input are consider invalid. User must re-enter input that is valid.
	
	//validateInput returns true when the User input is invalid
	//validateInput returns false when the User input is valid
	private static Boolean validateInput(String input) {
		char ch, ch_next;
		//Case 1
		if ( input.length() < 5 )
			return true;
	
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			
			if(i < input.length() -1)
			{
				ch_next = input.charAt(i+1);
				//Case 2
				if ( ch == '-'  && !Character.isDigit(ch_next))
					return true;
				//Case 3
				else if ( ch == ' ' && ch_next != '-' && !Character.isDigit(ch_next)) 
					return true;
				//Case 4a
				else if ( Character.isDigit(ch) && ch_next != ' '  && !Character.isDigit(ch_next)) 
					return true; 
			}
			//Case 4b
			else if (!Character.isDigit(ch))
				return true; 
		} 
		return false;
	}
	
	//Main program
	public static void main(String args[]) throws NegNumber {
		//Variable Initializations
		Boolean more = true, bad_input = true; 
		String temp = "", s[] = null;
		int a, b, c;
		Scanner sc = new Scanner(System.in);
		
		//Begin program
		while(more) {
			
			//Request for user input
			while (bad_input) {
				System.out.printf("Please enter ax^2 + bx + c (usage1: a b c or '-1' to terminate): ");
				temp = sc.nextLine();
				if (temp.equals("-1")){
					more = false;
					break;
				}
				//Validate user input
				//bad_input = true if  user input is invalid
				//bad_input = false if user input is valid
				bad_input = validateInput(temp);
			}
			
			if (more) {
				s = temp.split(" ");	//split temp string into string arrays
				
				if (s.length != 3)		//string array can only have size == 3
				{	
					bad_input = true;
					continue;
				}
				a = Integer.parseInt(s[0]);
				b = Integer.parseInt(s[1]);
				c = Integer.parseInt(s[2]);	
					
				Quadratic_Equation qe = new Quadratic_Equation(a,b,c);
				
				try {		//try and throw exception if discriminant is negative
					if(qe.discriminant() < 0) {
						throw new NegNumber(qe.discriminant());
					}
					else {
						qe.print_roots();
						bad_input = true;
					}
				} 
				catch (NegNumber bad) {
					System.out.println(bad);
					bad_input = true;
					continue;
				}
			}
		}
		return;
	}
}