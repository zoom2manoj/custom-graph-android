package com.manoj.grapviewdemo;

import java.text.SimpleDateFormat;
import java.util.Date;


import android.app.Activity;
import android.os.Bundle;



import android.widget.LinearLayout;


public class MainActivity extends Activity {

	LinearLayout layout;
	int screenWidth;
	  float aPoint[] = {1000, 1500, 2000, 100, 5000, 7000, 3000, 50, 500};
	    String bPoint[] = {"01-10-2012", "02-10-2012", "03-10-2012", "04-10-2012", "05-10-2012", "06-10-2012", "07-10-2012", "08-10-2012", "09-10-2012"};
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_main);
		layout = (LinearLayout) findViewById(R.id.graplayout);
		
		MyView mv = new MyView(getApplicationContext(), getWindowManager().getDefaultDisplay().getWidth(), aPoint, bPoint);
		layout.addView(mv);
		
	}

	private String getMonth() {
		// TODO Auto-generated method stub
		String value = null;
		try {

			String dateStart = "01-10-2012";
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date d = format.parse(dateStart);
			
			//Date d = new Date(dateStart);
			//Date d = sdf.parse(dateStart);
			//sdf.applyPattern("dd MMM");
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
			value = sdf.format(d);
			System.out.println("manoj : "+value);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage() );
			
		}
		
		return value;

	}

	private void dateDifference(String startDate, String endDate) {
		// TODO Auto-generated method stub
		String dateStart = "01-10-2012";
		String dateStop = "06-11-2012";

		// System.out.println(new SimpleDateFormat("MMMM").format(dateStart));

		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
