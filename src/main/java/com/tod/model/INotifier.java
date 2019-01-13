package com.tod.model;

public interface INotifier {

	public String getId();
	public String getContent();
	public boolean getAnonymous();
	public void setAnonymous(boolean anonymous);
	public boolean getAnswered();
	public void setAnswered(boolean answered);
}
