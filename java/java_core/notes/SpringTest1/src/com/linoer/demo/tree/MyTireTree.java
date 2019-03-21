package com.linoer.demo.tree;

class NodeTree{
    final static int MAX_SIZE = 26;
    char data;
    boolean isEnd = false;
    NodeTree[] childs;
    public NodeTree(){
        childs = new NodeTree[MAX_SIZE];
    }
}

public class MyTireTree {
    public void createMyTireTree(NodeTree treeNode, String str){
        char[] strs = str.toCharArray();
        for (int i=0; i<strs.length;i++){
            int loc = strs[i] - 'a';
            if(null == treeNode.childs[loc]){
                treeNode.childs[loc] = new NodeTree();
                treeNode.childs[loc].data = strs[i];
            }
            treeNode = treeNode.childs[loc];
        }
        treeNode.isEnd = true;
    }

    public boolean find(NodeTree treeNode, String str){
        char[] strs = str.toCharArray();
        for(int i=0; i<strs.length; i++){
            int loc = strs[i] - 'a';
            if(null != treeNode.childs[loc]){
                treeNode = treeNode.childs[loc];
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyTireTree myTireTree = new MyTireTree();
        NodeTree nodeTree = new NodeTree();
        String[] str = {"java", "php", "python"};
        for(String s: str){
            myTireTree.createMyTireTree(nodeTree, s);
        }
        boolean isFind = myTireTree.find(nodeTree, "av");
        System.out.println(isFind);
    }
}
