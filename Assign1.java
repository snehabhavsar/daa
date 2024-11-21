import java.math.BigInteger;
import java.util.Scanner; 

public class Assign1{
    public static void main(String Args[]){
        System.out.println("Enter a 20 digit number: ");
        Scanner sc= new Scanner(System.in);
        BigInteger x=sc.nextBigInteger();
        
        BigInteger square=calculatesq(x);
        System.out.println(square);

    }
    public static BigInteger calculatesq(BigInteger x){
        String number= x.toString();
        int n=number.length();

        if(n==1){
            return x.multiply(x);
        }
        else{
            int m=n/2;
            String a= number.substring(0,m);
            String b= number.substring(m,n);

            BigInteger l= new BigInteger(a);
            BigInteger r= new BigInteger(b);

            BigInteger l2= calculatesq(l);
            BigInteger r2= calculatesq(r);
            BigInteger lr= l.multiply(r).multiply(new BigInteger("2"));

            BigInteger result= l2.multiply(BigInteger.TEN.pow(2*(n-m))).add(lr.multiply(BigInteger.TEN.pow(n-m))).add(r2);


            return result;
        }
    }
}
