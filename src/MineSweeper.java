import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    //Bu kısımda ilk olarak mayın tarlasının kaç satır, kaç sütun ve kaç mayından oluşacağını belirlemek için fieldlar oluşturuyoruz.
    int rowNumber;
    int colNumber;
    int blockCount;
    int mineCount;
    int dashCount;
    int cDash;

    public MineSweeper(int rowNumber,int colNumber){//Bu kısımda kullanıcıdan veri alıyoruz
        this.colNumber=colNumber;
        this.rowNumber=rowNumber;
        this.blockCount=this.colNumber*this.rowNumber;
        this.mineCount=blockCount/4;
        this.dashCount=(blockCount);
        this.cDash=this.blockCount-this.mineCount;

    }



    public void run(){
        Scanner scan = new Scanner(System.in);
        Random random=new Random();
        String[][] arr=new String[this.rowNumber][this.colNumber];//Orijinal ve mayınların da gözüktüğü matris
        String[][] hiddenArr=new String[this.rowNumber][this.colNumber];//Orijinali gizlemek için kullandığım matris
        for (int i = 0; i < arr.length; i++) {//Burada orijinal matrisi oluşturuyoruz
            for (int j = 0; j < arr[i].length; j++) {
                if (dashCount!=0){
                    arr[i][j]="-";
                }
                dashCount--;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (mineCount>0) {
                    arr[random.nextInt(this.rowNumber)][random.nextInt(this.colNumber)] = "*";
                }
                mineCount--;
            }
        }

        for (int i = 0; i < arr.length; i++) {//Burada ise gizlemek için kullandığımız matrisi
            for (int j = 0; j < arr[i].length; j++) {
                hiddenArr[i][j]="-";
            }
        }

        for (String[] strings : arr) {//Orijinal matrisi ekrana bastırmak için
            for (String string : strings) {
                System.out.printf("%s\t", string);
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------");
        for (String[] strings : hiddenArr) {//Gizleyici matrisi ekrana bastırmak için
            for (String string : strings) {
                System.out.printf("%s\t", string);
            }
            System.out.println();
        }



        while(true){//Oyun ise asıl buradabaşlıyor

            System.out.println("Satır giriniz: ");
            int satir=scan.nextInt();
            System.out.println("Sütun giriniz: ");
            int sutun=scan.nextInt();
            if (satir>=0 || satir<=this.rowNumber || sutun>=0 ||sutun<=this.colNumber ){
                if (arr[satir][sutun].equals("-")){//Oyunu kazandığımızı anlamak için bir kontrol mekanizması
                    this.cDash--;
                }
                if (!arr[satir][sutun].equals("*")){//Seçtiğimiz noktanın etrafında mayın var mı yok mu kontrol etmek için kulllandığım kontrol mekanizması

                    int count=0;
                    if (satir==0)//İlk satırda bir sayının yanında tarayabileceği çok alan olmadığı için ve hata almamak için 3 farklı if ile tarayıcı oluşturdum
                    {
                        if (sutun==0){
                            for (int i=0;i<=1;i++){
                                for (int j = 0; j <= 1; j++) {
                                    if (arr[satir + i][sutun + j].equals("*")){
                                        count++;
                                    }
                                }
                            }
                            hiddenArr[satir][sutun]=Integer.toString(count);
                        }
                        else if (sutun<arr.length-1) {
                            for (int i=0;i<=1;i++){
                                for (int j = -1; j <= 1; j++) {
                                    if (arr[satir + i][sutun + j].equals("*")){
                                        count++;
                                    }
                                }
                            }
                            hiddenArr[satir][sutun]=Integer.toString(count);
                        }
                        else{
                            for (int i=0;i<=1;i++){
                                for (int j = -1; j <= 0; j++) {
                                    if (arr[satir + i][sutun + j].equals("*")){
                                        count++;
                                    }
                                }
                            }
                            hiddenArr[satir][sutun]=Integer.toString(count);
                        }
                    }
                    else if (satir>0 && satir< arr.length-1)//İlk satır ve son satır arasındaki her yeri taramak için ayrı bir if yapısı oluşturdum
                    {
                        if (sutun==0){
                            for (int i=-1;i<=1;i++){
                                for (int j = 0; j <= 1; j++) {
                                    if (arr[satir + i][sutun + j].equals("*")){
                                        count++;
                                    }
                                }
                            }
                            hiddenArr[satir][sutun]=Integer.toString(count);
                        }
                        else if (sutun<arr.length-1) {
                            for (int i=-1;i<=1;i++){
                                for (int j = -1; j <= 1; j++) {
                                    if (arr[satir + i][sutun + j].equals("*")){
                                        count++;
                                    }
                                }
                            }
                            hiddenArr[satir][sutun]=Integer.toString(count);
                        }
                        else{
                            for (int i=-1;i<=1;i++){
                                for (int j = -1; j <= 0; j++) {
                                    if (arr[satir + i][sutun + j].equals("*")){
                                        count++;
                                    }
                                }
                            }
                            hiddenArr[satir][sutun]=Integer.toString(count);
                        }

                    }
                    else if (satir<=arr.length)//Son satırda da aynı ilk satırda olduğu gibi sorunlar oluşmaması için ayrı bir if yapısı oluşturdum.
                    {
                        if (sutun==0){
                            for (int i=-1;i<=0;i++){
                                for (int j = 0; j <= 1; j++) {
                                    if (arr[satir + i][sutun + j].equals("*")){
                                        count++;
                                    }
                                }
                            }
                            hiddenArr[satir][sutun]=Integer.toString(count);
                        }
                        else if (sutun<arr.length-1) {
                            for (int i=-1;i<=0;i++){
                                for (int j = -1; j <= 1; j++) {
                                    if (arr[satir + i][sutun + j].equals("*")){
                                        count++;
                                    }
                                }
                            }
                            hiddenArr[satir][sutun]=Integer.toString(count);
                        }
                        else{
                            for (int i=-1;i<=0;i++){
                                for (int j = -1; j <= 0; j++) {
                                    if (arr[satir + i][sutun + j].equals("*")){
                                        count++;
                                    }
                                }
                            }
                            hiddenArr[satir][sutun]=Integer.toString(count);
                        }
                    }

                    for (String[] strings : hiddenArr) {//Seçtiğimiz noktanın etrafında kaç tane mayın varsa yazdırması için
                        for (String string : strings) {
                            System.out.printf("%s\t", string);
                        }
                        System.out.println();
                    }


                    if (this.cDash==0){//Kazanma durumu

                        System.out.println("Tebrikler! Kazandınız.");
                        break;
                    }

                }

                else {//Kaybetme durumu

                    System.out.println("Oyunu kaybettiniz :(");
                    break;
                }
            }
            else{
                System.out.println("Lütfen sınırlar içinde birer sayı giriniz.");
            }

        }
    }
}