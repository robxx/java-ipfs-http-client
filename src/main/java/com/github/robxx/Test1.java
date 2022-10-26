package com.github.robxx;

import java.io.IOException;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.ipfs.api.IPFS;
import io.ipfs.multiaddr.MultiAddress;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IPFS ipfs = new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001"));

		ipfs = ipfs.timeout(5000);

		try {
			Map m = ipfs.commands();

			if (JsonParser.parseString(m.toString()).isJsonObject()) {
				// JsonPrimitive command = o.getAsJsonPrimitive("Name"); //ipfs
				doCommands(JsonParser.parseString(m.toString()).getAsJsonObject(), "");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void doCommands(JsonObject o, String c) {
		String cmd;
		JsonArray subcommand = o.getAsJsonArray("Subcommands");
		for (int x = 0; x < subcommand.size(); x++) {
			
			o = subcommand.get(x).getAsJsonObject();
			String flg = "";
			JsonArray flags = o.getAsJsonArray("Options");
			
			for (int y = 0; y < flags.size(); y++) {
				flg += (" --" + flags.get(y).getAsJsonObject().get("Names").getAsJsonArray().get(0).getAsString());
			}
			
			cmd = c + " " + subcommand.get(x).getAsJsonObject().get("Name").getAsJsonPrimitive().getAsString();
			System.out.print(cmd + flg + "\r\n");

			
			doCommands(o, cmd);
		}
	}

}
