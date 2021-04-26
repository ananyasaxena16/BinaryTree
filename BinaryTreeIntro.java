import java.util.Stack;
public class BinaryTreeIntro{
    public static class Node{
        int data;
        Node left;
        Node right;
 
        Node(int data,Node left,Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public static class Pair{
        Node node;
        int state;
 
        Pair(Node node,int state){
            this.node = node;
            this.state = state;
        }
    }
    public static Node construct(Integer inp[]){
        Stack<Pair> st = new Stack<>();
 
        Node root = new Node(inp[0],null,null);
        st.push(new Pair(root,0));
        int idx = 1;
        while(st.size() > 0){
            Pair top = st.peek();
            if(top.state == 0){
                Integer ele = inp[idx];
                if(ele != null){
                    Node newNode = new Node(ele,null,null);
                    top.node.left = newNode;
                    st.push(new Pair(newNode,0));
                }
                idx++;
                top.state++;
            }else if(top.state == 1){
                Integer ele = inp[idx];
                if(ele != null){
                    Node newNode = new Node(ele,null,null);
                    top.node.right = newNode;
                    st.push(new Pair(newNode,0));
                }
                idx++;
                top.state++;
            }else{
                st.pop();
            }
        }        
        return root;
    }
    public static void display(Node node){
        if(node == null){
            return;
        }
        String str = "";
        str += node.left != null ? node.left.data : ".";
        str += " <- "+node.data+" -> ";
        str += node.right != null ? node.right.data : ".";
        System.out.println(str);
        display(node.left);
        display(node.right);
    }
    public static int size(Node node){
        if(node==null){
            return 0;
        }else{
            return size(node.left) + size(node.right) + 1;
        }
    }
    public static int sum(Node node){
        if(node==null){
            return 0;
        }else{
            return sum(node.left) + sum(node.right) + node.data;
        }
    }
    public static int max(Node node){
        if(node==null){
            return 0;
        }else{
            return Math.max(max(node.left), Math.max(max(node.right), node.data));
        }
    }
    public static int height(Node node){
        if(node==null){
            return -1;
        }else{
            return Math.max(height(node.left),height(node.right)) + 1;
        }
    }
    
    
    //Traversals
    public static void Preorder(Node node){
        if(node==null){
            return;
        }
        System.out.println(node.data);
        Preorder(node.left);
        Preorder(node.right);
        
    }
    public static void Inorder(Node node){
        if(node==null){
            return;
        }
        
        Inorder(node.left);
        System.out.println(node.data);
        Inorder(node.right);
        
    }
    public static void Postorder(Node node){
        if(node==null){
            return;
        }
       
        Postorder(node.left); 
        Postorder(node.right);
        System.out.println(node.data);
        
    }
    //Iterative Traversals
    public static void IterativeTraversal(Node node){
        Stack<Pair> st = new Stack<>();
    
    st.push(new Pair(node,1));
    String pre = "" , in = "" , post = "";
    while(st.size() > 0){
        Pair top = st.peek();
        
        if(top.state == 1){
            pre += top.node.data+" ";

            if(top.node.left == null){
                top.state++;
            }else{
                st.push(new Pair(top.node.left , 1));
                top.state++;
            }
        }else if(top.state == 2){
            in += top.node.data +" ";
            
            if(top.node.right == null){
                top.state++;
            }else{
                st.push(new Pair(top.node.right , 1));
                top.state++;
            }
        }else if(top.state == 3){
            post += top.node.data+" ";
            st.pop();
        }
    }
    System.out.println(pre + "+" in + "+"+ post);
 }
        

    public static void main(String[] args) {
        Integer inp[] = {10,20,40,null,null,50,80,null,null,null,30,60,null,90,null,null,70,null,null};
        Node root = construct(inp);
        display(root);
        System.out.println(max(root));
        System.out.println(size(root));
        System.out.println(sum(root));
        System.out.println(height(root));
        Preorder(root);
        Inorder(root);
        Postorder(root);
        IterativeTraversal(root);



    }
}
