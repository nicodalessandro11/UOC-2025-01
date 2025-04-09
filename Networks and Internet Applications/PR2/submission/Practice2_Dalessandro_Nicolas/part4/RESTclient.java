package xai.rest.client;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;
//import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
//import lsim.library.api.LSimLogger;
import xai.rest.jettyserver.api.Replaced;

/**
 * @author Joan-Manuel Marques
 *
 */

public class RESTclient {

	public int indexOf(String address, int port, String string, String caracters) {
		LSimLogger.log(Level.INFO, "indexOf");
		LSimLogger.log(Level.INFO, "string: "+string);
		LSimLogger.log(Level.INFO, "caracter: "+caracters);
		String crida_rest = "http://"+address+":"+port+"/strings/indexOf/"+string+"/"+caracters;

		/* Complete the operation code / Completa el codi de l'operació / Completa el código de la operación */

		// BEGIN-CODE-PART-3-2
		// 3.2.1 Create client
		Client client = ClientBuilder.newClient();

		// 3.2.2 Make request and response
		Response response = client.target(crida_rest)
				.request()
				.get();

		// 3.2.3 Check if request was OK
		if (response.getStatus() != 200) {
			LSimLogger.log(Level.ERROR, "Error calling REST service: " + response.getStatus());
			return -1;
		}

		// 3.2.4 Get response as string and convert to integer
		String responseStr = response.readEntity(String.class);
		int result = Integer.parseInt(responseStr);

		// 3.2.5 Close response and client
		response.close();
		client.close();

		// 3.2.6 Return result
		return result;
		// END-CODE-PART-3-2
	}
	
	public Set<String> parts(String address, int port, String string, String separador) {
		LSimLogger.log(Level.INFO, "parts");
		LSimLogger.log(Level.INFO, "string: "+string);
		LSimLogger.log(Level.INFO, "separador: "+separador);
		String crida_rest = "http://"+address+":"+port+"/strings/parts/"+string+"/"+separador;
		
		/* Complete the operation code / Completa el codi de l'operació / Completa el código de la operación */
		
		// BEGIN-CODE-PART-4-3
		// 4.3.1 Create client
		Client client = ClientBuilder.newClient();

		// 4.3.2 Make request and get response
		Response response = client.target(crida_rest)
				.request(MediaType.APPLICATION_JSON)
				.get();

		// 4.3.3 Check if request was OK
		if (response.getStatus() != 200) {
			LSimLogger.log(Level.ERROR, "Error calling REST service: " + response.getStatus());
			return null;
		}

		// 4.3.4 Get response as string
		String jsonResponse = response.readEntity(String.class);
		Gson gson = new Gson();
		Set<String> result = gson.fromJson(jsonResponse, new HashSet<String>() {
		}.getClass().getGenericSuperclass());

		// 4.3.5 Close response and client
		response.close();
		client.close();

		// 4.3.6 Return result
		return result;
		// END-CODE-PART-4-3

	}

	public Replaced replaceAll(String address, int port, String string, String originalWord, String newWord) {
		LSimLogger.log(Level.INFO, "replaceAll");
		LSimLogger.log(Level.INFO, "string: "+string);
		
		String crida_rest = "http://"+address+":"+port+"/strings/replaceAll/"+string+"/"+originalWord+"/"+newWord;
		
		/* Complete the operation code / Completa el codi de l'operació / Completa el código de la operación */
		
		// BEGIN-CODE-PART-4-4
		// 4.4.1 Create client
		Client client = ClientBuilder.newClient();

		// 4.4.2 Make request and get response
		Response response = client.target(crida_rest)
				.request(MediaType.APPLICATION_JSON)
				.get();

		// 4.4.3 Check if request was OK
		if (response.getStatus() != 200) {
			LSimLogger.log(Level.ERROR, "Error calling REST service: " + response.getStatus());
			return null;
		}

		// 4.4.4 Get response as string and convert
		String jsonResponse = response.readEntity(String.class);
		Gson gson = new Gson();
		Replaced result = gson.fromJson(jsonResponse, Replaced.class);

		// 4.4.5 Close response and client
		response.close();
		client.close();

		// 4.4.6 Return result
		return result;
		// END-CODE-PART-4-4

	}
}
