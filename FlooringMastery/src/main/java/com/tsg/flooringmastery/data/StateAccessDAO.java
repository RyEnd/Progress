package com.tsg.flooringmastery.data;

import com.tsg.flooringmastery.model.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author ryanbmolnar@gmail.com
 */

public class StateAccessDAO {
    public final Map<Integer, State> stateInfo = new HashMap<>();
    private static final String DELIMITER = "::";

    public void loadStates() throws FileNotFoundException {

        String ORDER_FILE = "stateTax.txt";
        Scanner sc = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
        String currentLine;
        String[] currentTokens;
        Integer stateNum = 0;


        while (sc.hasNextLine()) {
            stateNum++;
            
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            State state = new State();
            state.setName(currentTokens[0]);
            state.setTax(Double.parseDouble(currentTokens[1]));
            
            stateInfo.put(stateNum, state);
        }
    }

    public String getListOfStates()
    {
        Set<Integer> keys = stateInfo.keySet();

        String string = "";
        for (Integer key : keys)
        {
            string += "\n\t" + key + ": " +stateInfo.get(key).getName();
        }
        return string;
    }
    
    public State getStateInfo(Integer state)
    {
        return stateInfo.get(state);
    }
}
