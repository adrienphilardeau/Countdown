
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class CountdownDays {
    
    
    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now);
    }
    
  
    public static String getSubstring(String s, int i, int j){    //string and two int inputs
    	if(j<i)
    		throw new IllegalArgumentException("Your second integer must be greater than your first integer.");  //throws error if j>i
    	else
    	{
    		String str = "";   //initializes a string
    		for(int k = i; k <= j; k++)
    			str += s.charAt(k);   //adds to string values of s from i to j
    		return str;   //returns the new string
    	}
    }
    
    public static int getDay(String s){    //takes string as input
    	String str = getSubstring(s, 0, 1);    //creates new string from first and second character
    	return Integer.parseInt(str);   //turns the string into an int and returns it
    }
    
    public static int getMonth(String s){    //takes string as input
    	String str = getSubstring(s, 3, 4);   //creates new string from third and fourth character
    	return Integer.parseInt(str);    //turns the string into an int and returns it
    }
    
    public static int getYear(String s){    //takes string as input
    	String str = getSubstring(s, 6, 9);   //creates new string from sixth and ninth character
    	return Integer.parseInt(str);    //turns the string into an int and returns it
    }
    
    public static boolean isLeapYear(int x){  //takes int as input
    	if(x % 100 == 0){   //checks if year is century year
    		if(x % 400 == 0)   //checks if century year is divisible by 400
    			return true;  //returns true if it is
    		else
    			return false;  //false if it is not
    	}		
    	if(x % 4 == 0)     //if year is not a century year, checks if it is divisible by 4
    		return true;   //returns true if it is
    	else                  
    		return false;       //returns false otherwise
    }
    
    public static int getDaysInAMonth(int x, int y){
    	if(x == 1 || x == 3 || x == 5 || x == 7 || x == 8 || x == 10 || x == 12)  //first checks the months that always have 31 days
    		return 31;    
    	else if(isLeapYear(y) && x == 2)   //checks if the year is a leap year and the month is february
    		return 29;  
    	else if(x == 2)  //checks if the month is february when it is not a leap year
    		return 28;   
    	else
    		return 30;  //returns 30 days for all other months
    	}
    
    public static boolean dueDateHasPassed(String s, String t){
    	if(getYear(s) > getYear(t))   //checks if current year is later than due date year
    		return true;    //returns true if it is
    	else if(getYear(s) < getYear(t))
    		return false;   //if the current year is earlier, return false
    	if(getMonth(s) > getMonth(t))  //if the year is the same, does the same thing with month
    		return true;
    	else if(getMonth(s) < getMonth(t))
    		return false;
    	if(getDay(s) > getDay(t))  //if the year and month are the same, does the same thing with day
    		return true;
    	else if(getDay(s) < getDay(t))
    		return false;
    	return true;  //if the singular case where due date and current date are identical, return true
    }
    
    public static int countDaysLeft(String s, String t){
    	if(dueDateHasPassed(s,t))  //returns 0 if the due date has passed
    		return 0;
    	int days = 0;   //initializes number of days
    	for(int i = getYear(s) + 1; i < getYear(t); i++){  //adds 365 for each year and 365 for each leap year for each year in between the two dates
    		if(isLeapYear(i))
    			days += 366;
    		else
    			days += 365;
    	}
    	if(getYear(s) != getYear(t)){   //when the year is not the same
    		for(int j = getMonth(s) + 1; j <= 12; j++)  //add number of days in each month between the months in the strings, but only for years that are part of the two strings
    			days += getDaysInAMonth(j, getYear(s)); 
    		for(int k = 1; k < getMonth(t); k++)    
    			days += getDaysInAMonth(k, getYear(t));
    	}
    	else
    	{
    		for(int l = getMonth(s) + 1; l < getMonth(t); l++)   //when year is the same, add number of days in months in between
    			days += getDaysInAMonth(l, getYear(s));
    	}
    	if(getMonth(s) == getMonth(t)){   //checks if months are the same and the day of the first date is before
    		if(getDay(s) < getDay(t))
    			days += getDay(t) - getDay(s);  //if both are true, add the difference between the two days
    		}
    	else   //in all other cases add the days between the day in the first string and the number of days in that month and the days in the second string 
    	{
    		days += getDaysInAMonth(getMonth(s), getYear(s)) - getDay(s);  
    		days += getDay(t);
    	}
    	return days;    //return days
    }
    
   public static void displayCountdown(String s){
	   int x = countDaysLeft(getCurrentDate(), s);     //initializes x and assigns value of days left between two dates
	   if(x > 0){    //if the due date has not passed, print encouraging message with the two dates and how many days are left
		   System.out.println("Today is: " + getCurrentDate());
		   System.out.println("Due date: " + s);
		   System.out.println("You have " + x + " days left. You can do it!");
	   }
	   else   //otherwise, print the two dates and a sympathetic message
	   {
		   System.out.println("Today is: " + getCurrentDate());
		   System.out.println("Due date: " + s);
		   System.out.println("The due date has passed! :( Better luck next time!");
	   }	   
   }
    
    public static void main(String[] args) {
    	displayCountdown("23/01/2019");    //assignment due date
    }  
}