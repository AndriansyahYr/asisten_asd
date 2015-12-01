/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andori
 */
public class AVLMain {
    public static void main(String[] args) {
        AVLClass a = new AVLClass();
        // t.sisipDt(3); t.inOrderTraversal();System.out.println();
        // t.sisipDt(4); t.inOrderTraversal();System.out.println();
        // t.sisipDt(6); t.inOrderTraversal();System.out.println();
        // t.sisipDt(5); t.inOrderTraversal();System.out.println();
        // t.sisipDt(15); t.inOrderTraversal();System.out.println();
        // t.sisipDt(10); t.inOrderTraversal();System.out.println();
        // t.sisipDt(20); t.inOrderTraversal();System.out.println();
        // t.sisipDt(17); t.inOrderTraversal();System.out.println();
        // t.sisipDt(25)
        a.insert(3);
        a.insert(4);
        a.insert(6);
        a.insert(5);
        a.insert(15);
        a.insert(10);
        a.insert(20);
        a.insert(17);
        a.insert(25);
        // a.insert(80);
        // a.insert(90);
        // a.insert(40);
        // a.insert(5);
        // a.insert(55);
        // a.displayTree();
        // a.hapusAVL(60);
        a.displayTree();
    }
}
