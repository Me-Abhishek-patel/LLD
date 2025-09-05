package design_patterns.TemplateMethod;

public class TemplateMethodPattern {
    public static abstract class Algorithm {
        void step1(){
            System.out.println(1);
        }
        abstract boolean step2();

        void step3(){
            System.out.println(3);
        }
        void step4(){
            System.out.println(4);
        }
        void step5(){
            System.out.println(5);
        }
        
        public void executeAlgorithm(){
            step1();
            if(step2()){
                step3();
            }else{
                step4();
            }
            step5();
        }




    }

    public static class AlgoWith3 extends Algorithm {

        @Override
        boolean step2() {
            return true;        
        }
        
    }
    public static class AlgoWithOut3 extends Algorithm {

        @Override
        boolean step2() {
            return false;        
        }
        
    }

    public static void main(String[] args) {
        Algorithm algo3 = new AlgoWith3();
        Algorithm algo4 = new AlgoWithOut3();

        algo3.executeAlgorithm();
        algo4.executeAlgorithm();
        

    }
}
