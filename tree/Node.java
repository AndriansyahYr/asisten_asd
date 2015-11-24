public class Node{
	public int  data;
	public Node left;
	public Node right;

	public Node() // constructor-1
	{
                 this.data=0;
	   right=left=null;
	}
        
	public Node(int data) // constructor-2
	{
	   this.data=data;
	   right=left=null;
	}
}
