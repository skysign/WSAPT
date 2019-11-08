public class AB{
    public boolean checkPossible(int N, int K){
        int v = (N/2) * (N - (N/2));
        return (K <= v);
    }
    
    public String getS(int n, char x){
        String rtn = "";

        for(int i=0; i<n; ++i){
            rtn += x;
        }
        
        return rtn;
    }
    
    public String createString(int N, int K){
        String rtn = "";
        
        if(false == checkPossible(N, K))
            return rtn;
       
        if(K == 0){
            rtn += getS(N, 'B');
            return rtn;
        }
            
        for(int Na = 1; Na <= N/2; Na++) {
            int Nb = K/Na;
            if((K == Na * Nb) & ((Na+Nb) <= N)){
                int b = N - (Na + Nb);
                rtn += getS(b, 'B');
                rtn += getS(Na, 'A');
                rtn += getS(Nb, 'B');

                return rtn;
            }
        }
        
        return rtn;
    }
}