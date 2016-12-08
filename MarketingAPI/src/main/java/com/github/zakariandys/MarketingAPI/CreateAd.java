/**
 * 
 */
package com.github.zakariandys.MarketingAPI;

import java.awt.Image;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIRequest;
import com.facebook.ads.sdk.APIResponse;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.AdCreative;
import com.facebook.ads.sdk.AdImage;
import com.facebook.ads.sdk.AdSet;
import com.facebook.ads.sdk.BatchRequest;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.Ad;

/**
 * @author Zakaria Andy S
 *
 */
public class CreateAd {
	
	public static final String ACCESS_TOKEN = "EAAZAosaFlcrABAHQcMYGSac9jZCojSUFjw3YuZBwl7TDn2LG9QaBPZCuxbtyUgdxlEdnr7rpcqN0GrzgMeRV9MUYD4ggzsx58uZCdpeNWLzoHBZAfZBkWzZCxgxtVuLjGZBnPfDvrKDAKZBt7QSjA8HjxyxvcmZCZC2WrNAiLo1ARm3VMwZDZD";
	public static final String ACCOUNT_ID = "346182607";
	public static final String APP_SECRET = "c7fb919e242e28521d5ab934c669c574";
	public static final String CAMPAIGN_ID = "6074546337366";
	public static final String ADSET_ID = "6074546424166";
	public static final APIContext CONTEXT = new APIContext(ACCESS_TOKEN, APP_SECRET);
	public static final File imageFile = new File("D:/EclipseNeonProjects/MarketingAPI/src/main/java/com/github/zakariandys/MarketingAPI/image.png");

	
	public static void main(String[] args){
		
		try {
			Campaign campaign = new Campaign(CAMPAIGN_ID, CONTEXT);
			AdAccount account = new AdAccount(ACCOUNT_ID, CONTEXT);
			AdSet ad_set = new AdSet(ADSET_ID, CONTEXT);

			
			AdImage image = account.createAdImage()
			.addUploadFile("file-nya", imageFile)
			.execute();
			
			
			AdCreative creative = account.createAdCreative()
			.setTitle("Java SDK Batch Test Creative")
			.setBody("Java SDK Batch Test Creative")
			.setImageHash(image.getFieldHash())
			.setLinkUrl("www.facebook.com")
			.setObjectUrl("www.facebook.com")
			.execute();
			
			
			Ad ad = account.createAd()
			.setName("Java SDK Batch Test Ad")
			.setAdsetId(ADSET_ID)
			.setCreative(creative)
	        .setStatus("PAUSED")
	        .setBidAmount(1000L)
	        .setRedownload(true)
	        .execute();
	        
//			List<APIResponse> responses = batch.execute();
			
//			Ad ad = ((Ad)responses.get(4)).fetch();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
