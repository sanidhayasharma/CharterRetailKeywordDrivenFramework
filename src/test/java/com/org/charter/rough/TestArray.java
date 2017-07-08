package com.org.charter.rough;

public class TestArray 
{
	
	
	public void reverseArray(int[]b,int a)
	{
		for(int i=0;i<=a;i++)
		{
			for(int j=a;j>=0;j--)
			{
				if(b[i]==b[j])
				{
					//System.out.println(b[i]+" "+b[j]);
					break;
				}
				int temp=0;
				a=--j;
				temp=b[i];
				b[i]=b[j];
				b[j]=temp;
				//System.out.println(b[i]+" "+b[j]);
				break;
			}
		}
		for(int k=0;k<b.length;k++)
		{
			System.out.print(b[k]);
		}
		
	}
	public static void main(String args[])
	{
		int [] arr={1,2,3,4,5,6,7,8};
		
		TestArray a = new TestArray();
		a.reverseArray(arr,5);
		
	}

}
