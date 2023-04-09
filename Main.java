import java.io.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class Main {
   public String isim;
   public String soyisim;
   public String mail;

    public Main(String isim, String soyisim, String mail) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.mail = mail;
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Programa hoş geldiniz!");
        System.out.println("----------------------");
        //Sürekli dönen menü ekranı
        while (true) {
            System.out.println("Lütfen seçiminizi yapınız:");
            System.out.println("1. Elit üye ekle");
            System.out.println("2. Genel üye ekle");
            System.out.println("3. Mail gönder");
            //Seçime göre işlem seçimi
            int secim = scanner.nextInt();
            scanner.nextLine();
            if (secim == 1) {
                System.out.println("Elit üye ekleme işlemini seçtiniz.");
                System.out.println("Elit üye bilgilerini giriniz.");
                System.out.print("İsim: ");
                String isim = scanner.nextLine();
                System.out.print("Soyisim: ");
                String soyisim = scanner.nextLine();
                System.out.print("Mail: ");
                String mail = scanner.nextLine();
                try {
                    //Elit üyeler için text dosyası açılması ve seçim sonucu girilen bilgilerin içine yazdırılması
                    FileWriter file = new FileWriter("elitUyeler.txt", true);
                    file.write(isim + " " + soyisim + " " + mail + "\n");
                    //Text dosyasının kapatılması
                    file.close();
                    System.out.println("Elit üye başarıyla eklendi.");
                    //Try gövdesinde oluşabilecek hata catch gövdesinde yakalanılır.
                } catch (IOException e) {
                    System.out.println("Dosya açma hatası: " + e.getMessage());
                }
            } else if (secim == 2) {
                System.out.println("Genel üye ekleme işlemini seçtiniz.");
                System.out.println("Genel üye bilgilerini giriniz.");
                System.out.print("İsim: ");
                String isim = scanner.nextLine();
                System.out.print("Soyisim: ");
                String soyisim = scanner.nextLine();
                System.out.print("Mail: ");
                String mail = scanner.nextLine();
                try {
                    FileWriter file = new FileWriter("genelUyeler.txt", true);
                    file.write(isim + " " + soyisim + " " + mail + "\n");
                    file.close();
                    System.out.println("Genel üye başarıyla eklendi.");
                } catch (IOException e) {
                    System.out.println("Dosya açma hatası: " + e.getMessage());
                }
            } else if (secim == 3) {
                //Mail gönderme menüsü
                System.out.println("Mail gönderme menüsündesiniz.");
                System.out.println("Lütfen seçiminizi yapınız.");
                System.out.println("1-Elit üyelere mail");
                System.out.println("2-Genel üyelere mail");
                System.out.println("3-Tüm üyelere mail");
                int secim2= scanner.nextInt();
                scanner.nextLine();
                if(secim2==1)
                {
                    System.out.println("Elit üyelere mail gönderilecek.");
                }
                else if(secim2==2)
                {
                    System.out.println("Genel üyelere mail gönderilecek.");
                }
                else if(secim2==3)
                {
                    System.out.println("Tüm üyelere mail gönderilecek.");
                }
                else
                    System.out.println("Geçersiz işlem numarası girildi.");

                break;
            } else {
                System.out.println("Geçersiz seçim, lütfen tekrar deneyiniz.");
            }
        }
    }
}
 class DosyaBirlestirme
{
    public static void main(String[] args) throws Exception
    {
        //Elit ve genel üyeleri 3. bir text dosyasının içine kaydetme
        PrintWriter print = new PrintWriter("tumUyeler.txt");
        BufferedReader read1 =new BufferedReader(new FileReader("elitUyeler.txt"));
        BufferedReader read2=new BufferedReader(new FileReader("genelUyeler.txt"));
        String line1 = read1.readLine();
        String line2 = read2.readLine();

        while(line1 !=null || line2 !=null)
        {
            if(line1!=null)
            {
                print.println(line1);
                line1 = read1.readLine();
            }

            if(line2!=null)
            {
                print.println(line2);
                line2= read2.readLine();
            }
        }
        print.flush();
        read1.close();
        read2.close();
    }
}
class Email {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.transport.protocol","smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("beyza.caglar04@gmail.com","fwsnjrjwtlexuxzy");
            }
        });

        Message message = new MimeMessage(session);
        Scanner scan = new Scanner(System.in);
        String mesaj = scan.nextLine();
        message.setText(mesaj);
        Address address = new InternetAddress("reciever@gmail.com");
        message.setRecipient(Message.RecipientType.TO, address);
        Transport.send(message);
    }
}

