import java.util.ArrayList;
import java.util.List;

class MainClass {
  
  public static void main(String[] args) {
    
    yonetici yn = new yonetici("Pro");
    calisan cl = new calisan("qqq");
    calisan cl1 = new calisan("qqww");
    yn.AddCalisan(cl);
    yn.AddCalisan(cl1);
    System.out.println("Cl Yoneticisi "+cl.yonetici.isim);
    System.out.println("Cl1 Yoneticisi "+cl1.yonetici.isim);
    System.out.println("Yoneticiyin Calisanlari"+yn.ls.get(0).isim);
    System.out.println("Yoneticiyin Calisanlari"+yn.ls.get(1).isim);
  }
  
}

class calisan{
  calisan yonetici;
  String isim;
  public calisan(){}
  
  public calisan(String isim){ 
    this.isim = isim;
  }
}
class yonetici extends calisan{
  ArrayList<calisan> ls = new ArrayList<calisan>();
  
  public yonetici(String isim){
    super(isim);

  }
  
  public void AddCalisan(calisan c){
    ls.add(c);
    c.yonetici = this;
    
  }
}
