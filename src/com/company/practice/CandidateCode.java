package com.company.practice;

/**
 * Created by naresh.kapse on 22/04/16.
 */
public class CandidateCode
{

    private static int[] playerPasses;
    private static int totalPasses = 0;

    public static int passCount(int input1,int input2,int input3)
    {
        playerPasses = new int[input1];
        if ((input1 < 3 || input1 >1000) && (input2 < 3 || input2 >1000)) {
            return -1;
        }

        return start(input1, input2, input3);
    }

    private static int start(int totalPlayers, int skip, int maxPasses) {
        int currentPlayer = 0;
        while (true) {
            if (playerPasses[currentPlayer] == maxPasses) {
                break;
            }

            playerPasses[currentPlayer] = playerPasses[currentPlayer] + 1;
            System.out.println("cp: " + (currentPlayer) + " Times:" + playerPasses[currentPlayer]);
            // Player recieving for even times
            if(playerPasses[currentPlayer] / 2 == 0) {
                // Pass to right;
                currentPlayer = (currentPlayer + skip) % totalPlayers;
            }else {
                // Pass to left.
                int index = currentPlayer - skip;
                if(index < 0) {
                    currentPlayer = (totalPlayers + index) % totalPlayers;
                }else {
                    currentPlayer = index % totalPlayers;
                }
            }
            ++totalPasses;
        }
        return totalPasses;
    }

    public static void main(String[] args) {
        System.out.println("TotalPasses: " + CandidateCode.passCount(5,3,2));
    }
}
