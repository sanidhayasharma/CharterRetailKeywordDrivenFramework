package com.org.charter.rough;

public class Testing 
{
	
	public static void main(String args[])
	{
		int [][]a = new int[4][4];
		int m=0;
		for(int i=0;i<a.length;i++)
		{
			m++;
			for(int j=0;j<a.length;j++)
			{
				a[i][j]=m;
				System.out.print(a[i][j]);
				break;
				
			}
			
		}
		
	}

}
