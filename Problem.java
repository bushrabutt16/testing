/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nav
 */
public class Problem {
    String initialState;
    String goalState;

    Problem()
    {
        initialState = "";
        goalState = "";
    }
    
    Problem(String is, String gs)
    {
        initialState = is;
        goalState = gs;
    }
    public void setInitialState(String is)
    {
        initialState = is;
    }
    public void setGoalState(String gs)
    {
        goalState = gs;
    }

}
