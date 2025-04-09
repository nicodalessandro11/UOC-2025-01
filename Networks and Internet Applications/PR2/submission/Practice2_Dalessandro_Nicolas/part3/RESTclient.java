package xai.rest.client;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
		// 3.1 Create client
		Client client = ClientBuilder.newClient();

		// 3.2 Make request and response
		Response response = client.target(crida_rest)
				.request()
				.get();

		// 3.3 Check if request was OK
		if (response.getStatus() != 200) {
			LSimLogger.log(Level.ERROR, "Error calling REST service: " + response.getStatus());
			return -1;
		}

		// 3.4 Get response as string and convert to integer
		String responseStr = response.readEntity(String.class);
		int result = Integer.parseInt(responseStr);

		// 3.5 Close response and client
		response.close();
		client.close();

		// 3.6 Return result
		return result;
		// END-CODE-PART-3-2
	}
	
	public Set<String> parts(String address, int port, String string, String separador) {
		LSimLogger.log(Level.INFO, "parts");
		LSimLogger.log(Level.INFO, "string: "+string);
		LSimLogger.log(Level.INFO, "separador: "+separador);
		String crida_rest = "http://"+address+":"+port+"/strings/parts/"+string+"/"+separador;
		
		/* Complete the operation code / Completa el codi de l'operació / Completa el código de la operación */
		
		return null;
	}

	public Replaced replaceAll(String address, int port, String string, String originalWord, String newWord) {
		LSimLogger.log(Level.INFO, "replaceAll");
		LSimLogger.log(Level.INFO, "string: "+string);
		
		String crida_rest = "http://"+address+":"+port+"/strings/replaceAll/"+string+"/"+originalWord+"/"+newWord;
		
		/* Complete the operation code / Completa el codi de l'operació / Completa el código de la operación */
		
		return null;
	}
}
