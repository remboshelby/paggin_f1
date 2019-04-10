package com.example.common.data;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DriversItem{

	@SerializedName("driverId")
	private String driverId;

	@SerializedName("nationality")
	private String nationality;

	@SerializedName("givenName")
	private String givenName;

	@SerializedName("familyName")
	private String familyName;

	@SerializedName("dateOfBirth")
	private String dateOfBirth;

	@SerializedName("url")
	private String url;

	public void setDriverId(String driverId){
		this.driverId = driverId;
	}

	public String getDriverId(){
		return driverId;
	}

	public void setNationality(String nationality){
		this.nationality = nationality;
	}

	public String getNationality(){
		return nationality;
	}

	public void setGivenName(String givenName){
		this.givenName = givenName;
	}

	public String getGivenName(){
		return givenName;
	}

	public void setFamilyName(String familyName){
		this.familyName = familyName;
	}

	public String getFamilyName(){
		return familyName;
	}

	public void setDateOfBirth(String dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirth(){
		return dateOfBirth;
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
			"DriversItem{" + 
			"driverId = '" + driverId + '\'' + 
			",nationality = '" + nationality + '\'' + 
			",givenName = '" + givenName + '\'' + 
			",familyName = '" + familyName + '\'' + 
			",dateOfBirth = '" + dateOfBirth + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}