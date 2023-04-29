public class VerifyThreads {
    public static void main(String[] args) {
        for(int numThreads = 1; numThreads <= 16; ++numThreads) {
            System.out.println("\n#### numThreads = " + numThreads + "\n");
            int slice = 81/numThreads;
            int nextStart = 0;
            for(int i=0; i<numThreads; ++i) {
                final int start = Math.min(nextStart, 80-(numThreads-i));
                final int end   = (i<numThreads-1) ? Math.min(nextStart + slice, 80-(numThreads-i)) : 80;
                final int threadID = i;
                System.out.println("  Thread " + threadID + ": " + start + "-" + end);
                nextStart = end+1;
            }
        }
    }

}
