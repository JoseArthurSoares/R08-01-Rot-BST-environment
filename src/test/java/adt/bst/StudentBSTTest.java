package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bt.BTNode;

public class StudentBSTTest {

	private BSTImpl<Integer> tree;
	private BSTImpl<Integer> tree2;
	private BSTImpl<Integer> tree3;
	private BSTImpl<Integer> tree4;
	private BSTImpl<Integer> tree5;
	private SimpleBSTManipulationImpl<Integer> manip = new SimpleBSTManipulationImpl<>();
	private BTNode<Integer> NIL = new BTNode<Integer>();

	public StudentBSTTest() {
	}

	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}

		tree2.insert(2);
		tree2.insert(3);
		tree2.insert(0);
		tree2.insert(10);
		tree2.insert(100);
		tree3.insert(2);
		tree3.insert(3);
		tree3.insert(0);
		tree3.insert(10);
		tree3.insert(100);
		tree4.insert(2);
		tree4.insert(3);
		tree4.insert(0);
		tree4.insert(10);
		tree4.insert(99);
		tree5.insert(2);
		tree5.insert(3);
		tree5.insert(0);
		tree5.insert(10);
		tree5.insert(99);
		tree5.insert(100);
	}

	@Before
	public void setUp() {

		tree = new BSTImpl<>();
		tree2 = new BSTImpl<>();
		tree3 = new BSTImpl<>();
		tree4 = new BSTImpl<>();
		tree5 = new BSTImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(new Integer(-34), tree.sucessor(-40).getData());

		assertEquals(new Integer(-40), tree.predecessor(-34).getData());
		assertEquals(new Integer(0), tree.sucessor(-34).getData());

		assertEquals(new Integer(-34), tree.predecessor(0).getData());
		assertEquals(new Integer(2), tree.sucessor(0).getData());

		assertEquals(new Integer(0), tree.predecessor(2).getData());
		assertEquals(new Integer(5), tree.sucessor(2).getData());
	}

	@Test
	public void testSize() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		int size = 12;
		assertEquals(size, tree.size());

		while (!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12,
				76, 67, 232 };
		assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(4, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
	}

	@Test
	public void testRemove() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(6);
		order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(9);
		order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		assertEquals(NIL, tree.search(6));
		assertEquals(NIL, tree.search(9));

	}

	@Test
	public void testSearch() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(new Integer(-40), tree.search(-40).getData());
		assertEquals(new Integer(-34), tree.search(-34).getData());
		assertEquals(NIL, tree.search(2534));
	}

	@Test
	public void testEquals(){
		fillTree();
		//assertEquals(true, manip.equals(tree,tree));
		//assertEquals(false, manip.equals(tree2,tree4));
		//assertEquals(true, manip.equals(tree3,tree2));
		assertEquals(false, manip.equals(tree4,tree5));
	}

	@Test
	public void testSimilarity(){
		fillTree();
		assertEquals(true, manip.isSimilar(tree,tree));
		assertEquals(true, manip.isSimilar(tree2,tree4));
		assertEquals(false, manip.isSimilar(tree,tree2));
	}

	@Test
	public void testOrderStatics(){
		fillTree();
		assertEquals(new Integer(-40), manip.orderStatistic(tree, 1));
		assertEquals(new Integer(-34), manip.orderStatistic(tree, 2));
		assertEquals(new Integer(0), manip.orderStatistic(tree, 3));
		assertEquals(new Integer(2), manip.orderStatistic(tree, 4));
		assertEquals(new Integer(5), manip.orderStatistic(tree, 5));
		assertEquals(new Integer(6), manip.orderStatistic(tree, 6));
		assertEquals(new Integer(9), manip.orderStatistic(tree, 7));
		assertEquals(new Integer(12), manip.orderStatistic(tree, 8));
		assertEquals(new Integer(23), manip.orderStatistic(tree, 9));
		assertEquals(new Integer(67), manip.orderStatistic(tree, 10));
		assertEquals(new Integer(76), manip.orderStatistic(tree, 11));
		assertEquals(new Integer(232), manip.orderStatistic(tree, 12));
	}
}
