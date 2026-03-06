package ch11;

//インタフェースの継承
//Cretureクラス　ch11_12_13 
interface Human extends Creature {
	void talk();
	void watch();
	void hear();
	//さらに、親インタフェースからrun()を継承する
}
