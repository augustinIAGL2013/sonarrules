package com.app.web.webapplication;


/**
 * Hello world!
 *
 */
public class App 
{
	public int[] array;

	//Performance - Private method is never called
	//This private method is never called. Although it is possible that the method will be invoked through reflection, it is more likely that the method is never used, and should be removed.
	private void neverCalledMethod(){
		//nevercalled method
	}

	//Security - Array is stored directly
	//Constructors and methods receiving arrays should clone objects and store the copy. This prevents that future changes from the user affect the internal functionality.
	public void addArray(int[] list){
		array=list;
	}

	//Security - Method returns internal array
	//Exposing internal arrays directly allows the user to modify some code that could be critical. It is safer to return a copy of the array.
	public int[] returnArray(){
		return this.array;
	}

	//Missing Break In Switch
	//A switch statement without an enclosed break statement may be a bug.
	//and
	//Switch statement found where one case falls through to the next case
	//This method contains a switch statement where one case branch will fall through to the next case. Usually you need to end this case with a break or return.
	public void swithcasemeth(){
		switch (array[0]) {
		case 1:
			array[0]++;
			//error 

		case 2:
			array[0]++;
			break;

		default:
			break;
		}
	}


	//Unconditional If Statement
	//Do not use if statements that are always true or always false.
	public void iftest(){
		if(true) ;
	}


	//Naming - Suspicious equals method name
	//The method name and parameter number are suspiciously close to equals(Object), which may mean you are intending to override the equals(Object) method. Example :
	public boolean equals(Object o) {
		// oops, this probably was supposed to be boolean equals
		return true;
	}
	public boolean equals(String s) {
		// oops, this probably was supposed to be equals(Object)
		return true;
	}


	//Performance - Method concatenates strings using + in a loop
	//The method seems to be building a String using concatenation in a loop. In each iteration, the String is converted to a StringBuffer/StringBuilder, appended to, and converted back to a String. This can lead to a cost quadratic in the number of iterations, as the growing string is recopied in each iteration.
	//Better performance can be obtained by using a StringBuffer (or StringBuilder in Java 1.5) explicitly.
	public void perfplus(){
		// This is bad
		int[] field=new int[]{0,1,2,3,4,5,6};
		String s = "";
		for (int i = 0; i < field.length; ++i) {
			s = s + field[i];
		}
	}


	//Performance - Method invokes inefficient Number constructor; use static valueOf instead
	//Using new Integer(int) is guaranteed to always result in a new object whereas Integer.valueOf(int) allows caching of values to be done by the compiler, class library, or JVM. Using of cached values avoids object allocation and the code will be faster.
	public int useNonOptimisedIntegerMethod(){
		return new Integer(5); 
	}

	//Dodgy - Useless control flow
	//This method contains a useless control flow statement, where control flow continues onto the same place regardless of whether or not the branch is taken. For example, this is caused by having an empty statement block for an if statement:
	public void ifStatementEmpty(int a){
		if(a==0){
			//do something
		}
	}

	//Misplaced Null Check
	//The null check here is misplaced. if the variable is null you'll get a NullPointerException. Either the check is useless (the variable will never be null) or it's incorrect. 
	public void nullcheck(){
		String object1="aa",object2="er";
		if (object1!=null && object2.equals(object1)) { 
			//do something
		}      
	}


	public static void main( String[] args )
	{
		System.out.println( "Hello World!" );
		App ap=new App();
		ap.addArray(new int[]{1,2});
		ap.returnArray();
		ap.swithcasemeth();
		ap.iftest();
		ap.perfplus();
		ap.useNonOptimisedIntegerMethod();
		ap.nullcheck();
	}
}
