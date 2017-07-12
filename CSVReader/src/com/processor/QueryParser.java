package com.processor;

import java.io.BufferedReader;
import java.io.FileReader;

public class QueryParser
{
	public void executeQuery(String querystring) throws Exception
	{
		if (isQueryValid(querystring)) 
		{
			QueryParamters params=new QueryParamters();
			params.setQueryParam(querystring);
			HeaderFields headerrow=getHeaderRow(params);
			//queryProcessing(headerrow,obj);
			
		} 
		else 
		{
			System.out.println("Query String is not in Proper Format");
		}
	}

	private boolean isQueryValid(String querystring) 
	{
		if(querystring.contains("select") && querystring.contains("from") || (querystring.contains("where") ||querystring.contains("order by")|| querystring.contains("group by")||querystring.contains("")))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public HeaderFields getHeaderRow(QueryParamters qryparam) throws Exception
	{
		BufferedReader bufferreader=new BufferedReader(new FileReader(qryparam.getFilepath()));
		HeaderFields headerrow=new HeaderFields();
		
		if(bufferreader!=null)
		{
			String rowdata=bufferreader.readLine();
			String rowvalues[]=rowdata.split(",");
			int colindex=0;
			for(String rowvalue:rowvalues)
			{
				headerrow.headfields.put(rowvalue,colindex);
				colindex++;
			}
			
			System.out.println(headerrow.headfields);
		}
		
		return headerrow;
	}
	
	/*public DataSet queryProcessing(HeaderFields headerrow,QueryParamters qryparam)
	{
		DataSet ds=new DataSet();
		
		return ds;
	}*/

}
