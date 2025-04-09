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

package udp.client;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;


/**
 * @author Joan-Manuel Marques
 *
 */

public class RemoteMapUDPclient {

	public RemoteMapUDPclient() {
	}
	
	public Map<String, String> getMap (List<Key> keys) {
		Map<String, String> map = new HashMap<String, String>();
		int i = 1;
		for (Key key : keys) {
			LSimLogger.log(
					Level.TRACE,
					"["+i+"] Query for key "+key.getKey()+" at "+ key.getServerAddress() +":"+key.getServerPort()
					);

			String value = get(key.getKey(), key.getServerAddress(), key.getServerPort());

			LSimLogger.log(Level.TRACE, "["+i+"] RemoteMap("+key.getKey()+"): "+ value);
			i++;
			map.put(key.getKey(), value);
		}

		return map;
	}
	
	private String get(String key, String server_address, int server_port){
		LSimLogger.log(Level.INFO, "inici RemoteMapUDPclient.get ");
		LSimLogger.log(Level.TRACE, "key: " + key);
		LSimLogger.log(Level.TRACE, "server_address: " + server_address);
		LSimLogger.log(Level.TRACE, "server_port: " + server_port);

		DatagramSocket socket = null;
		String resposta = null;

		/* Implementa la part client UDP / Implement UDP client's side / Implementa la parte cliente UDP */
		
		//BEGIN-CODE-PART-1-2
		try {
			// 1.1 Create socket
			socket = new DatagramSocket();

			// 1.2 Get server address
			InetAddress serverAddr = InetAddress.getByName(server_address);

			// 1.3 Convert key to bytes and create packet
			byte[] sendData = key.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddr, server_port);

			// 1.4 Send packet to server
			socket.send(sendPacket);

			// 1.5 Create buffer for receiving response
			byte[] receiveData = new byte[256];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			// 1.6 Wait for response
			socket.receive(receivePacket);

			// 1.7 Convert response to string
			resposta = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();

		} catch (SocketException e) {
			LSimLogger.log(Level.ERROR, "SocketException");
			e.printStackTrace();
		} catch (UnknownHostException e) {
			LSimLogger.log(Level.ERROR, "UnknownHostException");
			e.printStackTrace();
		} catch (IOException e) {
			LSimLogger.log(Level.ERROR, "IOException");
			e.printStackTrace();
		} finally {
			// 1.8 Close socket
			if (socket != null) {
				socket.close();
			}
		}
		//END-CODE-PART-1-2
		
		return resposta;
	}
}