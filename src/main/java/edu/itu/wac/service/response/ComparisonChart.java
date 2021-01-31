package edu.itu.wac.service.response;

import lombok.Data;

import java.util.HashMap;

@Data
public class ComparisonChart {
    private String labelId;
    private String label;
    private int[] data;
    private int countLevel;
    private int chart;
    private HashMap<String,String> compareCriteria;


    public ComparisonChart() {
        chart = 1;
		compareCriteria.put("1","Toplam Hata Sayısı");
		compareCriteria.put("2","Toplam Sayfa Sayısı");
		compareCriteria.put("3","Table tag Sayısı");
		compareCriteria.put("4","Kontrast Hatası Sayısı");
		compareCriteria.put("5","Form tag Sayısı");
		compareCriteria.put("6","Button tag Sayısı");
		compareCriteria.put("7","Link Hatası Sayısı");
		compareCriteria.put("8","Dokuman Dili Hatası Sayısı");
		compareCriteria.put("9","Img tag Sayısı");
    }

}
