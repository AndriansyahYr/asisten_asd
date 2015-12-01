public class Node{
	public int  data;
	public Node pKiri;
	public Node pKanan;
    int level;
    Node pInduk;
    int tinggi;

	public Node() // constructor-1
	{
                 this.data=0;
	   pKanan=pKiri=null;
	}
        
	public Node(int data) // constructor-2
	{
	   this.data=data;
	   pKanan=pKiri=null;
	}
}
