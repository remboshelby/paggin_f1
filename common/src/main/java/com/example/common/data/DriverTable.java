package com.example.common.data;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DriverTable{

	@SerializedName("Drivers")
	private List<DriversItem> drivers;

	public void setDrivers(List<DriversItem> drivers){
		this.drivers = drivers;
	}

	public List<DriversItem> getDrivers(){
		return drivers;
	}

	@Override
 	public String toString(){
		return 
			"DriverTable{" + 
			"drivers = '" + drivers + '\'' + 
			"}";
		}
}