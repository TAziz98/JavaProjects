package Assignment9;
	import java.io.*; 
	import java.net.*; 
	import java.util.ArrayList; 
	import java.util.Date; 
	import java.util.HashMap; 
	import java.util.List; 
	import java.util.Map; 
	  
	public abstract class Receivers {
		//dummy method
		public  abstract  String getURL() throws IOException ;
		public abstract  void message(Date initialdate);
	}
	


