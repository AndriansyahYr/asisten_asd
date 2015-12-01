import java.util.Stack;

public class AVLClass
{
	private Node root;
                
	public AVLClass()
	{
		root = null;
	}

	public void insert(int data_masukan)
        {
          root = insert( data_masukan, root );
          System.out.print("\nInput : "+data_masukan+"\n");         
          displayTree();
        }

	private Node insert( int data_masukan, Node t )
        {
        if( t == null )
            return new Node(data_masukan);
        if( data_masukan<t.data )
        {

            t.pKiri = insert( data_masukan, t.pKiri );
            if (tinggi(t.pKiri) - tinggi(t.pKanan) == 2 ){
                if( data_masukan<t.pKiri.data)
                    t = singleRotation(t,0);
                else
                    t = doubleRotation(t,0);
            }
        }
        else if(data_masukan>t.data )
        {

            t.pKanan = insert( data_masukan, t.pKanan );
            if (tinggi(t.pKanan) - tinggi(t.pKiri) == 2 ){
                if( data_masukan>t.pKanan.data )
                    t = singleRotation(t,1);
                else
                    t = doubleRotation(t,1);
            }
         }
         return t;
        }

        private Node singleRotation (Node node, int side)
	{
	  Node temp = node;
          if (side == 0)
          {
            temp = node.pKiri;
            node.pKiri = temp.pKanan;
            temp.pKanan = node;
          }
          else if (side == 1) // Right Rotation
          {
            temp = node.pKanan;
            node.pKanan = temp.pKiri;
            temp.pKiri = node;
          }
         return temp;
        }

        private Node doubleRotation(Node node, int side)
        {
          if (side == 0) //Double Left Rotation
          {
             node.pKiri = singleRotation( node.pKiri, 1 );
             return singleRotation( node, 0 );
          }
          else if (side == 1) //Double Right Rotation
          {
             node.pKanan = singleRotation( node.pKanan, 0 );
             return singleRotation( node, 1 );
          }
          return node;
        }
            
        public int tinggi(){
            return tinggi(root);
        }
        
        private int tinggi (Node t)
	{
		if (t == null)
			return -1;
		else {
			return Math.max(1+tinggi(t.pKiri),1+tinggi(t.pKanan));
		}
	}
         
        public int jumNodeAtLevel(Node t,int level){
          return(tinggi(t));
        }
        
        public void SetLevelAllNode(Node t,int lev){
           if (t!=null){
              t.level=lev;
              SetLevelAllNode(t.pKiri,lev+1);
              SetLevelAllNode(t.pKanan,lev+1);
           } 
        }
        
        public int jumAtLevel(Node t,int lev){
           if (t!=null){
              if (t.level==lev){
                 return (1);
              } else {
                 return (jumAtLevel(t.pKiri,lev)+jumAtLevel(t.pKanan,lev));
              }  
           } else return(0);
        }
            
        public int jumAtLevel(int lev){
           SetLevelAllNode(root,0);
           return(jumAtLevel(root,lev));
        }
        
        public void PreOrder(){
            System.out.print("\nPreOrder : ");
            PreOrder(root);
            System.out.println(""); 
        }
        
        private void PreOrder(Node t){
            if (t!=null){
               System.out.print(t.data+" "); 
               PreOrder(t.pKiri);
               PreOrder(t.pKanan);
            }
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
					localStack.push(temp.pKiri);
					localStack.push(temp.pKanan);
					if(temp.pKiri != null || temp.pKanan != null)
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
        
         public boolean hapusAVL(int key) {
        //inisialisasi
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.data != key) { // untuk mencari node
            parent = current;
            if (key < current.data) { // ke kiri ?
                isLeftChild = true;
                current = current.pKiri;
            } else { // atau ke kanan ?
                isLeftChild = false;
                current = current.pKanan;
            }
            if (current == null) { // akhir dari data
                return false; // jika tidak ditemukan
            }
        } // akhir while
        // data yang ingin di hapus ditemukan
        // cara hapus jika tidak ada anak
        if (current.pKiri == null && current.pKanan == null) {
            if (current == root) { // jika root
                root = null; // tree kosong
            } else if (isLeftChild) {
                parent.pKiri = null; // disconnect
            } else { // dari parent
                parent.pKanan = null;
            }
        } // jika tidak memiliki anak kanan, ganti dengan anak kiri
        else if (current.pKanan == null) {
            if (current == root) {
                root = current.pKiri;
            } else if (isLeftChild) {
                parent.pKiri = current.pKiri;
            } else {
                parent.pKanan = current.pKiri;
            }
        } // jika tidak memiliki anak kiri, ganti dengan anak kanan
        else if (current.pKiri == null) {
            if (current == root) {
                root = current.pKanan;
            } else if (isLeftChild) {
                parent.pKiri = current.pKanan;
            } else {
                parent.pKanan = current.pKanan;
            }
        } else { // memiliki 2 anak, ganti dengan inorder successor
// get successor dari Node untuk dihapus (current)
            Node successor = getSuccessor(current);
// connect parent dari current ke successor instead
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.pKiri = successor;
            } else {
                parent.pKanan = successor;
            }
// connect successor ke anak kiri dari current’s
            successor.pKiri = current.pKiri;
        } // akhir else 2 anak
// (successor tidak memiliki anak kiri)
        Node temp = root;
        Node prev = null;
        while (temp != null) {
            //subtree pKiri dan pKanan memenuhi kondisi AVL
            if (Math.abs(tinggi(temp.pKiri) - tinggi(temp.pKanan)) <= 1) {
                temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
            } //kasus 1 algoritma AVL 
            else if (tinggi(temp.pKiri)
                    - tinggi(temp.pKanan) >= 2
                    && tinggi(temp.pKiri.pKiri)
                    >= tinggi(temp.pKiri.pKanan)) {
                parent = temp.pInduk;
                Node pKiri = temp.pKiri;
                temp.pKiri = pKiri.pKanan;
                if (temp.pKiri != null) {
                    temp.pKiri.pInduk = temp;
                }
                pKiri.pKanan = temp;
                temp.pInduk = pKiri;

                pKiri.pInduk = parent;
                if (parent == null) {
                    root = pKiri;
                } else if (parent.pKiri == temp) {
                    parent.pKiri = pKiri;
                } else {
                    parent.pKanan = pKiri;
                }

                //hitung tinggi subtree pKanan
                temp.tinggi = Math.max(tinggi(temp.pKiri),
                        tinggi(temp.pKanan)) + 1;

                temp = pKiri;
                //hitung tinggi dari root
                temp.tinggi = Math.max(tinggi(temp.pKiri),
                        tinggi(temp.pKanan)) + 1;
            } //case 2 algoritma AVl 
            else if (tinggi(temp.pKanan)
                    - tinggi(temp.pKiri) >= 2
                    && tinggi(temp.pKanan.pKanan)
                    >= tinggi(temp.pKanan.pKiri)) {
                parent = temp.pInduk;
                Node pKanan = temp.pKanan;

                temp.pKanan = pKanan.pKiri;
                if (temp.pKanan != null) {
                    temp.pKanan.pInduk = temp;
                }

                pKanan.pKiri = temp;
                temp.pInduk = pKanan;

                pKanan.pInduk = parent;
                if (parent == null) {
                    root = pKanan;
                } else if (parent.pKanan == temp) {
                    parent.pKanan = pKanan;
                } else {
                    parent.pKiri = pKanan;
                }

                //hitung tinggi subtree pKanan 
                temp.tinggi = Math.max(tinggi(temp.pKiri),
                        tinggi(temp.pKanan)) + 1;

                temp = pKanan;

                //hitung tinggi dari root
                temp.tinggi = Math.max(tinggi(temp.pKiri),
                        tinggi(temp.pKanan)) + 1;
            } //kasus 3 dari algoritma AVL 
            else if (tinggi(temp.pKiri)
                    - tinggi(temp.pKanan) >= 2
                    && tinggi(temp.pKiri.pKanan)
                    >= tinggi(temp.pKiri.pKiri)) {
                parent = temp.pInduk;
                Node pKiripKanan = temp.pKiri.pKanan;
                temp.pKiri.pKanan = pKiripKanan.pKiri;
                if (temp.pKiri.pKanan != null) {
                    temp.pKiri.pKanan.pInduk = temp.pKiri;
                }

                pKiripKanan.pKiri = temp.pKiri;
                temp.pKiri.pInduk = pKiripKanan;

                temp.pKiri = pKiripKanan.pKanan;
                if (temp.pKiri != null) {
                    temp.pKiri.pInduk = temp;
                }

                pKiripKanan.pKanan = temp;
                temp.pInduk = pKiripKanan;

                pKiripKanan.pInduk = parent;
                if (parent == null) {
                    root = pKiripKanan;
                } else if (parent.pKiri == temp) {
                    parent.pKiri = pKiripKanan;
                } else {
                    parent.pKanan = pKiripKanan;
                }

                //hitung tinggi subtree pKanan 
                temp.tinggi = Math.max(tinggi(temp.pKiri),
                        tinggi(temp.pKanan)) + 1;

                temp = pKiripKanan;

                //hitung tinggi dari root
                temp.tinggi = Math.max(tinggi(temp.pKiri),
                        tinggi(temp.pKanan)) + 1;
            } //kasus 4 dari algoritma AVL 
            else if (tinggi(temp.pKanan)
                    - tinggi(temp.pKiri) >= 2
                    && tinggi(temp.pKanan.pKiri)
                    >= tinggi(temp.pKanan.pKanan)) {
                parent = temp.pInduk;
                Node pKananpKiri = temp.pKanan.pKiri;

                temp.pKanan.pKiri = pKananpKiri.pKanan;
                if (temp.pKanan.pKiri != null) {
                    temp.pKanan.pKiri.pInduk = temp.pKanan;
                }

                pKananpKiri.pKanan = temp.pKanan;
                temp.pKanan.pInduk = pKananpKiri;

                temp.pKanan = pKananpKiri.pKiri;
                if (temp.pKanan != null) {
                    temp.pKanan.pInduk = temp;
                }

                pKananpKiri.pKiri = temp;
                temp.pInduk = pKananpKiri;

                pKananpKiri.pInduk = parent;
                if (parent == null) {
                    root = pKananpKiri;
                } else if (parent.pKanan == temp) {
                    parent.pKanan = pKananpKiri;
                } else {
                    parent.pKiri = pKananpKiri;
                }

                temp.tinggi = Math.max(tinggi(temp.pKiri),
                        tinggi(temp.pKanan)) + 1;

                temp = pKananpKiri;

                temp.tinggi = Math.max(tinggi(temp.pKiri),
                        tinggi(temp.pKanan)) + 1;
            }
            temp = temp.pInduk;
        }
        return true; // success
    } // akhir hapusAVL
        
        private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.pKanan; // go to right child
        while (current != null) // until no more
        { // left children,
            successorParent = successor;
            successor = current;
            current = current.pKiri; // go to left child
        }
// if successor not
        if (successor != delNode.pKanan) // right child,
        { // make connections
            successorParent.pKiri = successor.pKanan;
            successor.pKanan = delNode.pKanan;
        }
        return successor;
    }

}

