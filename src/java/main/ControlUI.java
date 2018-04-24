package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import org.graphstream.graph.Edge;
import org.graphstream.graph.EdgeFactory;
import org.graphstream.graph.EdgeRejectedException;
import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.Node;
import org.graphstream.graph.NodeFactory;
import org.graphstream.stream.AttributeSink;
import org.graphstream.stream.ElementSink;
import org.graphstream.stream.GraphParseException;
import org.graphstream.stream.Sink;
import org.graphstream.stream.file.FileSink;
import org.graphstream.stream.file.FileSource;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerListener;
import org.graphstream.ui.swingViewer.ViewerPipe;

import multicast.mst.Path;
import multicast.topology.Host;
import multicast.topology.Topology;


/**
 * UI Class that contains all the UI functions
 * @author brandon
 *
 */
public class ControlUI extends JFrame implements ActionListener, MouseWheelListener, MouseMotionListener {


	// Logic Variables
	private MainLogic mLogic; 
	private File graph_file = new File("/home/tuan/multicast/graph.txt");

	private  GraphUIProperty gUIProp;
	private NodeClickListener clisten = null;
	
	private Viewer vwr;
	private Topology topo;
	private String sourceIP;
// danh sach ip host dich
	private ArrayList<String> listIP;	
// danh sach canh cua cay mst
	private ArrayList<multicast.mst.Edge> edgeTree;
// danh sach ten cac switch sau khi sap xep	
	private ArrayList<String> listNode;
// danh sach duong di tu nguon toi dich	
	private ArrayList<Path> listPath;
	
	private Boolean checkIP = false;
	private Boolean checkTopo = false;
	
	private Graph graph = new Graph() {
		
		@Override
		public <T extends Node> Collection<T> getNodeSet() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Node> Iterator<T> getNodeIterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getNodeCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public <T extends Edge> Collection<T> getEdgeSet() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> Iterator<T> getEdgeIterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getEdgeCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public <T extends Node> Iterable<? extends T> getEachNode() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> Iterable<? extends T> getEachEdge() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterator<Node> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void stepBegins(String arg0, long arg1, double arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nodeRemoved(String arg0, long arg1, String arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nodeAdded(String arg0, long arg1, String arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void graphCleared(String arg0, long arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void edgeRemoved(String arg0, long arg1, String arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void edgeAdded(String arg0, long arg1, String arg2, String arg3, String arg4, boolean arg5) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nodeAttributeRemoved(String arg0, long arg1, String arg2, String arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nodeAttributeChanged(String arg0, long arg1, String arg2, String arg3, Object arg4, Object arg5) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nodeAttributeAdded(String arg0, long arg1, String arg2, String arg3, Object arg4) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void graphAttributeRemoved(String arg0, long arg1, String arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void graphAttributeChanged(String arg0, long arg1, String arg2, Object arg3, Object arg4) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void graphAttributeAdded(String arg0, long arg1, String arg2, Object arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void edgeAttributeRemoved(String arg0, long arg1, String arg2, String arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void edgeAttributeChanged(String arg0, long arg1, String arg2, String arg3, Object arg4, Object arg5) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void edgeAttributeAdded(String arg0, long arg1, String arg2, String arg3, Object arg4) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removeSink(Sink arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removeElementSink(ElementSink arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removeAttributeSink(AttributeSink arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void clearSinks() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void clearElementSinks() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void clearAttributeSinks() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void addSink(Sink arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void addElementSink(ElementSink arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void addAttributeSink(AttributeSink arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setAttribute(String arg0, Object... arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removeAttribute(String arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean hasVector(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasNumber(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasLabel(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasHash(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasAttribute(String arg0, Class<?> arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasAttribute(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasArray(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public ArrayList<? extends Number> getVector(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public double getNumber(String arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public CharSequence getLabel(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getIndex() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public HashMap<?, ?> getHash(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T> T getFirstAttributeOf(Class<T> arg0, String... arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T> T getFirstAttributeOf(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<String> getAttributeKeySet() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterator<String> getAttributeKeyIterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getAttributeCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public <T> T getAttribute(String arg0, Class<T> arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T> T getAttribute(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object[] getArray(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void clearAttributes() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void changeAttribute(String arg0, Object... arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void addAttributes(Map<String, Object> arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void addAttribute(String arg0, Object... arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void write(FileSink arg0, String arg1) throws IOException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void write(String arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void stepBegins(double arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setStrict(boolean arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setNullAttributesAreErrors(boolean arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setNodeFactory(NodeFactory<? extends Node> arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setEdgeFactory(EdgeFactory<? extends Edge> arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setAutoCreate(boolean arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public <T extends Node> T removeNode(Node arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Node> T removeNode(int arg0) throws IndexOutOfBoundsException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Node> T removeNode(String arg0) throws ElementNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T removeEdge(Node arg0, Node arg1) throws ElementNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T removeEdge(int arg0, int arg1)
				throws IndexOutOfBoundsException, ElementNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T removeEdge(String arg0, String arg1) throws ElementNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T removeEdge(Edge arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T removeEdge(int arg0) throws IndexOutOfBoundsException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T removeEdge(String arg0) throws ElementNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void read(FileSource arg0, String arg1) throws IOException, GraphParseException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void read(String arg0) throws IOException, GraphParseException, ElementNotFoundException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean nullAttributesAreErrors() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public NodeFactory<? extends Node> nodeFactory() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean isStrict() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isAutoCreationEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public double getStep() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public <T extends Node> T getNode(int arg0) throws IndexOutOfBoundsException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Node> T getNode(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T getEdge(int arg0) throws IndexOutOfBoundsException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T getEdge(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Iterable<ElementSink> elementSinks() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EdgeFactory<? extends Edge> edgeFactory() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Viewer display(boolean arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Viewer display() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public Iterable<AttributeSink> attributeSinks() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Node> T addNode(String arg0) throws IdAlreadyInUseException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T addEdge(String arg0, Node arg1, Node arg2, boolean arg3)
				throws IdAlreadyInUseException, EdgeRejectedException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T addEdge(String arg0, int arg1, int arg2, boolean arg3)
				throws IndexOutOfBoundsException, IdAlreadyInUseException, EdgeRejectedException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T addEdge(String arg0, String arg1, String arg2, boolean arg3)
				throws IdAlreadyInUseException, ElementNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T addEdge(String arg0, Node arg1, Node arg2)
				throws IdAlreadyInUseException, EdgeRejectedException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T addEdge(String arg0, int arg1, int arg2)
				throws IndexOutOfBoundsException, IdAlreadyInUseException, EdgeRejectedException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public <T extends Edge> T addEdge(String arg0, String arg1, String arg2)
				throws IdAlreadyInUseException, ElementNotFoundException, EdgeRejectedException {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	
	// UI Components

	private  int frm_width, frm_height;
	private  int ctrl_width, ctrl_height;

	private JFrame  jfrm;
	private JPanel ctrl_parent;
	private JPanel ctrl_panel;

	private JButton btn_loadTopo;
	private JButton btn_showDetails;
	private JButton btn_showMST;
	private JButton btn_multicast;
	private JButton btn_setIPSrc;
	private JButton btn_setIPDst;
	
	private JTextField tf_setIP;

	private CheckBoxList checkBoxList;
    private DefaultListModel model;    
    private JScrollPane jScroll;

	private View vw=null;

	public ControlUI ()
	{
		//sets the values in the fields dealing with the screen
		init ();
		//Creates the GUI
		startUI();
		System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	}

	private void init ()
	{
		// Init GUI Settings
		Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
		frm_width = screen_dim.width;
		frm_height = screen_dim.height;

//		ctrl_width = (int)(0.3 * (double)screen_dim.width);
		ctrl_width = 320;
		ctrl_height = screen_dim.height;

		//System.out.println(screen_dim.height);

		// Initialize graph UI property object to pass to sim
		gUIProp = new GraphUIProperty();
		gUIProp.height = screen_dim.height;
		gUIProp.width = screen_dim.width - ctrl_width;
		gUIProp.posx = 0;
		gUIProp.posy = 0;

		mLogic= new MainLogic();
	}

	private  void startUI(){

		jfrm = new JFrame();
		jfrm.setBounds(0, 0, frm_width, frm_height);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.getContentPane().setLayout(null);
		
		ctrl_parent = new JPanel();
		ctrl_parent.setBounds(frm_width - ctrl_width, 0, ctrl_width, frm_height);
		ctrl_parent.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 8));
		jfrm.getContentPane().add(ctrl_parent);
		
		try{

			ControlUI.class.getResourceAsStream("images/ProPPR.png");
			BufferedImage myPicture = ImageIO.read(ControlUI.class.getResourceAsStream("images/logo.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			ctrl_parent.add(picLabel);
		}
		catch(Exception e) { e.printStackTrace(); }
		
		ctrl_panel = new JPanel();
		ctrl_panel.setLayout(new GridLayout(12, 1));
		
		ctrl_parent.add(ctrl_panel);

		
		btn_loadTopo = new JButton("Load Topology");
		ctrl_panel.add(btn_loadTopo);
		btn_loadTopo.addActionListener(this);

		JLabel lbl1 = new JLabel("-------------------------------------------------");
		ctrl_panel.add(lbl1);
		
		btn_showDetails = new JButton("Show Details");
		ctrl_panel.add(btn_showDetails);
		btn_showDetails.addActionListener(this);
		
		JLabel lbl2 = new JLabel("-------------------------------------------------");
		ctrl_panel.add(lbl2);
		
		btn_showMST = new JButton("Show Minimum Spanning Tree");
		ctrl_panel.add(btn_showMST);
		btn_showMST.addActionListener(this);
		
		JLabel lbl3 = new JLabel("-------------------------------------------------");
		ctrl_panel.add(lbl3);
		
		btn_multicast = new JButton("Multicast");
		ctrl_panel.add(btn_multicast);
		btn_multicast.addActionListener(this);
		
		JLabel lbl4 = new JLabel("-------------------------------------------------");
		ctrl_panel.add(lbl4);
		
		btn_setIPSrc = new JButton("Set IP Source");
		ctrl_panel.add(btn_setIPSrc);
		btn_setIPSrc.addActionListener(this);
		
		tf_setIP = new JTextField();
		tf_setIP.setColumns(16);
		tf_setIP.setToolTipText("dia chi ip nguon");
		ctrl_panel.add(tf_setIP);
		
		JLabel lbl5 = new JLabel("-------------------------------------------------");
		ctrl_panel.add(lbl5);

		jfrm.setVisible(true);
	}

	/**
	 * Action listener for click of buttons
	 * This 
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btn_loadTopo)
		{
			loadTopo();
		}
		if (source == btn_showDetails)
		{
			showDetails();
		}
		if (source == btn_showMST)
		{
			showMST();
		}
		if (source == btn_multicast)
		{
			multicast();
		}
		if (source == btn_setIPSrc)
		{
			setIPSrc();
		}
		if (source == btn_setIPDst)
		{
			setIPDst();
		}
		
	}	

	public void mouseWheelMoved(MouseWheelEvent e) {
		if(vw != null){
			int notches = e.getWheelRotation();
			Point point = e.getPoint();
			double i = vw.getCamera().getViewPercent();
			if(i < 1){
				if(point.getX() < 400){
					//400 is an example of a hardcode value to change
					if(point.getY() < 300){
						vw.getCamera().getViewCenter().move(-1, 1);
					}
					else if(point.getY() < 600){
						vw.getCamera().getViewCenter().move(-1, 0);
					}
					else{
						vw.getCamera().getViewCenter().move(-1, -1);
					}
				}
				else if(point.getX() < 800){
					if(point.getY() < 300){
						vw.getCamera().getViewCenter().move(0, 1);
					}
					else if(point.getY() < 600){
						vw.getCamera().getViewCenter().move(0, 0);
					}
					else{
						vw.getCamera().getViewCenter().move(0, -1);
					}
				}
				else{
					if(point.getY() < 300){
						vw.getCamera().getViewCenter().move(1, 1);
					}
					else if(point.getY() < 600){
						vw.getCamera().getViewCenter().move(1, 0);
					}
					else{
						vw.getCamera().getViewCenter().move(1, -1);
					}
				}
			}
			else{
				vw.getCamera().resetView();
			}


			if(notches > 0){
				vw.getCamera().setViewPercent(i * 1.1);
			}
			else{

				vw.getCamera().setViewPercent(i * 0.9);
			}
		}
	}

	
	public void loadTopo()
	{
		this.topo = mLogic.getTopo();
		if (graph_file != null)
		{
			// close event listener for mouse first before removing view 
			// in next step
			if (clisten!=null)
			{
				clisten.viewClosed(null);
			}
			// Remove view if exists
			if (vw!=null)
			{
				jfrm.remove(vw);
			}

			//This is a sort of wrapper class which calls all 
			//the other methods in GraphSims and GraphSimsAlgorithm
			//the actually creates the graph and animates it
			Viewer vwr = mLogic.simulate_graph(graph_file);

			vw = vwr.addDefaultView(false);

			vw.setSize(gUIProp.width,gUIProp.height);
			vw.setLocation(gUIProp.posx, gUIProp.posy);

			// We connect back the viewer to the graph,
			// the graph becomes a sink for the viewer.
			// We also install us as a viewer listener to
			// intercept the graphic events.
			ViewerPipe fromViewer = vwr.newViewerPipe();

			graph = mLogic.getGraph();
			
			clisten = new NodeClickListener(fromViewer, vw, mLogic.getGraph());
			
			fromViewer.addViewerListener((ViewerListener) clisten);
			vw.addMouseWheelListener(this);
			vw.addMouseMotionListener(this);

			// Add in frame
			jfrm.add(vw,BorderLayout.LINE_START);
			
			// hien thi cac switch bang cac dau cham lom hon
			for (String swID : mLogic.getListSWName()) {
				Node node = graph.getNode(swID);
				node.addAttribute("ui.class", "switch");
			}
//set check load topology thanh cong			
			checkTopo = true;
		} 
		else
		{
			JOptionPane.showMessageDialog(jfrm, "NO TOPO!", "No Topology!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void showDetails()
	{
		for (int i = 0; i< graph.getNodeCount(); i++) {
			Node n = graph.getNode(i);
			String _ui_label = n.getAttribute("_ui.label");
			String ui_label = n.getAttribute("ui.label"); 
			
			// if not set toggle on node and adj edges
			if (ui_label==null || ui_label.equals(""))
			{
				n.setAttribute("ui.label", _ui_label);
			}
			else // Toggle node off and adj unbound edges
			{
				n.setAttribute("ui.label", "");
			}
		}
	}

// show Minimum SpanNing Tree	
	public void showMST() {
		if(this.checkIP) {
//hien thi cac canh cua cay mst
			for (int i = 0; i < edgeTree.size(); i++) {
				multicast.mst.Edge e = edgeTree.get(i);
				String id;
				String src = listNode.get(e.getSrc_id());
				String dst = listNode.get(e.getDst_id());
				
				String s[] = src.split(":", 2);
				String d[] = dst.split(":", 2);
				if (Integer.parseInt(s[1]) < Integer.parseInt(d[1])) {
					id = src + "->" + dst + ":sw";
				}else {
					id = dst + "->" + src + ":sw";
				}
				Edge edge = graph.getEdge(id);
				edge.addAttribute("ui.class", "mst");
			}
			
// set cac host va link host-sw ve mac dinh 
// host phat multicast
			Node n = graph.getNode(sourceIP);
			n.addAttribute("ui.class", "");
			Edge e = n.getEdge(0);
			e.addAttribute("ui.class", "");
			
			Node root = e.getTargetNode();
			root.addAttribute("ui.class", "switchroot");
// host nghe multicast
			for (String ip : listIP) {
				Node node = graph.getNode(ip);
				node.addAttribute("ui.class", "");
				Edge edge = node.getEdge(0);
				edge.addAttribute("ui.class", "");
			}
		}
	}
	
	public void multicast () {
		if (this.checkIP) {
// hien thi cay multicast
			for (int i = 0; i < listPath.size(); i++) {
				ArrayList<Integer> path = listPath.get(i).getPath();
				for (int j = 0; j < path.size()-1; j++) {
					String id;
					int srcID = path.get(j);
					int dstID = path.get(j+1);
					
					String src = listNode.get(srcID);
					String dst = listNode.get(dstID);
					
					String s[] = src.split(":", 2);
					String d[] = dst.split(":", 2);
					
					if (Integer.parseInt(s[1]) < Integer.parseInt(d[1])) {
						id = src + "->" + dst + ":sw";
					}else {
						id = dst + "->" + src + ":sw";
					}
					Edge edge = graph.getEdge(id);
					edge.addAttribute("ui.class", "multicast");
				}
			}
// hien thi cac host tham gia multicast
// host phat multicast
			Node n = graph.getNode(sourceIP);
			n.addAttribute("ui.class", "hostsrc");
			Edge e = n.getEdge(0);
			e.addAttribute("ui.class", "multicast");
			Node root = e.getTargetNode();
			root.addAttribute("ui.class", "switch");
// host nghe multicast
			for (String ip : listIP) {
				Node node = graph.getNode(ip);
				node.addAttribute("ui.class", "hostdst");
				Edge edge = node.getEdge(0);
				edge.addAttribute("ui.class", "multicast");
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setIPSrc() {
// kiem tra xem da load topo chua
		if (checkTopo) {
			ArrayList<String> lIPHost = new ArrayList<String>();
			lIPHost = mLogic.getListIPHost();
			
			String ip = tf_setIP.getText();
			Boolean b = false;
			for (String hostIP : lIPHost) {
				if (ip.equals(hostIP)) {
					b = true;
				}
			}
// check ip nguon		
			if (b) {
				btn_setIPSrc.setText("IP Source");
				btn_setIPSrc.setEnabled(false);
				
				tf_setIP.setEditable(false);
				
				this.sourceIP = ip;
				mLogic.setSrcIP(ip);
				
				btn_setIPDst = new JButton("Set IP Destination");
				ctrl_panel.add(btn_setIPDst);
				btn_setIPDst.addActionListener(this);
				
				checkBoxList = new CheckBoxList();
			    model = ((DefaultListModel)checkBoxList.getModel());
			    
			    for (Host host : topo.getList_host()) {
			    	String ipHost = host.getIp_address();
			    	if (!sourceIP.equals(ipHost)) {
			    		model.addElement(new CheckBoxListEntry(ipHost,false));
			    	}
			    }
			    
			    jScroll = new JScrollPane(checkBoxList);
			    jScroll.setPreferredSize(ctrl_panel.getMinimumSize());
			    ctrl_parent.add(jScroll);
			    
// repaint lai jpanel
			    ctrl_parent.validate();
			    ctrl_parent.repaint();
			}else {
				tf_setIP.setText("NO IP");
			}
		}
	}

	public void setIPDst() {
		ArrayList<String> listIP = new ArrayList<String>();
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		ArrayList<JCheckBox> listSelected = new ArrayList<JCheckBox>();
		listSelected = checkBoxList.getCheckedItems();
		
		for(JCheckBox cb : listSelected) {
			String ip = cb.getText();
			listIP.add(ip);
			model.addElement(ip);
		}
		
		
		JList<String> dsIP = new JList<String>(model);
		
		if (listSelected.size() > 1) {
			this.listIP = listIP;

// xoa list check box
			ctrl_parent.remove(jScroll);
			
// hien thi danh sach ip nghe multicast
			JScrollPane jS = new JScrollPane(dsIP);
		    jS.setPreferredSize(ctrl_panel.getMinimumSize());
		    ctrl_parent.add(jS);
		    
// repaint lai jpanel
		    ctrl_parent.validate();
		    ctrl_parent.repaint();
			
			
			btn_setIPDst.setText("List IP Destination");
			btn_setIPDst.setEnabled(false);
			
			jScroll.setEnabled(false);
			
			mLogic.mst(this.sourceIP, this.listIP);
			this.edgeTree = new ArrayList<multicast.mst.Edge>();
			this.edgeTree = mLogic.getEdgeTree();
			
			this.listNode = new ArrayList<String>();
			for (int i = 0; i < topo.getList_switch().size(); i++) {
				listNode.add(topo.getList_switch().get(i).getName());
			}
			
			this.listPath = new ArrayList<Path>();
			this.listPath = mLogic.getListPath();
			
			mLogic.configController();
			
// khi co ip nguon va ip dich thi check thanh cong
			this.checkIP = true;
			
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(vw != null){
		}
	}
	public NodeClickListener getClisten() {
		return clisten;
	}
	public void setClisten(NodeClickListener clisten) {
		this.clisten = clisten;
	}
}
