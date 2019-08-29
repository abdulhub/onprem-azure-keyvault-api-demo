package com.abdul.azure.keyvault;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.azure.keyvault.authentication.KeyVaultCredentials;

public class AzureKeyVaultCredService extends KeyVaultCredentials {
	
	
	    private  String clientId;
	    private  String clientKey;

	    public AzureKeyVaultCredService( String clientId, String clientKey ) {
	        this.clientId = clientId;
	        this.clientKey = clientKey;
	    }
	
	@Override
	public String doAuthenticate(String authorization, String resource, String scope) {
			
			AuthenticationResult authResult;
			try {
				authResult = getAccessToken(authorization, resource);
				return authResult.getAccessToken();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
	}
	
	/**
	 * Private helper method that gets the access token for the authorization and resource depending on which variables are supplied in the environment.
	 * 
	 * @param authorization
	 * @param resource
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws MalformedURLException 
	 * @throws Exception
	 */
	private AuthenticationResult getAccessToken(String authorization, String resource) throws InterruptedException, MalformedURLException, ExecutionException  {

		/*
		 * String clientId = System.getProperty("AZURE_CLIENT_ID"); String clientKey =
		 * System.getProperty("AZURE_CLIENT_SECRET");
		 */

		AuthenticationResult result = null;
		
		//Starts a service to fetch access token.
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(1);
			AuthenticationContext context = new AuthenticationContext(authorization, false, service);

			Future<AuthenticationResult> future = null;

			//Acquires token based on client ID and client secret.
			if (clientKey != null && clientKey != null) {
				ClientCredential credentials = new ClientCredential(this.clientId, this.clientKey);
				future = context.acquireToken(resource, credentials, null);
			}
			
			result = future.get();
		} finally {
			service.shutdown();
		}

		if (result == null) {
			throw new RuntimeException("Authentication results were null.");
		}
		return result;
	}

}
