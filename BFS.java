
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nav
 */
public class BFS {
    int noOfState, noOfAction, noOfProblem;
    int[][] tt;
    String[] state;
    String[] action;
    Problem[] problem;
    Queue<Node> frontier;
    Set<Node> exploredSet;//
    Queue<Integer> actionNodeQ;


    public BFS() {
        System.out.print("Enter States Action Problems: ");
        Scanner sc = new Scanner(System.in);
        Scanner scT = new Scanner(System.in);
        scT.useDelimiter("\t");
        Scanner scS = new Scanner(System.in);
        scS.useDelimiter(" ");
        noOfState = Integer.parseInt(scS.next());
        noOfAction = Integer.parseInt(scS.next());
        noOfProblem = Integer.parseInt(scS.nextLine().trim());

        state = new String[noOfState];
        action = new String[noOfAction];
        tt = new int[noOfState][noOfAction];
        
        System.out.println("Enter States");
        for(int i = 0 ; i < noOfState ; i++)
        {
            System.out.print("State " + i + ": ");
            state[i] = scS.nextLine();
        }
        System.out.println("Enter Action");
        for(int i = 0 ; i < noOfAction ; i++)
        {
            System.out.print("Action " + i + ": ");
            action[i] = scS.nextLine();
        }
         System.out.println("Enter Problem");
        problem = new Problem[noOfProblem];
        for ( int i=0; i< noOfProblem;i=i+1)
        {
            System.out.println("Problem ");
            System.out.println(i);
            System.out.println(" ");
            String s1,s2;
            System.out.println("Enter Initial State(Starting) ");
            s1 = scT.next();
            System.out.println("Enter Goal State  ");
            s2 = scT.nextLine().trim();
            problem[i].setInitialState(s1);
            problem[i].setGoalState(s2);
        }
        
        for(int i = 0 ; i < noOfState ; i++)
        {
            for(int j = 0 ; j < noOfAction ; j++)
            {
                System.out.print("Enter transition stateNumber for state " + i + " action " + j + " : ");
                tt[i][j] = sc.nextInt();
            }
        }
         
        Node n;
        for (int i = 0; i< noOfProblem;i++)
        {
	    n = bfs(problem[i]);
	    if (n.state == -1 && n.parent == null)
	    {
                System.out.println("no solution exists");
	    }
	    else if (n.parent == null)
	    {
		System.out.println("Initial state is the goal state");
	    }
	    else
	    {
                System.out.println("PLAN");
	        while(!actionNodeQ.isEmpty())
                {
                    int a = actionNodeQ.peek();
                    System.out.println(action[a]);
                    System.out.println("-->");
                    actionNodeQ.remove();
                }
                System.out.println(action[n.action]);
	    }
	        //set and queue
	    exploredSet.clear();
	    while(!frontier.isEmpty())
            {
                frontier.remove();
            }
            while(!actionNodeQ.isEmpty())
            {
                actionNodeQ.remove();
            }
	}
    }
    
    //BREADTH FIRST SEARCH FUNCTION
    public Node bfs(Problem pro)
    {
        int iniState = -1;
        int  goalState = -1;
        for(int i =0; i< noOfState; i=i+1)
        {
            if(state[i] == pro.initialState)
            {
                iniState = i;
            }
        }
        for(int j =0; j< noOfState; j=j+1)
        {
            if(state[j] == pro.goalState)
            {
                goalState = j;
            }
        }
        Node pn = new Node();
        pn.setState(iniState);
        boolean flag = goalTest(iniState,goalState);
        if(flag == true)
        {
            return pn;
        }
        frontier.add(pn);
        while(!frontier.isEmpty())
        {
            Node parentNode = frontier.peek();
	    frontier.remove();
	    exploredSet.add(parentNode);//
	    for (int i = 0; i < noOfAction; i = i + 1)
	    {
                int childState = tt[parentNode.state][i];
                Node childNode = new Node(childState, i, (pn.getCost() + 1), pn);
		boolean temp1 = inFrontier(frontier, childNode);
		boolean temp2 = inExploredSet(exploredSet, childNode);
		if (temp1==false && temp2 == false)
		{
     		    if (goalTest(childNode.state, goalState))
		    {
		        return childNode;
		    }
		    frontier.add(childNode);
	            actionNodeQ.add(childNode.action);
		}
	    }
        }
    return pn;
   }

    public boolean inFrontier(Queue<Node> q, Node n1)
	{
	    int size = 0;
	    while (!q.isEmpty())
	    {
            	size = size + 1;
		q.remove();
	    }
	    for (int i = 0; i < size; i = i + 1)
	    {
		Node n2 = q.peek();
		if (n1.state == n2.state && n1.action == n2.action && n1.cost == n2.cost && n1.parent == n2.parent)
		{
		    return true;
		}
		q.remove();
	    }
	    return false;
	}

    public  boolean inExploredSet(Set<Node> set,Node n)
    {
        if (set.equals(n) != set.isEmpty())
        {
            return true;
	}
        else
        {
            return false;
        }
    }

    public boolean goalTest(int iniIndex,int goalIndex)
    {
        if (iniIndex == goalIndex)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}