/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andori
 */
public class main {
    public static void main(String[] args) {
        BstClass t = new BstClass();
        t.insert(20);
        t.insert(10);
        t.insert(40);
        t.insert(30);
        t.insert(50);
//        t.insert(58);
//        t.insert(17);
//        t.insert(70);
//        t.insert(99);
//        t.insert(41);
        t.displayTree();
            t.Delete(40);
//                System.out.println("Banyak Node : "+t.banyakNode());
//                System.out.println("Banyak Daun : "+t.jumDaun());
//                System.out.println("Tinggi : "+t.height());
                t.displayTree();
        
    }
}
