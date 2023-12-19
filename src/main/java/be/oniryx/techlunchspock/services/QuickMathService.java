package be.oniryx.techlunchspock.services;

import org.springframework.stereotype.Service;

@Service
public class QuickMathService {

    private final String QUICK_MAF = "https://youtu.be/M3ujv8xdK2w?si=7OJgtcVGCT3T8iJo";
    public int veryComplexMethod(int one){
        return one + one;
    }

    public String getQUICK_MAF(){ return QUICK_MAF;}
}
