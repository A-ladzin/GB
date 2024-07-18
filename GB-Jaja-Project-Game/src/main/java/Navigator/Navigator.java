package Navigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Navigator {

    static public Stack<int[]> runawayRoute(int[][]field, int currentX,int currentY, int enemyX, int enemyY){
        int[][] costMap = new int[field.length+2][field[0].length+2];

        for(int i = 0; i < costMap[0].length; i++)
        {
            costMap[0][i] = -1;
            costMap[costMap.length-1][i] = -1;
        }
        for(int i = 0; i < costMap.length; i++){
            costMap[i][0] = -1;
            costMap[i][costMap[i].length-1] = -1;
        }
        for(int i = 0; i < field.length;i++){
            for(int j = 0; j < field[0].length;j++)
            {
                if(field[i][j] != 0){
                    costMap[i+1][j+1] = -1;
                }}
        }

        int targetX = enemyX;
        int targetY = enemyY;
        enemyX++;
        enemyY++;
        costMap[enemyX][enemyY] = 2;

        buildPath(field, costMap, new int[]{enemyX,enemyY}, new ArrayList<int[]>());

        Stack<int[]> result = new Stack<>();
        int maxValue = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (costMap[i+1][j+1] > maxValue){
                    targetX = i;
                    targetY = j;
                    maxValue = costMap[i+1][j+1];
                }
            }

        }
        costMap = new int[field.length+2][field[0].length+2];

        for(int i = 0; i < costMap[0].length; i++)
        {
            costMap[0][i] = -1;
            costMap[costMap.length-1][i] = -1;
        }
        for(int i = 0; i < costMap.length; i++){
            costMap[i][0] = -1;
            costMap[i][costMap[i].length-1] = -1;
        }
        for(int i = 0; i < field.length;i++){
            for(int j = 0; j < field[0].length;j++)
            {
                if(field[i][j] != 0){
                    costMap[i+1][j+1] = -1;
                }}
        }



        currentX++;
        currentY++;
        costMap[currentX][currentY] = 2;
        buildPath(field, costMap, new int[]{currentX,currentY}, new ArrayList<int[]>());

        int[] current = new int[]{targetX+1,targetY+1};
        result.push(current);

//        while(current[0]!=currentX || current[1] != currentY){
//            int[] next = new int[]{current[0],current[1]+1};
//            int adjacent = costMap[current[0]][current[1]+1] == -1?Integer.MAX_VALUE:costMap[current[0]][current[1]+1];
//            if (costMap[current[0]+1][current[1]] < adjacent && costMap[current[0]+1][current[1]] != -1){
//                adjacent = costMap[current[0]+1][current[1]];
//                next = new int[]{current[0]+1,current[1]};
//            }
//            if (costMap[current[0]-1][current[1]] < adjacent && costMap[current[0]-1][current[1]] != -1){
//                adjacent = costMap[current[0]-1][current[1]];
//                next = new int[]{current[0]-1,current[1]};
//            }
//            if (costMap[current[0]][current[1]-1] < adjacent && costMap[current[0]][current[1]-1] != -1){
//                adjacent = costMap[current[0]][current[1]-1];
//                next = new int[]{current[0],current[1]-1};
//            }
//            current = next.clone();
//            result.push(next);
//
//
//        }
        if(Math.abs(currentX - targetX) > Math.abs(currentY-targetY)) {
            while (current[0] != currentX || current[1] != currentY) {
                int[] next = new int[]{current[0], current[1] + 1};
                int adjacent = costMap[current[0]][current[1] + 1] == -1 ? Integer.MAX_VALUE : costMap[current[0]][current[1] + 1];
                if (costMap[current[0]][current[1] - 1] < adjacent &&  costMap[current[0]][current[1] - 1] != -1) {
                    adjacent = costMap[current[0]][current[1] - 1];
                    next = new int[]{current[0], current[1] - 1};
                }
                if (costMap[current[0] + 1][current[1]] < adjacent && costMap[current[0] + 1][current[1]] != -1) {
                    adjacent = costMap[current[0] + 1][current[1]];
                    next = new int[]{current[0] + 1, current[1]};
                }
                if (costMap[current[0] - 1][current[1]] < adjacent && costMap[current[0] - 1][current[1]] != -1) {
                    adjacent = costMap[current[0] - 1][current[1]];
                    next = new int[]{current[0] - 1, current[1]};
                }
                if (adjacent > (costMap[current[0]][current[1]] - 1) && costMap[current[0]][current[1]] != -1) return null;
                current = next.clone();
                result.push(next);
            }
        }
        else {
            while (current[0] != currentX || current[1] != currentY) {
                int[] next = new int[]{current[0] + 1, current[1]};
                int adjacent = costMap[current[0] + 1][current[1]] == -1 ? Integer.MAX_VALUE : costMap[current[0] + 1][current[1]];

                if (costMap[current[0] - 1][current[1]] < adjacent && costMap[current[0] - 1][current[1]] != -1) {
                    adjacent = costMap[current[0] - 1][current[1]];
                    next = new int[]{current[0] - 1, current[1]};
                }
                if (costMap[current[0]][current[1] + 1] < adjacent && costMap[current[0]][current[1] + 1] != -1) {
                    adjacent = costMap[current[0]][current[1] + 1];
                    next = new int[]{current[0], current[1] + 1};
                }
                if (costMap[current[0]][current[1] - 1] < adjacent && costMap[current[0]][current[1] - 1] != -1) {
                    adjacent = costMap[current[0]][current[1] - 1];
                    next = new int[]{current[0], current[1] - 1};
                }

                if (adjacent > (costMap[current[0]][current[1]] - 1) && costMap[current[0]][current[1]] != -1) return null;
                current = next.clone();
                result.push(next);
            }
        }

        return result;



    }
    static public Stack<int[]> findPath(int[][]field, int currentX,int currentY,int targetX,int targetY){
        int[][] costMap = new int[field.length+2][field[0].length+2];
        
        for(int i = 0; i < costMap[0].length; i++)
        {
            costMap[0][i] = -1;
            costMap[costMap.length-1][i] = -1;
        }
        for(int i = 0; i < costMap.length; i++){
            costMap[i][0] = -1;
            costMap[i][costMap[i].length-1] = -1;
        }
        for(int i = 0; i < field.length;i++){
            for(int j = 0; j < field[0].length;j++)
        { 
            if(field[i][j] != 0){
                costMap[i+1][j+1] = -1;
        }}
        }
        costMap[targetX+1][targetY+1] = 0;

        currentX++;
        currentY++;
        costMap[currentX][currentY] = 2;
        buildPath(field, costMap, new int[]{currentX,currentY}, new ArrayList<int[]>());
        if (costMap[targetX+1][targetY+1] == 0) {return null;}
        costMap[targetX+1][targetY+1] = -1;
        
        Stack<int[]> result = new Stack<>();
        int[] current = new int[]{targetX+1,targetY+1};
        result.push(current);
        
//        while(current[0]!=currentX || current[1] != currentY){
//            int[] next = new int[]{current[0],current[1]+1};
//            int adjacent = costMap[current[0]][current[1]+1] == -1?Integer.MAX_VALUE:costMap[current[0]][current[1]+1];
//            if (costMap[current[0]+1][current[1]] < adjacent && costMap[current[0]+1][current[1]] != -1){
//                adjacent = costMap[current[0]+1][current[1]];
//                next = new int[]{current[0]+1,current[1]};
//            }
//            if (costMap[current[0]-1][current[1]] < adjacent && costMap[current[0]-1][current[1]] != -1){
//                adjacent = costMap[current[0]-1][current[1]];
//                next = new int[]{current[0]-1,current[1]};
//            }
//            if (costMap[current[0]][current[1]-1] < adjacent && costMap[current[0]][current[1]-1] != -1){
//                adjacent = costMap[current[0]][current[1]-1];
//                next = new int[]{current[0],current[1]-1};
//            }
//            current = next.clone();
//            result.push(next);
//
//
//        }
        if(Math.abs(currentX - targetX) > Math.abs(currentY-targetY)) {
            while (current[0] != currentX || current[1] != currentY) {
                int[] next = new int[]{current[0], current[1] + 1};
                int adjacent = costMap[current[0]][current[1] + 1] == -1 ? Integer.MAX_VALUE : costMap[current[0]][current[1] + 1];
                if (costMap[current[0]][current[1] - 1] < adjacent && costMap[current[0]][current[1] - 1] != -1) {
                    adjacent = costMap[current[0]][current[1] - 1];
                    next = new int[]{current[0], current[1] - 1};
                }
                if (costMap[current[0] + 1][current[1]] < adjacent && costMap[current[0] + 1][current[1]] != -1) {
                    adjacent = costMap[current[0] + 1][current[1]];
                    next = new int[]{current[0] + 1, current[1]};
                }
                if (costMap[current[0] - 1][current[1]] < adjacent && costMap[current[0] - 1][current[1]] != -1) {
                    adjacent = costMap[current[0] - 1][current[1]];
                    next = new int[]{current[0] - 1, current[1]};
                }
                if (adjacent > (costMap[current[0]][current[1]] - 1) && costMap[current[0]][current[1]] != -1) return null;
                current = next.clone();
                result.push(next);
            }
        }
        else {
            while (current[0] != currentX || current[1] != currentY) {
                int[] next = new int[]{current[0] + 1, current[1]};
                int adjacent = costMap[current[0] + 1][current[1]] == -1 ? Integer.MAX_VALUE : costMap[current[0] + 1][current[1]];

                if (costMap[current[0] - 1][current[1]] < adjacent && costMap[current[0] - 1][current[1]] != -1) {
                    adjacent = costMap[current[0] - 1][current[1]];
                    next = new int[]{current[0] - 1, current[1]};
                }
                if (costMap[current[0]][current[1] + 1] < adjacent && costMap[current[0]][current[1] + 1] != -1) {
                    adjacent = costMap[current[0]][current[1] + 1];
                    next = new int[]{current[0], current[1] + 1};
                }
                if (costMap[current[0]][current[1] - 1] < adjacent && costMap[current[0]][current[1] - 1] != -1) {
                    adjacent = costMap[current[0]][current[1] - 1];
                    next = new int[]{current[0], current[1] - 1};
                }
                if (adjacent > (costMap[current[0]][current[1]] - 1) && costMap[current[0]][current[1]] != -1) return null;
                current = next.clone();
                result.push(next);
            }
        }


        return result;


        

    }
    static private void buildPath(int[][]field,int[][]costMap,int[] curr, ArrayList<int[]>path)
    {
        path = (ArrayList<int[]>)path.clone();
        int[] current = curr.clone();
        if (costMap[current[0]][current[1]] == -1) return;
        // if (field[current[0]-1][current[1]-1]!=0){
        //     costMap[current[0]][current[1]] = -1;
        //     return;
        // };
        
        for(int[] i:path){
            if (i.toString()==curr.toString()){
                return;
            }
        }

        int currentValue = costMap[current[0]][current[1]]+1;
        path.add(current);
        

        if (costMap[current[0]-1][current[1]]>currentValue || costMap[current[0]-1][current[1]] == 0){
            costMap[current[0]-1][current[1]] = currentValue;
            buildPath(field,costMap, new int[] {current[0]-1, current[1]},path);

        }
        if (costMap[current[0]][current[1]+1]>currentValue || costMap[current[0]][current[1]+1] == 0){
            costMap[current[0]][current[1]+1] = currentValue;
            buildPath(field,costMap, new int[] {current[0], current[1]+1},path);
        }
        if (costMap[current[0]][current[1]-1]>currentValue || costMap[current[0]][current[1]-1] == 0){
            costMap[current[0]][current[1]-1] = currentValue;
            buildPath(field,costMap, new int[] {current[0], current[1]-1},path);

        }
        if (costMap[current[0]+1][current[1]]>currentValue || costMap[current[0]+1][current[1]] == 0){
            costMap[current[0]+1][current[1]] = currentValue;
           
            buildPath(field,costMap, new int[] {current[0]+1, current[1]},path);
        }

        
    }
}