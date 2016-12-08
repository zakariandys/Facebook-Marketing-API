/**
 * 
 */
package com.github.zakariandys.MarketingAPI;

import java.math.BigInteger;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.AdAccount;
//import com.facebook.ads.sdk.AdAccount.EnumCampaignStatus;
//import com.facebook.ads.sdk.AdAccount.EnumCampaignObjective;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.APIException;

/**
 * @author Zakaria Andy S
 *
 */
public class CreateCampaign {

	public static final String ACCESS_TOKEN = "EAAZAosaFlcrABAHQcMYGSac9jZCojSUFjw3YuZBwl7TDn2LG9QaBPZCuxbtyUgdxlEdnr7rpcqN0GrzgMeRV9MUYD4ggzsx58uZCdpeNWLzoHBZAfZBkWzZCxgxtVuLjGZBnPfDvrKDAKZBt7QSjA8HjxyxvcmZCZC2WrNAiLo1ARm3VMwZDZD";
	public static final String ACCOUNT_ID = "346182607";
	public static final String APP_SECRET = "c7fb919e242e28521d5ab934c669c574";

	public static final APIContext context = new APIContext(ACCESS_TOKEN, APP_SECRET);
	AccountDetail accDetail = new AccountDetail();

	public static void main(String[] args) {
		try {
			AdAccount account = new AdAccount(ACCOUNT_ID, context);
			Campaign campaign = account.createCampaign().setName("Coba Yang Ini")
					.setObjective(Campaign.EnumObjective.VALUE_LINK_CLICKS).setStatus(Campaign.EnumStatus.VALUE_PAUSED).execute();
			System.out.println("test ini haha " + campaign.fetch());
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
}
