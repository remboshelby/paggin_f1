package com.example.common.data;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DriversResponse {

	@SerializedName("MRData")
	private MRData mRData;

	public void setMRData(MRData mRData){
		this.mRData = mRData;
	}

	public MRData getMRData(){
		return mRData;
	}

	@Override
 	public String toString(){
		return 
			"DriversResponse{" +
			"mRData = '" + mRData + '\'' + 
			"}";
		}
}