#include<iostream>
#include<string>
#include<queue>
#include<set>
using namespace std;

// NODE CLASS
class Node{

public:
    Node *parent;
    int state;
    int cost;
    int action;

    Node(int s = -1,int a = -1,int c = 0,Node *n = NULL)
    {
        parent = NULL;
        state= s;
        cost = c;
        action = a;
    }
};

// PROBLEM CLASS
class Problem{

public:
    string initialState;
    string goalState;

    Problem(string is="", string gs="")
    {
        initialState = is;
        goalState = gs;
    }
    void setInitialState(string is)
    {
        initialState = is;
    }
    void setGoalState(string gs)
    {
        goalState = gs;
    }
};

//BFS CLASS
class BFS{

public:
    int noOfState, noOfAction, noOfProblem;
    string *state,*action;
    int **tt;
    Problem *problem;
	queue<Node> frontier;
	set<Node> exploredSet;//
	queue<int> actionNodeQ;
	queue<int> resultQ;

	BFS()
	{
	    cout << "Enter No of States: ";
        cin >> noOfState;
        cout << "\nEnter No of Actions: ";
        cin >> noOfAction;
        cout << "\nEnter No of Problems: ";
        cin >> noOfProblem;

        state = new string[noOfState];
        cout << "\nEnter States"<< endl;
        for ( int i=0; i< noOfState;i=i+1)
        {
            cin >> state[i];
        }

        action = new string [noOfAction];
        cout << "\nEnter Actions"<< endl;
        for ( int i=0; i< noOfAction;i=i+1)
        {
            cin >> action[i];
        }

        tt = new int*[noOfState];
        for(int i=0; i<noOfState; i=i+1)
        {
            tt[i] = new int[noOfAction];
        }
        cout << "\nEnter Transition Table" << endl;
        for(int i=0; i<noOfState; i=i+1)
        {
            for(int j=0; j<noOfAction; j=j+1)
            {
                cin >> tt[i][j];
            }
        }

        problem = new Problem[noOfProblem];
        for ( int i=0; i< noOfProblem;i=i+1)
        {
            cout << "\nProblem [" << i << "] " << endl;
            string s1,s2;
            cout << "Enter Initial State(Starting) ";
            cin >> s1;
            cout << "Enter Goal State ";
            cin >> s2;
            problem[i].setInitialState(s1);
            problem[i].setGoalState(s2);
        }

        Node n;
		for (int i = 0; i < noOfProblem; i = i + 1)
		{
			n = bfs(problem[i]);

			if (n.state == -1 && n.parent == NULL)
			{
				cout << "no solution exists" << endl;
			}
			else if (n.parent == NULL)
			{
				cout << "Initial state is the goal state" << endl;
			}
			else
			{
			    while(!actionNodeQ.empty())
                {
                    int a = actionNodeQ.front();
                    resultQ.push(a);
                    actionNodeQ.pop();
                }
                cout << "PLAN" << endl;
                while(!resultQ.empty())
                {
                    int a = resultQ.front();
                    cout << action[a] << "->";
                    resultQ.pop();
                }
                cout << action[n.action] << endl;
			}
			//set and queue
			exploredSet.clear();
			while(!frontier.empty())
            {
                frontier.pop();
            }
            while(!actionNodeQ.empty())
            {
                actionNodeQ.pop();
            }
            while(!resultQ.empty())
            {
                resultQ.pop();
            }
		}
	}

    //BREADTH FIRST SEARCH FUNCTION
    Node bfs(Problem pro)
    {
        int iniState , goalState;
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
        Node parentNode(iniState);//
        if(goalTest(iniState,goalState))
        {
            return parentNode;
        }
        frontier.push(parentNode);
        while(!frontier.empty())
        {
            Node parentNode = frontier.front();
			frontier.pop();
			exploredSet.insert(parentNode);//
			for (int i = 0; i < noOfAction; i = i + 1)
			{
                int childState = tt[parentNode.state][i];
                Node childNode(childState, i, (parentNode.cost + 1), &parentNode);
				bool temp1 = inFrontier(frontier, childNode);
				bool temp2 = inExploredSet(exploredSet, childNode);

				if (temp1==false && temp2 == false)
				{
					if (goalTest(childNode.state, goalState))
					{
						return childNode;
					}
					frontier.push(childNode);
					actionNodeQ.push(childNode.action);
				}
			}
        }
		return parentNode;
   }

   bool inFrontier(queue<Node> q, Node n1)
	{
	    int size;
		while (!q.empty())
		{
			size = size + 1;
			q.pop();
		}
		for (int i = 0; i < size; i = i + 1)
		{
			Node n2 = q.front();
			if (n1.state == n2.state && n1.action == n2.action && n1.cost == n2.cost && n1.parent == n2.parent)
			{
				return true;
			}
			q.pop();
		}
		return false;
	}

	bool inExploredSet(set<Node> set,Node n)
	{
		if (set.find(n) != set.end())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

    bool goalTest(int iniIndex,int goalIndex)
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
};
//DRIVER
int main()
{
    BFS bfs;
    return 0;
}
