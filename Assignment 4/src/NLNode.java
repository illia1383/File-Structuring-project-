/**
 * March 24th 2023
 * Author @Illia Lotfalian
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class NLNode<T> {

    private NLNode<T> parent; //Parent node
    private ListNodes<NLNode<T>> children; //reference to a list sorting the children of ndoe

    private T data;

    //Constructors

    public NLNode(){
        parent = null; // Parent set to null
        data = null; // data set to null
        ListNodes<NLNode<T>> children = new ListNodes<NLNode<T>>(); // Intialized to an empty object
    }

    public NLNode(T d, NLNode<T> p){
        parent = p;
        data = d;
        children = new ListNodes<NLNode<T>>();
    }
    //Getters and setters
    public void setParent(NLNode<T> p){
        this.parent = p;
    }
    public NLNode<T> getParent(){
        return parent;
    }
    public void addChild(NLNode<T> newChild){
        newChild.setParent(this);
        if(children!=null) {
            children.add(newChild);
        }
    }
    public Iterator<NLNode<T>> getChildren(){
        return children.getList(); // This is  how to convert it to an iterator.


    }
    public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter){

        return children.sortedList(sorter); // Sorting the iterator 
    }
    public T getData() {
        return data;
    }
    public void setData(T d){
        this.data = d;
    }
}