
package com.viacom.viacompt.models;

import java.util.List;

/**
 * Created by Shilpan Patel on 1/29/15.
 */
public class Likes{
   	private Number anchor;
   	private String anchorStr;
   	private String backAnchor;
   	private Number count;
   	private Number nextPage;
   	private String previousPage;
   	private List records;
   	private Number size;

 	public Number getAnchor(){
		return this.anchor;
	}
	public void setAnchor(Number anchor){
		this.anchor = anchor;
	}
 	public String getAnchorStr(){
		return this.anchorStr;
	}
	public void setAnchorStr(String anchorStr){
		this.anchorStr = anchorStr;
	}
 	public String getBackAnchor(){
		return this.backAnchor;
	}
	public void setBackAnchor(String backAnchor){
		this.backAnchor = backAnchor;
	}
 	public Number getCount(){
		return this.count;
	}
	public void setCount(Number count){
		this.count = count;
	}
 	public Number getNextPage(){
		return this.nextPage;
	}
	public void setNextPage(Number nextPage){
		this.nextPage = nextPage;
	}
 	public String getPreviousPage(){
		return this.previousPage;
	}
	public void setPreviousPage(String previousPage){
		this.previousPage = previousPage;
	}
 	public List getRecords(){
		return this.records;
	}
	public void setRecords(List records){
		this.records = records;
	}
 	public Number getSize(){
		return this.size;
	}
	public void setSize(Number size){
		this.size = size;
	}
}
