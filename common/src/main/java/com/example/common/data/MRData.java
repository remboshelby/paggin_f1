package com.example.common.data;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class MRData{

	@SerializedName("xmlns")
	private String xmlns;

	@SerializedName("total")
	private String total;

	@SerializedName("offset")
	private String offset;

	@SerializedName("series")
	private String series;

	@SerializedName("limit")
	private String limit;

	@SerializedName("DriverTable")
	private DriverTable driverTable;

	@SerializedName("url")
	private String url;

	public void setXmlns(String xmlns){
		this.xmlns = xmlns;
	}

	public String getXmlns(){
		return xmlns;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setOffset(String offset){
		this.offset = offset;
	}

	public String getOffset(){
		return offset;
	}

	public void setSeries(String series){
		this.series = series;
	}

	public String getSeries(){
		return series;
	}

	public void setLimit(String limit){
		this.limit = limit;
	}

	public String getLimit(){
		return limit;
	}

	public void setDriverTable(DriverTable driverTable){
		this.driverTable = driverTable;
	}

	public DriverTable getDriverTable(){
		return driverTable;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"MRData{" + 
			"xmlns = '" + xmlns + '\'' + 
			",total = '" + total + '\'' + 
			",offset = '" + offset + '\'' + 
			",series = '" + series + '\'' + 
			",limit = '" + limit + '\'' + 
			",driverTable = '" + driverTable + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}