package ch12;

//Xを定義
interface X{void a();}

//Yを定義
abstract class Y implements X{
	public abstract void a();
	public abstract void b();
}

//Aを定義
final class A extends Y{
	public void a() {System.out.println("A-a");}
	public void b() {System.out.println("A-b");}
}

//Bを定義
class B extends Y{
	public  void a() {System.out.println("B-a");}
	public void b() {System.out.println("B-b");}
}
