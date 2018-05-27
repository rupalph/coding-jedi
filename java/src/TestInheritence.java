/**
 * Created by rupalh on 4/13/18.
 */
interface ITest {
     void test();

}
abstract class ATest implements ITest{

    protected abstract   void methodA();
    protected     void methodB() {
        System.out.println("ATest:Method B");
    }

    public void test() {
        System.out.println("TestA:test");
        process();
    }

    abstract void process();


    private void privateMethod() {
        methodA();
        methodB();
    }
    protected void protectedMethodA() {
        privateMethod();
    }
}

abstract class BTest extends ATest{

    protected     void methodB() {
        System.out.println("BTest:Method B");
    }
//    protected    void methodA() {
//        System.out.println("BTest:Method A");
//    }
    protected void protectedMethodB() {
        protectedMethodA();
    }
}
public class TestInheritence extends BTest{

    protected    void methodA() {
        System.out.println("TestInheritence:Method A");
    }
    protected    void process() {
        protectedMethodB();
    }

    public static void main(String[] args) {
        ITest a = new TestInheritence();
       a.test();


    }
}

