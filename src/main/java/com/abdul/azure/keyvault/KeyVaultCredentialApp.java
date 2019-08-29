package com.abdul.azure.keyvault;

import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.models.SecretBundle;

public class KeyVaultCredentialApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		    String clientId = "f0141583-9f23-4672-a63d-af6c6710a512";
		    String clientKey = "tRBNqYOFLqmm0@ZhOL]K2LeIv8XL@I.1";
		    String KEYVAULT_URL = "https://credstore.vault.azure.net/";
		 
		KeyVaultClient client = new KeyVaultClient(
		        new AzureKeyVaultCredService(clientId, clientKey));
 
		SecretBundle secret = client.getSecret( KEYVAULT_URL, "spring-db-password" );
		System.out.println( "Secret Value: " + secret.value() );

	}

}
