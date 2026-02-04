package ch11;

//インタフェース　クリーニングサービスを宣言
interface  CleaningService {
	Shirt washShirt(Shirt s);
	Towl washTowl(Towl t);
	Coat washCoat(Coat c); 

}
