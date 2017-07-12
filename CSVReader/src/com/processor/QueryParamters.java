package com.processor;

public class QueryParamters 
{

	private String filepath;
	private String orderbycol,groupbycol,selectcol;
	private RestrictionCriteria criteria=new RestrictionCriteria(); 
	private FieldNames fields=new FieldNames();
	
	private boolean hasgroupby,hasorderby,haswhere,hasAllColumn;
	
	public String getFilepath() 
	{
		return filepath;
	}

	public void setFilepath(String filepath) 
	{
		this.filepath = filepath;
	}

	public void setQueryParam(String qrystring)
	{
		String basequery=null,conditionqry=null;
		
		if(qrystring.contains("order by"))
		{
			basequery=qrystring.split("order by")[0].trim();
			orderbycol=qrystring.split("order by")[1].trim();
			filepath=basequery.split("from")[1].trim();
			basequery=basequery.split("from")[0].trim();
			selectcol=basequery.split("select")[1].trim();
			this.fieldsProcessing(selectcol);
			hasorderby=true;
		}
		if(qrystring.contains("group by"))
		{
			basequery=qrystring.split("group by")[0].trim();
			groupbycol=qrystring.split("group by")[1].trim();
			System.out.println(basequery);
			if(basequery.contains("where"))
			{
				conditionqry=basequery.split("where")[1].trim();
				String relationalqry=conditionqry.split("and|or")[0].trim();
				this.relationalExpressionProcessing(relationalqry);
				basequery=basequery.split("where")[0].trim();			
			}
			filepath=basequery.split("from")[1].trim();
			basequery=basequery.split("from")[0].trim();
			selectcol=basequery.split("select")[1].trim();
			this.fieldsProcessing(selectcol);
			hasgroupby=true;
		}
		else if(qrystring.contains("where"))
		{
			basequery=qrystring.split("where")[0];
			conditionqry=qrystring.split("where")[1];
			conditionqry=conditionqry.trim();
			filepath=basequery.split("from")[1].trim();
			String relationalqry=conditionqry.split("and|or")[0].trim();
			System.out.println(relationalqry);
			this.relationalExpressionProcessing(relationalqry);
			selectcol=basequery.split("select")[1].trim();
			this.fieldsProcessing(selectcol);
			haswhere=true;
		}
		else
		{
			basequery=qrystring.split("from")[0].trim();
			filepath=basequery.split("from")[1].trim();
			selectcol=basequery.split("select")[1].trim();
			this.fieldsProcessing(selectcol);
		}
		
		System.out.println(basequery);
		System.out.println(orderbycol);
		System.out.println(groupbycol);
		System.out.println(conditionqry);	
		System.out.println(filepath);
		
		System.out.println(criteria.getColumn());
		System.out.println(criteria.getValue());
		System.out.println(criteria.getOperator());
	}
	
	private void relationalExpressionProcessing(String relationqry)
	{
		String oper[]={">","<",">=","<=","=","!="};
		
		for(String operator:oper)
		{
			if(relationqry.contains(operator))
			{
				criteria.setColumn(relationqry.split(operator)[0].trim());
				criteria.setValue(relationqry.split(operator)[1].trim());
				criteria.setOperator(operator);
			break;
			}
		}
	}
	
	private void fieldsProcessing(String selectcolumn)
	{
		if(selectcolumn.trim().contains("*") && selectcolumn.length()==1)
		{
			hasAllColumn=true;
		}
		if(selectcolumn.trim().contains(","))
		{
			String columnlist[]=selectcolumn.split(",");
			
			int i=0;
			for(String column:columnlist)
			{
				fields.fieldnames.put(column,i);
				i++;
			}
			
		}
	}

}
