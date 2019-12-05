package algo;

public class Influencer {
	    /**
	     * Given a matrix of following relationships between N LinkedIn users (with ids from 0 to N-1):
	     * followingMatrix[i][j] == true iff user i is following user j
	     * thus followingMatrix[i][j] doesn't imply followingMatrix[j][i].
	     * Let's also agree that followingMatrix[i][i] == false.
	     *
	     * An influencer is a user who is:
	     * - followed by everyone else and
	     * - not following anyone herself/himself
	     *
	     * This method should return the influencer's id in a given matrix of following relationships,
	     * or return -1 if there is no influencer in this group.
	     
	                                               followingMatrix[x][y]
	                                                   1 2 3 4 5 . .
	                                                 1 F T T T T . .
	                                                 2 F F F T F . .
	                                                 3 F F F T F . .
	                                                 4 F F F F F . .
	                                                 5 F T F T F . .
	                                                 . . . . . . . .
	                                                 . . . . . . . .
	     
	     */
	    int getLiInfluencer(boolean[][] followingMatrix) {
	    
	        for(int col=0;col<followingMatrix[0].length;col++)
	        {
	            boolean influencer = true;
	            for(int row=0;row<followingMatrix.length;row++)
	            {
	                if(row!=col && followingMatrix[row][col]==false) {
	                    influencer=false;
	                    break;
	                }
	            }
	            if(influencer==true)
	                return col;
	        }
	        return -1;
	        
	    
	    }
	


	// ---------------------------------------

	/** 
	    Stack (Unbounded Stack)
	    
	    Requirement: getMiddle() function which returns the middle element of a stack in constant time.
	    
	*/




}
