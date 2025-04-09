package xai.rest.jettyserver.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;

/**
 * @author Joan-Manuel Marques
 *
 */

@Path("/strings")
public class RestServerAPI {

	/**
	 * indexOf
	 *
	 * @param string
	 * @param char
	 * @return
	 */
	@GET
	@Path("/indexOf/{string}/{caracter}")
	@Produces(MediaType.TEXT_PLAIN)
	public String indexOf (@PathParam("string") String string, @PathParam("caracter") String caracter) {
		LSimLogger.log(Level.INFO, "indexOf");
		LSimLogger.log(Level.INFO, "string: " + string);
		LSimLogger.log(Level.INFO, "caracter: " + caracter);

		String resultat = "";

		/* Afegeix el teu codi / Insert your code / Añade tu código */
		
		// BEGIN-CODE-PART-3-1
		// 3.1.1 Get first character
		char searchChar = caracter.charAt(0);

		// 3.1.2 Get first index
		int index = string.indexOf(searchChar);

		// 3.1.3 Convert result to string
		resultat = String.valueOf(index);
		// END-CODE-PART-3-1

		LSimLogger.log(Level.INFO, "response: " + resultat);
		return resultat;
		
	}

	/**
	 * parts
	 *
	 * @param string
	 * @param separator
	 * @return
	 */
	@GET
	@Path("/parts/{string}/{separador}")
	@Produces(MediaType.APPLICATION_JSON)
	public String parts(@PathParam("string") String string, @PathParam("separador") String separador) {
		LSimLogger.log(Level.INFO, "parts");
		LSimLogger.log(Level.INFO, "string: "+string);
		LSimLogger.log(Level.INFO, "separador: "+separador);

		Object resultat = null;
		Gson gson = null;

		/* Afegeix el teu codi / Insert your code / Añade tu código */
		
		// BEGIN-CODE-PART-4-1
		// 4.1.1 Create Gson instance
		gson = new GsonBuilder().create();

		// 4.1.2 Get first character
		char sep = separador.charAt(0);

		// 4.1.3 Split string
		Set<String> parts = new HashSet<String>();
		for (String part : string.split(String.valueOf(sep))) {
			parts.add(part);
		}

		// 4.1.4 Set result
		resultat = parts;
		// END-CODE-PART-4-1

		LSimLogger.log(Level.INFO, "response: "+gson.toJson(resultat));
		return gson.toJson(resultat);
	}

	/**
	 * replaceAll
	 *
	 * @param replaceAll
	 * @param string
	 * @param originalWord
	 * @param newWord
	 * @return a json object containing the parameters and the result
	 */
	@GET
	@Path("/replaceAll/{string}/{originalWord}/{newWord}")
	@Produces(MediaType.APPLICATION_JSON)
	public String replaceAll(
			@PathParam("string") String string, 
			@PathParam("originalWord") String originalWord,
			@PathParam("newWord") String newWord
			) {
		LSimLogger.log(Level.INFO, "replaceAll");
		LSimLogger.log(Level.INFO, "string: "+string);
		LSimLogger.log(Level.INFO, "original word: "+originalWord);
		LSimLogger.log(Level.INFO, "new word: "+newWord);

		
		Replaced result = null;
		Gson gson = null;

		/* Afegeix el teu codi / Insert your code / Añade tu código */
		
		// BEGIN-CODE-PART-4-2
		// 4.2.1 Create Gson
		gson = new GsonBuilder().create();

		// 4.2.2 Replace with newWord
		String replacedString = string.replaceAll(originalWord, newWord);

		// 4.2.3 Create Replaced object
		result = new Replaced(string, replacedString);
		// END-CODE-PART-4-2
		
		return gson.toJson(result);
	}
}
