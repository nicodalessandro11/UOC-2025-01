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

package udp.servidor;

import java.nio.ByteBuffer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;

/**
 * @author Joan-Manuel Marques
 *
 */

public class RemoteMapUDPservidor {
	
	public RemoteMapUDPservidor(int server_port, Map<String, String> map){
		LSimLogger.log(Level.INFO, "Inici RemoteMapUDPservidor ");
		LSimLogger.log(Level.INFO, "server_port: " + server_port);
		LSimLogger.log(Level.INFO, "map: " + map);

		// Server waits for requests a maximum time (timeout_time)
		Timer timer = new Timer();
		timer.schedule(
				new TimerTask() {
					@Override
					public void run() {
						System.exit(0);
					}
				},
				90000 //90 seconds
				); 

		/* Implementa la part servidor UDP / implement UDP server's side / implementa la parte servidor UDP */
		
		//BEGIN-CODE-PART-1-1
		try {
			// 2.1 Create socket
			DatagramSocket serverSocket = new DatagramSocket(server_port);
			LSimLogger.log(Level.INFO, "Server listening on port " + server_port);

			while (true) {
				// 2.2 Create buffer
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

				// 2.3 Wait for client request
				serverSocket.receive(receivePacket);

				// 2.4 Parse key from request
				String key = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
				LSimLogger.log(Level.INFO, "Received request for key: " + key);

				// 2.5 Find the value in the map
				String value = map.get(key);
				if (value == null) {
					value = "Key not found";
					LSimLogger.log(Level.WARN, "Key not found: " + key);
				}

				// 2.6 Send response back to client
				byte[] sendData = value.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
						receivePacket.getAddress(), receivePacket.getPort());
				serverSocket.send(sendPacket);

				LSimLogger.log(Level.INFO, "Sent response: " + value);
			}
		} catch (SocketException e) {
			LSimLogger.log(Level.ERROR, "SocketException");
			e.printStackTrace();
		} catch (IOException e) {
			LSimLogger.log(Level.ERROR, "IOException");
			e.printStackTrace();
		}
	}
	//END-CODE-PART-1-1
}
