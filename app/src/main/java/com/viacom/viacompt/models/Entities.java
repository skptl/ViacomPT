
package com.viacom.viacompt.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shilpan Patel on 1/29/15.
 */
public class Entities{
   	private Number id;
   	private String link;
   	private List range;
   	private String title;
   	private String type;
   	private ArrayList<String> vanityUrls;

 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getLink(){
		return this.link;
	}
	public void setLink(String link){
		this.link = link;
	}
 	public List getRange(){
		return this.range;
	}
	public void setRange(List range){
		this.range = range;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
 	public ArrayList<String> getVanityUrls(){
		return this.vanityUrls;
	}
	public void setVanityUrls(ArrayList<String> vanityUrls){
		this.vanityUrls = vanityUrls;
	}
}
