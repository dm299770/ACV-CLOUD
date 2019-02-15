//public class Base {
//    private static String baseName = "baseName";
//
//    public Base() {
//        System.out.println("父类构造");
//        callName();
//    }
//
//    public void callName() {
//        System.out.println(baseName);
//    }
//
//    static class Sub extends Base {
//        private String subName = "subName";
//
//        public Sub() {
//            System.out.println("子类构造");
//        }
//
//        public void callName() {
//            System.out.println(baseName);
//            System.out.println(subName);
//        }
//    }
//
//    public static void main(String[] args) {
//        Base sub = new Sub();
//    }
//}
//

class SingleTon {
    private static SingleTon singleTon = new SingleTon();
    public static int count1;
    public static int count2 = 0;

    private SingleTon() {
        count1++;
        count2++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }
}

public class Test {
    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("count1=" + singleTon.count1);
        System.out.println("count2=" + singleTon.count2);
    }
}

//
//class SuperClass {
//    static {
//        System.out.println("superclass init");
//    }
//    public static int value = 123;
//}
//
//class SubClass extends SuperClass {
//    static {
//        System.out.println("subclass init");
//    }
//}
//
//public class Test {
//    public static void main(String[] args) {
//        System.out.println(SubClass.value);// 被动应用1
//        SubClass[] sca = new SubClass[10];// 被动引用2
//    }
//}
//
//class Person {
//    private String name;
//    private int age;
//    public String getName()
//    {
//        return name;
//    }
//    public void setName(String name)
//    {
//        this.name=name;
//    }
//    public int getAge()
//    {
//        return age;
//    }
//    public void setAge(int age)
//    {
//        this.age=age;
//    }
//    public Person()
//    {
//        System.out.println("父类的构造方法");
//    }
//}
//class Student extends Person{
//    private String schoolName;
//    public String getSchoolName()
//    {
//        return schoolName;
//    }
//    public void setSchoolName(String schoolName)
//    {
//        this.schoolName=schoolName;
//    }
//    public Student()
//    {
//        System.out.println("子类的构造方法");
//    }
//}
//class Test2{
//    public static void main(String[] args)
//    {
//        Student student=new Student();
//        student.setName("花花");
//        student.setAge(18);
//        student.setSchoolName("励志中学");
//        System.out.println("学校："+student.getSchoolName() +" 姓名："+student.getName()+"  年龄："+student.getAge());
//    }
//}
//class HelloA {
//    //构造方法
//    public HelloA() {
//        System.out.println("Hello A!父类构造方法");
//    }
//
//    //非静态代码块
//    {
//        System.out.println("i'm A class.父类非静态代码块");
//    }
//
//    //静态代码块
//    static {
//        System.out.println("static A 父类静态代码块");
//    }
//}
//
//public class HelloB extends HelloA {
//    //构造方法
//    public HelloB() {
//        System.out.println("Hello B! 构造方法");
//    }
//
//    //非静态代码块
//    {
//        System.out.println("i'm B class.非静态代码块");
//    }
//
//    //静态代码块
//    static {
//        System.out.println("static B 静态代码块");
//    }
//
//    public static void main(String[] args) {
//        System.out.println("---start- ");
//        new HelloB();
//        new HelloB();
//        System.out.println("---end- ");
//    }
//}

