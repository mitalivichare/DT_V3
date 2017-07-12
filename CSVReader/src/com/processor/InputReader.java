package com.processor;

import java.util.Scanner;

public class InputReader 
{
	public static void main(String[] args) throws Exception
	{
		String querystring;

		Scanner scan=new Scanner(System.in);

		System.out.println("Enter The Query : ");
		
		querystring=scan.nextLine();

		QueryParser query=new QueryParser();

		query.executeQuery(querystring);
		
		
	}

}
