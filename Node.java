/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nav
 */
public class Node {
    Node parent;
    int state;
    int action;
    int cost;

    public Node() {
        parent = null;
        state = -1;
        action = -1;
        cost = 0;
    }
    
    public Node(int state , int action , int cost,Node parent) 
    {
        this.parent = parent;
        this.state = state;
        this.action = action;
        this.cost = cost;
    }    
    public void setState(int s)
    {
        state =s;
    }
    public void setAction(int a)
    {
        action = a;
    }
    public void setCost(int c)
    {
        cost = c;
    }
    public int getState() 
    {
        return state;
    }

    public int getAction() 
    {
        return action;
    }

    public int getCost() 
    {
        return cost;
    }

    public Node getParent() 
    {
        return parent;
    }
}

