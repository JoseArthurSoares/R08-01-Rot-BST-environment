package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		for (int i : array){
			tree.insert(i);
		}
		return calculateFloor(tree.getRoot(), numero);
	}

	private Integer calculateFloor(BSTNode<Integer> node, double numero) {
		if (node.isEmpty()) return null;
		else if (node.getData() == numero) return node.getData();
		else if (node.getData() > numero){
			return calculateFloor((BSTNode<Integer>) node.getLeft(), numero);
		} else {
			if (node.getRight().getData() > numero){

			}
		}
		return null;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		//TODO Implemente seu codigo aqui
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
