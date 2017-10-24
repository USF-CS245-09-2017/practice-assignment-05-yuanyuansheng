import java.util.ArrayList;

public class BSTree {

    public TreeNode root;

    public BSTree() {
        this.root = null;
    }

    public void insert(String s) {
        TreeNode newNode = new TreeNode(s);
        TreeNode current = root;
        TreeNode parent  = null;
        if (current == null) {
            root = newNode;
            return;
        }
        while (true) {
            parent = current;
            if (s.compareTo(current.value) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public boolean find(String s) {
        TreeNode current = root;
        while (current != null
                && !s.equals(current.value)) {
            if (s.compareTo(current.value) < 0)
                current = current.left;
            else
                current = current.right;
        }
        if (current == null)
            return false;
        return true;
    }

    public void delete(String s) {
        TreeNode parent  = root;
        TreeNode current = root;
        boolean isLeftChild = false;

        while (!current.value.equals(s)) {
            parent = current;
            if (current.value.compareTo(s) > 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }

            if (current == null) {
                return;
            }
        }

        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }

        }
        else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            TreeNode successor = getDeleteSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
    }

    private TreeNode getDeleteSuccessor(TreeNode deleteNode) {
        TreeNode successor = null;
        TreeNode successorParent = null;
        TreeNode current = deleteNode.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }

        return successor;
    }

    public String toStringInOrder() {
        ArrayList<String> result = new ArrayList<String>();
        inOrder(root, result);
        String str = "";
        for (String s: result) {
            if (!str.equals(""))
                str += " ";
            str += s;
        }
        return str;
    }

    private void inOrder(TreeNode root, ArrayList<String> result) {
        if(root == null) {
            return;
        }

        inOrder(root.left, result);
        result.add(root.value);
        inOrder(root.right, result);
    }

    public String toStringPreOrder() {
        ArrayList<String> result = new ArrayList<String>();
        preOrder(root, result);
        String str = "";
        for (String s: result) {
            if (!str.equals(""))
                str += " ";
            str += s;
        }
        return str;
    }

    private void preOrder(TreeNode root, ArrayList<String> result) {
        if(root == null) {
            return;
        }
        result.add(root.value);
        preOrder(root.left, result);
        preOrder(root.right, result);
    }
}

class TreeNode {
    String value;
    TreeNode left;
    TreeNode right;

    public TreeNode(String value) {
        this.value = value;
        left  = null;
        right = null;
    }
}