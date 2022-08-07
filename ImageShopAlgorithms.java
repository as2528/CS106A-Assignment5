/* 
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 * 
 * You should remove the stub lines from each method and replace them with your
 * implementation that returns an updated image.
 */

// This file runs an imageshop programming. An image can be entered, and a desired behavior can be called upon on the display screen. 

import java.util.*;
import acm.graphics.*;

public class ImageShopAlgorithms implements ImageShopAlgorithmsInterface {

	public GImage flipHorizontal(GImage source) {
		// This method returns a picture flipped horizontally. 
		//Input: The image
		//Output: The image flipped horizontally
		int[][] pixelArray = source.getPixelArray();
		int height = pixelArray.length;
		int width = pixelArray[0].length;
		int[][]rotatedArray = new int[width][height];
		for (int i=0; i<height; i++) {
			for(int j=0;j<width;j++) {
				
				rotatedArray[i][Math.abs(j-(width-1))]=pixelArray[i][j];
				
				
		}
		}

		
		return new GImage(rotatedArray);
	}

	public GImage rotateLeft(GImage source) {
		// This method returns a picture rotated 90 degrees left. 
		//Input: the image
		//Output: The image rotated 90 degrees left
		int[][] pixelArray = source.getPixelArray();
		int height = pixelArray.length;
		int width = pixelArray[0].length;
		int[][]rotatedArray = new int[width][height];
		for (int i=0; i<height; i++) {
			for(int j=0;j<width;j++) {
				
				rotatedArray[Math.abs(j-(height-1))][i]=pixelArray[i][j];
				
				
		}
		}

		
		return new GImage(rotatedArray);

	
	}

	public GImage rotateRight(GImage source) {
		// This method rotates the image 90 degrees right. 
		//Input: The image
		//Output: The rotated image
		
		int[][] pixelArray = source.getPixelArray();
		int height = pixelArray.length;
		int width = pixelArray[0].length;
		int[][]rotatedArray = new int[width][height];
		for (int i=0; i<height; i++) {
			for(int j=0;j<width;j++) {
				
				rotatedArray[j][Math.abs(i-(height-1))]=pixelArray[i][j];
				
				
		}
		}

		
		return new GImage(rotatedArray);

	
	}


	public GImage greenScreen(GImage source) {
		// This method returns an image that has all of the green removed. 
		//Input:Image
		//Output:Image with green pixels removed from image
		int[][] pixelArray = source.getPixelArray();
		int height = pixelArray.length;
		int width = pixelArray[0].length;
		for (int i=0; i<height; i++) {
			for(int j=0;j<width;j++) {
				int pixel=pixelArray[i][j];
				int red = (pixel>>16)& 0xFF;
				int green = (pixel>>8)&0xFF;
				int blue=pixel&0xFF;
				int alphaTransparent =0;
				
				if(green>=2*Math.max(red, blue)) {
				int newPixel = (alphaTransparent<<24)|(red<<16)|(green<<8)|blue;
				pixelArray[i][j]=newPixel;
				
				
		}
				else {
					int newPixel = (0xFF<<24)|(red<<16)|(green<<8)|blue;
					pixelArray[i][j]=newPixel;
		}

		
	}}

		return new GImage(pixelArray);
	
	}

	public GImage equalize(GImage source) {
		// This method returns an equalized image
		//Input: Image
		//Output: Equalized Image
		
		//Initializing variables
		int[][] pixelArray = source.getPixelArray();
		int[] luminousArray = new int[257]; 
		int[] cumulativeLuminousArray = new int[257]; 
		int height = pixelArray.length;
		int width = pixelArray[0].length;
		int k =-1;
		int luminosity =0;
		int l=0;
		int[][]equalizedArray = new int[height][width];
		int newRed = 0;
		int newGreen = 0; 
		int newBlue = 0;
		int newPixelVal = 0;
		int pixelsLessThan = 0;
		int v = 0;
		int totalPixels = height*width;
		//This for loop goes through every pixels and creates the luminosity array showing how luminous all pixels are
		for (int i=0; i<height; i++) {
			for(int j=0;j<width;j++) {
				int pixel=pixelArray[i][j];
				int red = (pixel>>16)& 0xFF;
				int green = (pixel>>8)&0xFF;
				int blue=pixel&0xFF;
				
				luminosity = computeLuminosity(red, green, blue);
				luminousArray[luminosity] = luminousArray[luminosity] +1;
				
				
	}}
	//This while loop uses the luminousity array to create the cumulativeLuminousArray
		while(k<=256) {
			k++;
			l=k;
			while(l>=0&&k<=256) {
				cumulativeLuminousArray[k] = cumulativeLuminousArray[k]+luminousArray[l];
				l--;
				
			}
		}
		//This for loop creates the altered image.  
		for (int i=0; i<height; i++) {
			for(int j=0;j<width;j++) {
				int pixel=pixelArray[i][j];
				int red = (pixel>>16)& 0xFF;
				int green = (pixel>>8)&0xFF;
				int blue=pixel&0xFF;
		
				luminosity = computeLuminosity(red, green, blue);
				v = luminosity; //This stores the luminosity of the pixel
				pixelsLessThan=cumulativeLuminousArray[v];//This finds all pixels less luminous than the given pixel
				newPixelVal = (int)(255*(float)pixelsLessThan/(float)totalPixels);//new pixel vals are stored here
				//The new pixel value goes to red, blue, and green pixels
				newRed= newPixelVal; 
				newBlue = newPixelVal; 
				newGreen = newPixelVal;
				int newPixel = (0xFF<<24)|(newRed<<16)|(newGreen<<8)|newBlue;//The altered pixel is stored here
				equalizedArray[i][j]=newPixel;//This is the array of altered pixels

				
			}
		}
		return new GImage(equalizedArray);//This shows the altered image on the screen
			}

	public GImage negative(GImage source) {
		// This method returns a picture that is the negative of the image entered. 
		//Input: Image
		//Output: Negative image
		
		int[][] pixelArray = source.getPixelArray();
		int height = pixelArray.length;
		int width = pixelArray[0].length;
		for (int i=0; i<height; i++) {
			for(int j=0;j<width;j++) {
				int pixel=pixelArray[i][j];
				int red = (pixel>>16)& 0xFF;
				int green = (pixel>>8)&0xFF;
				int blue=pixel&0xFF;
				
				int newRed = 255-red;
				int newGreen = 255-green; 
				int newBlue = 255-blue;
				
				int newPixel = (0xFF<<24)|(newRed<<16)|(newGreen<<8)|newBlue;
				pixelArray[i][j]=newPixel;
				
				
		}
		}

		
		return new GImage(pixelArray);
	}

	public GImage translate(GImage source, int dx, int dy) {
		// This method takes as input an image, and moves it a requested dx and dy pixels. 
		//Input: Image, dx, dy
		//Output: Image translated a number of dx and dy pixels. 
		
		int[][] pixelArray = source.getPixelArray();
		int height = pixelArray.length;
		int width = pixelArray[0].length;
		int newI=0;
		int newJ=0;
		int tempDY=0;
		int tempDX=0;
		int[][]rotatedArray = new int[height][width];
		for (int i=0; i<height; i++) {
			for(int j=0;j<width;j++) {
				if(dy>=0) {//This if loop works if the dy is greater than or equals 0
				
				if(i+dy<width) {//if its less than the width, no further mutations are needed to the number and it can be added
					newI = i+dy;
				}
				
				
				if(i+dy>=width) {//if its greater than or equal to the width, the modulus operator is called on the number
					newI = Math.abs(i+dy)%width;
				}
				
				}
				
				if(dy<0) {//if it is less than zero, then this method runs
					if(Math.abs(dy)>width) {//if the dy requested is greater than the width, then the modulus division of the absolute value must be found, and negated. 
						tempDY = -(Math.abs(dy)%width);
					}else {//if dy is not greater, tempDY takes the value of dy
						tempDY = dy;
					}
					if(i+dy>=0) {//if the addition is greater than 0, the pixel does not require any rolling on the screen. 
					
						newI = i+tempDY;
					}
					
					if(i+tempDY<0) {//if the pixel is less than zero, it is off the screen and a mutation must be carried out
						newI = width+tempDY+i;
						
					}
					
				}
				
				if(dx<0) {//if it is less than zero, then this method runs
					if(Math.abs(dx)>height) {//if the dx requested is greater than the height, then the modulus division of the absolute value must be found, and negated. 
						tempDX = -(Math.abs(dx)%height);
					}else {//if dx is not greater, tempDX takes the value of dx
						tempDX = dx;
						
					if(j+tempDX>=0) {//if the addition is greater than 0, the pixel does not require any rolling on the screen. 
						
						newJ = j+tempDX;
					}
					if(j+tempDX<0) {//if the pixel is less than zero, it is off the screen and a mutation must be carried out
						newJ = height+tempDX+j;
					}
					
				}}
				
				if(dx>=0) {//This if loop works if the dx is greater than  or equal to 0
					
					if(j+dx<height) {//if its less than the width, no further mutations are needed to the number and it can be added
						newJ = j+dx;
					}
					if(j+dx>=height) {//if its greater than the width, the modulus operator is called on the number
						newJ = Math.abs(j+dx)%height;
					}
				}
				
				rotatedArray[newI][newJ] = pixelArray[i][j];//The rotated array is the mutated array which holds the final values
				
				
		}
		}

		
		return new GImage(rotatedArray);
	}

	public GImage blur(GImage source) {
		//This method returns a blurred image of the image input. 
		//Input: Image
		//Output: Blurred Image
		
		int[][] pixelArray = source.getPixelArray();//This gets the array of pixel luminosities from the image
		//initializing the variables
		int newRed=0;
		int newGreen=0;
		int red=0;
		int blue=0;
		int green=0;
		int newBlue=0;
		int pixel=0;
		int v = 0;
		int height = pixelArray.length;
		int width = pixelArray[0].length;
		//array for the blurred image, so that the array itself is not mutated by the changing of pixels
		int[][]blurredArray = new int[height][width];
		for (int i=0; i<height; i++) {//going through the rows
			for(int j=0;j<width;j++) {//going through the columns
				v-=v;//resetting the number of pixels in the average square
				newRed-=newRed;//resetting the sums of reds
				newGreen-=newGreen;//resetting the sums of greens
				newBlue-=newBlue;//resetting the sums of blues
				for(int g =-1;g<2;g++) {//going through the rows of the averaged pixels
					for(int k=-1;k<2;k++) {//going through the columns of the averaged pixels
			
					

				if(i+g>0&&j+k>0&&i+g<height&&j+k<width) {
					pixel=pixelArray[i+g][j+k];//choosing a pixel to average
					red = (pixel>>16)& 0xFF;//choosing the red component
					green = (pixel>>8)&0xFF;//choosing the green component
					blue=pixel&0xFF;//choosing the blue component
				
					newRed += red;//adding the red components
					newGreen += green; //adding the green components 
					newBlue += blue;//adding the blue components
					v+=1;//adding one more to the number of pixels averaged
		
				}
				if(g==1&&k==1) {//if the number of averaged pixels is over, then the averages can be taken
					newRed = newRed/v; //average red components
					newGreen = newGreen/v;//average green components
					newBlue = newBlue/v;//average blue components
				}
					}
					
				}
				
				int newPixel = (0xFF<<24)|(newRed<<16)|(newGreen<<8)|newBlue;//newpixels for the new array

				blurredArray[i][j]=newPixel;//creating the new blurred array
		
			}
		}

		
		return new GImage(blurredArray);//display the blurred image
	}
}
