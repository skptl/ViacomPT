
package com.viacom.viacompt.models;


/**
 * Created by Shilpan Patel on 1/29/15.
 */
public class ApiResponse{
   	private String code;
   	private Data data;
   	private String error;
   	private boolean success;

 	public String getCode(){
		return this.code;
	}
	public void setCode(String code){
		this.code = code;
	}
 	public Data getData(){
		return this.data;
	}
	public void setData(Data data){
		this.data = data;
	}
 	public String getError(){
		return this.error;
	}
	public void setError(String error){
		this.error = error;
	}
 	public boolean getSuccess(){
		return this.success;
	}
	public void setSuccess(boolean success){
		this.success = success;
	}
}
