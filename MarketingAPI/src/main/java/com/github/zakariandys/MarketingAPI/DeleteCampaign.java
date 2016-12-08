/**
 * 
 */
package com.github.zakariandys.MarketingAPI;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Campaign;

/**
 * @author Zakaria Andy S
 *
 */
public class DeleteCampaign {

	/**
	 * @param args
	 */

	public static final String ACCESS_TOKEN = "EAAIVCbiIh0gBAKt8XX5QqEuZCXKl1e7jk52gMR322s5cnLPjHq9P2OwkZABne2FQGV84pWyQRMEDyX6XzlqNdjMtAoph43SZBngHIMAdDtCSK3cW0UpeVbMIBvMasWPaZArDvk4sgegZCZBJSH8DXbyTvJAbAABUXzWqLU37oTNgZDZD";
	public static final String CAMPAIGN_ID = "6074129702366";
	public static final String ACCOUNT_ID = "346182607";
	public static final String APP_SECRET = "c810bc8dc2a9e9414026dc5933b6fe95";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			APIContext context = new APIContext(ACCESS_TOKEN, APP_SECRET);
			AdAccount account = new AdAccount(ACCOUNT_ID, context);
			Campaign campaign = new Campaign(CAMPAIGN_ID, context); // this only
																	// creates a
																	// new empty
																	// object
			campaign = campaign.get().requestAllFields().execute();
			campaign.delete().execute();
			System.out.println("Isi campaign " + campaign);
			System.out.println("Campaign Berhasil Dihapus");
		} catch (Exception e) {
			System.out.println("Errornya karena " + e);
			e.printStackTrace();
		}

	}

}
