package eu.arkitech.aws.simpledb.webui.client;

import com.google.gwt.http.client.URL;

public class AwsCredentials
{
	public static String awsAccessKeyId;
	public static String awsSecretKey;
	
	public static String asUrlData()
	{
		return "aws_access_key=" + URL.encodeQueryString(awsAccessKeyId) + "&aws_secret_key=" + URL.encodeQueryString(awsSecretKey); 
	}
}
