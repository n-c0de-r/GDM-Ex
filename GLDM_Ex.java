import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

//erste Uebung (elementare Bilderzeugung)

public class GLDM_Ex implements PlugIn {


	public static void main(String args[]) {
		ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen
		ij.exitWhenQuitting(true);

		GLDM_Ex imageGeneration = new GLDM_Ex();
		imageGeneration.run("");
	}

	public void run(String arg) {

		int width = 560; // Breite

		// RGB-Bild erzeugen
		ImagePlus imagePlus = NewImage.createRGBImage("GLDM_U1", width, width, 1, NewImage.FILL_BLACK);
		ImageProcessor ip = imagePlus.getProcessor();

		// Arrays fuer den Zugriff auf die Pixelwerte
		int[] pixels = (int[]) ip.getPixels();

		////////////////////////////////////////////////////////////////
		// Hier bitte Ihre Aenderungen / Erweiterungen

		generateCheckboard(width, width, pixels);

		////////////////////////////////////////////////////////////////////

		// neues Bild anzeigen
		imagePlus.show();
		imagePlus.updateAndDraw();
	}

	private void generateCheckboard(int width, int height, int[] pixels) {
		//Seitenlängew eines Quadrats festlegen.
		int quadrat = 40;
		
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				
				int pos = y * width + x; // Arrayposition bestimmen

				//Farbwerte initialisieren, blau kommt nicht vor!
				int r = 0;
				int g = 0;
				
				//Gerade Zeilen
				if (y % (quadrat*2) >= quadrat) {
					//Ungerade Spalten
					if (x % (quadrat*2) < quadrat) {
						g = 255;
					} else {
						/* Die x Werte mit 1.0 multiplizieren,
						 * um Double-Werte zu erzeugen, für eine präzise
						 * Berechnung der Mischfarbwerte.
						 * Horizontalwerte in Verhältnis zur Breite setzen.*/
						r = (int) ((1.0 * x / width) * 255);
					}
				} else { //Ungerade Zeilen
					//Gerade Spalten
					if (x % (quadrat*2)>quadrat) {
						g = 255;
					} else {
						r = (int) ((1.0 * x / width) * 255);
					}
				}
				//Werte zurueckschreiben. Da blau nicht vorhanden, auf 0 setzen!
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | 0;
			}
		}
	}

}