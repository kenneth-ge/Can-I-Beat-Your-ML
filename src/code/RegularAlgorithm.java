package code;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import code.Util.Type;
import static code.Util.Type.*;

public class RegularAlgorithm {

	public RegularAlgorithm() {
		
	}
	
	public Type classify(BufferedImage image) {
		final int width = image.getWidth();
		final int height = image.getHeight();
		
		int[][][] buckets = new int[10][10][3];
		int[][] bucketSize = new int[10][10];
		
		for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
            	int rgb = image.getRGB(x, y);
            	
            	int[] color = new int[] {(rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF};
            	
            	for(int i = 0; i < 3; i++)
            		buckets[Math.min(x / (width / 10), 9)][Math.min(y / (height / 10), 9)][i] += color[i];
            	
            	bucketSize[Math.min(x / (width / 10), 9)][Math.min(y / (height / 10), 9)]++;
            }
         }
				
		for(int i = 0; i < buckets.length; i++) {
			for(int j = 0; j < buckets[i].length; j++) {
				for(int k = 0; k < 3; k++)
					buckets[i][j][k] /= bucketSize[i][j];
			}
		}
		
		int desert = 0, forest = 0;
		
		for(int i = 0; i < buckets.length; i++) {
			for(int j = 0; j < buckets[i].length; j++) {
				double[] hsv = RGBtoHSV(buckets[i][j][0], buckets[i][j][1], buckets[i][j][2]);
				hsv[0] *= 360;
				
				if(hsv[0] > 300 || hsv[0] < 57 && hsv[2] >= 0.5) {
					//desert++;
										
					//add to this score the closer it is to yellow
					double distScore = Math.min(Math.abs(hsv[0] - 330.) / 30., Math.abs(hsv[0] - 30.) / 30.);
					double brightnessScore = Math.sqrt(hsv[2]);
					
					double score = (1 - distScore) * brightnessScore;
					
					if(score > 0.2) {
						desert++;
					}else {
						forest++;
					}
				}else if(hsv[0] >= 57 && hsv[0] < 180 && hsv[1] > 0.15) {
					forest++;
				}else{
					//sky
				}
			}
		}
		
		if(desert > forest) {
			return Desert;
		}else{
			return Forest;
		}
	}
	
	double distScore(double num, double mid, double cutoff) {
		double x = cutoff - mid;
		
		return normalFormula((num - mid) / x);
	}
	
	double normalFormula(double x) {
		return 1.0 / (0.4 * Math.sqrt(2 * Math.PI)) * Math.exp(-1./2. * (x / 0.4) * (x / 0.4));
	}
	
    double[] RGBtoHSV(int r, int g, int b) {
        
        double hue, saturation, brightness;
        double[] hsbvals = new double[3];
        
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((double) cmax) / 255.0;
        if (cmax != 0)
            saturation = ((double) (cmax - cmin)) / ((double) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            double redc = ((double) (cmax - r)) / ((double) (cmax - cmin));
            double greenc = ((double) (cmax - g)) / ((double) (cmax - cmin));
            double bluec = ((double) (cmax - b)) / ((double) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

}
