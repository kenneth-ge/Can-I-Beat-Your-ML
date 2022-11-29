package code;
import static code.Util.Type.Desert;
import static code.Util.Type.Forest;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tester {

	public static void main(String[] args) throws Exception {
		RegularAlgorithm ra = new RegularAlgorithm();
		
		File deserts = new File("Data/Desert");
		File forests = new File("Data/Forest");
		
		double allCorrect = 0;
		double allCount = 0;
		
		double totalCount = 0;
		double numCorrect = 0;
		
		System.out.println("Desert");
		for(File f: deserts.listFiles()) {
			BufferedImage image = ImageIO.read(f);
			
			if(ra.classify(image) == Desert) {
				numCorrect++;
				allCorrect++;
			}else {
				//System.out.println(f);
			}
			
			totalCount++;
			allCount++;
		}
		
		System.out.println(numCorrect + " / " + totalCount);
		System.out.println((numCorrect / totalCount * 100) + "%");
		
		totalCount = 0;
		numCorrect = 0;
		
		System.out.println("Forest");
		for(File f: forests.listFiles()) {
			BufferedImage image = ImageIO.read(f);
			
			if(ra.classify(image) == Forest) {
				numCorrect++;
				allCorrect++;
			}
			
			totalCount++;
			allCount++;
		}
		
		System.out.println(numCorrect + " / " + totalCount);
		System.out.println((numCorrect / totalCount * 100) + "%");
		
		System.out.println("Total");
		System.out.println(allCorrect + " / " + allCount);
		System.out.println((allCorrect / allCount * 100) + "%");
	}

}
