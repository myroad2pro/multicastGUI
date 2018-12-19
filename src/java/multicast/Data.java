package multicast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import multicast.topology.*;

public class Data {
	private String data = "";
	public Data (Topology topo) {
		ArrayList<Link> listLink = new ArrayList<Link>();
		listLink = topo.getList_link();
		
//ghi cac link noi sw va sw		
		for (int i = 0; i < listLink.size(); i++) {
			Link link = listLink.get(i);
			
			String src = link.getSource();
			String [] s = src.split(":",2);
			
			String dst = link.getDestination();
			String [] d = dst.split(":",2);
			
			if (Integer.parseInt(s[1]) < Integer.parseInt(d[1])) {
				data = data + src + ">" + dst + ">>sw>>>";
				
			}
		}
//ghi cac link host
		ArrayList<Host> listHost = new ArrayList<Host>();
		listHost = topo.getList_host();
		
		for (int i = 0; i < listHost.size(); i++) {
			Host host = listHost.get(i);
			data = data + host.getIp_address() + ">" + host.getSwitch_name() + ">>host";
			if (i+1 != listHost.size()) {
				data = data + ">>>";
			}
		}
		
	}
	public boolean writeData() {
		boolean bol = true;
		File folder = new File("../../../..");
		if (folder.exists() == false)
			bol = folder.mkdirs();
		if (bol == false) {
			System.out.println("Error Directory");
			return bol;
		} else {
			File file = new File("../../../../graph.txt");
			if (file.exists() == true) {
				file.delete();
				try {
					file.createNewFile();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				BufferedWriter wr = new BufferedWriter(new FileWriter(file));
				wr.write(this.data);
				wr.flush();
				wr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Luu data vao file graph.txt");
			return bol;
		}
	}

}
