package ch11;

//インタフェースを継承した京都クリーニング店を宣言
class KyoutoCleaningShop implements CleaningService{
	//店主の名前、住所、電話番号を宣言
	String ownerName;
	String address;
	int phone;
	
	//シャツを洗う
	public Shirt washShirt(Shirt s) {
		//大型洗濯機１５分
		return s;
	}
	//タオルを洗う
	public Towl washTowl(Towl t) {
		//大型洗濯機１０分
		return t;
	}
	//コートを洗う
	public Coat washCoat(Coat c) {
		//ドライ２０分
		return c;
	}

}
