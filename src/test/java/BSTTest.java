import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ubuntu-master on 22.04.17.
 */
public class BSTTest {
    @Test
    public void insert() throws Exception {
        BST bst = builtExampleBST();

        assertEquals(bst.findNode(21).data, 21);

        assertEquals(bst.checkBST(), true);

        assertEquals(bst.findNode(13).data, 13);
        assertEquals(bst.checkBST(), true);

        assertEquals(bst.findNode(2).data, 2);
        assertEquals(bst.checkBST(), true);

        assertEquals(bst.findNode(3).data, 3);
        assertEquals(bst.checkBST(), true);

        assertEquals(bst.findNode(1).data, 1);
        assertEquals(bst.checkBST(), true);

        assertEquals(bst.findNode(-10).data, -10);
        assertEquals(bst.checkBST(), true);

        assertEquals(bst.findNode(64).data, 64);
        assertEquals(bst.checkBST(), true);

        assertEquals(bst.findNode(23).data, 23);
        assertEquals(bst.checkBST(), true);

        assertEquals(bst.findNode(25).data, 25);
        assertEquals(bst.checkBST(), true);

        assertEquals(bst.findNode(24).data, 24);
        assertEquals(bst.checkBST(), true);
    }




    @Test
    public void findRootNodeTest() throws Exception {
        BST bst = builtExampleBST();

        assertEquals(bst.findNode(21).data, 21);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(21).parent, null);
        assertEquals(bst.findNode(21).left, bst.getRoot().left);
        assertEquals(bst.findNode(21).right, bst.getRoot().right);
    }

    @Test
    public void findNotExistNodeTest() throws Exception {
        BST bst = builtExampleBST();
        assertEquals(bst.findNode(9), null);
    }

    @Test
    public void findRepeatNodeTest() throws Exception {
        BST bst = builtExampleBST();

        assertEquals(bst.findNode(13).data, 13);
        assertEquals(bst.findNode(13).left, bst.getRoot().left.left);
        assertEquals(bst.findNode(13).right, bst.getRoot().left.right);
        assertEquals(bst.findNode(13).parent, bst.getRoot());

        assertEquals(bst.findNode(13).right.data, 13);
        assertEquals(bst.findNode(13).right.left, null);
        assertEquals(bst.findNode(13).right.right, bst.getRoot().left.right.right);
        assertEquals(bst.findNode(13).right.parent, bst.getRoot().left);

    }

    @Test
    public void findNodeWithoutChildrenTest() throws Exception {
        BST bst = builtExampleBST();

        assertEquals(bst.findNode(-5).data, -5);
        assertEquals(bst.findNode(-5).left, null);
        assertEquals(bst.findNode(-5).right, null);
        assertEquals(bst.findNode(-5).parent, bst.getRoot().left.left.left.left);

    }

    @Test
    public void findBothChildrenNodeTest() throws Exception {
        BST bst = builtExampleBST();

        assertEquals(bst.findNode(2).data, 2);
        assertEquals(bst.findNode(2).left, bst.getRoot().left.left.left);
        assertEquals(bst.findNode(2).right, bst.getRoot().left.left.right);
        assertEquals(bst.findNode(2).parent, bst.getRoot().left);
    }


    @Test
    public void findOneChildNodeTest() throws Exception {
        BST bst = builtExampleBST();

        assertEquals(bst.findNode(23).data, 23);
        assertEquals(bst.findNode(23).left, null);
        assertEquals(bst.findNode(23).right, bst.getRoot().right.left.right);
        assertEquals(bst.findNode(23).parent, bst.getRoot().right);


    }







    @Test
    public void getMaxNodeTest(){
        BST bst = builtExampleBST();
        assertEquals(bst.getMaxNode().data, 130);
    }

    @Test
    public void getMinNodeTest(){
        BST bst = builtExampleBST();
        assertEquals(bst.getMinNode().data, -20);
    }




    @Test
    public void deleteNodeWithoutChildrenTest(){
        BST bst = builtExampleBST();
        assertEquals(bst.checkBST(), true);
        bst.delete(-5);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(-5), null);
    }

    @Test
    public void deleteRootWithoutChildren(){
        BST bst = new BST();
        bst.insert(5);
        assertEquals(bst.checkBST(), true);
        bst.delete(5);
        assertEquals(bst.getRoot(), null);
    }

    @Test
    public void deleteNodeWithoutLeftChildTest(){
        BST bst = builtExampleBST();

        assertEquals(bst.checkBST(), true);
        bst.delete(23);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(23), null);
        assertEquals(bst.findNode(64).left, bst.findNode(25));
        assertEquals(bst.findNode(25).left, bst.findNode(24));
        assertEquals(bst.findNode(25).right, null);
        assertEquals(bst.findNode(25).parent, bst.findNode(64));


    }

    @Test
    public void deleteNodeWithoutRightChildTest(){
        BST bst = builtExampleBST();

        assertEquals(bst.checkBST(), true);
        bst.delete(1);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(1), null);
        assertEquals(bst.findNode(2).left, bst.findNode(-10));
        assertEquals(bst.findNode(2).right, bst.findNode(3));
        assertEquals(bst.findNode(-10).parent, bst.findNode(2));
    }

    @Test
    public void deleteNodeWithOneChildTest(){
        BST bst = new BST();


        bst.insert(7);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.getRoot(), bst.findNode(7));
        assertEquals(bst.getRoot().parent, null );
        assertEquals(bst.getRoot().left, null );
        assertEquals(bst.getRoot().right, null );
        bst.insert(3);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.getRoot().left, bst.findNode(3));
        assertEquals(bst.getRoot().right, null );
        bst.delete(3);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.getRoot(), bst.findNode(7));
        assertEquals(bst.getRoot().parent, null );
        assertEquals(bst.getRoot().left, null );
        assertEquals(bst.getRoot().right, null );
        bst.insert(15);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.getRoot().right, bst.findNode(15));
        assertEquals(bst.getRoot().left, null );
        bst.delete(15);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.getRoot(), bst.findNode(7));
        assertEquals(bst.getRoot().parent, null );
        assertEquals(bst.getRoot().left, null );
        assertEquals(bst.getRoot().right, null );
    }

    @Test
    public void deleteNodeWithBothChildrenTest(){
        BST bst = builtExampleBST();

        assertEquals(bst.checkBST(), true);
        bst.delete(2);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(2), null);
        assertEquals(bst.findNode(3).left, bst.findNode(1));
        assertEquals(bst.findNode(3).right, null);
        assertEquals(bst.findNode(3).parent, bst.findNode(13));
        assertEquals(bst.findNode(1).parent, bst.findNode(3));
        assertEquals(bst.findNode(13).left, bst.findNode(3));

        bst = builtExampleBST();
        assertEquals(bst.checkBST(), true);
        bst.delete(100);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(100), null);
        assertEquals(bst.findNode(64).right, bst.findNode(115));
        assertEquals(bst.findNode(64).left, bst.findNode(23));
        assertEquals(bst.findNode(115).right, bst.findNode(120));
        assertEquals(bst.findNode(115).left, bst.findNode(75));
        assertEquals(bst.findNode(115).parent, bst.findNode(64));
        assertEquals(bst.findNode(75).parent, bst.findNode(115));
        assertEquals(bst.findNode(120).parent, bst.findNode(115));
        assertEquals(bst.findNode(120).left, null);

        bst = builtExampleBST();
        bst.delete(120);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(120), null);
        assertEquals(bst.findNode(130).left, bst.findNode(115));
        assertEquals(bst.findNode(130).right, null);
        assertEquals(bst.findNode(100).right, bst.findNode(130));
        assertEquals(bst.findNode(130).parent, bst.findNode(100));

        bst = builtExampleBST();
        bst.delete(13);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(13), bst.getRoot().left);
        assertEquals(bst.findNode(13).left, bst.findNode(2));
        assertEquals(bst.findNode(13).right, bst.getRoot().left.right);
        assertEquals(bst.findNode(13), bst.findNode(13).left.parent);
        assertEquals(bst.findNode(13), bst.findNode(13).right.parent);
        assertEquals(bst.findNode(21).left, bst.findNode(13));
        bst.delete(13);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(13), bst.getRoot().left);
        assertEquals(bst.findNode(13).left, bst.findNode(2));
        assertEquals(bst.findNode(13).right, bst.getRoot().left.right);
    }

    @Test
    public void deleteRootTest(){
        BST bst = builtExampleBST();

        bst.delete(21);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(21), null);
        assertEquals(bst.getRoot(), bst.findNode(23));
        assertEquals(bst.findNode(23).left, bst.findNode(13));
        assertEquals(bst.findNode(23).parent, null);
        assertEquals(bst.findNode(23).right, bst.findNode(64));
        assertEquals(bst.findNode(64).left, bst.findNode(25));
        assertEquals(bst.findNode(25).parent, bst.findNode(64));
        assertEquals(bst.findNode(64).parent, bst.findNode(23));
        assertEquals(bst.findNode(13).parent, bst.findNode(23));

        bst.delete(23);
        assertEquals(bst.checkBST(), true);
        assertEquals(bst.findNode(23), null);
        assertEquals(bst.getRoot(), bst.findNode(24));
        assertEquals(bst.findNode(24).left, bst.findNode(13));
        assertEquals(bst.findNode(24).parent, null);
        assertEquals(bst.findNode(24).right, bst.findNode(64));
        assertEquals(bst.findNode(64).left, bst.findNode(25));
        assertEquals(bst.findNode(25).parent, bst.findNode(64));
        assertEquals(bst.findNode(25).left, null);
        assertEquals(bst.findNode(64).parent, bst.findNode(24));
        assertEquals(bst.findNode(13).parent, bst.findNode(24));


    }



    @Test
    public void findTest(){
        BST bst = builtExampleBST();
        assertEquals(bst.find(-10), 1);
        assertEquals(bst.find(1), 1);
        assertEquals(bst.find(24), 1);
        assertEquals(bst.find(23), 1);
        assertEquals(bst.find(21), 1);
        assertEquals(bst.find(64), 1);

        assertEquals(bst.find(-11), 0);
        assertEquals(bst.find(6), 0);
        assertEquals(bst.find(7), 0);
        assertEquals(bst.find(99), 0);
    }

    @Test
    public void insertTest(){
        BST bst = new BST();
        assertEquals(bst.checkBST(), true);


        assertEquals(bst.checkBST(), true);
        bst.insert(21);
        assertEquals(bst.checkBST(), true);
        bst.insert(13);
        assertEquals(bst.checkBST(), true);
        bst.insert(64);
        assertEquals(bst.checkBST(), true);
        bst.insert(23);
        assertEquals(bst.checkBST(), true);
        bst.insert(2);
        assertEquals(bst.checkBST(), true);
        bst.insert(3);
        assertEquals(bst.checkBST(), true);
        bst.insert(13);
        assertEquals(bst.checkBST(), true);
        bst.insert(1);
        assertEquals(bst.checkBST(), true);
        bst.insert(25);
        assertEquals(bst.checkBST(), true);
        bst.insert(-10);
        assertEquals(bst.checkBST(), true);
        bst.insert(-5);
        assertEquals(bst.checkBST(), true);
        bst.insert(-20);
        assertEquals(bst.checkBST(), true);
        bst.insert(24);
        assertEquals(bst.checkBST(), true);
        bst.insert(13);
        assertEquals(bst.checkBST(), true);
        bst.insert(100);
        assertEquals(bst.checkBST(), true);
        bst.insert(120);
        assertEquals(bst.checkBST(), true);
        bst.insert(130);
        assertEquals(bst.checkBST(), true);
        bst.insert(115);
        assertEquals(bst.checkBST(), true);
        bst.insert(75);
        assertEquals(bst.checkBST(), true);
        bst.insert(67);
        assertEquals(bst.checkBST(), true);
        bst.insert(87);
        assertEquals(bst.checkBST(), true);
    }

    private BST builtExampleBST(){
        BST bst = new BST();

        bst.insert(21);
        bst.insert(13);
        bst.insert(64);
        bst.insert(23);
        bst.insert(2);
        bst.insert(3);
        bst.insert(13);
        bst.insert(1);
        bst.insert(25);
        bst.insert(-10);
        bst.insert(-5);
        bst.insert(-20);
        bst.insert(24);
        bst.insert(13);
        bst.insert(100);
        bst.insert(120);
        bst.insert(130);
        bst.insert(115);
        bst.insert(75);
        bst.insert(67);
        bst.insert(87);
        return bst;
    }

}