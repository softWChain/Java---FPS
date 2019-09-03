package maps.veys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Oyun extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	static int WIDTH  = 640;
	static int HEIGHT = 480;
	
	BufferedImage gorselBuffer;
	boolean oyunDevam = true; // running
	

	
	Insets icKisim;
	
	int hedefFps = 30;
	
	int x=300,y=30;
	
	public static void main(String[] args) {
		Oyun oyun = new Oyun();
		oyun.dongu();
	}
	
	long fpsYazdirmaZamani = 0;
	int mevcutFps  = 0;

	private void dongu() {
		baslangic();
		
		long oncekiDöngüBaslangici= System.nanoTime(); // O an ki sistem zamanı , 10 'üzeri 9 
		long hedefDonguZamani  = 1000000000 / hedefFps;
		
		while(oyunDevam){
			
			long donguBaslangici= System.nanoTime();
			long donguZamanı = donguBaslangici - oncekiDöngüBaslangici;
			
			oncekiDöngüBaslangici = donguBaslangici;
			
			double zamanDegiskeni = donguZamanı  / hedefDonguZamani;
			
			
			//double zamanDegi
			
			mevcutFps++;
			fpsYazdirmaZamani = fpsYazdirmaZamani + donguZamanı;
			
			
			
			if(fpsYazdirmaZamani >= 1000000000){
				System.out.println("FPS : " +mevcutFps);
				fpsYazdirmaZamani = 0;
				mevcutFps = 0;
			}
			
			//(Update)
			
			oyunMantıgıGüncelle();
			
			//(Render)
			
			oyunGrafigiGüncelle();
			
			long artanNanoZaman = hedefDonguZamani 
					- (System.nanoTime() - donguBaslangici);

			
			try {
				if(artanNanoZaman>0){
					Thread.sleep(artanNanoZaman/1000000);
					}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	//Render
	private void oyunGrafigiGüncelle() {
		
		Graphics frameGrafigi = getGraphics();
		
		Graphics bufferGrafigi = gorselBuffer.getGraphics();
		

		
		//Buffer Güncelle
		bufferGrafigi.setColor(Color.WHITE);
		bufferGrafigi.fillRect(0, 0, getWidth(), getHeight());
		

		
		bufferGrafigi.setColor(Color.BLACK);
		bufferGrafigi.fillRect(x, y, 40, 40);

		
		
		//Buffer frame çevir
		frameGrafigi.drawImage(gorselBuffer, icKisim.left, icKisim.top, this);
		
	}
	
	//Update
	private void oyunMantıgıGüncelle() {
		y++;
		
		if(y >= HEIGHT){
			y=-60;
		}
		
		
	}
	
	

	private void baslangic() {
		setTitle("GAME");
		
		icKisim = getInsets();
		
		setSize(icKisim.left + WIDTH + icKisim.right
				,icKisim.top + HEIGHT + icKisim.bottom);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		gorselBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
	
		
	}
}



