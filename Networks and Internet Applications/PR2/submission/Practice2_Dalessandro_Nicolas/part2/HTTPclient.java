/*

* Copyright (c) Joan-Manuel Marques 2013. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This file is part of the practical assignment of Distributed Systems course.
*
* This code is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This code is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this code.  If not, see <http://www.gnu.org/licenses/>.
*/

package tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;

/**
 * @author Joan-Manuel Marques
 *
 */

public class HTTPclient {

	public HTTPclient() {
	}

	public HTTPrequestResponse head(String http_server_address, int http_server_port) {
		LSimLogger.log(Level.INFO, "inici HTTPclient.get ");
		LSimLogger.log(Level.INFO, "HTTP server_address: " + http_server_address);
		LSimLogger.log(Level.INFO, "HTTP server_port: " + http_server_port);

		/* ENVIAR LA PETICIÓ I REBRE LA RESPOSTA */
		/* SEND REQUEST AND RECEIVE RESPONSE */
		/* ENVIAR LA PETICIÓN Y RECIBIR LA RESPUESTA */

		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder responseBuilder = new StringBuilder();
		String peticio = ""; /* petició HTTP / HTTP request / petición HTTP */

		// BEGIN-CODE-PART-2-1
		// 2.1 Create socket connection and establish communication
		try {
			// 2.1.1 Create and connect socket
			socket = new Socket(http_server_address, http_server_port);

			// 2.1.2 Set up output stream for sending request
			out = new PrintWriter(socket.getOutputStream(), true);

			// 2.1.3 Set up input stream for reading response
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 2.1.4 Build and send HEAD request with required headers
			peticio = "HEAD / HTTP/1.0\r\n" +
					"Host: " + http_server_address + "\r\n" +
					"Accept: text/html\r\n" +
					"\r\n";

			// 2.1.5 Send the request
			out.print(peticio);
			out.flush();

			// 2.1.6 Read response headers
			String line;
			while ((line = in.readLine()) != null && !line.isEmpty()) {
				responseBuilder.append(line).append("\r\n");
			}
		} catch (UnknownHostException e) {
			LSimLogger.log(Level.ERROR, "Unknown host: " + e.getMessage());
		} catch (IOException e) {
			LSimLogger.log(Level.ERROR, "I/O error: " + e.getMessage());
		} finally {
			// 2.1.7 Close all resources
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				LSimLogger.log(Level.ERROR, "Error closing resources: " + e.getMessage());
			}
		}
		// END-CODE-PART-2-1

		// BEGIN-CODE-PART-2-2
		// 2.2.1 Process and return response
		String response = responseBuilder.toString();
		LSimLogger.log(Level.INFO, "Request: " + peticio);
		LSimLogger.log(Level.INFO, "Response: " + response);

		return new HTTPrequestResponse(peticio, response);
		// END-CODE-PART-2-2
	}
}
