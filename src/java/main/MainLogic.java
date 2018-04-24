package main;

import gsim.GraphSim;
import multicast.Data;
import multicast.Multicast;
import multicast.mst.Convert;
import multicast.mst.Edge;
import multicast.mst.MST;
import multicast.mst.Path;
import multicast.topology.Host;
import multicast.topology.ScanTopology;
import multicast.topology.Topology;

import java.io.File;
import java.util.ArrayList;

import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.Viewer;
/**
 * Maing logic controller which the UI calls
 * @author brandon
 *
 */
public class MainLogic { 


	private GraphSim gs;
	private Topology topo;
// IP nguon
	private String srcIP;
// danh sach IP tham gia nghe multicast
	private ArrayList<String> listIP;
// danh sach id switch tu nguon toi cac dich 	
	private ArrayList<Path> listPath;
// danh sach cac canh cua cay mst	
	private ArrayList<Edge> edgeTree;
	
	public String getSrcIP() {
		return srcIP;
	}

	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}

	public ArrayList<String> getListIP() {
		return listIP;
	}

	public void setListIP(ArrayList<String> listIP) {
		this.listIP = listIP;
	}

	public ArrayList<Path> getListPath() {
		return listPath;
	}

	public void setListPath(ArrayList<Path> listPath) {
		this.listPath = listPath;
	}

	public ArrayList<Edge> getEdgeTree() {
		return edgeTree;
	}

	public void setEdgeTree(ArrayList<Edge> edgeTree) {
		this.edgeTree = edgeTree;
	}

	public void setTopo(Topology topo) {
		this.topo = topo;
	}

	public MainLogic()
	{

	}
	
	public Topology getTopo() {
//quet topology mang luu vao file network.json    	
		ScanTopology scan = new ScanTopology();
	 	scan.writeFileTopo();
		
//lay du lieu topo mang tu file network.json     	
	 	topo = new Topology();
	 	topo.getData();
	 	
// ghi du lieu vao file graph.txt
	 	Data data = new Data(topo);
	    data.writeData();
	    return topo;
	}

	public void mst (String srcIP, ArrayList<String> listIP) {
		this.srcIP = srcIP;
		this.listIP = listIP;
//xac dinh sw nguon phat multicast
     	topo.sortSW(srcIP); 	
    	
//danh sach cac sw dki nghe multicast     	
     	ArrayList<Integer> listIDSW = new ArrayList<Integer>();
     	listIDSW = topo.getListIDSW(listIP);
     	
//chuyen doi topo mang thanh canh do thi     	
     	Convert convert = new Convert();
     	convert.convertLink(topo);
     	
     	ArrayList<Edge> listEdge = new ArrayList<Edge>();
     	listEdge = convert.getListEdge();
     	
     	int num_SW = topo.getList_switch().size();
     	int num_Link = topo.getList_link().size();
//khoi tao thuat toan PRIM - minimum spanning tree		
     	MST tree = new MST(num_SW);
		  
// tao ma tran (sw x sw)
		  int graph[][] = new int[num_SW][num_SW];
		                             
		  for (int i = 0; i < num_SW; i++) {
			  for (int j = 0; j < num_SW; j++) {
				  graph[i][j] = 0;
			  }
		  }
// set trong so cac canh 		  
		  for (int i = 0; i < num_Link; i++) {
			  Edge e = new Edge();
			  e = listEdge.get(i);
			  graph[e.getSrc_id()][e.getDst_id()] = 50;
// cac canh mac dinh bang 50, canh cac sw nghe multicast bang 5
			  for (int j = 0; j < listIDSW.size(); j++) {
				  for (int k = 0; k < listIDSW.size(); k++) {
					  if (e.getSrc_id()==listIDSW.get(j) && e.getDst_id()==listIDSW.get(k)) {
						  graph[e.getSrc_id()][e.getDst_id()] = 5;
					  }
				  }
			  }		  
		  }
		  
// in ma tran cac canh
		  System.out.println("Ma tran");
		  for (int i = 0; i < num_SW; i++) {
			  for (int j = 0; j < num_SW; j++) {
				  System.out.print(String.valueOf(graph[i][j] + "	"));
			  }
			  System.out.println("");
			  System.out.println("");
		  }
// thuc hien thuat toan
		  edgeTree = new ArrayList<Edge>();
		  edgeTree = tree.primMST(graph);
      
// tim duong tu sw bien ve root
		  this.listPath = new ArrayList<Path>();
		  System.out.println("");
		  System.out.println("Path multicast");
		  for (int i = 1; i < listIDSW.size(); i++) {
			  Path path = new Path(tree.path(listIDSW.get(i)));
			  listPath.add(path);
			  System.out.println("");
		  }
      
// in duong dan multicast
	      System.out.println("");
	      System.out.println("Duong dan multicast");
	      for (int i = 0; i < listPath.size(); i++) {
	    	  Path path = listPath.get(i);
	    	  for (int j = 0; j < path.getPath().size(); j++) {
	    		  String id = topo.getList_switch().get(path.getPath().get(j)).getName();
	    		  System.out.print(id + "	");
	    	  }
	    	  System.out.println("");
	      }
      
	      ArrayList<String> listNode = new ArrayList<String>();
	      for (int i = 0; i < topo.getList_switch().size(); i++) {
	    	  listNode.add(topo.getList_switch().get(i).getName());
	      }
	}
	
	public void configController() {
// them cac flow va group cho sw
	      Multicast mul = new Multicast(topo, listPath, listIP);
	      mul.createMulticast(srcIP, "224.0.0.1");
	      
	      System.out.println("Hello Multicast");
	}
	
	public ArrayList<String> getListSWName (){
		ArrayList<String> listSWName = new ArrayList<String>();
	    for (int i = 0; i < topo.getList_switch().size(); i++) {
	    	  listSWName.add(topo.getList_switch().get(i).getName());
	    }
	    return listSWName;
	}
	
	public ArrayList<String> getListIPHost(){
		ArrayList<String> lHostName = new ArrayList<String>();
		for (Host h : topo.getList_host()) {
			lHostName.add(h.getIp_address());
		}
		return lHostName;
	}
	
	public Viewer simulate_graph(File graph_file)
	{
		gs = new GraphSim("PageRankGraph");

		// Import vertices/edges to start and init/display graph
		gs.importGraph(graph_file);
		
		gs.initSim();

		//Comment out these 2 lines for no animate
		gs.process();

		return gs.get_display();


	}

	public Graph getGraph()
	{
		return gs.getGraph();
	}
}
