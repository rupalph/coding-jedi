

class HitCounter {
    public static void main(String[] args) {

        HitCounter s= new HitCounter();

        long timestamp = System.currentTimeMillis();
        s.logHit(timestamp); //0
        s.logHit(timestamp+100000); //100
        s.logHit(timestamp+100000); //100
        s.logHit(timestamp+201000); //201
        s.logHit(timestamp+222000); //222
        s.logHit(timestamp+300000); //300
        s.logHit(timestamp+300000); //300
        s.logHit(timestamp+400000); //400
        s.logHit(timestamp+500000); //500


        System.out.println(s.getHits(timestamp));
    }

    private long[] hitCounts;
    private long[] indexToTimestamp;

    private final int MAX_LIMIT = 300;

    public HitCounter() {
        hitCounts = new long[MAX_LIMIT];
        indexToTimestamp = new long[MAX_LIMIT];
    }
    public void logHit(long timestamp){
        timestamp /= 1000;
        int index =  (int) (timestamp % MAX_LIMIT);
        System.out.println(timestamp+" "+index);
        if(indexToTimestamp[index]!=timestamp) {
            hitCounts[index]=1;
            indexToTimestamp[index]=timestamp;
        }
        else
            hitCounts[index]++;

    }

    public int getHits(long timestamp) {
        timestamp /= 1000;
        int result=0;
        for(int i=0;i<300;i++) {
            if(timestamp - indexToTimestamp[i] < 300)
                result += hitCounts[i];
        }
        return result;
    }
}

