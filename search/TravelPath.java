package search;

import java.util.*;

public class TravelPath {
    public static void main(String[] args) {
//        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets = {{"ICN", "SFO"},  {"ICN", "ATL"},{"SFO", "ATL"}, {"ATL","SFO"}, {"ATL","ICN"}};
                                // ICN SFO SFO ATL ATL SFO
        String[] answer = {};

        List<String[]> ticketList = new ArrayList<>();

        for(String [] ticket : tickets){
            ticketList.add(ticket);
        }
        Collections.sort(ticketList,(s1,s2)->s1[1].compareTo(s2[1]));

        boolean[] visited = new boolean[tickets.length];
        List<String> path = new ArrayList<>();
        path.add("ICN");

        path = dfs(ticketList,path,visited);
        System.out.println(path);
        answer=path.toArray(new String[0]);
    }
    static List<String> dfs(List<String[]> tickets, List<String> path, boolean[] visited){

        List<String> path_r = new ArrayList<>();
        String start = path.get(path.size()-1);

        for(int i=0;i<tickets.size();i++){
            if(!visited[i]){
                String[] ticket =  tickets.get(i);
                if(ticket[0].equals(start)){
                    path.add(ticket[1]);
                    visited[i]=true;
                    List<String> tmp = dfs(tickets,path,visited);
                    if(tmp!=null)
                        path_r = tmp;
                    else{
                        path.remove(path.size()-1);
                        visited[i]=false;
                    }
                } else if(i==tickets.size()-1){ // 방문하지 않은 티켓인데 start와 일치하지 않은 경우, 해당 경로가 잘못되었음. 되돌리기
                    return null;
                }
            }
        }
        for(boolean b : visited){
            if(!b){
                return path_r;
            }
        }
        return path;
    }
}
