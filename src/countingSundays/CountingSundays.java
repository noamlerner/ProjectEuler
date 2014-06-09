package countingSundays;

import java.util.Scanner;

public class CountingSundays {
	public int days; //[day since january 1 1901]
	final static int daysInJanuary = 31;
	final static int daysInFebruaryNLP = 28;
	final static int daysInFebruaryLP = 29;
	final static int daysInMarch = 31;
	final static int daysInApril= 30;
	final static int daysInMay = 31;
	final static int daysInJune = 30;
	final static int daysInJuly= 31;
	final static int daysInAugust = 31;
	final static int daysInSeptember = 30;
	final static int daysInOctober= 31;
	final static int daysInNovember= 30;
	final static int daysInDecember = 31;
	
	public CountingSundays(){
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Year: ");
		int year = keyboard.nextInt();

		System.out.print("Month: ");
		int month = keyboard.nextInt();

		System.out.print("Day: ");
		int day = keyboard.nextInt();
		days = daysSince111901(day, month, year);
		System.out.println("Total Days: " + days);
		System.out.print(countDay(1));
		keyboard.close();
	}
	private int daysSince111901(int day, int month, int year){
		int days =0;
		month--;
		for(int i = 1901; i < year; i++){
			/*if(i%4 == 0 && i%100!=0)days+= 366;
			if(i%4 != 0 && i%100!=0)days+= 365;
			if(i%400 == 0)days+= 366; 
			if(i%400 != 0 && i%100 == 0)days+= 365;*/
			if(leapYear(i)) days+= 366;
			else days+=365;
		}
		days--;
		for(int i =0; i <month; i++){
			if(i==3||i==5||i==8||i==10||i==11) days+=30;
			if(i==0||i==2||i==4||i==6||i==7||i==9) days+=31;
			if(i==1){
				if(leapYear(year)) days+=29;
				else days+=28;
			}
		}
		days+=day;
		
		return days;
	}
	
	
	private int countDay(int day){
		int count = 0;
		int currentDay =3;
		for(int i = 0; i <days;i++){
			if(firstDay(i) && currentDay == day)
				count++;
			currentDay++;
			if(currentDay ==8)currentDay=1;
		}
		return count;
	}
	private boolean firstDay(int daySince111901){
		int firstDay=0;
		int process = 1;
		while(daySince111901 >= firstDay){
			if (firstDay == daySince111901) return true;
			if(process == 13)process =1;
			switch (process) {
			case 1:
				firstDay+= daysInJanuary;
				break;
			case 2:
				if(leapYear((int)(firstDay/365)+1901))	firstDay+= daysInFebruaryLP;
				else	firstDay+= daysInFebruaryNLP;
				break;
			case 3:
				firstDay+= daysInMarch;
				break;
			case 4:
				firstDay+= daysInApril;
				break;
			case 5:
				firstDay+= daysInMay;
				break;
			case 6:
				firstDay+= daysInJune;
				break;
			case 7:
				firstDay+= daysInJuly;
				break;
			case 8:
				firstDay+= daysInAugust;
				break;
			case 9:
				firstDay+= daysInSeptember;
				break;
			case 10:
				firstDay+= daysInOctober;
				break;
			case 11:
				firstDay+= daysInNovember;
				break;
			case 12:
				firstDay+= daysInDecember;
				break;
			default:
				break;
			}
			process++;
		}
		return false;
	}
	private boolean leapYear(int year){
		if(year%400 == 0)return true; 
		if(year%4 == 0 && year%100!=0)return true;
		return false;
	}
	public static void main(String [] args){
		CountingSundays cs = new CountingSundays();
	}
}
