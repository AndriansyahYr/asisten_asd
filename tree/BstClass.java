import java.util.Stack;

public class BstClass
{
	private Node root;
        
	public BstClass()
	{
		root = null;
	}
        
        public int f1(int x){
          return(f1(x,root));
        }
        
        public int f1(int x,Node t){
          if (t==null){ 
             return -1000;  
          } else
           if (x==t.data)
              return 0; 
            else 
            if (x<t.data) 
              return (1+f1(x,t.left));
            else
              return (1+f1(x,t.right));        
         }
        
        public int f2(){
           return f2(root); 
        }
        
        private int f2(Node t){
           if (t!=null){
              if ((t.left!=null) && (t.right==null))  
                return 1+f2(t.left);
              else
                return(f2(t.left)+f2(t.right));
           } else
             return 0;  
        }
        
        public int f3(){
          return (f3(root)); 
        }
        
        private int f3(Node t){
          if (t.left==null)
            return (t.data);
          else
            return f3(t.left);
        }
        
        public int f4(){
          return (f4(root)); 
        }
        
        private int f4(Node t){
          if (t.right==null)
            return (t.data);
          else
            return f4(t.right);
        }
        
        
        
	public void insert(int data_masukan)
        {
          root = insert( data_masukan, root );
        }

        
	private Node insert( int data_masukan, Node t )
        {
        if( t == null )
            return new Node(data_masukan);
        else {
          if( data_masukan<t.data )
          {
             t.left = insert( data_masukan, t.left );
          }
          else if(data_masukan>=t.data )
          {
             t.right = insert( data_masukan, t.right );
          }
         return t;
        }
      }
       

        public int height()
        {
            return Height(root);
        }
        
        private int Height (Node t)
	{
		if (t == null)
		   return -1;
		else {
                   return Math.max(1+Height(t.left),1+Height(t.right));
		}
	}
        
	public boolean isEmpty()
	{
		return(root==null);
	}

        public void InOrder(){
            System.out.print("InOrder : ");
            InOrder(root);
            System.out.println("");
        }
        
        private void InOrder(Node t){
           if (t!=null){
              InOrder(t.left);
              System.out.print(t.data+" ");
              InOrder(t.right);
           } 
        }
        
        public void PreOrder(){
            System.out.print("PreOrder : ");
            PreOrder(root);
            System.out.println("");
        }
        
        private void PreOrder(Node t){
           if (t!=null){
              System.out.print(t.data+" ");
              PreOrder(t.left);
              PreOrder(t.right);
           } 
        }
        
        
        public float rata(){
            return (float)jumData()/(float)banyakNode();
        }
        
        public int banyakNode(){
            return (banyakNode(root));
        }
        
        private int banyakNode(Node t){
           if (t==null)
               return 0;
           else
               return (1+banyakNode(t.left)+banyakNode(t.right));
        }
                
        public int jumData(){
            return (jumData(root));
        }
        
        private int jumData(Node t){
           if (t==null)
               return 0;
           else
               return (t.data+jumData(t.left)+jumData(t.right));
        }
        
                
        public boolean find(int x){
           boolean ketemu=false; 
           return find(x,ketemu,root);    
        } 
        
        private boolean find(int x,boolean ketemu, Node t){
           if (t!=null){
              if (x==t.data){
                  ketemu=true;
              } else {
                if (x<t.data){
                   return (find(x,ketemu,t.left)); 
                } else {
                   return (find(x,ketemu,t.right));  
                }   
              }          
           }
           return (ketemu); 
        }
        
        
        public int jumDaun(){
            return(jumDaun(root));
        }
        
        private int jumDaun(Node t){
            if (t==null){ // syarat 1
                return 0;
            } else {
              if ((t.right==null) && (t.left==null)){ // syarat 2
                 return 1; 
              } else { // syarat 3
                 return (jumDaun(t.left)+jumDaun(t.right)); 
              }
            }
        }
        
        public void Delete(int x){
            Delete(root,x);
        }
        
        public Node Delete(Node t,int x){
          if (t!=null){    
          if (x<t.data) 
            t.left=Delete(t.left,x);
          else
            if (x>t.data)
              t.right=remove(t.right,x);
            else {
              // t.data==x  
              if ((t.right==null) && (t.left==null))  
              { if (root==t)
                  root=null;
                t=null;  
              }
              else {
                // yg dihapus bukan daun
                  
              }  
            }
          }
          return t;
        }
        
        private boolean IsDaun(Node t){
          return((t.left==null) && (t.right==null));              
        }
        
        private Node remove(Node t,int x) {
         if (t!=null){
           if (t.data==x){
              if ((t.left==null) && (t.right==null)){
                 if (root==t){
                     root=null;
                 } 
                 t=null; 
              } else {
                 if (t.left!=null){
                    Node parent=t;
                    Node besar=t.left;
                    //mencari nilai terbesar di sub pohon kiri
                    while (besar.right!=null){
                        parent=besar;
                        besar =besar.right;
                    }
                    t.data=besar.data;  
                    besar=remove(besar,besar.data);  
                    if (t!=parent)
                      parent.right=besar;
                    else
                      parent.left=besar;   
                 } else {
                    Node parent=t;
                    Node kecil=t.right;
                    //mencari nilai terkecil di sub pohon kanan
                    while (kecil.left!=null){
                        parent=kecil;
                        kecil =kecil.left;
                    }
                    t.data=kecil.data;  
                    kecil=remove(kecil,kecil.data);
                    if (t!=parent)
                      parent.left=kecil;
                    else
                      parent.right=kecil;                      
                 }                 
              } 
           }else {
              if (x<t.data) 
                  t.left=remove(t.left,x);
              else
                  t.right=remove(t.right,x);
           }  
          } 
          return t;
        }
        
	public void displayTree()
	{
		Stack globalStack = new Stack();
		globalStack.push(root);
		int kosong = 32;
		int counter=1;
		int temp1=0;
		boolean kolomkosong = false;
		boolean baris_pertama=true;
		while(kolomkosong==false)
		{
			Stack localStack = new Stack();
			kolomkosong = true;
			if(baris_pertama==false)
			{
				for(int i=1;i<=counter/2;i++)
				{
					for(int j=1;j<kosong*2+1;j++)
						System.out.print(" ");
					System.out.print("|");
					if(i!=counter)
					{
						for(int j=1;j<kosong*2;j++)
							System.out.print(" ");
					}
				}
				System.out.println("");
				for(int i=1;i<=counter*2;i++)
				{
					if(i%4==2||i%4==3)
					{
						for(int j=1;j<=kosong;j++)
							System.out.print("-");
					}
					else
					{
						for(int j=1;j<kosong+1;j++)
							System.out.print(" ");
					}
				}
				System.out.println("");
				for(int i=1;i<=counter;i++)
				{
					for(int j=1;j<kosong+1;j++)
						System.out.print(" ");
					System.out.print("|");
					if(i!=counter)
					{
						for(int j=1;j<kosong;j++)
							System.out.print(" ");
					}
				}
				System.out.println("");
			}
			for(int j=0; j<kosong; j++)
				System.out.print(" ");
			while(globalStack.isEmpty()==false)
			{
				Node temp = (Node)globalStack.pop();
				if(temp != null)
				{
					System.out.print(temp.data);
					localStack.push(temp.left);
					localStack.push(temp.right);
					if(temp.left != null || temp.right != null)
						kolomkosong = false;
					temp1++;
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
					temp1++;
				}
				if(temp1<counter)
				{
					for(int j=0; j<kosong*2-2; j++)
						System.out.print(" ");
				}
			}
			temp1=0;
			counter=counter*2;
			System.out.println();
			kosong /= 2;
			baris_pertama=false;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		}
	}
}

