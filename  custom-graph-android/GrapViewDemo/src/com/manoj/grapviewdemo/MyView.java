package com.manoj.grapviewdemo;

import java.text.SimpleDateFormat;
import java.util.Date;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.View;

public class MyView extends View {
	
	int screenWidth ;
    
    
    float caloriesArray[] = {100, 300, 600, 1600, 3000, 5000, 8000};
    
    
    float aPoint[] ;
    String bPoint[] ;
 
    
    int xConstant = 45;
    int yConstant = 80;
    
    
    public MyView(Context context, int screenWidth, float aPoint[], String bPoint[]) {
        super(context);
        this.screenWidth = screenWidth;
        this.aPoint = aPoint;
        this.bPoint = bPoint;
   }
    
   @Override
   protected void onDraw(Canvas canvas) {
      // TODO Auto-generated method stub
      super.onDraw(canvas);
      int x = getWidth();
      int y = getHeight();
      
      System.out.println(" x : "+x);
      int radius;
      radius = 100;
      Paint paint = new Paint();
      paint.setStyle(Paint.Style.FILL);
      paint.setColor(Color.GREEN);
      canvas.drawPaint(paint);
      // Use Color.parseColor to define HTML colors
      paint.setColor(Color.parseColor("#CD5C5C"));
      //canvas.drawCircle(x / 2, y / 2, radius, paint);
      paint.setStrokeWidth(2);
      paint.setStyle(Paint.Style.STROKE); 
      
      //========== Draw lines for point ======
      float xRange = x-25-xConstant;
      float yRange = y-yConstant-xConstant;
      
      
      //=== X and Y Asix ========
      drawCustomLine(canvas, new float[]{xConstant, y-yConstant}, new float[]{x-25, y-yConstant} , paint);
      drawCustomLine(canvas, new float[]{xConstant, xConstant}, new float[]{xConstant, y-yConstant} , paint);
     
      
      /// draw text 
      Typeface tf = Typeface.create("Helvetica",Typeface.BOLD);
      
      paint.setTypeface(tf);
      paint.setTextSize(20);
      drawCustomText(canvas, new float[]{xConstant-10, y-yConstant+20}, paint, "(0,0)");
      //drawCustomText(canvas, new float[]{(x/2)-12, y-yConstant+30}, paint, "X axis");
      drawCustomText(canvas, new float[]{xConstant-15, yConstant-50}, paint, "Kcal");
      pointManipulation(canvas, paint, x, y, aPoint, bPoint);
      
      //======= Horizontal line according to calories
      customYLine(canvas, paint, x, y, caloriesArray, new float[]{xConstant, x-xConstant+20});
      customXLine(canvas, paint, x, y);
     // dateDifference();
  }
   
   
   private int dateDifference(String dateStart, String dateStop) {
		// TODO Auto-generated method stub
	   int diffDay = 0;
		
			//HH converts hour in 24 hours format (0-23), day calculation
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

			Date d1 = null;
			Date d2 = null;

			try {
				d1 = format.parse(dateStart);
				d2 = format.parse(dateStop);

				//in milliseconds
				long diff = d2.getTime() - d1.getTime();

				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);

				diffDay = (int) diffDays ;
				//System.out.print(diffDays + " days, ");

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return diffDay;
	}
   
   private void customXLine(Canvas canvas, Paint paint, int x, int y) {
	   
	   float xRange ;
	  
	   
	   xRange = x-80-xConstant;
	   
	   float yRange = y-yConstant-xConstant;
	   
	   
	   
	   for (int i = 0; i < bPoint.length; i++) {
		   
		   String fDate = bPoint[0];
		   String displayDate ;
		   float a ;
		   
			   if (i==0) {
				   a = xConstant+(xRange/bPoint.length)*(1);
				   displayDate = getMonth(fDate);
			   }else {
				   int diff = dateDifference(fDate, bPoint[i]);
				   a = xConstant+(xRange/bPoint.length)*(diff+1);
				   displayDate = getMonth(bPoint[i]);
			   }
			   
		
		   
		   

		   paint.setStyle(Style.FILL);
		   paint.setAntiAlias(true);
		   paint.setColor(Color.BLACK);
		   canvas.drawCircle(a, y-yConstant, 2, paint);
		   drawCustomText(canvas, new float[]{a-15, y-yConstant+25}, paint, displayDate);
	}  
   }
   private String getMonth(String dateValue) {
		// TODO Auto-generated method stub
		String value = null;
		try {			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date d = format.parse(dateValue);
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
			value = sdf.format(d);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage() );
			
		}
		return value;

	}
   private void customYLine(Canvas canvas, Paint paint, float x, float y, float[] calaries, float[] xPoint) {
	// TODO Auto-generated method stub
	   for (int i = 0; i < calaries.length; i++) {
			float c1 = calaries[i];
			
			String calValue = String.valueOf((int) c1); 
		   
		   float xRange = x-25-xConstant;
		   float yRange = y-yConstant-xConstant;
		   
		   c1 = y-yConstant-(yRange/10000)*c1;
		   
		   
		   if (calValue.equals("100")) {
			   paint.setColor(Color.parseColor("#883F31"));
		   }else if (calValue.equals("300")) {
			   paint.setColor(Color.parseColor("#9A682B"));
		   }else if (calValue.equals("600")) {
			   paint.setColor(Color.parseColor("#7E802C"));
		   }else if (calValue.equals("1600")) {
			   paint.setColor(Color.parseColor("#2A782E"));
		   }else if (calValue.equals("3000")) {
			   paint.setColor(Color.parseColor("#1D7176"));
		   }else if (calValue.equals("5000")) {
			   paint.setColor(Color.parseColor("#2E2E46"));
		   }else if (calValue.equals("yConstant00")) {
			   paint.setColor(Color.parseColor("#481338"));
		   }
		   
		   
		   drawCustomText(canvas, new float[]{0,c1 }, paint, calValue);
		   drawCustomLine(canvas, new float[]{xPoint[0],c1 }, new float[]{xPoint[1], c1}, paint);
		   
	   }
}
   
   private void pointManipulation(Canvas canvas, Paint paint, float x, float y, float[] calaries, String[] days) {
	// TODO Auto-generated method stub
	  
	   
	   
	   
	   for (int i = 0; i < bPoint.length; i++) {
		
		   float c1 = 0;
			float c2 = 0;
		String fDate = days[0];
		float d1, d2;
		if (i==0) {
			d1 = 0;
			d2 = 1;		
			c1 = 0;
			c2 = calaries[i];
		}else if (i==(bPoint.length-1)){
			
			c1 = calaries[i-1];
			c2 = calaries[i];
			
			d1 = dateDifference(fDate, days[i-1])+1;
			d2 = dateDifference(fDate, days[i])+1;	
		}else {
			c1 = calaries[i-1];
			c2 = calaries[i];
			
			d1 = dateDifference(fDate, days[i-1])+1;
			d2 = dateDifference(fDate, days[i])+1;		
		}
		
	   

	   
	   
		 float xRange ;
		   
		   
		   xRange = x-80-xConstant;
		   float yRange = y-yConstant-xConstant;
		   
		   
			   c1 = y-yConstant-(yRange/10000)*c1;
			   c2 = y-yConstant-(yRange/10000)*c2;
			   d1 = xConstant+(xRange/(bPoint.length))*d1;
			   d2 = xConstant+((xRange/bPoint.length))*d2;
		   
	      
	    
	   
	   drawCustomLine(canvas, new float[]{d1,c1 }, new float[]{d2, c2}, paint);
	   drawCustomBitMap(canvas, new float[]{d2, c2});
	   
	   }
}
   
   
   private void drawCustomBitMap(Canvas canvas, float[] point) {
	// TODO Auto-generated method stub
	   Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.blue_spiral);
	   
	   
       canvas.drawBitmap(bmp, point[0]-(bmp.getWidth()/2), point[1]-bmp.getHeight()/2, null); // 24 is the height of image       
    
}
   
   private void drawCustomText(Canvas canvas, float[] point, Paint paint, String text) {
	// TODO Auto-generated method stub
	   paint.setTextSize(10);
	   canvas.drawText(text, point[0], point[1], paint);
}
   
   public void drawCustomLine(Canvas canvas, float[] firstPoint, float[] secondPoint, Paint paint) {
	// TODO Auto-generated method stub
	   canvas.drawLine(firstPoint[0], firstPoint[1], secondPoint[0], secondPoint[1], paint);	
}
   
   
   @Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// TODO Auto-generated method stub
	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	
	if (bPoint.length>10) {
		
		setMeasuredDimension(((screenWidth-100)/10)*bPoint.length+350, MeasureSpec.getSize(heightMeasureSpec));
	}else {
		setMeasuredDimension(screenWidth+100, MeasureSpec.getSize(heightMeasureSpec));
	}
	
}
}