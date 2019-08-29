package com.abdul.azure.keyvault;

import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.models.SecretBundle;

public class KeyVaultCredentialApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		    String clientId = "";
		    String clientKey = "";
		    String KEYVAULT_URL = "";
		 
		KeyVaultClient client = new KeyVaultClient(
		        new AzureKeyVaultCredService(clientId, clientKey));
 
		SecretBundle secret = client.getSecret( KEYVAULT_URL, "spring-db-password" );
		System.out.println( "Secret Value: " + secret.value() );

	}

}
